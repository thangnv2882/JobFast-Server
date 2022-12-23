package com.thangnv2882.jobfastserver.domain.entity;

import com.thangnv2882.jobfastserver.application.constants.JobDetailType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idJobDetail;

  private Boolean isApprove = Boolean.FALSE;

  private Boolean isPass = Boolean.FALSE;

  private JobDetailType jobDetailType;

  private String cv;

  //    link to table user
  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinColumn(name = "account_id")
  private Account account;

  //    link to table job
  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinColumn(name = "job_id")
  private Job job;

  public JobDetail(Account account, Job job, JobDetailType jobDetailType, Boolean isApprove, Boolean isPass) {
    this.isApprove = isApprove;
    this.isPass = isPass;
    this.jobDetailType = jobDetailType;
    this.account = account;
    this.job = job;
  }

  public JobDetail(Account account, Job job, JobDetailType jobDetailType, Boolean isApprove, Boolean isPass, String cv) {
    this.isApprove = isApprove;
    this.isPass = isPass;
    this.jobDetailType = jobDetailType;
    this.cv = cv;
    this.account = account;
    this.job = job;
  }
}
