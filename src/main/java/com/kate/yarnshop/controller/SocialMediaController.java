package com.kate.yarnshop.controller;

import com.kate.yarnshop.dao.SocialMediaRepository;
import com.kate.yarnshop.entity.SocialMedia;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SocialMediaController {
    private final SocialMediaRepository socialMediaRepository;

    public SocialMediaController(SocialMediaRepository socialMediaRepository) {
        this.socialMediaRepository = socialMediaRepository;
    }

    @GetMapping("socialMedia")
    public List<SocialMedia> getSocialMedia() {
        return socialMediaRepository.findAll();
    }
}
