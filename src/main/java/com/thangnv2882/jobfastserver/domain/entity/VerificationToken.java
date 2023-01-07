package com.thangnv2882.jobfastserver.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerificationToken {

  // Expiration time 24h
  private static final int EXPIRATION_TIME = 24;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String token;

  private Date expirationTime;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "account_id",
      nullable = false,
      foreignKey = @ForeignKey(name = "FK_USER_VERIFY_TOKEN")
  )
  private Account account;

  public VerificationToken(Account account, String token) {
    super();
    this.token = token;
    this.account = account;
    this.expirationTime = calculateExpirationDate(EXPIRATION_TIME);
  }

  public VerificationToken(String token) {
    super();
    this.token = token;
    this.expirationTime = calculateExpirationDate(EXPIRATION_TIME);
  }

  private Date calculateExpirationDate(int expirationTime) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(new Date().getTime());
    calendar.add(Calendar.HOUR, expirationTime);
    return new Date(calendar.getTime().getTime());

  }
}
