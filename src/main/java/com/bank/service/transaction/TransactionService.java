package com.bank.service.transaction;

import com.bank.dto.transaction.TransactionDto;
import com.bank.dto.transaction.TransactionRequestDto;
import com.bank.dto.transaction.TransactionResponseDto;
import com.bank.entity.transaction.Transaction;

import java.util.List;

public interface TransactionService {

    public void performBankTransaction(TransactionDto transactionDetailsDto);
    public List<Transaction> getStatementByYearAndMonth(int year, int month) ;
    public void  performTransactionByPhoneNumber(TransactionRequestDto transactionDto);

}
