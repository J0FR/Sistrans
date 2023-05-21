create sequence alohandes_sequence;

SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;

CREATE TABLE A_OPERADORUSUARIO
   (IDENTIFICACION VARCHAR2(255 BYTE), 
    NOMBRE VARCHAR2(255 BYTE) NOT NULL,
    TIPOVINCULO VARCHAR2(255 BYTE) NOT NULL,
    CORREOELECTRONICO VARCHAR2(255 BYTE) NOT NULL,  
    TELEFONO VARCHAR2(255 BYTE) NOT NULL, 
	CONSTRAINT A_OPERADORUSUARIO_PK PRIMARY KEY (IDENTIFICACION));

ALTER TABLE A_OPERADORUSUARIO
	ADD CONSTRAINT CK_OPERADORUSUARIO
	CHECK(TIPOVINCULO IN ('profesor', 'empleado', 'egresado', 'estudiante', 'padre de estudiante', 'externo'))
ENABLE;

CREATE TABLE A_CLIENTE
   (IDENTIFICACION VARCHAR2(255 BYTE), 
    NOMBRE VARCHAR2(255 BYTE) NOT NULL,
    TIPOVINCULO VARCHAR2(255 BYTE) NOT NULL ,
    CORREOELECTRONICO VARCHAR2(255 BYTE) NOT NULL,
	TELEFONO VARCHAR2(255 BYTE) NOT NULL,
	ULTIMAFECHARESERVA DATE NOT NULL,
	SALDO NUMBER NOT NULL,
	CONSTRAINT A_CLIENTE_PK PRIMARY KEY (IDENTIFICACION));

ALTER TABLE A_CLIENTE
	ADD CONSTRAINT CK_CLIENTE_TIPOVINCULO
	CHECK(TIPOVINCULO IN ('profesor', 'empleado', 'egresado', 'estudiante', 'padre de estudiante', 'profesor invitado', 'persona evento uniandes'))
ENABLE;

CREATE TABLE A_ALOJAMIENTO
   (ID NUMBER, 
    UBICACION VARCHAR2(255 BYTE) NOT NULL,
    DURACIONMIN NUMBER NOT NULL CHECK(DURACIONMIN > 0),
    COSTO NUMBER NOT NULL CHECK(COSTO >= 0), 
	ESTATUS VARCHAR2(255 BYTE) NOT NULL,
	TIPOALOJAMIENTO VARCHAR2(255 BYTE) NOT NULL,
	CONSTRAINT A_ALOJAMIENTO_PK PRIMARY KEY (ID));

ALTER TABLE A_ALOJAMIENTO
	ADD CONSTRAINT CK_ALOJAMIENTO_TIPOALOJAMIENTO
	CHECK(TIPOALOJAMIENTO IN ('HabitacionHuesped', 'ApartamentoAlquiler', 'ViviendaTemporal', 'HabitacionHotel', 'HabitacionViviendaUniversitaria', 'HabitacionHostal'))
ENABLE;

ALTER TABLE A_ALOJAMIENTO
	ADD CONSTRAINT CK_ALOJAMIENTO_ESTATUS
	CHECK(ESTATUS IN ('Y', 'N'))
ENABLE;

CREATE TABLE A_HABITACIONHUESPED
   (IDALOJAMIENTO NUMBER, 
    COMIDAS NUMBER NOT NULL CHECK(COMIDAS >= 0),
    TIPOBANIO VARCHAR2(255 BYTE) NOT NULL,
    TIPOHABITACION VARCHAR2(255 BYTE) NOT NULL,
    DTOMESEXTRA NUMBER NOT NULL CHECK(DTOMESEXTRA > 0 AND DTOMESEXTRA <= 100), 
	IDENTIFICACIONOPERADORUSUARIO VARCHAR2(255 BYTE), 
	CONSTRAINT A_HABITACIONHUESPED_PK PRIMARY KEY (IDALOJAMIENTO));

ALTER TABLE A_HABITACIONHUESPED
ADD CONSTRAINT fk_a_alojamiento_habitacionhuesped
    FOREIGN KEY (idalojamiento)
    REFERENCES a_alojamiento(id)
ENABLE;

ALTER TABLE A_HABITACIONHUESPED
ADD CONSTRAINT fk_habitacionhuesped_operadorusuario
    FOREIGN KEY (IDENTIFICACIONOPERADORUSUARIO)
    REFERENCES a_operadorusuario(identificacion)
