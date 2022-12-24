package com.thangnv2882.jobfastserver.application.dai;

import com.thangnv2882.jobfastserver.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {

  @Query("select a from Account a where a.deleteFlag = ?1 and a.enable = ?2")
  List<Account> findAllByDeleteFlagAndEnable(Boolean isDeleteFlag, Boolean isEnable);

  @Query("select a from Account a where a.email = ?1")
  Account findByEmail(String email);

}
