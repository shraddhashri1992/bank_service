package com.bank.repository.transaction;

import com.bank.entity.account.Account;
import com.bank.entity.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    @Query(value = "select * from transaction e where (year(transaction_date) = ?1) and (month(transaction_date) = ?2  )",nativeQuery = true)
    List<Transaction> getStatementByYearAndMonth(int year, int month);

}
