package com.thangnv2882.jobfastserver.application.service.impl;

import com.thangnv2882.jobfastserver.application.constants.CommonConstant;
import com.thangnv2882.jobfastserver.application.constants.JobDetailTypeEnum;
import com.thangnv2882.jobfastserver.application.constants.MessageConstant;
import com.thangnv2882.jobfastserver.application.dai.IAccountRepository;
import com.thangnv2882.jobfastserver.application.dai.ICVRepository;
import com.thangnv2882.jobfastserver.application.dai.IJobDetailRepository;
import com.thangnv2882.jobfastserver.application.dai.IJobRepository;
import com.thangnv2882.jobfastserver.application.input.commons.Input;
import com.thangnv2882.jobfastserver.application.input.commons.PageMetaInput;
import com.thangnv2882.jobfastserver.application.input.job_detail.ApplyJobInput;
import com.thangnv2882.jobfastserver.application.input.job_detail.UpdateJobDetailInput;
import com.thangnv2882.jobfastserver.application.output.common.Output;
import com.thangnv2882.jobfastserver.application.output.common.PagingMeta;
import com.thangnv2882.jobfastserver.application.output.job_detail.GetListJobDetailOutput;
import com.thangnv2882.jobfastserver.application.service.IJobDetailService;
import com.thangnv2882.jobfastserver.application.service.ISendMailService;
import com.thangnv2882.jobfastserver.application.utils.SecurityUtil;
import com.thangnv2882.jobfastserver.config.exception.VsException;
import com.thangnv2882.jobfastserver.domain.entity.Account;
import com.thangnv2882.jobfastserver.domain.entity.CV;
import com.thangnv2882.jobfastserver.domain.entity.Job;
import com.thangnv2882.jobfastserver.domain.entity.JobDetail;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobDetailServiceImpl implements IJobDetailService {

  private final IJobDetailRepository jobDetailRepository;
  private final IAccountRepository accountRepository;
  private final IJobRepository jobRepository;
  private final ICVRepository cvRepository;
  private final ISendMailService sendMailService;
  private final ModelMapper modelMapper;

  public JobDetailServiceImpl(IJobDetailRepository jobDetailRepository, IAccountRepository accountRepository,
                              IJobRepository jobRepository, ICVRepository cvRepository,
                              ISendMailService sendMailService,
                              ModelMapper modelMapper) {
    this.jobDetailRepository = jobDetailRepository;
    this.accountRepository = accountRepository;
    this.jobRepository = jobRepository;
    this.cvRepository = cvRepository;
    this.sendMailService = sendMailService;
    this.modelMapper = modelMapper;
  }

  public static void checkJobDetailExists(Optional<JobDetail> jobDetail) {
    if (jobDetail.isEmpty()) {
      throw new VsException(MessageConstant.JOB_DETAIL_NOT_EXISTS);
    }
  }

  @Override
  public GetListJobDetailOutput findAll(PageMetaInput input) {
    Long total = jobDetailRepository.countAll();
    Sort sort = Sort.by(Sort.Direction.valueOf(input.getSortType()), input.getSortBy());

    List<JobDetail> jobDetails = jobDetailRepository.findAll(
        input, PageRequest.of(input.getPageNum(), input.getPageSize(), sort));

    PagingMeta meta = new PagingMeta(total, input.getPageNum(), input.getPageSize(), input.getSortBy(),
        input.getSortType());

    return new GetListJobDetailOutput(jobDetails, meta);
  }

  @Override
  public JobDetail findJobDetailById(Input input) {
    Optional<JobDetail> jobDetail = jobDetailRepository.findById(input.getId());
    checkJobDetailExists(jobDetail);
    return jobDetail.get();
  }

  @Override
  public List<JobDetail> findAllJobDetailAppliedByIdJob(Long idJob) {
    Optional<Job> job = jobRepository.findById(idJob);
    JobServiceImpl.checkJobExists(job);
    List<JobDetail> jobDetails = jobDetailRepository.findAllByJobAndJobDetailType(job.get(), JobDetailTypeEnum.APPLY);
    return jobDetails;
  }

  @Override
  public Output applyJob(ApplyJobInput input) {
    Account account = accountRepository.findByEmail(SecurityUtil.getCurrentAccountLogin());
    AuthServiceImpl.checkAccountExists(Optional.ofNullable(account));
    Optional<Job> job = jobRepository.findById(input.getIdJob());
    JobServiceImpl.checkJobExists(job);
    Optional<CV> cv = cvRepository.findById(input.getIdCV());

    JobDetail jobDetail = new JobDetail(account, job.get(), JobDetailTypeEnum.APPLY, Boolean.FALSE, Boolean.FALSE,
        cv.get().getPathCV());
    jobDetailRepository.save(jobDetail);

    return new Output(CommonConstant.TRUE, CommonConstant.EMPTY_STRING);
  }

  @Override
  public Output updateJobDetail(UpdateJobDetailInput input) {
    Optional<JobDetail> jobDetail = jobDetailRepository.findById(input.getId());
    checkJobDetailExists(jobDetail);

    modelMapper.map(input, jobDetail.get());
    jobDetailRepository.save(jobDetail.get());

    return new Output(CommonConstant.TRUE, CommonConstant.EMPTY_STRING);
  }

  @Override
  public Output deleteJobDetail(Input input) {
    Optional<JobDetail> jobDetail = jobDetailRepository.findById(input.getId());
    checkJobDetailExists(jobDetail);
    jobDetailRepository.delete(jobDetail.get());

    return new Output(CommonConstant.TRUE, CommonConstant.EMPTY_STRING);
  }

}
