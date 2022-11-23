package net.demo.springboot.web.dto;

import net.demo.springboot.model.BalanceActivityType;

public class UserTopupDto {
  private String email;
  private Long topupAmount;
  private String topupActivity;
  private BalanceActivityType balanceActivityType;

  public UserTopupDto() {
  }

  public UserTopupDto(String email, Long topupAmount, String topupActivity, BalanceActivityType balanceActivityType) {
    super();
    this.email = email;
    this.topupAmount = topupAmount;
    this.topupActivity = topupActivity;
    this.balanceActivityType = balanceActivityType;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Long getTopupAmount() {
    return topupAmount;
  }

  public void setTopupAmount(Long topupAmount) {
    this.topupAmount = topupAmount;
  }

  public String getTopupActivity() {
    return topupActivity;
  }

  public void setTopupActivity(String topupActivity) {
    this.topupActivity = topupActivity;
  }

  public BalanceActivityType getBalanceActivityType() {
    return balanceActivityType;
  }

  public void setBalanceActivityType(BalanceActivityType balanceActivityType) {
    this.balanceActivityType = balanceActivityType;
  }
}
