package net.javaguides.springboot.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bank_balance_history")
public class BankBalanceHistory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long bankBalanceId = Long.valueOf(1);
  private Long balanceBefore;
  private Long balanceAfter;
  private String activity = "TOP UP";
  @Enumerated(value = EnumType.STRING)
  private BalanceActivityType balanceActivityType = BalanceActivityType.DEBIT;
  private String ip = "127.0.0.1";
  private String location = "INDONESIA";
  private String userAgent = "SYSTEM";
  private String author = "SYSTEM";

  public BankBalanceHistory() {
  }

  public BankBalanceHistory(Long balanceBefore, Long balanceAfter) {
    super();
    this.balanceBefore = balanceBefore;
    this.balanceAfter = balanceAfter;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getBankBalanceId() {
    return bankBalanceId;
  }

  public void setBankBalanceId(Long bankBalanceId) {
    this.bankBalanceId = bankBalanceId;
  }

  public Long getBalanceBefore() {
    return balanceBefore;
  }

  public void setBalanceBefore(Long balanceBefore) {
    this.balanceBefore = balanceBefore;
  }

  public Long getBalanceAfter() {
    return balanceAfter;
  }

  public void setBalanceAfter(Long balanceAfter) {
    this.balanceAfter = balanceAfter;
  }

  public String getActivity() {
    return activity;
  }

  public void setActivity(String activity) {
    this.activity = activity;
  }

  public BalanceActivityType getBalanceActivityType() {
    return balanceActivityType;
  }

  public void setBalanceActivityType(BalanceActivityType balanceActivityType) {
    this.balanceActivityType = balanceActivityType;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getUserAgent() {
    return userAgent;
  }

  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }
}
