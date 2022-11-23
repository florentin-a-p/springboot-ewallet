package net.demo.springboot.service;

import net.demo.springboot.model.User;
import net.demo.springboot.web.dto.UserRegistrationDto;
import net.demo.springboot.web.dto.UserTopupDto;
import net.demo.springboot.web.dto.UserTransferDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
	void topup(UserTopupDto userTopupDto) throws Exception;
	void transfer(UserTransferDto userTransferDto) throws Exception;
}
