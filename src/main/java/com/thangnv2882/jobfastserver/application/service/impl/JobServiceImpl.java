package com.thangnv2882.jobfastserver.application.service.impl;

import com.thangnv2882.jobfastserver.application.constants.CommonConstant;
import com.thangnv2882.jobfastserver.application.constants.JobDetailTypeEnum;
import com.thangnv2882.jobfastserver.application.constants.MessageConstant;
import com.thangnv2882.jobfastserver.application.dai.IAccountRepository;
import com.thangnv2882.jobfastserver.application.dai.ICategoryRepository;
import com.thangnv2882.jobfastserver.application.dai.IJobDetailRepository;
import com.thangnv2882.jobfastserver.application.dai.IJobRepository;
import com.thangnv2882.jobfastserver.application.input.commons.Input;
import com.thangnv2882.jobfastserver.application.input.job.CreateJobInput;
import com.thangnv2882.jobfastserver.application.input.job.FindJobInput;
import com.thangnv2882.jobfastserver.application.input.job.UpdateJobInput;
import com.thangnv2882.jobfastserver.application.output.common.Output;
import com.thangnv2882.jobfastserver.application.output.common.PagingMeta;
import com.thangnv2882.jobfastserver.application.output.job.GetListJobOutput;
import com.thangnv2882.jobfastserver.application.service.IJobService;
import com.thangnv2882.jobfastserver.application.utils.SecurityUtil;
import com.thangnv2882.jobfastserver.config.exception.NotFoundException;
import com.thangnv2882.jobfastserver.domain.entity.Account;
import com.thangnv2882.jobfastserver.domain.entity.Category;
import com.thangnv2882.jobfastserver.domain.entity.Job;
import com.thangnv2882.jobfastserver.domain.entity.JobDetail;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JobServiceImpl implements IJobService {

  private final IJobRepository jobRepository;
  private final ICategoryRepository categoryRepository;
  private final IAccountRepository accountRepository;
  private final IJobDetailRepository jobDetailRepository;
  private final ModelMapper modelMapper;

  public JobServiceImpl(IJobRepository jobRepository, ICategoryRepository categoryRepository,
                        IAccountRepository accountRepository, IJobDetailRepository jobDetailRepository,
                        ModelMapper modelMapper) {
    this.jobRepository = jobRepository;
    this.categoryRepository = categoryRepository;
    this.accountRepository = accountRepository;
    this.jobDetailRepository = jobDetailRepository;
    this.modelMapper = modelMapper;
  }

  public static void checkJobExists(Optional<Job> job) {
    if (job.isEmpty()) {
      throw new NotFoundException(MessageConstant.JOB_NOT_EXISTS);
    }
  }

  @Override
  public Output createJob(CreateJobInput input) {
    Job job = modelMapper.map(input, Job.class);
    Optional<Category> category = categoryRepository.findById(input.getCategoryId());
    CategoryServiceImpl.checkCategoryExists(category);
    job.setCategory(category.get());
    jobRepository.save(job);

    Account account = accountRepository.findByEmail(input.getCreatedBy());
    JobDetail jobDetail = new JobDetail(account, job, JobDetailTypeEnum.JOB, Boolean.FALSE, Boolean.FALSE);
    jobDetailRepository.save(jobDetail);

    return new Output(CommonConstant.TRUE, CommonConstant.EMPTY_STRING);
  }

  @Override
  public GetListJobOutput findAll(FindJobInput input) {
    Long total = jobRepository.countJob(input);
    Sort sort = Sort.by(Sort.Direction.valueOf(input.getSortType()), input.getSortBy());
    List<Job> jobs = jobRepository.findJob(input, PageRequest.of(input.getPageNum(), input.getPageSize(), sort));

    PagingMeta meta = new PagingMeta(total, input.getPageNum(), input.getPageSize(), input.getSortBy(),
        input.getSortType());

    return new GetListJobOutput(jobs, meta);
  }

  @Override
  public Job findJobById(Input input) {
    Optional<Job> job = jobRepository.findById(input.getId());
    checkJobExists(job);
    return job.get();
  }

  @Override
  public Map<String, List<Job>> findJobOfUser() {
    Map<String, List<Job>> map = new HashMap<>();

    Account account = accountRepository.findByEmail(SecurityUtil.getCurrentAccountLogin());
    AuthServiceImpl.checkAccountExists(Optional.ofNullable(account));

    List<JobDetail> jobDetails = jobDetailRepository.findAllByAccountAndJobDetailType(account, JobDetailTypeEnum.APPLY);
    List<Job> applys = new ArrayList<>();
    jobDetails.forEach(jobDetail -> {
      applys.add(jobDetail.getJob());
    });
    map.put(JobDetailTypeEnum.APPLY.toString(), applys);

    jobDetails = jobDetailRepository.findAllByAccountAndJobDetailType(account, JobDetailTypeEnum.JOB);
    List<Job> jobs = new ArrayList<>();
    jobDetails.forEach(jobDetail -> {
      jobs.add(jobDetail.getJob());
    });
    map.put(JobDetailTypeEnum.JOB.toString(), jobs);

    return map;
  }

  @Override
  public Output updateJob(UpdateJobInput input) {
    Optional<Job> job = jobRepository.findById(input.getId());
    JobServiceImpl.checkJobExists(job);

    modelMapper.map(input, job.get());
    if (input.getCategoryId() != null) {
      Optional<Category> category = categoryRepository.findById(input.getCategoryId());
      CategoryServiceImpl.checkCategoryExists(category);
      job.get().setCategory(category.get());
    }
    jobRepository.save(job.get());

    return new Output(CommonConstant.TRUE, CommonConstant.EMPTY_STRING);
  }

  @Override
  public Output deleteJob(Input input) {
    Optional<Job> job = jobRepository.findById(input.getId());
    JobServiceImpl.checkJobExists(job);
    jobRepository.delete(job.get());

    return new Output(CommonConstant.TRUE, CommonConstant.EMPTY_STRING);
  }

}
