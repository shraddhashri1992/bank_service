package com.bank.controller.user;

import com.bank.constant.GlobalConstant;
import com.bank.dto.user.UserDto;
import com.bank.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService usersService;
    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody UserDto userDto) {
        Long accountNumber= usersService.saveUser(userDto);
        if(accountNumber>0l) {
            LOGGER.info("Record Inserted Successfully");
            return ResponseEntity.status(HttpStatus.OK).body(GlobalConstant.USER_INSERTED + " " + "Account Number = " + accountNumber);
        }
        return ResponseEntity.status(HttpStatus.OK).body(GlobalConstant.USER_ALREADY_EXIST);

    }

    @GetMapping("/verifyUser/{phoneNumber}")
        public boolean verifyPhoneNumber( @PathVariable String phoneNumber) {
            return usersService.verifyPhoneNumber(phoneNumber);


    }



    }
