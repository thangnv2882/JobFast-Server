package com.thangnv2882.jobfastserver.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thangnv2882.jobfastserver.application.constants.JobLanguageEnum;
import com.thangnv2882.jobfastserver.application.constants.JobTypeEnum;
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
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Job extends AbstractAuditingEntity {

  @Nationalized
  private String jobName;

  private Double salary;

  @Nationalized
  private String jobDescription;

  private JobTypeEnum jobType;

  @Nationalized
  private String jobRequirements;

  @Nationalized
  private String location;

  private JobLanguageEnum jobLanguage;

  @Nationalized
  private String timeUp;

  @Nationalized
  private Integer numberOfRecruitments;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "job")
  @JsonIgnore
  private Set<JobDetail> jobDetails = new HashSet<>();

  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinColumn(name = "category_id")
  private Category category;

}
