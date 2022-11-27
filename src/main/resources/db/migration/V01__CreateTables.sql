create TABLE student
(
    id         bigint auto_increment PRIMARY KEY,
    name       VARCHAR(200) NOT NULL,
    birthday   DATE,
    created    TIMESTAMP,
    email      VARCHAR(60),
    gender     VARCHAR(20)  NOT NULL,
    height     DOUBLE       NOT NULL,
    weight     DOUBLE       NOT NULL,
    student_id VARCHAR(20)
);