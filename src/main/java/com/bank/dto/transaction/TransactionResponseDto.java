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
    public class TransactionResponseDto {
    private Long transactionId;
        private String fromPhoneNumber;
        private String toPhoneNumber;
        private Double transferAmount;
        private String description;
        private LocalDateTime transactionDate;
    }


