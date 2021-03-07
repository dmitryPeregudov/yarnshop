package com.kate.yarnshop.dao;

import com.kate.yarnshop.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsRepository extends JpaRepository<Contact, String> {
}
