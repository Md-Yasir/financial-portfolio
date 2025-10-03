package com.yass.fin.portfolio.controller;

import com.yass.fin.portfolio.dto.CreateUserDto;
import com.yass.fin.portfolio.dto.LoginDto;
import com.yass.fin.portfolio.dto.LoginResponse;
import com.yass.fin.portfolio.dto.ResponseDto;
import com.yass.fin.portfolio.exception.UnAuthorizedException;
import com.yass.fin.portfolio.service.AuthenticationService;
import com.yass.fin.portfolio.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/v1/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseDto<LoginResponse> register(
            @RequestBody CreateUserDto createUserDto
    ) {
        return ResponseUtil.success(service.register(createUserDto));
    }

    @PostMapping("/auth")
    public ResponseDto<LoginResponse> authenticate(@RequestBody LoginDto request) throws UnAuthorizedException {
        return ResponseUtil.success(service.authenticate(request));
    }

//    @PostMapping("rtoken")
//    public void refreshToken(
//            HttpServletRequest request,
//            HttpServletResponse response
//    ) throws IOException {
//        service.refreshToken(request, response);
//    }
}
