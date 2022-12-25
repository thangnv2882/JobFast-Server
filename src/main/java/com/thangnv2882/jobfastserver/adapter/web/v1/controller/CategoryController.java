package com.thangnv2882.jobfastserver.adapter.web.v1.controller;

import com.thangnv2882.jobfastserver.adapter.web.base.RestApiV1;
import com.thangnv2882.jobfastserver.adapter.web.base.VsResponseUtil;
import com.thangnv2882.jobfastserver.application.constants.UrlConstant;
import com.thangnv2882.jobfastserver.application.input.category.CreateCategoryInput;
import com.thangnv2882.jobfastserver.application.input.category.UpdateCategoryInput;
import com.thangnv2882.jobfastserver.application.input.commons.Input;
import com.thangnv2882.jobfastserver.application.input.commons.PageMetaInput;
import com.thangnv2882.jobfastserver.application.output.category.GetListCategoryOutput;
import com.thangnv2882.jobfastserver.application.output.common.Output;
import com.thangnv2882.jobfastserver.application.service.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestApiV1
public class CategoryController {

  private final ICategoryService categoryService;

  public CategoryController(ICategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @Operation(summary = "API Get List Category")
  @GetMapping(UrlConstant.Category.GET_ALL)
  public ResponseEntity<?> getCategories(@RequestBody PageMetaInput pageMetaInput) {
    // Get output
    GetListCategoryOutput output = categoryService.findAll(pageMetaInput);
    // Return output
    return VsResponseUtil.ok(output);
  }

  @Operation(summary = "API Get Category By Id")
  @GetMapping(UrlConstant.Category.GET)
  public ResponseEntity<?> getCategoryById(@PathVariable("idCategory") Long idCategory) {
    // Create input
    Input input = new Input(idCategory);
    // Return output
    return VsResponseUtil.ok(categoryService.findCategoryById(input));
  }

  @Operation(summary = "API Create Category")
  @PostMapping(UrlConstant.Category.CREATE)
  public ResponseEntity<?> createCategory(@Valid @RequestBody CreateCategoryInput input) {
    // Get output
    Output output = categoryService.createCategory(input);
    // Return output
    return VsResponseUtil.ok(output);
  }

  @Operation(summary = "API Update Category")
  @PatchMapping(UrlConstant.Category.UPDATE)
  public ResponseEntity<?> updateCategory(@Valid @RequestBody UpdateCategoryInput input) {
    // Get output
    Output output = categoryService.updateCategory(input);
    // Return output
    return VsResponseUtil.ok(output);
  }

  @Operation(summary = "API Delete Category")
  @DeleteMapping(UrlConstant.Category.DELETE)
  public ResponseEntity<?> deleteCategory(@PathVariable("idCategory") Long idCategory) {
    // Create input
    Input input = new Input(idCategory);
    // Get output
    Output output = categoryService.deleteCategory(input);
    // Return output
    return VsResponseUtil.ok(output);
  }

}
