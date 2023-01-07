package com.thangnv2882.jobfastserver;

import com.thangnv2882.jobfastserver.application.constants.EmailConstant;
import com.thangnv2882.jobfastserver.application.constants.RoleConstant;
import com.thangnv2882.jobfastserver.application.dai.IAccountRepository;
import com.thangnv2882.jobfastserver.application.dai.IRoleRepository;
import com.thangnv2882.jobfastserver.domain.entity.Account;
import com.thangnv2882.jobfastserver.domain.entity.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class JobFastServerApplication {

  private final IAccountRepository accountRepository;
  private final IRoleRepository roleRepository;

  public JobFastServerApplication(IAccountRepository accountRepository, IRoleRepository roleRepository) {
    this.accountRepository = accountRepository;
    this.roleRepository = roleRepository;
  }

  public static void main(String[] args) {
    SpringApplication.run(JobFastServerApplication.class, args);
  }

  @Bean
  CommandLineRunner init() {
    return args -> {
      PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

      if (roleRepository.count() == 0) {
        roleRepository.save(new Role(RoleConstant.ROLE_ADMIN, null));
        roleRepository.save(new Role(RoleConstant.ROLE_USER, null));
      }

      if (accountRepository.count() == 0) {
        Account account = new Account(EmailConstant.EMAIL_ADMIN, passwordEncoder.encode("admin"),
            "admin", Set.copyOf(roleRepository.findAll()), true);
        accountRepository.save(account);
      }
    };
  }

}
