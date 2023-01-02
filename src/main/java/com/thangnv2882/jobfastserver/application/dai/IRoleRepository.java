package com.thangnv2882.jobfastserver.application.dai;

import com.thangnv2882.jobfastserver.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

  @Query("select r from Role r where r.roleName = ?1")
  Role findByRoleName(String roleName);

  @Transactional
  @Modifying
  @Query("delete from Role r where r.id = ?1")
  void deleteById(Long id);

}
