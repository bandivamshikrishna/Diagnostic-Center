package com.dc.controller;


import com.dc.dto.JWTTokens;
import com.dc.dto.UserCreateRequestDTO;
import com.dc.dto.UserLoginRequestDTO;
import com.dc.dto.UserSetPasswordDTO;
import com.dc.service.UserAuthService;
import com.dc.service.UserAuthTokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserAuthController {

    @Value("${jwt.refresh.token.expiration}")
    Integer jwtRefreshExpiration;

    private final UserAuthService userAuthService;
    private final UserAuthTokenService userAuthTokenService;

    public UserAuthController(UserAuthService userAuthService,UserAuthTokenService userAuthTokenService){
        this.userAuthService = userAuthService;
        this.userAuthTokenService = userAuthTokenService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserCreateRequestDTO userCreateRequestDTO){
        return new ResponseEntity<>(userAuthService.createUser(userCreateRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/validate-token")
    public ResponseEntity<Void> validToken(@RequestParam(name = "token") String token){
        userAuthTokenService.validateToken(token);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/validate-token")
    public ResponseEntity<Void> setUserPassword(@RequestParam(name = "token") String token, @Valid @RequestBody UserSetPasswordDTO userSetPasswordDTO){
            userAuthService.setUserPassword(token,userSetPasswordDTO.getPassword());
        return ResponseEntity.ok().build();
    }


    @PostMapping("/login")
    public ResponseEntity<Void> loginUser(@Valid @RequestBody UserLoginRequestDTO userLoginRequestDTO, HttpServletResponse httpServletResponse){
        JWTTokens jwtTokens = userAuthService.loginUser(userLoginRequestDTO);
        httpServletResponse.setHeader("Authorization", "Bearer "+jwtTokens.getAccessToken());

        Cookie cookie = new Cookie("refreshToken", jwtTokens.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setMaxAge(jwtRefreshExpiration/1000);
        cookie.setPath("/api/user/refreshToken");
        httpServletResponse.addCookie(cookie);
        return ResponseEntity.ok().build();
    }
}
