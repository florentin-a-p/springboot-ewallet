package net.demo.springboot.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_balance")
public class UserBalance extends User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  //@OneToOne
  //private User user;
  private Long userId;
  private String balance = "IDR";
  private Long balanceAchieve;
  //@OneToMany
  //private List<User> userList;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id")
  private UserBalanceHistory userBalanceHistory;

  public UserBalance() {
  }

  public UserBalance(Long id, Long userId, Long balanceAchieve) {
    this.id = id;
    this.userId = userId;
    this.balanceAchieve = balanceAchieve;
  }

  public UserBalance(Long userId, Long balanceAchieve) {
    super();
    this.userId = userId;
    this.balanceAchieve = balanceAchieve;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getBalance() {
    return balance;
  }

  public void setBalance(String balance) {
    this.balance = balance;
  }

  public Long getBalanceAchieve() {
    return balanceAchieve;
  }

  public void setBalanceAchieve(Long balanceAchieve) {
    this.balanceAchieve = balanceAchieve;
  }
}
