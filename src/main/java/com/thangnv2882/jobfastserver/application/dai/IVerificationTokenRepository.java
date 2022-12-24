package com.thangnv2882.jobfastserver.application.dai;

import com.thangnv2882.jobfastserver.domain.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IVerificationTokenRepository
    extends JpaRepository<VerificationToken, Long> {

  @Query("select v from VerificationToken v where v.token = ?1")
  VerificationToken findByToken(String token);

}
