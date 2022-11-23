package net.demo.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bank_balance")
public class BankBalance {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String balance = "IDR";
  private Long balance_achieve;
  private String code = "999";
  private boolean enable = true;

  public BankBalance() {
  }

  public BankBalance(Long id, Long balance_achieve) {
    this.id = id;
    this.balance_achieve = balance_achieve;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getBalance() {
    return balance;
  }

  public void setBalance(String balance) {
    this.balance = balance;
  }

  public Long getBalance_achieve() {
    return balance_achieve;
  }

  public void setBalance_achieve(Long balance_achieve) {
    this.balance_achieve = balance_achieve;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public boolean isEnable() {
    return enable;
  }

  public void setEnable(boolean enable) {
    this.enable = enable;
  }
}
