package net.demo.springboot.repository;

import net.demo.springboot.model.BankBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankBalanceRepository extends JpaRepository<BankBalance, Long> {
  BankBalance findByBalance(String balance);
}
