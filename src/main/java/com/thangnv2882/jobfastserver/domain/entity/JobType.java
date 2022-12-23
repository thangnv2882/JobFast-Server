package com.thangnv2882.jobfastserver.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thangnv2882.jobfastserver.domain.entity.base.AbstractAuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JobType extends AbstractAuditingEntity {

  @Nationalized
  @NotBlank
  private String typeName;

  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "jobTypes")
  @JsonIgnore
  private Set<Job> jobs = new HashSet<>();

}
