package com.thangnv2882.jobfastserver.application.service.impl;

import com.thangnv2882.jobfastserver.application.constants.CommonConstant;
import com.thangnv2882.jobfastserver.application.constants.MessageConstant;
import com.thangnv2882.jobfastserver.application.dai.ICategoryRepository;
import com.thangnv2882.jobfastserver.application.input.category.CreateCategoryInput;
import com.thangnv2882.jobfastserver.application.input.category.UpdateCategoryInput;
import com.thangnv2882.jobfastserver.application.input.commons.Input;
import com.thangnv2882.jobfastserver.application.input.commons.PageMetaInput;
import com.thangnv2882.jobfastserver.application.output.category.GetListCategoryOutput;
import com.thangnv2882.jobfastserver.application.output.common.Output;
import com.thangnv2882.jobfastserver.application.output.common.PagingMeta;
import com.thangnv2882.jobfastserver.application.service.ICategoryService;
import com.thangnv2882.jobfastserver.config.exception.VsException;
import com.thangnv2882.jobfastserver.domain.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {

  private final ICategoryRepository categoryRepository;
  private final ModelMapper modelMapper;

  public CategoryServiceImpl(ICategoryRepository categoryRepository, ModelMapper modelMapper) {
    this.categoryRepository = categoryRepository;
    this.modelMapper = modelMapper;
  }

  public static void checkCategoryExists(Optional<Category> category) {
    if (category.isEmpty()) {
      throw new VsException(MessageConstant.CATEGORY_NOT_EXISTS);
    }
  }

  @Override
  public GetListCategoryOutput findAll(PageMetaInput input) {
    Long total = categoryRepository.countAll();

    List<Category> categories = categoryRepository.findAll(input, PageRequest.of(input.getPageNum(),
            input.getPageSize()),
        Sort.by(Sort.Direction.valueOf(input.getSortType()), input.getSortBy()));

    PagingMeta meta = new PagingMeta(total, input.getPageNum(), input.getPageSize(), input.getSortBy(),
        input.getSortType());

    return new GetListCategoryOutput(categories, meta);
  }

  @Override
  public Category findCategoryById(Input input) {
    Optional<Category> category = categoryRepository.findById(input.getId());
    checkCategoryExists(category);
    return category.get();
  }

  @Override
  public Output createCategory(CreateCategoryInput input) {
    Category category = modelMapper.map(input, Category.class);
    categoryRepository.save(category);
    return new Output(CommonConstant.TRUE, CommonConstant.EMPTY_STRING);
  }

  @Override
  public Output updateCategory(UpdateCategoryInput input) {
    Optional<Category> category = categoryRepository.findById(input.getId());
    checkCategoryExists(category);

    modelMapper.map(input, category.get());
    categoryRepository.save(category.get());

    return new Output(CommonConstant.TRUE, CommonConstant.EMPTY_STRING);
  }

  @Override
  public Output deleteCategory(Input input) {
    Optional<Category> category = categoryRepository.findById(input.getId());
    checkCategoryExists(category);

    categoryRepository.delete(category.get());
    return new Output(CommonConstant.TRUE, CommonConstant.EMPTY_STRING);
  }

}
