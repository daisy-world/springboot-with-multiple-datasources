package com.easy2excel.springbootwithmultipledatasource.userdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easy2excel.springbootwithmultipledatasource.userdb.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
