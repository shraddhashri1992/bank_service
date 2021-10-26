package com.bank.controller.transaction;

import com.bank.constant.GlobalConstant;
import com.bank.dto.transaction.TransactionDto;
import com.bank.dto.transaction.TransactionRequestDto;
import com.bank.dto.transaction.TransactionResponseDto;
import com.bank.entity.transaction.Transaction;
import com.bank.service.transaction.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/bankTransaction")
    public ResponseEntity<?> performBankTransaction(@Valid @RequestBody TransactionDto transactionDto) {
        transactionService.performBankTransaction(transactionDto);
        LOGGER.info(GlobalConstant.SUCCESSFUL_TRANSACTION_MESSAGE+" forAccount ="+transactionDto.getFromAccountNumber()+" "
      +"To Account = "  +transactionDto.getToAccountNumber());
        return  ResponseEntity.status(HttpStatus.OK).body(GlobalConstant.SUCCESSFUL_TRANSACTION_MESSAGE+" forAccount ="+transactionDto.getFromAccountNumber()+" "
                +"To Account = "  +transactionDto.getToAccountNumber());
    }

    @PostMapping("/gpayTransaction")
    public ResponseEntity<String> performTransactionByPhoneNumber(@Valid @RequestBody TransactionRequestDto transactionRequestDto) {
         transactionService.performTransactionByPhoneNumber(transactionRequestDto);
        LOGGER.info(GlobalConstant.SUCCESSFUL_TRANSACTION_MESSAGE);
        return  ResponseEntity.status(HttpStatus.OK).body(GlobalConstant.SUCCESSFUL_TRANSACTION_MESSAGE+" forAccount ="+transactionRequestDto.getFromPhoneNumber()+" "
                +"To Account = "  +transactionRequestDto.getToPhoneNumber());


    }

    @GetMapping("/get")
    public ResponseEntity<?> getStatement(@RequestParam int year, @RequestParam int month) {
        List<Transaction> transactionDetailsList=transactionService.getStatementByYearAndMonth(year, month);
        return new ResponseEntity<List<Transaction>>(transactionDetailsList, HttpStatus.OK);
    }

}
