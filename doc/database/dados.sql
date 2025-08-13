create database biblioteca;

use biblioteca;

create table CadastroLivros(
     nome varchar not null,
     isbn int not null,
     primary key (isbn);
)

create table CadastroUsuarios(
    nome varchar not null,
    cpf varchar not null (14),
    int numero not null,
    primary key (cpf);
)


