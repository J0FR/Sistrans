-- RF1 | REGISTRAR LOS OPERADORES DE ALOJAMIENTO PARA ALOHANDES

-- OperadorUsuario
INSERT INTO  A_OperadorUsuario (identificacion, nombre, tipoVinculo, correoElectronico, telefono) values (?, ?, ?, ?, ?)

-- Hotel
INSERT INTO A_Hotel (regComercio, nit, nombre, restaurante, parqueadero, piscina) values (?, ?, ?, ?, ?, ?)

-- Hostal
INSERT INTO A_Hostal (regComercio, nit, nombre, horaAperturaRecepcion, horaCierreRecepcion) values (?, ?, ?, ?, ?)

-- ViviendaUniversitaria
INSERT INTO A_ViviendaUniversitaria (regComercio, nit, nombre, precioSalaEstudio, precioSalaEsparcimiento, precioGimnasio, restaurante) values (?, ?, ?, ?, ?, ?, ?)