package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.BankBalanceHistory;
import net.javaguides.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankBalanceHistoryRepository extends JpaRepository<BankBalanceHistory, Long>  {
}
