package com.bank.repository.account;

import com.bank.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByAccountNumberEquals(Long accountNumber);

    Account findByPhoneNumberEquals(String fromPhoneNumber);
}
