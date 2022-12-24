package com.thangnv2882.jobfastserver.application.dai;

import com.thangnv2882.jobfastserver.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

  @Query("select r from Role r where r.roleName = ?1")
  Role findByRoleName(String roleName);

}