ENABLE;

ALTER TABLE A_HABITACIONHUESPED
	ADD CONSTRAINT CK_HABITACIONHUESPED_TIPOBANIO
	CHECK(TIPOBANIO IN ('privado', 'compartido'))
ENABLE;

ALTER TABLE A_HABITACIONHUESPED
	ADD CONSTRAINT CK_HABITACIONHUESPED_TIPOHABITACION
	CHECK(TIPOHABITACION IN ('individual', 'compartido'))
ENABLE;


CREATE TABLE A_APARTAMENTOALQUILER
   (IDALOJAMIENTO NUMBER, 
    SERVPUBLICO VARCHAR2(255 BYTE) NOT NULL,  
    ADMINISTRACION VARCHAR2(255 BYTE) NOT NULL, 
	IDENTIFICACIONOPERADORUSUARIO VARCHAR2(255 BYTE), 
	CONSTRAINT A_APARTAMENTOALQUILER_PK PRIMARY KEY (IDALOJAMIENTO));

ALTER TABLE A_APARTAMENTOALQUILER
ADD CONSTRAINT fk_a_alojamiento_apartamentoalquiler
    FOREIGN KEY (idalojamiento)
    REFERENCES a_alojamiento(id)
ENABLE;

ALTER TABLE A_APARTAMENTOALQUILER
ADD CONSTRAINT fk_apartamentoalquiler_operadorusuario
    FOREIGN KEY (IDENTIFICACIONOPERADORUSUARIO)
    REFERENCES a_operadorusuario(identificacion)
ENABLE;

ALTER TABLE A_APARTAMENTOALQUILER
	ADD CONSTRAINT CK_APARTAMENTOALQUILER_SERVPUBLICO
	CHECK(SERVPUBLICO IN ('Y', 'N'))
ENABLE;

ALTER TABLE A_APARTAMENTOALQUILER
	ADD CONSTRAINT CK_APARTAMENTOALQUILER_ADMINISTRACION
	CHECK(ADMINISTRACION IN ('Y', 'N'))
ENABLE;

CREATE TABLE A_VIVIENDATEMPORAL
   (IDALOJAMIENTO NUMBER, 
    NUMEROHABITACIONES NUMBER NOT NULL,  
    PRECIOSEGUROARRENDAMIENTO NUMBER NOT NULL, 
    CARACTSEGURO VARCHAR2(255 BYTE) NOT NULL,
    DIASALQUILADO NUMBER NOT NULL,
	IDENTIFICACIONOPERADORUSUARIO VARCHAR2(255 BYTE) NOT NULL, 
	CONSTRAINT A_VIVIENDATEMPORAL_PK PRIMARY KEY (IDALOJAMIENTO));

ALTER TABLE A_VIVIENDATEMPORAL
ADD CONSTRAINT fk_a_alojamiento_viviendatemporal
    FOREIGN KEY (idalojamiento)
    REFERENCES a_alojamiento(id)
ENABLE;

ALTER TABLE A_VIVIENDATEMPORAL
ADD CONSTRAINT fk_viviendatemporal_operadorusuario
    FOREIGN KEY (IDENTIFICACIONOPERADORUSUARIO)
    REFERENCES a_operadorusuario(identificacion)
ENABLE;

ALTER TABLE A_VIVIENDATEMPORAL
	ADD CONSTRAINT CK_VIVIENDATEMPORAL_NUMEROHABITACIONES
	CHECK(NUMEROHABITACIONES > 0)
ENABLE;

ALTER TABLE A_VIVIENDATEMPORAL
	ADD CONSTRAINT CK_VIVIENDATEMPORAL_PRECIOSEGUROARRENDAMIENTO
	CHECK(PRECIOSEGUROARRENDAMIENTO > 0)
ENABLE;

ALTER TABLE A_VIVIENDATEMPORAL
	ADD CONSTRAINT CK_VIVIENDATEMPORAL_DIASALQUILADO
	CHECK(DIASALQUILADO >= 0 AND DIASALQUILADO <= 30)
ENABLE;


