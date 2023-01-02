package com.thangnv2882.jobfastserver.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thangnv2882.jobfastserver.domain.entity.base.AbstractAuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

  @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinTable(name = "account_notification",
      joinColumns = @JoinColumn(name = "notification_id",
          referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "account_id",
          referencedColumnName = "id"))
  @JsonIgnore
  private Set<Account> accounts = new HashSet<>();

}
