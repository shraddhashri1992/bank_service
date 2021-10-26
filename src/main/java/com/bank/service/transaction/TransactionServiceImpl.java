package com.bank.service.transaction;

import com.bank.dto.transaction.TransactionDto;
import com.bank.dto.transaction.TransactionRequestDto;
import com.bank.dto.transaction.TransactionResponseDto;
import com.bank.entity.account.Account;
import com.bank.entity.transaction.Transaction;
import com.bank.repository.account.AccountRepository;
import com.bank.repository.transaction.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);


    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;


    @Override
    public void performBankTransaction(TransactionDto transactionDto) {
        Transaction transaction=new Transaction();
        transaction.setTransactionDate(LocalDateTime.now());
        BeanUtils.copyProperties(transactionDto,transaction);
        transactionRepository.save(transaction);
        this.updateAccountDetailsByAccountNumber(transactionDto);

    }

    @Override
    public List<Transaction> getStatementByYearAndMonth(int year, int month) {
        List<Transaction>transactionDtoList=new ArrayList<>();
      List<Transaction> transaction=transactionRepository.getStatementByYearAndMonth(year,month);
        //System.out.println(transaction);
      //BeanUtils.copyProperties(transaction,transactionDtoList);
      return transaction;

    }

    @Override
    public void  performTransactionByPhoneNumber(TransactionRequestDto transactionRequestDto) {

         this.updateAccountDetailsByPhoneNumber(transactionRequestDto);
    }

    private void updateAccountDetailsByAccountNumber(TransactionDto transactionDto) {
        Long fromAccountNumber = transactionDto.getFromAccountNumber();
        Long toAccountNumber = transactionDto.getToAccountNumber();
        Double amount = transactionDto.getTransferAmount();
        LOGGER.info(fromAccountNumber + " " + toAccountNumber + "  " + amount);
        Account fromAccountDetails = accountRepository.findByAccountNumberEquals(fromAccountNumber);
        Account toAccountDetails = accountRepository.findByAccountNumberEquals(toAccountNumber);

        if (fromAccountDetails.getAccountBalance() >= amount) {
            fromAccountDetails.setAccountBalance(fromAccountDetails.getAccountBalance() - amount);
            accountRepository.save(fromAccountDetails);
            toAccountDetails.setAccountBalance(toAccountDetails.getAccountBalance() + (amount));
            accountRepository.save(toAccountDetails);
        }

    }

    private  void updateAccountDetailsByPhoneNumber(TransactionRequestDto transactionRequestDto) {
        String fromPhoneNumber = transactionRequestDto.getFromPhoneNumber();
        String toPhoneNumber = transactionRequestDto.getToPhoneNumber();
        Double amount = transactionRequestDto.getTransferAmount();

        LOGGER.info(fromPhoneNumber + " " + toPhoneNumber + "  " + amount);
        Account fromAccountDetails = accountRepository.findByPhoneNumberEquals(fromPhoneNumber);
        Account toAccountDetails = accountRepository.findByPhoneNumberEquals(toPhoneNumber);

        if (fromAccountDetails.getAccountBalance() >= amount) {
            fromAccountDetails.setAccountBalance(fromAccountDetails.getAccountBalance() - amount);
            accountRepository.save(fromAccountDetails);
            toAccountDetails.setAccountBalance(toAccountDetails.getAccountBalance() + (amount));
            accountRepository.save(toAccountDetails);
        }

    }
}
