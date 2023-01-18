package net.demo.springboot.model;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_balance_history")
public class UserBalanceHistory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long balanceBefore;
  private Long balanceAfter;
  private String activity;
  @Enumerated(value = EnumType.STRING)
  private BalanceActivityType balanceActivityType;
  private String ip = "127.0.0.1";
  private String location = "INDONESIA";
  private String userAgent = "SYSTEM";
  private String author = "SYSTEM";
  @ManyToOne
  @JoinColumn(name = "userBalanceId")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private UserBalance userBalance;

  public UserBalanceHistory() {
  }

  public UserBalanceHistory( Long balanceBefore, Long balanceAfter, String activity, BalanceActivityType balanceActivityType) {
    super();
    this.balanceBefore = balanceBefore;
    this.balanceAfter = balanceAfter;
    this.activity = activity;
    this.balanceActivityType = balanceActivityType;
  }

  public UserBalanceHistory(Long balanceBefore, Long balanceAfter, BalanceActivityType balanceActivityType) {
    super();
    this.balanceBefore = balanceBefore;
    this.balanceAfter = balanceAfter;
    this.activity = "Transfer";
    this.balanceActivityType = balanceActivityType;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  //public Long getUserBalanceId() {
    //return userBalanceId;
  //}

  //public void setUserBalanceId(Long userBalanceId) {
    //this.userBalanceId = userBalanceId;
  //}

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
