package net.demo.springboot.repository;

import net.demo.springboot.model.BankBalanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankBalanceHistoryRepository extends JpaRepository<BankBalanceHistory, Long>  {
}
