package com.kate.yarnshop.entity;

import lombok.Data;

import javax.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;

import static com.kate.yarnshop.constants.Constants.*;

@Entity
@Table(name = FORUM_MESSAGES_TABLE)
@Data
public class ForumMessages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID, nullable = false, unique = true)
    private Long id;
    @ManyToOne(targetEntity = User.class)
    private User user;
    @Column(name = MESSAGE_ROW)
    private String message;
    @Column(name = DATE_ROW)
    private Timestamp date;
}
