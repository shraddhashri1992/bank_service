package com.bank.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.Min;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Long userId;
    private Long accountNumber;
    @Min(10000)
    private Double accountBalance;
    private Double currentBalance;
    private String accountType;
    private String phoneNumber;

}
