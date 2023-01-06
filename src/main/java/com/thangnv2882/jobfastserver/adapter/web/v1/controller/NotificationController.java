package com.thangnv2882.jobfastserver.adapter.web.v1.controller;

import com.thangnv2882.jobfastserver.adapter.web.base.RestApiV1;
import com.thangnv2882.jobfastserver.adapter.web.base.VsResponseUtil;
import com.thangnv2882.jobfastserver.application.constants.UrlConstant;
import com.thangnv2882.jobfastserver.application.input.commons.Input;
import com.thangnv2882.jobfastserver.application.input.notification.CreateNotificationInput;
import com.thangnv2882.jobfastserver.application.input.notification.SendNotificationInput;
import com.thangnv2882.jobfastserver.application.input.notification.UpdateNotificationInput;
import com.thangnv2882.jobfastserver.application.output.common.Output;
import com.thangnv2882.jobfastserver.application.service.INotificationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestApiV1
public class NotificationController {

  private final INotificationService notificationService;

  public NotificationController(INotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  @Operation(summary = "API Get Notification By Id")
  @GetMapping(UrlConstant.Notification.GET)
  public ResponseEntity<?> getNotificationById(@PathVariable("idNotification") Long idNotification) {
    // Create input
    Input input = new Input(idNotification);
    // Return output
    return VsResponseUtil.ok(notificationService.findNotificationById(input));
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  @Operation(summary = "API Get Notification By Account")
  @GetMapping(UrlConstant.Notification.GET_BY_ACCOUNT)
  public ResponseEntity<?> getNotificationByAccount(@PathVariable("idAccount") Long idAccount) {
    // Create input
    Input input = new Input(idAccount);
    // Return output
    return VsResponseUtil.ok(notificationService.findNotificationByAccount(input));
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  @Operation(summary = "API Create Notification")
  @PostMapping(UrlConstant.Notification.CREATE)
  public ResponseEntity<?> createNotification(@Valid @RequestBody CreateNotificationInput input) {
    // Get output
    Output output = notificationService.createNotification(input);
    // Return output
    return VsResponseUtil.ok(output);
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  @Operation(summary = "API Send Notification")
  @PatchMapping(UrlConstant.Notification.SEND)
  public ResponseEntity<?> sendNotification(@Valid @RequestBody SendNotificationInput input) {
    // Get output
    Output output = notificationService.sendNotification(input);
    // Return output
    return VsResponseUtil.ok(output);
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  @Operation(summary = "API Update Notification")
  @PatchMapping(UrlConstant.Notification.UPDATE)
  public ResponseEntity<?> updateNotification(@Valid @RequestBody UpdateNotificationInput input) {
    // Get output
    Output output = notificationService.updateNotification(input);
    // Return output
    return VsResponseUtil.ok(output);
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  @Operation(summary = "API Read Notification")
  @PatchMapping(UrlConstant.Notification.READ)
  public ResponseEntity<?> readNotification(@PathVariable("idNotification") Long idNotification) {
    // Create Input
    Input input = new Input(idNotification);
    // Get output
    Output output = notificationService.readNotification(input);
    // Return output
    return VsResponseUtil.ok(output);
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  @Operation(summary = "API Read All Notification")
  @PatchMapping(UrlConstant.Notification.READ_ALL)
  public ResponseEntity<?> readAllNotification(@PathVariable("idAccount") Long idAccount) {
    // Create Input
    Input input = new Input(idAccount);
    // Get output
    Output output = notificationService.readAllNotification(input);
    // Return output
    return VsResponseUtil.ok(output);
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  @Operation(summary = "API Delete Notification")
  @DeleteMapping(UrlConstant.Notification.DELETE)
  public ResponseEntity<?> deleteNotification(@PathVariable("idNotification") Long idNotification) {
    // Create input
    Input input = new Input(idNotification);
    // Get output
    Output output = notificationService.deleteNotification(input);
    // Return output
    return VsResponseUtil.ok(output);
  }

}
