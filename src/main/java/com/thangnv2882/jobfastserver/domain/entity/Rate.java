package com.thangnv2882.jobfastserver.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Rate {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private double star;

  @Nationalized
  private String content;

  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinColumn(name = "account_id")
  private Account account;

//  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
//  @JoinColumn(name = "company_id")
//  private Company company;

  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinColumn(name = "job_id")
  private Job job;

//  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rateParent")
//  @JsonIgnore
//  private Set<Rate> rateChilds = new HashSet<>();
//
//  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
//  @JoinColumn(name = "rate_parent_id")
//  private Rate rateParent;


}
