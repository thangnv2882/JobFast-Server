package com.thangnv2882.jobfastserver.domain.entity.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@MappedSuperclass
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractAuditingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonProperty("createdBy")
  private String createdBy;

  @JsonProperty("createdDate")
  @CreationTimestamp
  private Timestamp createdDate;

  @JsonProperty("lastModifiedBy")
  private String lastModifiedBy;

  @JsonProperty("lastModifiedDate")
  @UpdateTimestamp
  private Timestamp lastModifiedDate;

  @JsonProperty("activeFlag")
  private Boolean activeFlag = Boolean.TRUE;

  @JsonProperty("deleteFlag")
  private Boolean deleteFlag = Boolean.FALSE;

}
