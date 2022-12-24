package com.thangnv2882.jobfastserver.application.dai;

import com.thangnv2882.jobfastserver.domain.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPasswordResetTokenRepository extends
    JpaRepository<PasswordResetToken, Long> {

  @Query("select p from PasswordResetToken p where p.token = ?1")
  PasswordResetToken findByToken(String token);

}
