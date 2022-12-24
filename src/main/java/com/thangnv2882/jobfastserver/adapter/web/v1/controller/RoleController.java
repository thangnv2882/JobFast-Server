package com.thangnv2882.jobfastserver.adapter.web.v1.controller;


import com.thangnv2882.jobfastserver.adapter.web.base.RestApiV1;
import com.thangnv2882.jobfastserver.adapter.web.base.VsResponseUtil;
import com.thangnv2882.jobfastserver.application.constants.UrlConstant;
import com.thangnv2882.jobfastserver.application.input.role.CreateRoleInput;
import com.thangnv2882.jobfastserver.application.output.Output;
import com.thangnv2882.jobfastserver.application.service.IRoleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiV1
public class RoleController {
  private final IRoleService roleService;

  public RoleController(IRoleService roleService) {
    this.roleService = roleService;
  }

  @Operation(summary = "API Get List Role")
  @GetMapping(UrlConstant.Role.GET_ALL)
  public ResponseEntity<?> getRoles() {
    return ResponseEntity.ok().body(roleService.getAll());
  }

  @Operation(summary = "API Get Role By Role Name")
  @GetMapping(UrlConstant.Role.GET)
  public ResponseEntity<?> getRoleByRoleName(@PathVariable("roleName") String roleName) {
    return ResponseEntity.ok().body(roleService.getRole(roleName));
  }

  @Operation(summary = "API Create Role")
  @PostMapping(UrlConstant.Role.CREATE)
  public ResponseEntity<?> createRole(@RequestBody CreateRoleInput input) {
    // Get output
    Output output = roleService.saveRole(input);
    // Return output
    return VsResponseUtil.ok(output);
  }

  @Operation(summary = "API Delete Role")
  @DeleteMapping(UrlConstant.Role.DELETE)
  public ResponseEntity<?> deleteRole(@PathVariable("idRole") Long idRole) {
    // Get output
    Output output = roleService.deleteRole(idRole);
    // Return output
    return VsResponseUtil.ok(output);
  }
}
