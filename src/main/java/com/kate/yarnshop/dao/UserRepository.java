package com.kate.yarnshop.dao;

import com.kate.yarnshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional(readOnly = true)
    @Query(value = "select user from User user where user.login = :login or user.email = :login")
    User getUserByLogin(@Param("login") String login);
}
