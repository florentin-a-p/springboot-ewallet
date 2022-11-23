package net.javaguides.springboot.service;

import net.javaguides.springboot.web.dto.UserTopupDto;
import net.javaguides.springboot.web.dto.UserTransferDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
	void topup(UserTopupDto userTopupDto) throws Exception;
	void transfer(UserTransferDto userTransferDto) throws Exception;
}
