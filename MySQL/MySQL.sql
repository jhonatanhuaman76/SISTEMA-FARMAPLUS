
DROP DATABASE IF EXISTS BD_FARMAPLUS;

CREATE DATABASE BD_FARMAPLUS; 
USE BD_FARMAPLUS;

CREATE TABLE CLIENTE(
COD_CLIENTE 		INT AUTOINCREMENT,
);

CREATE TABLE MARCA(
COD_MARCA VARCHAR(10),
NOMBRE_MARCA VARCHAR(30) NOT NULL,
PRIMARY KEY(COD_MARCA)
);

CREATE TABLE LABORATORIO(
COD_LABORATORIO VARCHAR(10),
NOMBRE_LABORATORIO VARCHAR(30) NOT NULL,
PRIMARY KEY (COD_LABORATORIO)
);

CREATE TABLE CATEGORIA(
NUM_CATEGORIA INT AUTO_INCREMENT,
PRIMARY KEY(NUM_CATEGORIA)
);

CREATE TABLE UNIDAD_MEDIDA(
COD_UNIDAD INT,  /TODO ES SOLO UNA REFERENCIA, LUEGO SE MODIFICA/
UNIDAD_MEDIDA VARCHAR(20) NOT NULL,
DESCRIPCION VARCHAR(50) NOT NULL,
PRIMARY KEY (COD_UNIDAD) 
);

CREATE TABLE PRODUCTO(
COD_PROD VARCHAR(10),
NUM_CATEGORIA INT,
LABORATORIO VARCHAR(10),
NOMBRE_PRODUCTO VARCHAR(30),
PRECIO_UNIT_COMPRA DECIMAL(10,2),
PRECIO_UNIT_VENTA DECIMAL(10,2),
STOCK_MIN INT,
STOCK_MAC INT,
MARCA VARCHAR(30),
/M_CONTROLADO(BIT)/
PRIMARY KEY (COD_PROD),
FOREIGN KEY(NUM_CATEGORIA) REFERENCES CATEGORIA(NUM_CATEGORIA),
FOREIGN KEY(LABORATORIO) REFERENCES LABORATORIO(COD_LABORATORIO)
);

CREATE TABLE PRESENTACION (
COD_UNIDAD INT,
COD_PRODUCTO VARCHAR(10),
FOREIGN KEY(COD_UNIDAD) REFERENCES UNIDAD_MEDIDA(COD_UNIDAD),
FOREIGN KEY(COD_PRODUCTO) REFERENCES PRODUCTO(COD_PROD),
PRIMARY KEY(COD_UNIDAD, COD_PRODUCTO)
);