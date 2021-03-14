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
    LOGIN         VARCHAR(255) unique            NOT NULL,
    PASSWORD      VARCHAR(255)                   NOT NULL,
    NAME          VARCHAR(255)                   NOT NULL,
    MIDDLE_NAME   VARCHAR(255)                   NOT NULL,
    SURNAME       VARCHAR(255)                   NOT NULL,
    ADDRESS       VARCHAR(500)                   NOT NULL,
    EMAIL         VARCHAR(255) unique            NOT NULL,
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
    PICTURE      VARCHAR(255)                   NOT NULL,
    YARN_TYPE    VARCHAR(255)                   NOT NULL
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
       (3, 'DONE'),
       (4, 'CANCELLED');

CREATE TABLE IF NOT EXISTS ORDERED_PRODUCT_AND_QUANTITY
(
    LINE_ID         BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    ORDER_ID BIGINT NOT NULL, FOREIGN KEY(ORDER_ID) REFERENCES ORDERS(ID),
    PRODUCT_ID INT                               NOT NULL,
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCTS (ID),
    QUANTITY   INT                               NOT NULL
);

CREATE TABLE IF NOT EXISTS ORDERS
(
    ID         BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    USER_ID    INT                               not null,
    FOREIGN KEY (USER_ID) references USERS (ID),
    STATUS     INT,
    FOREIGN KEY (STATUS) REFERENCES ORDER_STATUSES (ID)
);

insert INTO PRODUCTS
VALUES (1, 1, 'Ангора арт. Angora 80 . Итальянская бобинная пряжа', 370.00, 100, 80, 'белый', 'img/angora.jpg',
        'Ангора'),
       (2, 1, 'Ангора арт. Angora 80 . Итальянская бобинная пряжа', 370.00, 100, 80, 'розовый', 'img/angora2.jpg',
        'Ангора'),
       (3, 1, 'Ангора арт. Angora 80 . Итальянская бобинная пряжа', 370.00, 100, 80, 'синий', 'img/angora3.jpg',
        'Ангора'),
       (4, 1, 'Ангора арт. Angora 80 . Итальянская бобинная пряжа', 370.00, 100, 80, 'зеленый', 'img/angora4.jpg',
        'Ангора'),
       (5, 1, 'Ангора арт. Angora 80 . Итальянская бобинная пряжа', 370.00, 100, 80, 'салатовый', 'img/angora5.jpg',
        'Ангора'),
       (6, 2, 'Меринос с беби альпакой. Итальянская бобинная пряжа', 120.00, 100, 80, 'черный', 'img/Alpaca.jpg',
        'Альпака'),
       (7, 2, 'Итальянская бобинная пряжа. Смесовки с альпакой', 120.00, 100, 80, 'берюзовый', 'img/Alpaca2.jpg',
        'Альпака'),
       (8, 2, 'Итальянская бобинная пряжа. Смесовки с альпакой', 120.00, 100, 80, 'черный', 'img/Alpaca3.jpg',
        'Альпака'),
       (9, 2, 'Итальянская бобинная пряжа. Смесовки с альпакой', 120.00, 100, 80, 'синий', 'img/Alpaca4.jpg',
        'Альпака'),
       (10, 2, 'Итальянская бобинная пряжа. Смесовки с альпакой', 120.00, 100, 80, 'фиолетовый', 'img/Alpaca5.jpg',
        'Альпака'),
       (11, 2, 'Итальянская бобинная пряжа. Смесовки с альпакой', 120.00, 100, 80, 'черный', 'img/Alpaca6.jpg',
        'Альпака'),
       (12, 3, 'Полушерсть Forty-4. Итальянская бобинная пряжа', 68.00, 100, 80, 'синий', 'img/Wool_big.jpg', 'Шерсть'),
       (13, 3, 'Полушерсть арт. Australia. Итальянская бобинная пряжа', 68.00, 100, 80, 'белый', 'img/Wool2_big.jpg',
        'Шерсть'),
       (14, 3, 'Полушерсть арт. Australia. Итальянская бобинная пряжа', 63.00, 100, 80, 'рыжий', 'img/Wool3_big.jpg',
        'Шерсть'),
       (15, 3, 'Полушерсть арт. Australia. Итальянская бобинная пряжа', 63.00, 100, 80, 'белый', 'img/Wool4_big.jpg',
        'Шерсть'),
       (16, 4, 'Шерсть. Итальянская бобинная пряжа', 63.00, 100, 80, 'серый', 'img/Wool5_big.jpg', 'Шерсть'),
       (17, 4, 'Меринос Piper. Итальянская бобинная пряжа', 80.00, 100, 80, 'черный', 'img/Merino_big.jpg', 'Меринос'),
       (18, 4, 'Меринос New Jersey. Итальянская бобинная пряжа', 80.00, 100, 80, 'черный', 'img/Merino2_big.jpg',
        'Меринос'),
       (19, 4, 'Меринос арт. Harmony. Итальянская бобинная пряжа', 80.00, 100, 80, 'розовый', 'img/Merino3_big.jpg',
        'Меринос'),
       (20, 4, 'Меринос арт. Harmony. Итальянская бобинная пряжа', 80.00, 100, 80, 'черный', 'img/Merino4_big.jpg',
        'Меринос'),
       (21, 4, 'Меринос Clipper. Итальянская бобинная пряжа', 85.00, 100, 80, 'хаки', 'img/Merino5_big.jpg', 'Меринос'),
       (22, 4, 'Меринос New Jersey Итальянская бобинная пряжа', 85.00, 100, 80, 'серый', 'img/Merino6_big.jpg',
        'Меринос'),
       (23, 5, 'Хлопок. Penye.Турецкая бобинная пряжа', 50.00, 100, 80, 'розовый', 'img/Yarn_big.jpg', 'Хлопок'),
       (24, 5, 'Хлопок. Tricot R.Garanti Iplik.Турецкая бобинная пряжа', 50.00, 100, 80, 'берюзовый',
        'img/Yarn2_big.jpg', 'Хлопок'),
       (25, 5, 'Лен Equinox. Итальянская бобинная пряжа', 65.00, 100, 80, 'черный', 'img/Yarn3_big.jpg', 'Лён'),
       (26, 5, 'Шёлк 100 % арт. Dragon. Итальянская бобинная пряжа', 150.00, 100, 80, 'белый', 'img/Yarn4_big.jpg',
        'Шёлк'),
       (27, 5, 'Шёлк арт. Shappe. Итальянская бобинная пряжа', 150.00, 100, 80, 'красный', 'img/Yarn5_big.jpg', 'Шёлк');
