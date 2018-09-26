drop database if exists usuarios;
create database usuarios;
use usuarios;
create table usuario(
nombre     varchar(20),
contra   varchar(20),
rango       varchar(20)
);
insert into usuario values('Admin','1234','Administrador');
insert into usuario values('User','4321','Usuario');
