package com.yass.fin.portfolio.dto;

import com.yass.fin.portfolio.model.UserRoleLinkT;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateUserDto {

  private String firstname;
  private String lastname;
  private String email;
  private String password;
//  private String role; Pass Role
}
