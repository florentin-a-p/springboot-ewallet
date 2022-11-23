package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.UserBalance;
import net.javaguides.springboot.model.UserBalanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBalanceHistoryRepository extends JpaRepository<UserBalanceHistory, Long> {
  UserBalanceHistory findByUserBalanceId(Long userBalanceId);

  List<UserBalanceHistory> findByUserBalanceIdOrderByIdDesc(Long userBalanceId);
}
