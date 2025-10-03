package com.yass.fin.portfolio.service;

import com.yass.fin.portfolio.dto.CreateUserDto;
import com.yass.fin.portfolio.dto.LoginDto;
import com.yass.fin.portfolio.dto.LoginResponse;
import com.yass.fin.portfolio.exception.UnAuthorizedException;
import com.yass.fin.portfolio.model.Token;
import com.yass.fin.portfolio.model.UserRoleLinkT;
import com.yass.fin.portfolio.model.UserT;
import com.yass.fin.portfolio.repository.TokenRepository;
import com.yass.fin.portfolio.repository.UserRepository;
import com.yass.fin.portfolio.repository.UserRoleLinkRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final UserRoleLinkRepository roleLinkRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private static final Logger log = LoggerFactory.getLogger("fin-portfolio");

    public LoginResponse register(CreateUserDto createUserDto) {
        var user = UserT.builder()
                .firstName(createUserDto.getFirstname())
                .lastName(createUserDto.getLastname())
                .email(createUserDto.getEmail())
                .active(true)
                .password(passwordEncoder.encode(createUserDto.getPassword()))
                .build();
        var savedUser = repository.save(user);
        roleLinkRepository.save(UserRoleLinkT.builder()
                .roleId(1)
                .userId(savedUser.getUserId())
                .build());
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return LoginResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public LoginResponse authenticate(LoginDto request) throws UnAuthorizedException {
        try {
        	System.out.println("check ------------------ 3");
            System.out.println(request.getUserId());
            System.out.println(request.getPassword());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUserId(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException ex) {
        	System.out.println("check ------------------ error");

            log.error("UnAuthorized " + ex.getMessage());
            throw new UnAuthorizedException("UnAuthorized");
        }
        
        var user = repository.findById(Integer.parseInt(request.getUserId()))
                .orElseThrow();
        
        log.info("############### user" + user.getFirstName());
        
            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);
//            revokeAllUserTokens(user);
//            saveUserToken(user, jwtToken);
            return LoginResponse.builder()
                .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(UserT user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

//    private void revokeAllUserTokens(User user) {
//        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
//        if (validUserTokens.isEmpty())
//            return;
//        validUserTokens.forEach(token -> {
//            token.setExpired(true);
//            token.setRevoked(true);
//        });
//        tokenRepository.saveAll(validUserTokens);
//    }

//    public void refreshToken(
//            HttpServletRequest request,
//            HttpServletResponse response
//    ) throws IOException {
//        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        final String refreshToken;
//        final String userEmail;
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            return;
//        }
//        refreshToken = authHeader.substring(7);
//        userEmail = jwtService.extractUsername(refreshToken);
//        if (userEmail != null) {
//            var user = this.repository.findByEmail(userEmail)
//                    .orElseThrow();
//            if (jwtService.isTokenValid(refreshToken, user)) {
//                var accessToken = jwtService.generateToken(user);
////                revokeAllUserTokens(user);
////                saveUserToken(user, accessToken);
//                var authResponse = LoginResponse.builder()
//                        .accessToken(accessToken)
//                        .refreshToken(refreshToken)
//                        .build();
//                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
//            }
//        }
//    }
}
