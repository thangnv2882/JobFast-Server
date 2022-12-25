package com.thangnv2882.jobfastserver.application.service.impl;

import com.thangnv2882.jobfastserver.application.constants.CommonConstant;
import com.thangnv2882.jobfastserver.application.constants.MessageConstant;
import com.thangnv2882.jobfastserver.application.dai.IRoleRepository;
import com.thangnv2882.jobfastserver.application.input.role.CreateRoleInput;
import com.thangnv2882.jobfastserver.application.output.common.Output;
import com.thangnv2882.jobfastserver.application.service.IRoleService;
import com.thangnv2882.jobfastserver.config.exception.NotFoundException;
import com.thangnv2882.jobfastserver.domain.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
  private final IRoleRepository roleRepository;
  private final ModelMapper modelMapper;

  public RoleServiceImpl(IRoleRepository roleRepository, ModelMapper modelMapper) {
    this.roleRepository = roleRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public List<Role> getAll() {
    return roleRepository.findAll();
  }

  @Override
  public Role getRole(String roleName) {
    return roleRepository.findByRoleName(roleName);
  }

  @Override
  public Output saveRole(CreateRoleInput input) {
    Role role = modelMapper.map(input, Role.class);
    roleRepository.save(role);
    return new Output(CommonConstant.TRUE, CommonConstant.EMPTY_STRING);
  }

  @Override
  public Output deleteRole(Long idRole) {
    Optional<Role> role = roleRepository.findById(idRole);
    checkRoleExists(role);
    roleRepository.delete(role.get());
    return new Output(CommonConstant.TRUE, CommonConstant.EMPTY_STRING);
  }

  public static void checkRoleExists(Optional<Role> role) {
    if (role.isEmpty()) {
      throw new NotFoundException(MessageConstant.ROLE_NOT_EXISTS);
    }
  }
}
