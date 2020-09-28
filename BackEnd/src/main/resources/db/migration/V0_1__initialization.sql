DROP TABLE IF EXISTS "user_table";

create table user_table
(
    id                SERIAL,
    name              varchar(50) NOT NULL,
    lastName          varchar(50) NOT NULL,
    email             varchar(50) NOT NULL,
    birthDate         timestamp   NOT NULL,
    PRIMARY KEY (id)
);