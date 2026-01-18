package com.dc.config;

import com.dc.entity.UserAuthEntity;
import com.dc.enums.RoleEnum;
import com.dc.repository.UserAuthRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Scanner;

//@Component
public class AdminCreation implements CommandLineRunner {

    private final UserAuthRepository userAuthRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminCreation(UserAuthRepository userAuthRepository,PasswordEncoder passwordEncoder){
        this.userAuthRepository = userAuthRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello"+ args[0].equalsIgnoreCase("admin-creation"));
        if(!"admin-creation".equalsIgnoreCase(args[0]))
            return;
        if(userAuthRepository.existsByRoleID(RoleEnum.ADMIN.getId())){
            System.out.println("Admin User Already Exists");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Email : ");
        String email = scanner.nextLine();

        System.out.print("Enter Password : ");
        String password = scanner.nextLine();

        System.out.print("Enter Confirm Password : ");
        String confirmPassword = scanner.nextLine();


        if(!password.equalsIgnoreCase(confirmPassword)){
            System.out.println("Password do not match");
            return;
        }

        UserAuthEntity userAuthEntity = new UserAuthEntity();
        userAuthEntity.setEmail(email);
        userAuthEntity.setPassword(passwordEncoder.encode(confirmPassword));
        userAuthEntity.setRoleID(RoleEnum.ADMIN.getId());
        userAuthEntity.setVendorID(0L);
        userAuthEntity.setActive(true);
        userAuthEntity.setLocked(false);
        userAuthEntity.setCreatedByUserID(0L);
        userAuthEntity.setCreatedDate(LocalDate.now());
        userAuthRepository.save(userAuthEntity);
        System.out.println("Admin User Created Successfully...");
    }
}
