package com.thangnv2882.jobfastserver.application.dai;

import com.thangnv2882.jobfastserver.application.input.commons.PageMetaInput;
import com.thangnv2882.jobfastserver.application.output.common.PagingMeta;
import com.thangnv2882.jobfastserver.domain.entity.CV;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICVRepository extends JpaRepository<CV, Long> {

  @Query("select count(c) from CV c")
  Long countAll();

  @Query("select c from CV c")
  List<CV> findAll(PageMetaInput pageMetaInput, PageRequest pageRequest, Sort sort);

  @Query("select c from CV c where c.account.id = ?1")
  List<CV> findAllByAccount(Long id);

}
