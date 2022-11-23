package net.javaguides.springboot.web;

import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.service.UserService;
import net.javaguides.springboot.web.dto.UserTransferDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/transfer")
public class UserTransferController {
  private UserService userService;

  public UserTransferController(UserService userService) {
    super();
    this.userService = userService;
  }

  @ModelAttribute("userTransfer")
  public UserTransferDto userTransferDto() {
    return new UserTransferDto();
  }

  @GetMapping
  public String showTransferForm() {
    return "transfer";
  }

  @PostMapping
  public String executeTransfer(@ModelAttribute("userTransfer") UserTransferDto userTransferDto) {
    try {
      userService.transfer(userTransferDto);
      return "redirect:/transfer?success";
    } catch (Exception e) {
      return "redirect:/transfer?error";
    }
  }
}
