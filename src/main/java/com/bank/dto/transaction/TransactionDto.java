package com.bank.dto.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    @NotNull(message = "Please enter Account Number")
    private Long fromAccountNumber;
    @NotNull(message = "Please Account Number")
    private Long toAccountNumber;
    @NotNull(message = "Please enter Total amount")
    private Double transferAmount;
    @NotNull(message = "Please enter Description")
    private String description;

}
