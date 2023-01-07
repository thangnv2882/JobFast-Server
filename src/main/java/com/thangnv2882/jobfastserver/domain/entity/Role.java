package com.thangnv2882.jobfastserver.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thangnv2882.jobfastserver.domain.entity.base.AbstractAuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends AbstractAuditingEntity {

  @Nationalized
  @NotBlank
  private String roleName;

  @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "roles")
  @JsonIgnore
  private Set<Account> accounts = new HashSet<>();

}
