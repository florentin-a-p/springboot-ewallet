package net.demo.springboot.repository;

import net.demo.springboot.model.UserBalanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBalanceHistoryRepository extends JpaRepository<UserBalanceHistory, Long> {
  UserBalanceHistory findByUserBalanceId(Long userBalanceId);

  List<UserBalanceHistory> findByUserBalanceIdOrderByIdDesc(Long userBalanceId);
}