CREATE TABLE A_RESERVA
   (ID NUMBER, 
    FECHAINI DATE NOT NULL,
    FECHAFIN DATE NOT NULL,  
    IDENTIFICACIONCLIENTE VARCHAR2(255 BYTE) NOT NULL, 
    IDALOJAMIENTO NUMBER NOT NULL,
    ESTADO VARCHAR2(255 BYTE) NOT NULL, 
	IDGRUPO NUMBER NOT NULL,
	GANANCIA NUMBER NOT NULL,
	NUMOCUPAMIENTO NUMBER NOT NULL,
	CONSTRAINT A_RESERVA_PK PRIMARY KEY (ID));

ALTER TABLE A_RESERVA
ADD CONSTRAINT fk_a_cliente_reserva
    FOREIGN KEY (identificacioncliente)
    REFERENCES a_cliente(identificacion)
ENABLE;

ALTER TABLE A_RESERVA
ADD CONSTRAINT fk_a_alojamiento_reserva
    FOREIGN KEY (idalojamiento)
    REFERENCES a_alojamiento(id)
ENABLE;

ALTER TABLE A_RESERVA
	ADD CONSTRAINT CK_RESERVA_ESTADO
	CHECK(ESTADO IN ('Y', 'N'))
ENABLE;

CREATE TABLE A_SERVICIO
	(
		ID NUMBER NOT NULL,
		TIPO VARCHAR2(255 BYTE) NOT NULL,
		CONSTRAINT PK_SERVICIO PRIMARY KEY (ID)
	);

ALTER TABLE A_SERVICIO
	ADD CONSTRAINT CK_SERVICIO_TIPO
	CHECK(TIPO IN ('salaEsparcimiento', 'tvCable', 'gimnasio', 'salaEstudio', 'wifi', 'baniera', 'sala', 'yacuzzi', 'menaje', 'luz', 'telefono', 'cocina', 'agua'))
ENABLE;

CREATE TABLE A_HOTEL
	(
		REGCOMERCIO VARCHAR2(255 BYTE),
		NIT VARCHAR2(255 BYTE) NOT NULL,
		NOMBRE VARCHAR2(255 BYTE) NOT NULL,
		RESTAURANTE VARCHAR2(255 BYTE) NOT NULL,
		PARQUEADERO VARCHAR2(255 BYTE) NOT NULL,
		PISCINA VARCHAR2(255 BYTE) NOT NULL,
		CONSTRAINT PK_HOTEL PRIMARY KEY (REGCOMERCIO)
	);

ALTER TABLE A_HOTEL
	ADD CONSTRAINT CK_HOTEL_RESTAURANTE
	CHECK(RESTAURANTE IN ('Y', 'N'))
ENABLE;

ALTER TABLE A_HOTEL
	ADD CONSTRAINT CK_HOTEL_PARQUEADERO
	CHECK(PARQUEADERO IN ('Y', 'N'))
ENABLE;

ALTER TABLE A_HOTEL
	ADD CONSTRAINT CK_HOTEL_PISCINA
	CHECK(PISCINA IN ('Y', 'N'))
ENABLE;

CREATE TABLE A_VIVIENDAUNIVERSITARIA
	(
		REGCOMERCIO VARCHAR2(255 BYTE),
		NIT VARCHAR2(255 BYTE) NOT NULL,
		NOMBRE VARCHAR2(255 BYTE) NOT NULL,
        PRECIOSALAESTUDIO NUMBER NOT NULL,
		PRECIOSALAESPARCIMIENTO NUMBER NOT NULL,
		PRECIOGIMNASIO NUMBER NOT NULL,
		RESTAURANTE VARCHAR2(255 BYTE) NOT NULL,
		CONSTRAINT PK_VIVIENDAUNIVERSITARIA PRIMARY KEY (REGCOMERCIO)
	);

ALTER TABLE A_VIVIENDAUNIVERSITARIA
	ADD CONSTRAINT CK_VIVIENDAUNIVERSITARIA_RESTAURANTE
	CHECK(RESTAURANTE IN ('Y', 'N'))
ENABLE;

CREATE TABLE A_HOSTAL
(
	REGCOMERCIO VARCHAR2(255 BYTE),
	NIT VARCHAR2(255 BYTE) NOT NULL,
	NOMBRE VARCHAR2(255 BYTE) NOT NULL,
	HORAAPERTURARECEPCION VARCHAR2(255 BYTE) NOT NULL,
	HORACIERRERECEPCION VARCHAR2(255 BYTE) NOT NULL,
	CONSTRAINT PK_HOSTAL PRIMARY KEY (REGCOMERCIO)
);

