--Caso en el que todo sale correctamente:

--Primero se limpian los datos


DELETE FROM A_HOTEL;
DELETE FROM A_HABITACIONHOTEL;
DELETE FROM A_CLIENTE;
DELETE FROM A_RESERVA;
DELETE FROM A_ALOJAMIENTO;
DELETE FROM A_ALOJAMIENTOSERVICIO;
DELETE FROM A_APARTAMENTOALQUILER;
DELETE FROM A_HABITACIONHOSTAL;
DELETE FROM A_HABITACIONHUESPED;
DELETE FROM A_HABITACIONVIVIENDAUNIVERSITARIA;
DELETE FROM A_HOSTAL;
DELETE FROM A_OPERADORUSUARIO;
DELETE FROM A_SERVICIO;
DELETE FROM A_VIVIENDATEMPORAL;
DELETE FROM A_VIVIENDAUNIVERSITARIA;
Commit;

--Caso en el que todo sale correctamente para una sola reserva
Insert into A_HOTEL (REGCOMERCIO,NIT,NOMBRE,RESTAURANTE,PARQUEADERO,PISCINA) values ('900','139270435','Kunde Inc','N','N','N');
Insert into A_ALOJAMIENTO (ID,UBICACION,DURACIONMIN,COSTO,ESTATUS) values ('1','oeste','1','99', 'Y');
Insert into A_ALOJAMIENTO (ID,UBICACION,DURACIONMIN,COSTO,ESTATUS) values ('2','oeste','1','99', 'Y');
Insert into A_HABITACIONHOTEL (ID,TIPOHABITACION,TAMANIO,IDHOTEL) values ('1','suite','grande','900');
Insert into A_HABITACIONHOTEL (ID,TIPOHABITACION,TAMANIO,IDHOTEL) values ('2','suite','grande','900');
Insert into A_CLIENTE (IDENTIFICACION,NOMBRE,TIPOVINCULO,CORREOELECTRONICO,TELEFONO,ULTIMAFECHARESERVA) values ('100','Hiram','estudiante','heborall2r@nyu.edu','1579959169',to_date('03/12/22','DD/MM/RR'));
Insert into A_RESERVA (ID,FECHAINI,FECHAFIN,IDENTIFICACIONCLIENTE,IDALOJAMIENTO) values ('1',to_date('12/01/24','DD/MM/RR'),to_date('12/06/24','DD/MM/RR'),'100','1');
COMMIT;

--Caso para varias reservas a un solo alojamiento

Insert into A_HOTEL (REGCOMERCIO,NIT,NOMBRE,RESTAURANTE,PARQUEADERO,PISCINA) values ('900','139270435','Kunde Inc','N','N','N');
Insert into A_ALOJAMIENTO (ID,UBICACION,DURACIONMIN,COSTO,ESTATUS,TIPOALOJAMIENTO) values ('1','oeste','1','99', 'Y','HabitacionHotel');
Insert into A_ALOJAMIENTO (ID,UBICACION,DURACIONMIN,COSTO,ESTATUS,TIPOALOJAMIENTO) values ('2','oeste','1','99', 'Y','HabitacionHotel');
Insert into A_ALOJAMIENTO (ID,UBICACION,DURACIONMIN,COSTO,ESTATUS,TIPOALOJAMIENTO) values ('3','oeste','1','99', 'Y','HabitacionHotel');
Insert into A_ALOJAMIENTO (ID,UBICACION,DURACIONMIN,COSTO,ESTATUS,TIPOALOJAMIENTO) values ('4','oeste','1','99', 'Y','HabitacionHotel');
Insert into A_HABITACIONHOTEL (ID,TIPOHABITACION,TAMANIO,IDHOTEL) values ('1','suite','grande','900');
Insert into A_HABITACIONHOTEL (ID,TIPOHABITACION,TAMANIO,IDHOTEL) values ('2','suite','grande','900');
Insert into A_HABITACIONHOTEL (ID,TIPOHABITACION,TAMANIO,IDHOTEL) values ('3','suite','grande','900');
Insert into A_HABITACIONHOTEL (ID,TIPOHABITACION,TAMANIO,IDHOTEL) values ('4','suite','grande','900');
Insert into A_CLIENTE (IDENTIFICACION,NOMBRE,TIPOVINCULO,CORREOELECTRONICO,TELEFONO,ULTIMAFECHARESERVA, SALDO) values ('100','Hiram','estudiante','heborall2r@nyu.edu','1579959169',to_date('03/12/22','DD/MM/RR'),'0');
Insert into A_RESERVA (ID,FECHAINI,FECHAFIN,IDENTIFICACIONCLIENTE,IDALOJAMIENTO,ESTADO, IDGRUPO, GANANCIA) values ('1',to_date('12/01/24','DD/MM/RR'),to_date('12/02/24','DD/MM/RR'),'100','1','Y', '100','0');
Insert into A_RESERVA (ID,FECHAINI,FECHAFIN,IDENTIFICACIONCLIENTE,IDALOJAMIENTO,ESTADO, IDGRUPO,GANANCIA) values ('2',to_date('12/03/24','DD/MM/RR'),to_date('12/04/24','DD/MM/RR'),'100','1','Y','200','0');
Insert into A_RESERVA (ID,FECHAINI,FECHAFIN,IDENTIFICACIONCLIENTE,IDALOJAMIENTO,ESTADO, IDGRUPO, GANANCIA) values ('3',to_date('12/05/24','DD/MM/RR'),to_date('12/06/24','DD/MM/RR'),'100','1','Y','300','0');
COMMIT;

