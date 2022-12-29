package com.thangnv2882.jobfastserver.application.dai;

import com.thangnv2882.jobfastserver.application.input.job.FindJobInput;
import com.thangnv2882.jobfastserver.domain.entity.Job;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IJobRepository extends JpaRepository<Job, Long> {


  @Query("select count(j) " +
      "from Job j " +
      "where (:#{#findJobInput.jobName} is null or j.jobName like %:#{#findJobInput.jobName}%) " +
      "and (:#{#findJobInput.salary} is null or j.salary >= :#{#findJobInput.salary})" +
      "and (:#{#findJobInput.location} is null or j.location like  %:#{#findJobInput.location}%)" +
      "and (:#{#findJobInput.jobType} is null or j.jobType = :#{#findJobInput.jobType})" +
      "and (:#{#findJobInput.jobLanguage} is null or j.jobLanguage = :#{#findJobInput.jobLanguage})")
  Long countJob(FindJobInput findJobInput);

  @Query("select j " +
      "from Job j " +
      "where (:#{#findJobInput.jobName} is null or j.jobName like %:#{#findJobInput.jobName}%) " +
      "and (:#{#findJobInput.salary} is null or j.salary >= :#{#findJobInput.salary})" +
      "and (:#{#findJobInput.location} is null or j.location like %:#{#findJobInput.location}%)" +
      "and (:#{#findJobInput.jobType} is null or j.jobType = :#{#findJobInput.jobType})" +
      "and (:#{#findJobInput.jobLanguage} is null or j.jobLanguage = :#{#findJobInput.jobLanguage})")
  List<Job> findJob(FindJobInput findJobInput, PageRequest pageRequest);

}
