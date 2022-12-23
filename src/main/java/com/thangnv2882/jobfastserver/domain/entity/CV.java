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
public class CV extends AbstractAuditingEntity {

  @Nationalized
  private String name;

  private String pathCV;

  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinColumn(name = "account_id")
  private Account account;

}
