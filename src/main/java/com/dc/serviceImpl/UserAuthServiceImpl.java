package com.dc.serviceImpl;

import com.dc.dto.JWTTokens;
import com.dc.dto.UserCreateRequestDTO;
import com.dc.dto.UserLoginRequestDTO;
import com.dc.entity.UserAuthEntity;
import com.dc.enums.TokenTypeEnum;
import com.dc.exception.UserAlreadyExistsException;
import com.dc.exception.UserNotFoundException;
import com.dc.exception.VendorNotFoundException;
import com.dc.mapper.UserAuthMapper;
import com.dc.repository.UserAuthRepository;
import com.dc.repository.VendorRepository;
import com.dc.service.UserAuthService;
import com.dc.service.UserAuthTokenService;
import com.dc.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserAuthServiceImpl implements UserAuthService, UserDetailsService {

    private final UserAuthRepository userAuthRepository;
    private final VendorRepository vendorRepository;
    private final UserAuthTokenService userAuthTokenService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;

    @Value("${password.setOrReset.token.expiration}")
    private Long passwordExpirationMinutes;

    @Value("${jwt.refresh.token.expiration}")
    private Long refreshTokenExpiration;


    public UserAuthServiceImpl(UserAuthRepository userAuthRepository,VendorRepository vendorRepository,
                               UserAuthTokenService userAuthTokenService,PasswordEncoder passwordEncoder,
                               AuthenticationManager authenticationManager, JWTUtils jwtUtils
                               ){
        this.userAuthRepository = userAuthRepository;
        this.vendorRepository = vendorRepository;
        this.userAuthTokenService = userAuthTokenService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public String createUser(UserCreateRequestDTO userCreateRequestDTO) {
        if(userAuthRepository.existsByEmail(userCreateRequestDTO.getEmail().toLowerCase().trim()))
            throw new UserAlreadyExistsException(String.format("User Already with Email ID : %s", userCreateRequestDTO.getEmail()));

        if(vendorRepository.findById(userCreateRequestDTO.getVendorID()).isEmpty())
            throw new VendorNotFoundException(String.format("Vendor Not Found with ID : %d", userCreateRequestDTO.getVendorID()));

        if(!userAuthRepository.existsById(userCreateRequestDTO.getCreatedByUserID()))
            throw new UserNotFoundException(String.format("User Not Found with ID : %d", userCreateRequestDTO.getCreatedByUserID()));

        UserAuthEntity userAuthEntity = UserAuthMapper.fromCreateDTOToEntity(userCreateRequestDTO);
        userAuthEntity.setEmail(userAuthEntity.getEmail().toLowerCase());
        userAuthEntity.setActive(true);
        userAuthEntity.setLocked(true);
        userAuthEntity.setCreatedDate(LocalDate.now());
        Long id = userAuthRepository.save(userAuthEntity).getId();
        userAuthTokenService.createToken(userAuthEntity,null,
                TokenTypeEnum.SET_OR_RESET_PASSWORD_TOKEN, passwordExpirationMinutes);
        return String.format("User Created Successfully with ID : %d",id);
    }

    @Override
    public void setUserPassword(String token,String password) {
        if(userAuthTokenService.validateToken(token)) {
            UserAuthEntity userAuthEntity = userAuthTokenService.getUserFromToken(token);
            userAuthEntity.setPassword(passwordEncoder.encode(password));
            userAuthEntity.setLocked(false);
            userAuthRepository.save(userAuthEntity);
            userAuthTokenService.updateTokenUsed(token);
        }
    }


    @Override
    public JWTTokens loginUser(UserLoginRequestDTO userLoginRequestDTO){
        JWTTokens jwtTokens = new JWTTokens();
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userLoginRequestDTO.getEmail(), userLoginRequestDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if(authentication.isAuthenticated()){
            jwtTokens.setAccessToken(jwtUtils.generateToken(userLoginRequestDTO.getEmail(),true));
            jwtTokens.setRefreshToken(jwtUtils.generateToken(userLoginRequestDTO.getEmail(),false));
            userAuthTokenService.invalidateOldRefreshTokens(userLoginRequestDTO.getEmail());
            userAuthTokenService.createToken((UserAuthEntity) authentication.getPrincipal(), jwtTokens.getRefreshToken(),
                        TokenTypeEnum.REFRESH_TOKEN, refreshTokenExpiration);
        }
        return  jwtTokens;
    }


    @Override
    public UserAuthEntity loadUserByUsername(String email) throws UsernameNotFoundException {
        return userAuthRepository.findByEmail(email).orElseThrow(
                ()-> new UserNotFoundException(String.format("User Not Found with Email : %s", email))
        );
    }
}
