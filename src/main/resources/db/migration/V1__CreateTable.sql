CREATE TABLE if not exists users (
    id UUID PRIMARY KEY NOT NULL,

    apelido varchar(32) NOT NULL UNIQUE,
    nome varchar(100) NOT NULL,
    nascimento date NOT NULL,
    stack varchar(32) ARRAY,

    userIndex text
);