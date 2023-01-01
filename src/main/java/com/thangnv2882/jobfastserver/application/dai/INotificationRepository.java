package com.thangnv2882.jobfastserver.application.dai;

import com.thangnv2882.jobfastserver.domain.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface INotificationRepository extends JpaRepository<Notification, Long> {

  @Transactional
  @Modifying
  @Query("update Notification n set n.isRead = true where n.id = :id")
  void readNotification(Long id);

  @Transactional
  @Modifying
  @Query("delete from Notification n where n.id = ?1")
  void deleteById(Long id);

}
