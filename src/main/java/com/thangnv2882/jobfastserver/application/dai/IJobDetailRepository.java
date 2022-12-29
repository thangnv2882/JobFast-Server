package com.thangnv2882.jobfastserver.application.dai;

import com.thangnv2882.jobfastserver.application.constants.JobDetailTypeEnum;
import com.thangnv2882.jobfastserver.application.input.commons.PageMetaInput;
import com.thangnv2882.jobfastserver.domain.entity.Account;
import com.thangnv2882.jobfastserver.domain.entity.Job;
import com.thangnv2882.jobfastserver.domain.entity.JobDetail;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IJobDetailRepository extends JpaRepository<JobDetail, Long> {

  @Query("select count(jd) from JobDetail jd")
  Long countAll();

  @Query("select jd from JobDetail jd")
  List<JobDetail> findAll(PageMetaInput pageMetaInput, PageRequest pageRequest);

  @Query("select jd from JobDetail jd where jd.account = ?1 and jd.jobDetailType = ?2")
  List<JobDetail> findAllByAccountAndJobDetailType(Account account, JobDetailTypeEnum jobDetailType);

  @Query("select jd from JobDetail jd where jd.job = ?1 and jd.jobDetailType = ?2")
  List<JobDetail> findAllByJobAndJobDetailType(Job job, JobDetailTypeEnum jobDetailType);

}
