package com.kate.yarnshop.dao;

import com.kate.yarnshop.entity.ForumMessages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumRepository extends JpaRepository<ForumMessages, String> {
}
