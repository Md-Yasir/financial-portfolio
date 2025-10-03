package com.yass.fin.portfolio.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDto {
  String userId;
  String password;
}
