package com.example.dockerspringbootmysql.repository;

import com.example.dockerspringbootmysql.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("select c from Account c where c.id = 1 and (c.id in (COALESCE(?1, c.id))) ")
    List<Account> filterAccounts(List<Long> accountIds);
}