CREATE TABLE A_HABITACIONHOTEL
	(
		ID NUMBER NOT NULL,
		TIPOHABITACION VARCHAR2(255 BYTE) NOT NULL,
		TAMANIO VARCHAR2(255 BYTE) NOT NULL,
		IDHOTEL VARCHAR2(255 BYTE) NOT NULL,
		CONSTRAINT PK_HABITACIONHOTEL PRIMARY KEY (ID)
	);

ALTER TABLE A_HABITACIONHOTEL
ADD CONSTRAINT FK_HH_ALOJAMIENTO
FOREIGN KEY (ID) REFERENCES A_ALOJAMIENTO(ID);

ALTER TABLE A_HABITACIONHOTEL
ADD CONSTRAINT FK_HH_HOTEL
FOREIGN KEY (IDHOTEL) REFERENCES A_HOTEL(REGCOMERCIO);

ALTER TABLE A_HABITACIONHOTEL
ADD CONSTRAINT CK_TIPOHABITACION 
CHECK (TIPOHABITACION IN ('estandar', 'semisuite', 'suite'));

CREATE TABLE A_HABITACIONVIVIENDAUNIVERSITARIA
	(
		ID NUMBER NOT NULL,
		TIPOHABITACION VARCHAR2(255 BYTE) NOT NULL,
		CAPACIDAD NUMBER NOT NULL,
		IDVIVIENDAUNIVERSITARIA VARCHAR2(255 BYTE) NOT NULL,
		CONSTRAINT PK_HABITACIONVIVIENDAUNIVERSITARIA PRIMARY KEY (ID)
	);

ALTER TABLE A_HABITACIONVIVIENDAUNIVERSITARIA
ADD CONSTRAINT FK_HVU_ALOJAMIENTO
FOREIGN KEY (ID) REFERENCES A_ALOJAMIENTO(ID);

ALTER TABLE A_HABITACIONVIVIENDAUNIVERSITARIA
ADD CONSTRAINT FK_HVU_VIVIENDAUNIVERSITARIA
FOREIGN KEY (IDVIVIENDAUNIVERSITARIA) REFERENCES A_VIVIENDAUNIVERSITARIA(REGCOMERCIO);

ALTER TABLE A_HABITACIONVIVIENDAUNIVERSITARIA
ADD CONSTRAINT CK_TIPOHABITACION_HABITACIONVIVIENDAUNIVERSITARIA
CHECK (TIPOHABITACION IN('individual','compartido'));

CREATE TABLE A_HABITACIONHOSTAL
	(
		ID NUMBER NOT NULL,
		AFORO NUMBER NOT NULL,
		IDHOSTAL VARCHAR2(255 BYTE) NOT NULL,
		CONSTRAINT PK_HABITACIONHOSTAL PRIMARY KEY (ID)
	);

ALTER TABLE A_HABITACIONHOSTAL
ADD CONSTRAINT FK_HHOS_ALOJAMIENTO
FOREIGN KEY (ID) REFERENCES A_ALOJAMIENTO(ID);

ALTER TABLE A_HABITACIONHOSTAL
ADD CONSTRAINT FK_HHOS_HOSTAL
FOREIGN KEY (IDHOSTAL) REFERENCES A_HOSTAL(REGCOMERCIO);

ALTER TABLE A_HABITACIONHOSTAL
ADD CONSTRAINT CK_AFORO
CHECK (AFORO >0);

CREATE TABLE A_ALOJAMIENTOSERVICIO
	(
		IDALOJAMIENTO NUMBER,
		IDSERVICIO NUMBER,
		COSTO NUMBER NOT NULL CHECK (COSTO >= 0),
		
		CONSTRAINT PK_EMPRESASERVICIO PRIMARY KEY (IDALOJAMIENTO, IDSERVICIO)
	);

ALTER TABLE A_ALOJAMIENTOSERVICIO
ADD CONSTRAINT FK_AL_ALOJAMIENTOSERVICIO
    FOREIGN KEY (IDALOJAMIENTO)
    REFERENCES A_ALOJAMIENTO(ID)
ENABLE;
    
ALTER TABLE A_ALOJAMIENTOSERVICIO
ADD CONSTRAINT FK_SERV_ALOJAMIENTOSERVICIO
    FOREIGN KEY (IDSERVICIO)
    REFERENCES A_SERVICIO(ID)
ENABLE;

COMMIT;