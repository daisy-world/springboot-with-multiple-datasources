package com.easy2excel.springbootwithmultipledatasource.accountdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easy2excel.springbootwithmultipledatasource.accountdb.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

}
