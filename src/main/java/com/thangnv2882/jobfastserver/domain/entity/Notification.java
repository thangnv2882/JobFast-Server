package com.thangnv2882.jobfastserver.domain.entity;

import com.thangnv2882.jobfastserver.domain.entity.base.AbstractAuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification extends AbstractAuditingEntity {

  @Nationalized
  private String title;

  @Nationalized
  private String content;

  private String path;

  private Boolean isRead;

  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinColumn(name = "account_id")
  private Account account;

}
