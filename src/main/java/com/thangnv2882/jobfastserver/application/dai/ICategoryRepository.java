package com.thangnv2882.jobfastserver.application.dai;

import com.thangnv2882.jobfastserver.application.input.commons.PageMetaInput;
import com.thangnv2882.jobfastserver.domain.entity.Category;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

  @Query("select count(c) from Category c")
  Long countAll();

  @Query("select c from Category c")
  List<Category> findAll(PageMetaInput pageMetaInput, PageRequest pageRequest, Sort sort);

}
