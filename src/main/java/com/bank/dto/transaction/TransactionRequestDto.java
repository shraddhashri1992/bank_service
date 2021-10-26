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
public class TransactionRequestDto {
    @NotNull(message = "Please Phone  Number")
    private String fromPhoneNumber;
    @NotNull(message = "Please Phone Number")
    private String toPhoneNumber;
    @NotNull(message = "Please enter Total amount")
    private Double transferAmount;
    @NotNull(message = "Please enter Description")
    private String description;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime transactionDate;
}


