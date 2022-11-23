package net.demo.springboot.service;

import java.util.Collection;
import java.util.Collections;

import lombok.extern.slf4j.Slf4j;
import net.demo.springboot.model.BalanceActivityType;
import net.demo.springboot.model.BankBalance;
import net.demo.springboot.model.BankBalanceHistory;
import net.demo.springboot.model.User;
import net.demo.springboot.model.UserBalance;
import net.demo.springboot.model.UserBalanceHistory;
import net.demo.springboot.repository.BankBalanceHistoryRepository;
import net.demo.springboot.repository.BankBalanceRepository;
import net.demo.springboot.repository.UserBalanceHistoryRepository;
import net.demo.springboot.repository.UserBalanceRepository;
import net.demo.springboot.repository.UserRepository;
import net.demo.springboot.web.dto.UserRegistrationDto;
import net.demo.springboot.web.dto.UserTopupDto;
import net.demo.springboot.web.dto.UserTransferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	private UserBalanceRepository userBalanceRepository;
	private UserBalanceHistoryRepository userBalanceHistoryRepository;
	private BankBalanceRepository bankBalanceRepository;
	private BankBalanceHistoryRepository bankBalanceHistoryRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository,
												 UserBalanceRepository userBalanceRepository,
												 UserBalanceHistoryRepository userBalanceHistoryRepository,
												 BankBalanceRepository bankBalanceRepository,
												 BankBalanceHistoryRepository bankBalanceHistoryRepository) {
		super();
		this.userRepository = userRepository;
		this.userBalanceRepository = userBalanceRepository;
		this.userBalanceHistoryRepository = userBalanceHistoryRepository;
		this.bankBalanceRepository = bankBalanceRepository;
		this.bankBalanceHistoryRepository = bankBalanceHistoryRepository;
	}

	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user = new User(registrationDto.getFirstName(), 
				registrationDto.getLastName(), registrationDto.getEmail(),
				passwordEncoder.encode(registrationDto.getPassword()));
		
		return userRepository.save(user);
	}

	@Override
	public void topup(UserTopupDto userTopupDto) throws Exception {
		log.info("[FLO] userService.topup is called");
		BankBalance currentBankBalance = bankBalanceRepository.findByBalance("IDR");
		Long currentBankBalanceAmount = currentBankBalance.getBalance_achieve();
		Long newBankBalanceAmount = currentBankBalance.getBalance_achieve()-userTopupDto.getTopupAmount();

		if (userTopupDto.getTopupAmount() > currentBankBalance.getBalance_achieve()) {
			throw new Exception();
		}

		// ----- change bank_balance table -----
		bankBalanceRepository.save(new BankBalance(currentBankBalance.getId(),newBankBalanceAmount));

		// ----- change bank_balance_history table -----
		bankBalanceHistoryRepository.save(new BankBalanceHistory(currentBankBalanceAmount,newBankBalanceAmount));

		// ----- change user_balance table -----
		User user = userRepository.findByEmail(userTopupDto.getEmail());

		try { // if there is exsiting user balance
			log.info("[FLO] userService.topup.change_user_balance_tbl try block is called");

			UserBalance userBalance = userBalanceRepository.findByUserId(user.getId());
			Long currentBalance = userBalance.getBalanceAchieve();
			Long newBalance = userTopupDto.getTopupAmount()+currentBalance;

			userBalance = new UserBalance(userBalance.getId(),user.getId(),  newBalance);
			userBalanceRepository.save(userBalance);
		} catch (Exception e) { // if there isn't existing user balance
			log.info("[FLO] userService.topup.change_user_balance_tbl catch block is called");
			UserBalance userBalance = new UserBalance(user.getId(), userTopupDto.getTopupAmount());
			userBalanceRepository.save(userBalance);
			log.info("[FLO] userService.topup.change_user_balance_tbl catch block, data saving successful");
		}

		// ----- change user_balance_history table -----
		try {
			log.info("[FLO] userService.topup.change_user_balance_history_tbl try block is called");
			UserBalance userBalance = userBalanceRepository.findByUserId(user.getId());
			UserBalanceHistory userBalanceHistory = userBalanceHistoryRepository.findByUserBalanceIdOrderByIdDesc(userBalance.getId()).get(0);

			Long currentBalance = userBalanceHistory.getBalanceAfter();
			Long newBalance = userTopupDto.getTopupAmount()+currentBalance;

			log.info("[FLO] userService.topup.change_user_balance_history_tbl try block, balanceActivityType: "+String.valueOf(userTopupDto.getBalanceActivityType()));
			UserBalanceHistory newUserBalanceHistory = new UserBalanceHistory(userBalance.getId(),currentBalance,newBalance,userTopupDto.getTopupActivity(),userTopupDto.getBalanceActivityType());
			userBalanceHistoryRepository.save(newUserBalanceHistory);

		} catch (Exception e) { // if there isn't existing user balance history
			log.info("[FLO] userService.topup.change_user_balance_history_tbl catch block is called");
			UserBalance userBalance = userBalanceRepository.findByUserId(user.getId());
			UserBalanceHistory userBalanceHistory = userBalanceHistoryRepository.findByUserBalanceId(userBalance.getId());

			log.info("[FLO] userService.topup.change_user_balance_history_tbl catch block, now saving the data");
			log.info("[FLO] userService.topup.change_user_balance_history_tbl catch block, balanceActivityType: "+String.valueOf(userTopupDto.getBalanceActivityType()));
			UserBalanceHistory newUserBalanceHistory = new UserBalanceHistory(userBalance.getId(), 0L, userBalance.getBalanceAchieve(), userTopupDto.getTopupActivity(), userTopupDto.getBalanceActivityType());
			userBalanceHistoryRepository.save(newUserBalanceHistory);
			log.info("[FLO] userService.topup.change_user_balance_history_tbl catch block, data saving is successful");
		}
	}

	@Override
	public void transfer(UserTransferDto userTransferDto) throws Exception {
		log.info("[FLO] userService.transfer is called");

		//----- check if any of this will throw exception -----
		User sender = userRepository.findByEmail(userTransferDto.getSenderEmail());
		UserBalance senderBalance = userBalanceRepository.findByUserId(sender.getId());

		log.info("[FLO] receiver is going to be retrieved");
		User receiver = userRepository.findByEmail(userTransferDto.getReceiverEmail());
		log.info("[FLO] receiver found");
		log.info("[FLO] receiver.getClass() " + receiver.getClass());

		if ((userTransferDto.getSenderEmail() == userTransferDto.getReceiverEmail())
				|| (senderBalance.getBalanceAchieve()<=0)
				|| (senderBalance.getBalanceAchieve()<userTransferDto.getTransferAmount())
				|| (receiver.getClass() == null)) {
			log.info("[FLO] Exception is going to be thrown");
			throw new Exception();
		}

		//----- change user_balance (sender) table -----
		Long currentBalanceSender = senderBalance.getBalanceAchieve();
		Long newBalanceSender = senderBalance.getBalanceAchieve()-userTransferDto.getTransferAmount();

		UserBalance newSenderBalance = new UserBalance(senderBalance.getId(),sender.getId(),newBalanceSender);
		userBalanceRepository.save(newSenderBalance);

		// ----- change user_balance_history (sender) table -----
		UserBalanceHistory senderBalanceHistory = userBalanceHistoryRepository.findByUserBalanceIdOrderByIdDesc(senderBalance.getId()).get(0);
		log.info("[FLO] senderBalanceHistory found");
		UserBalanceHistory newUserBalanceHistorySender = new UserBalanceHistory(senderBalance.getId(),currentBalanceSender,newBalanceSender, BalanceActivityType.DEBIT);
		log.info("[FLO] newUserBalanceHistorySender created");
		userBalanceHistoryRepository.save(newUserBalanceHistorySender);
		log.info("[FLO] newUserBalanceHistorySender saved");

		// ----- change user_balance (receiver) table -----
		// there is still error when sender transfers to receiver when receiver's balance is 0
		try { //if there is existing balance
			UserBalance receiverBalance = userBalanceRepository.findByUserId(receiver.getId());

			Long currentBalanceReceiver = receiverBalance.getBalanceAchieve();
			Long newBalanceReceiver = receiverBalance.getBalanceAchieve()+userTransferDto.getTransferAmount();

			UserBalance newReceiverBalance = new UserBalance(receiverBalance.getId(),receiver.getId(),newBalanceReceiver);
			userBalanceRepository.save(newReceiverBalance);

			// ----- change user_balance_history (receiver) table -----
			log.info("[FLO] receiverBalance found");
			UserBalanceHistory receiverBalanceHistory = userBalanceHistoryRepository.findByUserBalanceIdOrderByIdDesc(receiverBalance.getId()).get(0);
			log.info("[FLO] receiverBalanceHistory created");

			UserBalanceHistory newUserBalanceHistoryReceiver = new UserBalanceHistory(receiverBalance.getId(),currentBalanceReceiver,newBalanceReceiver, BalanceActivityType.CREDIT);
			userBalanceHistoryRepository.save(newUserBalanceHistoryReceiver);
			log.info("[FLO] userBalanceHistoryRepository saved");
		} catch (Exception e) {
			// if there isn't existing user balance
			UserBalance receiverBalance = new UserBalance(receiver.getId(), userTransferDto.getTransferAmount());
			userBalanceRepository.save(receiverBalance);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = userRepository.findByEmail(username);
		log.info("[FLO] username IS: " + username);
		log.info("[FLO] user IS: " + user);
		log.info("[FLO] user.getEmail() IS: " + user.getEmail());
		log.info("[FLO] user.getPassword() IS: " + user.getPassword());
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities());
	}

	private Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}
}
