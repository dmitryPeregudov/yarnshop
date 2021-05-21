package com.kate.yarnshop.controller;

import com.kate.yarnshop.dao.ForumRepository;
import com.kate.yarnshop.entity.ForumMessages;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import static com.kate.yarnshop.constants.Constants.FORUM_PATH;

@RestController
@RequestMapping(FORUM_PATH)
public class ForumController {
    private ForumRepository forumRepository;

    public ForumController(ForumRepository forumRepository) {
        this.forumRepository = forumRepository;
    }

    @GetMapping
    public List<ForumMessages> getAllMessages() {
        return forumRepository.findAll();
    }

    @PostMapping
    public ForumMessages saveMessage(@RequestBody ForumMessages forumMessages) {
        if (forumMessages.getDate() == null) {
            forumMessages.setDate(new Timestamp(System.currentTimeMillis()));
        }
        return forumRepository.save(forumMessages);
    }
}
