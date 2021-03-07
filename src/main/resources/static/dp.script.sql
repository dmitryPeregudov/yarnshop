CREATE TABLE IF NOT EXISTS ROLES
(
    ID   INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    NAME VARCHAR(255)                   NOT NULL
);

INSERT INTO ROLES
VALUES (1, 'admin'),
       (2, 'customer'),
       (3, 'seller');


CREATE TABLE IF NOT EXISTS USERS
(
    ID            INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    LOGIN
                  VARCHAR(255)                   NOT NULL,
    PASSWORD      VARCHAR(255)                   NOT NULL,
    NAME          VARCHAR(255)                   NOT NULL,
    MIDDLE_NAME   VARCHAR(255)                   NOT NULL,
    SURNAME       VARCHAR(255)                   NOT NULL,
    ADDRESS       VARCHAR(500)                   NOT NULL,
    EMAIL         VARCHAR(255)                   NOT NULL,
    POST          VARCHAR(255)                   NOT NULL,
    DATE_OF_BIRTH DATE,
    ROLE          INT                            NOT NULL,
    FOREIGN KEY (ROLE) REFERENCES ROLES (ID)
);

create table if not exists PRODUCT_TYPES
(
    ID   INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    NAME VARCHAR(255)                   NOT NULL,
    INFO VARCHAR(1000)
);

INSERT INTO PRODUCT_TYPES
values (1, 'Ангора', null),
       (2, 'Альпака', null),
       (3, 'Шерсть', null),
       (4, 'Меринос', null),
       (5, 'Летняя пряжа', null);

create table IF NOT EXISTS PRODUCTS
(
    ID           INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    PRODUCT_TYPE INT                            NOT NULL,
    FOREIGN KEY (PRODUCT_TYPE) REFERENCES PRODUCT_TYPES (ID),
    DESCRIPTION  VARCHAR(1000)                  NOT NULL,
    PRICE        DOUBLE                         NOT NULL,
    WEIGHT       INT                            NOT NULL,
    THICKNESS    INT                            NOT NULL,
    COLOR        VARCHAR(100)                   NOT NULL,
    PICTURE      VARCHAR(255)                   NOT NULL
);

CREATE TABLE IF NOT EXISTS SHOP_CONTACTS
(
    NAME        VARCHAR(255) NOT NULL,
    PHONE       VARCHAR(255) NOT NULL,
    DESCRIPTION VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS SOCIAL_MEDIA
(
    NAME VARCHAR(255) NOT NULL,
    LINK VARCHAR(255) NOT NULL
);

CREATE TABLE ORDER_STATUSES
(
    ID   INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    NAME VARCHAR(255)
);

INSERT INTO ORDER_STATUSES
VALUES (1, 'PROCESSING'),
       (2, 'SENT'),
       (3, 'DONE');

CREATE TABLE IF NOT EXISTS ORDERS
(
    ID         BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    USER_ID    INT                               not null,
    FOREIGN KEY (USER_ID) references USERS (ID),
    ORDER_INFO JSON                              NOT NULL,
    STATUS     INT,
    FOREIGN KEY (STATUS) REFERENCES ORDER_STATUSES (ID)
);