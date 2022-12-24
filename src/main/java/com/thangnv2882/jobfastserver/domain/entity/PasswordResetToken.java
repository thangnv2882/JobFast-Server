package com.thangnv2882.jobfastserver.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetToken {
  // Expiration time 10 minutes
  private static final int EXPIRATION_TIME = 10;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String token;

  private Date expirationTime;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id",
      nullable = false,
      foreignKey = @ForeignKey(name = "FK_USER_PASSWORD_TOKEN")
  )
  private Account account;

  public PasswordResetToken(Account account, String token) {
    super();
    this.token = token;
    this.account = account;
    this.expirationTime = calculateExpirationDate(EXPIRATION_TIME);
  }

  public PasswordResetToken(String token) {
    super();
    this.token = token;
    this.expirationTime = calculateExpirationDate(EXPIRATION_TIME);
  }

  private Date calculateExpirationDate(int expirationTime) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(new Date().getTime());
    calendar.add(Calendar.MINUTE, expirationTime);
    return new Date(calendar.getTime().getTime());

  }
}
