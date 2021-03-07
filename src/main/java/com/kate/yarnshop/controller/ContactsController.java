package com.kate.yarnshop.controller;

import com.kate.yarnshop.dao.ContactsRepository;
import com.kate.yarnshop.entity.Contact;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContactsController {
    private final ContactsRepository contactsRepository;

    public ContactsController(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    @GetMapping("/contacts")
    public List<Contact> getShopContacts() {
        return contactsRepository.findAll();
    }
}
