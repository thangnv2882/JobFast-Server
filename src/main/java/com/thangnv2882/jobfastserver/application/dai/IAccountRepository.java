package com.thangnv2882.jobfastserver.application.dai;

import com.thangnv2882.jobfastserver.application.input.commons.FindAccountInput;
import com.thangnv2882.jobfastserver.domain.entity.Account;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {

  @Query("select count(a) " +
      "from Account a " +
      "where (:#{#findAccountInput.accountType} is null or a.accountType = :#{#findAccountInput.accountType}) " +
      "and (:#{#findAccountInput.jobPosition} is null or a.jobPosition like %:#{#findAccountInput.jobPosition}%)" +
      "and (:#{#findAccountInput.experience} is null or a.experience >= :#{#findAccountInput.experience})" +
      "and (:#{#findAccountInput.gender} is null or a.gender like %:#{#findAccountInput.gender}%)" +
      "and (:#{#findAccountInput.address} is null or a.address like %:#{#findAccountInput.address}%)" +
      "and a.deleteFlag = :isDeleteFlag and a.enable = :isEnable")
  Long countAccount(FindAccountInput findAccountInput,
                    @Param("isDeleteFlag") Boolean isDeleteFlag,
                    @Param(("isEnable")) Boolean isEnable);

  @Query("select a " +
      "from Account a " +
      "where (:#{#findAccountInput.accountType} is null or a.accountType = :#{#findAccountInput.accountType})" +
      "and (:#{#findAccountInput.jobPosition} is null or a.jobPosition like %:#{#findAccountInput.jobPosition}%)" +
      "and (:#{#findAccountInput.experience} is null or a.experience >= :#{#findAccountInput.experience})" +
      "and (:#{#findAccountInput.gender} is null or a.gender like %:#{#findAccountInput.gender}%)" +
      "and (:#{#findAccountInput.address} is null or a.address like %:#{#findAccountInput.address}%)" +
      "and a.deleteFlag = :isDeleteFlag and a.enable = :isEnable")
  List<Account> findAll(FindAccountInput findAccountInput,
                        PageRequest pageRequest,
                        Sort sort,
                        @Param("isDeleteFlag") Boolean isDeleteFlag,
                        @Param(("isEnable")) Boolean isEnable);


  @Query("select a from Account a where a.email = ?1")
  Account findByEmail(String email);

}
