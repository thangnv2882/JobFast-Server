package com.thangnv2882.jobfastserver.application.service.impl;

import com.thangnv2882.jobfastserver.application.constants.CommonConstant;
import com.thangnv2882.jobfastserver.application.constants.MessageConstant;
import com.thangnv2882.jobfastserver.application.dai.IAccountRepository;
import com.thangnv2882.jobfastserver.application.dai.ICVRepository;
import com.thangnv2882.jobfastserver.application.input.commons.Input;
import com.thangnv2882.jobfastserver.application.input.commons.PageMetaInput;
import com.thangnv2882.jobfastserver.application.input.cv.UpdateCVInput;
import com.thangnv2882.jobfastserver.application.input.cv.UploadCVInput;
import com.thangnv2882.jobfastserver.application.output.GetListCVOutput;
import com.thangnv2882.jobfastserver.application.output.UploadFileOutput;
import com.thangnv2882.jobfastserver.application.output.common.Output;
import com.thangnv2882.jobfastserver.application.output.common.PagingMeta;
import com.thangnv2882.jobfastserver.application.service.ICVService;
import com.thangnv2882.jobfastserver.application.utils.FileUtil;
import com.thangnv2882.jobfastserver.application.utils.SecurityUtil;
import com.thangnv2882.jobfastserver.config.exception.NotFoundException;
import com.thangnv2882.jobfastserver.config.exception.VsException;
import com.thangnv2882.jobfastserver.domain.entity.Account;
import com.thangnv2882.jobfastserver.domain.entity.CV;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CVServiceImpl implements ICVService {

  private final ICVRepository cvRepository;
  private final IAccountRepository accountRepository;
  private final ModelMapper modelMapper;


  public CVServiceImpl(ICVRepository cvRepository, IAccountRepository accountRepository, ModelMapper modelMapper) {
    this.cvRepository = cvRepository;
    this.accountRepository = accountRepository;
    this.modelMapper = modelMapper;
  }

  public static void checkCVExists(Optional<CV> cv) {
    if (cv.isEmpty()) {
      throw new VsException(MessageConstant.CV_NOT_EXISTS);
    }
  }

  @Override
  public GetListCVOutput findAll(PageMetaInput input) {
    Long total = cvRepository.countAll();

    List<CV> cvs = cvRepository.findAll(input, PageRequest.of(input.getPageNum(), input.getPageSize()),
        Sort.by(Sort.Direction.valueOf(input.getSortType()), input.getSortBy()));

    PagingMeta meta = new PagingMeta(total, input.getPageNum(), input.getPageSize(), input.getSortBy(),
        input.getSortType());

    return new GetListCVOutput(cvs, meta);
  }

  @Override
  public CV findCVById(Input input) {
    Optional<CV> cv = cvRepository.findById(input.getId());
    checkCVExists(cv);
    return cv.get();
  }

  @Override
  public List<CV> findCVByAccount(Input input) {
    List<CV> cvs = cvRepository.findAllByAccount(input.getId());
    return cvs;
  }

  @Override
  public UploadFileOutput uploadCV(UploadCVInput input) throws IOException {
    Account account = accountRepository.findByEmail(SecurityUtil.getCurrentAccountLogin());
    AuthServiceImpl.checkAccountExists(Optional.ofNullable(account));

    UUID name = UUID.randomUUID();
    String fileName = FileUtil.saveFile(name.toString(), CommonConstant.CVS, input.getFile());
    String pathCV = input.getApplicationUrl() + CommonConstant.URL_CV + fileName;

    CV cv = modelMapper.map(input, CV.class);
//    cv.setName(input.getName());
    cv.setAccount(account);
    cv.setPathCV(pathCV);

    cvRepository.save(cv);

    return new UploadFileOutput(CommonConstant.TRUE, CommonConstant.EMPTY_STRING, pathCV);
  }

  @Override
  public Output updateCV(UpdateCVInput input) {
    Optional<CV> cv = cvRepository.findById(input.getId());
    CVServiceImpl.checkCVExists(cv);

    if (cv.get().getCreatedBy().compareTo(SecurityUtil.getCurrentAccountLogin()) == 0) {
      cv.get().setName(input.getName());
      cvRepository.save(cv.get());
    } else {
      throw new NotFoundException(MessageConstant.NOT_ACCESS);
    }
    return new Output(CommonConstant.TRUE, CommonConstant.EMPTY_STRING);
  }

  @Override
  public Output deleteCV(Input input) {
    Optional<CV> cv = cvRepository.findById(input.getId());
    CVServiceImpl.checkCVExists(cv);

    if (cv.get().getCreatedBy().compareTo(SecurityUtil.getCurrentAccountLogin()) == 0) {
      cvRepository.delete(cv.get());
    } else {
      throw new NotFoundException(MessageConstant.NOT_ACCESS);
    }
    return new Output(CommonConstant.TRUE, CommonConstant.EMPTY_STRING);
  }

}
