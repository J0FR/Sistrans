-- Pruebas A_Alojamiento
-- 1. Pruebas de unicidad de tuplas.
    -- a. Inserte una tupla 1 con una PK conocida y nueva
INSERT INTO A_Alojamiento (ID, Ubicacion, DuracionMin, Costo)
VALUES (99999, 'Bogota, Colombia', 1, 500);
    -- b. Inserte una tupla 2, con la misma PK que la tupla 1
INSERT INTO A_Alojamiento (ID, Ubicacion, DuracionMin, Costo)
VALUES (99999, 'Cali, Colombia', 1, 250);
    -- c. Haga las pruebas de resultados de la inserción del primer registro y del segundo registro.
-- 2. Pruebas de integridad con FK | No tiene FK
-- 3. Pruebas de integridad de acuerdo con restricciones de chequeo
    -- a. Inserte tuplas que cumplen con las restricciones de chequeo establecidas
INSERT INTO A_Alojamiento (ID, Ubicacion, DuracionMin, Costo)
VALUES (99998, 'Barranquilla, Colombia', 1, 300);
INSERT INTO A_Alojamiento (ID, Ubicacion, DuracionMin, Costo)
VALUES (99997, 'Huila, Colombia', 1, 10000);
    -- b. Inserte tuplas que violan las restricciones de chequeo establecidas
INSERT INTO A_Alojamiento (ID, Ubicacion, DuracionMin, Costo)
VALUES (99996, 'Barranquilla, Colombia', 1, -1500);
INSERT INTO A_Alojamiento (ID, Ubicacion, DuracionMin, Costo)
VALUES (99995, 'Huila, Colombia', 0, 10000);
    -- c. Haga las pruebas de inserción y borrado correspondientes.
INSERT INTO A_Alojamiento (ID, Ubicacion, DuracionMin, Costo)
VALUES (99994, 'Putumayo, Colombia', 1, 2434);
INSERT INTO A_Alojamiento (ID, Ubicacion, DuracionMin, Costo)
VALUES (99993, 'Choco, Colombia', 1, 4542);
DELETE FROM A_Alojamiento WHERE ID=99994;
DELETE FROM A_Alojamiento WHERE ID=99993;



-- Pruebas A_AlojamientoServicio
-- 1. Pruebas de unicidad de tuplas. Para cada tabla,
    -- a. Inserte una tupla 1 con una PK conocida y nueva
INSERT INTO A_AlojamientoServicio (IDAlojamiento, IDServicio, Costo)
VALUES (99999, 1, 244);
    -- b. Inserte una tupla 2, con la misma PK que la tupla 1
INSERT INTO A_AlojamientoServicio (IDAlojamiento, IDServicio, Costo)
VALUES (99999, 1, 244);
    -- c. Haga las pruebas de resultados de la inserción del primer registro y del segundo registro.
-- 2. Pruebas de integridad con FK
    -- a. Inserte una tupla 1 que tenga una FK que se encuentra en la tabla referenciada
INSERT INTO A_AlojamientoServicio (IDAlojamiento, IDServicio, Costo)
VALUES (99999, 1, 244);
    -- b. Inserte una tupla 1 que tenga una FK que no se encuentra en la tabla referenciada
INSERT INTO A_AlojamientoServicio (IDAlojamiento, IDServicio, Costo)
VALUES (999990, 1, 244);
INSERT INTO A_AlojamientoServicio (IDAlojamiento, IDServicio, Costo)
VALUES (99999, 34, 244);
    -- c. Haga pruebas de borrado de tuplas maestras y dependientes.
DELETE FROM A_Alojamiento WHERE ID=99999;
DELETE FROM A_AlojamientoServicio WHERE IDAlojamiento=99999;
-- 3. Pruebas de integridad de acuerdo con restricciones de chequeo
    -- a. Inserte tuplas que cumplen con las restricciones de chequeo establecidas
INSERT INTO A_AlojamientoServicio (IDAlojamiento, IDServicio, Costo)
VALUES (99998, 5, 345);
INSERT INTO A_AlojamientoServicio (IDAlojamiento, IDServicio, Costo)
VALUES (99998, 2, 75);
    -- b. Inserte tuplas que violan las restricciones de chequeo establecidas
INSERT INTO A_AlojamientoServicio (IDAlojamiento, IDServicio, Costo)
VALUES (99998, 2, -15);
    -- c. Haga las pruebas de inserción y borrado correspondientes.
INSERT INTO A_AlojamientoServicio (IDAlojamiento, IDServicio, Costo)
VALUES (99998, 14, 345);
INSERT INTO A_AlojamientoServicio (IDAlojamiento, IDServicio, Costo)
VALUES (99998, 13, 534);
DELETE FROM A_AlojamientoServicio WHERE IDAlojamiento=99998;



-- Pruebas A_OperadorUsuario
-- 1. Pruebas de unicidad de tuplas. Para cada tabla,
    -- a. Inserte una tupla 1 con una PK conocida y nueva
INSERT INTO A_OperadorUsuario (Identificacion, Nombre, TipoVinculo, CorreoElectronico, Telefono)
VALUES ('1001977635', 'Juan', 'estudiante', 'alpha@uniandes.edu.co', '3168472948');
    -- b. Inserte una tupla 2, con la misma PK que la tupla 1
