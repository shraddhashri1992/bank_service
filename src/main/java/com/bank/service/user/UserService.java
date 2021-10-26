package com.bank.service.user;

import com.bank.dto.user.UserDto;
import com.bank.exception.UserNotFoundException;

public interface UserService {
    public Long saveUser(UserDto userDetailsDto);

    boolean verifyPhoneNumber(String phoneNumber);
}
