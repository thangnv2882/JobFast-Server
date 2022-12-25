package com.thangnv2882.jobfastserver.application.service;

import com.thangnv2882.jobfastserver.application.input.category.CreateCategoryInput;
import com.thangnv2882.jobfastserver.application.input.category.UpdateCategoryInput;
import com.thangnv2882.jobfastserver.application.input.commons.Input;
import com.thangnv2882.jobfastserver.application.input.commons.PageMetaInput;
import com.thangnv2882.jobfastserver.application.output.category.GetListCategoryOutput;
import com.thangnv2882.jobfastserver.application.output.common.Output;
import com.thangnv2882.jobfastserver.domain.entity.Category;

public interface ICategoryService {

  GetListCategoryOutput findAll(PageMetaInput input);

  Category findCategoryById(Input input);

  Output createCategory(CreateCategoryInput input);

  Output updateCategory(UpdateCategoryInput input);

  Output deleteCategory(Input input);

}