INSERT INTO A_OperadorUsuario (Identificacion, Nombre, TipoVinculo, CorreoElectronico, Telefono)
VALUES ('1001977635', 'Pepe', 'profesor', 'alpha@uniandes.edu.co', '3168472948');
    -- c. Haga las pruebas de resultados de la inserción del primer registro y del segundo registro.
-- 2. Pruebas de integridad con FK | No tiene FK
-- 3. Pruebas de integridad de acuerdo con restricciones de chequeo
    -- a. Inserte tuplas que cumplen con las restricciones de chequeo establecidas
INSERT INTO A_OperadorUsuario (Identificacion, Nombre, TipoVinculo, CorreoElectronico, Telefono)
VALUES ('1001977634', 'Juan Pepe', 'egresado', 'alpha2@uniandes.edu.co', '3168472958');
INSERT INTO A_OperadorUsuario (Identificacion, Nombre, TipoVinculo, CorreoElectronico, Telefono)
VALUES ('1001977633', 'Juan Fino', 'externo', 'alpha3@uniandes.edu.co', '3168472558');
    -- b. Inserte tuplas que violan las restricciones de chequeo establecidas
INSERT INTO A_OperadorUsuario (Identificacion, Nombre, TipoVinculo, CorreoElectronico, Telefono)
VALUES ('1001977633', 'Juan Fino', 'elChacho', 'alpha4@uniandes.edu.co', '3168472558');
    -- c. Haga las pruebas de inserción y borrado correspondientes.
INSERT INTO A_OperadorUsuario (Identificacion, Nombre, TipoVinculo, CorreoElectronico, Telefono)
VALUES ('1001977632', 'Juan Melo', 'profesor', 'alpha5@uniandes.edu.co', '3168472558');
INSERT INTO A_OperadorUsuario (Identificacion, Nombre, TipoVinculo, CorreoElectronico, Telefono)
VALUES ('1001977631', 'Juan Palito', 'empleado', 'alpha6uniandes.edu.co', '3168472558');
DELETE FROM A_OperadorUsuario WHERE Identificacion='1001977631';
DELETE FROM A_OperadorUsuario WHERE Identificacion='1001977632';



-- Pruebas A_ApartamentoAlquiler
-- 1. Pruebas de unicidad de tuplas. Para cada tabla,
    -- a. Inserte una tupla 1 con una PK conocida y nueva
INSERT INTO A_ApartamentoAlquiler (IDAlojamiento, ServPublico, Administracion, IdentificacionOperadorUsuario)
VALUES (99998, 'Y', 'N', '1001977635');
    -- b. Inserte una tupla 2, con la misma PK que la tupla 1
INSERT INTO A_ApartamentoAlquiler (IDAlojamiento, ServPublico, Administracion, IdentificacionOperadorUsuario)
VALUES (99998, 'N', 'N', '1001977634');
    -- c. Haga las pruebas de resultados de la inserción del primer registro y del segundo registro.
-- 2. Pruebas de integridad con FK
    -- a. Inserte una tupla 1 que tenga una FK que se encuentra en la tabla referenciada
INSERT INTO A_ApartamentoAlquiler (IDAlojamiento, ServPublico, Administracion, IdentificacionOperadorUsuario)
VALUES (99998, 'Y', 'Y', '1001977635');
    -- b. Inserte una tupla 1 que tenga una FK que no se encuentra en la tabla referenciada
INSERT INTO A_ApartamentoAlquiler (IDAlojamiento, ServPublico, Administracion, IdentificacionOperadorUsuario)
VALUES (999982, 'Y', 'N', '1001977635');
INSERT INTO A_ApartamentoAlquiler (IDAlojamiento, ServPublico, Administracion, IdentificacionOperadorUsuario)
VALUES (99998, 'Y', 'N', '10019776345');
    -- c. Haga pruebas de borrado de tuplas maestras y dependientes.
DELETE FROM A_Alojamiento WHERE ID=99998;
DELETE FROM A_OperadorUsuario WHERE Identificacion='1001977635';
DELETE FROM A_ApartamentoAlquiler WHERE IDAlojamiento=99998;
-- 3. Pruebas de integridad de acuerdo con restricciones de chequeo
    -- a. Inserte tuplas que cumplen con las restricciones de chequeo establecidas
INSERT INTO A_ApartamentoAlquiler (IDAlojamiento, ServPublico, Administracion, IdentificacionOperadorUsuario)
VALUES (99998, 'Y', 'N', '1001977635');
    -- b. Inserte tuplas que violan las restricciones de chequeo establecidas
INSERT INTO A_ApartamentoAlquiler (IDAlojamiento, ServPublico, Administracion, IdentificacionOperadorUsuario)
VALUES (99998, 'YES', 'N', '1001977635');
INSERT INTO A_ApartamentoAlquiler (IDAlojamiento, ServPublico, Administracion, IdentificacionOperadorUsuario)
VALUES (99998, 'Y', 'NO', '1001977635');
    -- c. Haga las pruebas de inserción y borrado correspondientes.
INSERT INTO A_ApartamentoAlquiler (IDAlojamiento, ServPublico, Administracion, IdentificacionOperadorUsuario)
VALUES (99998, 'Y', 'N', '1001977635');
DELETE FROM A_ApartamentoAlquiler WHERE IDAlojamiento=99998;
