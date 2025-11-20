drop database systenez;
create database systenez;
use systenez;

create table usuario (
    id int not null auto_increment,
    nome varchar(100) not null,
    senha varchar(255) not null,
    tipo varchar(20) not null,
    primary key (id)
);


CREATE TABLE produto (
    id int not null auto_increment,
    nome varchar(100) not null,
    preco_custo decimal(10, 2) not null,
    preco_venda decimal(10, 2) not null,
    categoria varchar(50),
    quantidade int not null,
    primary key (id)
);

CREATE TABLE venda (
    id int not null auto_increment,
    quantidade_vendida int not null,
    data_venda date not null,
    produto_id int not null,
    primary key(id),
    foreign key (produto_id) references produto(id) on delete cascade
);

set SQL_SAFE_UPDATES = 0;

