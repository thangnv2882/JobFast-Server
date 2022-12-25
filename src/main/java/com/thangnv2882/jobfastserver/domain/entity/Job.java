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
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Job extends AbstractAuditingEntity {

    @Nationalized
    private String jobName;

    private String salary;

    @Nationalized
    private String jobDescription;

    @Nationalized
    private String jobRequirements;

    @Nationalized
    private String location;

    @Nationalized
    private String timeUp;

    @Nationalized
    private int numberOfRecruitments;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    //phải để EAGER k thì sẽ lỗi "could not initialize proxy – no Session"
    @JoinTable(name = "job_job_type",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "job_type_id"))
    @JsonIgnore
    private Set<JobType> jobTypes = new HashSet<>();

    //    link to table JobDetail
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "job")
    @JsonIgnore
    private Set<JobDetail> jobDetails = new HashSet<>();

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "job")
//    @JsonIgnore
//    private Set<Comment> comments = new HashSet<>();

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

//    link to table Rate
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "job")
    @JsonIgnore
    private Set<Rate> Rate = new HashSet<>();

}
