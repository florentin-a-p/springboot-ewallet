package net.javaguides.springboot.web;

import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.service.UserService;
import net.javaguides.springboot.web.dto.UserTopupDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/topup")
public class UserTopupController {
  private UserService userService;

  public UserTopupController(UserService userService) {
    super();
    this.userService = userService;
  }

  @ModelAttribute("userTopup")
  public UserTopupDto userTopupDto() {
    log.info("[FLO] usertopupcontroller.userTopupDto is created");
    return new UserTopupDto();
  }

  @GetMapping
  public String showTopupForm() {
    return "topup";
  }

  @PostMapping
  public String executeTopup(@ModelAttribute("userTopup") UserTopupDto userTopupDto) {
    try {
      log.info("[FLO] usertopupcontroller.executeTopUp is called");
      userService.topup(userTopupDto);
      log.info("[FLO] usertopupcontroller.userService.topup is called");
      return "redirect:/topup?success";
    } catch (Exception e) {
      return "redirect:/topup?error";
    }
  }
}
