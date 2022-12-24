package com.thangnv2882.jobfastserver.application.service;


import com.thangnv2882.jobfastserver.application.input.role.CreateRoleInput;
import com.thangnv2882.jobfastserver.application.output.Output;
import com.thangnv2882.jobfastserver.domain.entity.Role;

import java.util.List;

public interface IRoleService {
  List<Role> getAll();

  Role getRole(String roleName);

  Output saveRole(CreateRoleInput input);

  Output deleteRole(Long idRole);
}
