package com.kate.yarnshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.net.URL;

import static com.kate.yarnshop.constants.Constants.*;

@Entity
@Table(name = SOCIAL_MEDIA_TABLE)
@Data
public class SocialMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = NAME_ROW, unique = true, nullable = false)
    private String name;
    @Column(name = LINK_ROW, nullable = false)
    private URL link;
}