Insert into A_HOTEL (REGCOMERCIO,NIT,NOMBRE,RESTAURANTE,PARQUEADERO,PISCINA) values ('900','139270435','Kunde Inc','N','N','N');
Insert into A_ALOJAMIENTO (ID,UBICACION,DURACIONMIN,COSTO,ESTATUS,TIPOALOJAMIENTO) values ('1','oeste','1','99', 'Y','HabitacionHotel');
Insert into A_ALOJAMIENTO (ID,UBICACION,DURACIONMIN,COSTO,ESTATUS,TIPOALOJAMIENTO) values ('2','oeste','1','99', 'Y','HabitacionHotel');
Insert into A_ALOJAMIENTO (ID,UBICACION,DURACIONMIN,COSTO,ESTATUS,TIPOALOJAMIENTO) values ('3','oeste','1','99', 'Y','HabitacionHotel');
Insert into A_ALOJAMIENTO (ID,UBICACION,DURACIONMIN,COSTO,ESTATUS,TIPOALOJAMIENTO) values ('4','oeste','1','99', 'Y','HabitacionHotel');
Insert into A_HABITACIONHOTEL (ID,TIPOHABITACION,TAMANIO,IDHOTEL) values ('1','suite','grande','900');
Insert into A_HABITACIONHOTEL (ID,TIPOHABITACION,TAMANIO,IDHOTEL) values ('2','suite','grande','900');
Insert into A_HABITACIONHOTEL (ID,TIPOHABITACION,TAMANIO,IDHOTEL) values ('3','suite','grande','900');
Insert into A_HABITACIONHOTEL (ID,TIPOHABITACION,TAMANIO,IDHOTEL) values ('4','suite','grande','900');
Insert into A_CLIENTE (IDENTIFICACION,NOMBRE,TIPOVINCULO,CORREOELECTRONICO,TELEFONO,ULTIMAFECHARESERVA, SALDO) values ('100','Hiram','estudiante','heborall2r@nyu.edu','1579959169',to_date('03/12/22','DD/MM/RR'),'0');
Insert into A_RESERVA (ID,FECHAINI,FECHAFIN,IDENTIFICACIONCLIENTE,IDALOJAMIENTO,ESTADO, IDGRUPO, GANANCIA) values ('1',to_date('12/01/24','DD/MM/RR'),to_date('12/02/24','DD/MM/RR'),'100','1','Y', '100','0');
Insert into A_RESERVA (ID,FECHAINI,FECHAFIN,IDENTIFICACIONCLIENTE,IDALOJAMIENTO,ESTADO, IDGRUPO,GANANCIA) values ('2',to_date('12/06/24','DD/MM/RR'),to_date('12/07/24','DD/MM/RR'),'100','1','Y','200','0');
Insert into A_RESERVA (ID,FECHAINI,FECHAFIN,IDENTIFICACIONCLIENTE,IDALOJAMIENTO,ESTADO, IDGRUPO, GANANCIA) values ('3',to_date('12/03/24','DD/MM/RR'),to_date('12/04/24','DD/MM/RR'),'100','1','Y','300','0');
Insert into A_RESERVA (ID,FECHAINI,FECHAFIN,IDENTIFICACIONCLIENTE,IDALOJAMIENTO,ESTADO, IDGRUPO, GANANCIA) values ('4',to_date('12/03/24','DD/MM/RR'),to_date('12/04/24','DD/MM/RR'),'100','4','Y','300','0');
COMMIT;

SELECT 'DELETE FROM '  ||table_name|| ';' 
FROM user_tables;

DELETE FROM A_HOTEL;
DELETE FROM A_ALOJAMIENTO;
DELETE FROM A_HABITACIONHOTEL;
DELETE FROM A_CLIENTE;
DELETE FROM A_RESERVA;
DELETE FROM A_ALOJAMIENTOSERVICIO;
DELETE FROM A_APARTAMENTOALQUILER;
DELETE FROM A_HABITACIONHOSTAL;
DELETE FROM A_HABITACIONHUESPED;
DELETE FROM A_HABITACIONVIVIENDAUNIVERSITARIA;
DELETE FROM A_HOSTAL;
DELETE FROM A_OPERADORUSUARIO;
DELETE FROM A_SERVICIO;
DELETE FROM A_VIVIENDATEMPORAL;
DELETE FROM A_VIVIENDAUNIVERSITARIA;