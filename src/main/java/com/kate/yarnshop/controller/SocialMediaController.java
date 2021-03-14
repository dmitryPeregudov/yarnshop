package com.kate.yarnshop.controller;

import com.kate.yarnshop.dao.SocialMediaRepository;
import com.kate.yarnshop.entity.SocialMedia;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.kate.yarnshop.constants.Constants.SOCIAL_MEDIA_PATH;

@RestController
@RequestMapping(SOCIAL_MEDIA_PATH)
public class SocialMediaController {
    private final SocialMediaRepository socialMediaRepository;

    public SocialMediaController(SocialMediaRepository socialMediaRepository) {
        this.socialMediaRepository = socialMediaRepository;
    }

    @GetMapping
    public List<SocialMedia> getSocialMedia() {
        return socialMediaRepository.findAll();
    }
}
