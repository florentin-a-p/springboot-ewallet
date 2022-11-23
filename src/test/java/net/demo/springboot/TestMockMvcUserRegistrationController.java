package net.demo.springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import net.demo.springboot.model.User;
import net.demo.springboot.repository.BankBalanceHistoryRepository;
import net.demo.springboot.repository.BankBalanceRepository;
import net.demo.springboot.repository.UserBalanceHistoryRepository;
import net.demo.springboot.repository.UserBalanceRepository;
import net.demo.springboot.repository.UserRepository;
import net.demo.springboot.service.UserService;
import net.demo.springboot.service.UserServiceImpl;
import net.demo.springboot.web.UserRegistrationController;
import net.demo.springboot.web.dto.UserRegistrationDto;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes={UserRegistrationController.class})
public class TestMockMvcUserRegistrationController {
  @Autowired
  private MockMvc mockMvc;
  @Mock
  private UserService userService;
  @Mock
  private UserRepository userRepository;
  @Mock
  private UserBalanceRepository userBalanceRepository;
  @Mock
  private UserBalanceHistoryRepository userBalanceHistoryRepository;
  @Mock
  private BankBalanceRepository bankBalanceRepository;
  @Mock
  private BankBalanceHistoryRepository bankBalanceHistoryRepository;
  @Mock
  private BCryptPasswordEncoder passwordEncoder;
  @Mock
  private Log log;
  @InjectMocks
  private UserRegistrationController userRegistrationController;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(userRegistrationController).build();
    userService = new UserServiceImpl(userRepository,
                                      userBalanceRepository,
                                      userBalanceHistoryRepository,
                                      bankBalanceRepository,
                                      bankBalanceHistoryRepository);
  }

  // start region scenario for showRegistrationForm //
  // end region scenario for showRegistrationForm //

  // start region scenario for registerUserAccount //
  @Test
  @ExtendWith(OutputCaptureExtension.class)
  public void registerUserAccountNoException_WillReturnCorrectResponse(CapturedOutput output) throws Exception {
    // Given
    EasyRandom easyRandom = new EasyRandom();
    UserRegistrationDto registrationDto = easyRandom.nextObject(UserRegistrationDto.class);
    User user = easyRandom.nextObject(User.class);
    //given(passwordEncoder.encode(any(CharSequence.class))).willReturn(easyRandom.nextObject(String.class));
    given(userService.save(registrationDto)).willReturn(user);


    // When
    //MvcResult actualResponse = this.mockMvc.perform(get("/registration"))
    //    .andExpect(status().isOk())
    //    .andDo(print())
    //    .andReturn();

    // Then
    //assertEquals(HttpStatus.OK.value(), actualResponse.getResponse().getStatus());
    //assertEquals(countryListString,actualResponse.getResponse().getContentAsString());
    //then(userService).should(times(1)).save(registrationDto);
    //assertThat(output).contains("[FLO] /getCountries is called");
  }
  // start region scenario for registerUserAccount //

}
