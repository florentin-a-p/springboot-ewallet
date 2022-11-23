package net.demo.springboot.repository;

import net.demo.springboot.model.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBalanceRepository extends JpaRepository<UserBalance, Long> {
  UserBalance findByUserId(Long userId);
}
