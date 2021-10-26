package com.bank.service.user;

import com.bank.dto.user.UserDto;
import com.bank.entity.account.Account;
import com.bank.entity.user.User;
import com.bank.exception.UserNotFoundException;
import com.bank.repository.account.AccountRepository;
import com.bank.repository.user.UserRepository;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements  UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;

    public Long saveUser(UserDto userDto) {
        Optional<User> userData=userRepository.findByPhoneNumber(userDto.getPhoneNumber());
        User user = new User();
        Long accountNumber=0l;
        if(!userData.isPresent()) {
            BeanUtils.copyProperties(userDto, user);
            userRepository.save(user);
             accountNumber = saveAccountDetails(user);
        }

        return  accountNumber;
    }

    @Override
    public boolean verifyPhoneNumber(String phoneNumber) {
       Optional<User> user= userRepository.findByPhoneNumber(phoneNumber);
       if(user.isPresent())
           return true;
       else {
       }      return  false;
    }



    private Long saveAccountDetails(User user) {
        Random random = new Random();
        Account accountDetails = new Account();
       Long  accountNumber  = (random.nextInt(1000000) + 1000000000l) * (random.nextInt(900) + 100);
        accountDetails.setUserId(user.getUserId());
        accountDetails.setAccountNumber(accountNumber);
        accountDetails.setAccountBalance(10000d);
        accountDetails.setAccountType("Saving");
        accountDetails.setPhoneNumber(user.getPhoneNumber());
        accountRepository.save(accountDetails);
        return accountNumber;
    }

}
