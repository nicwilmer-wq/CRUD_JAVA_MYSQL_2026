create database bd_productos;
use bd_productos;

create table productos(
ID int auto_increment primary key not null,
nombreProducto varchar (50),
descripcionProducto varchar (100),
precioProducto decimal (55)
);


select * from productos;



