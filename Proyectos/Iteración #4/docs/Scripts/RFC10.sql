SELECT A_CLIENTE.IDENTIFICACION IDENTIFICACION,
A_CLIENTE.NOMBRE NOMBRE,
A_CLIENTE.TIPOVINCULO TIPOVINCULO,
A_CLIENTE.CORREOELECTRONICO CORREOELECTRONICO,
A_CLIENTE.TELEFONO TELEFONO,
A_RESERVA.ID ID,
A_RESERVA.FECHAINI FECHAINI,
A_RESERVA.FECHAFIN FECHAFIN,
A_RESERVA.IDALOJAMIENTO IDALOJAMIENTO,
A_RESERVA.ESTADO ESTADO
FROM A_CLIENTE, A_RESERVA, A_ALOJAMIENTO
WHERE A_RESERVA.IDALOJAMIENTO = A_ALOJAMIENTO.ID
    AND A_CLIENTE.IDENTIFICACION = A_RESERVA.IDENTIFICACIONCLIENTE 
    AND A_RESERVA.FECHAINI BETWEEN '01/01/2023' AND '11/06/2023' 
    AND A_RESERVA.FECHAFIN BETWEEN '01/01/2023' AND '11/06/2023'
    AND ROWNUM <= 100;
GROUP BY A_CLIENTE.IDENTIFICACION ,
A_CLIENTE.NOMBRE ,
A_CLIENTE.TIPOVINCULO ,
A_CLIENTE.CORREOELECTRONICO ,
A_CLIENTE.TELEFONO ,
A_RESERVA.ID ,
A_RESERVA.FECHAINI ,
A_RESERVA.FECHAFIN ,
A_RESERVA.IDALOJAMIENTO ,
A_RESERVA.ESTADO ,
A_ALOJAMIENTO.TIPOALOJAMIENTO
ORDER BY A_CLIENTE.IDENTIFICACION
FETCH FIRST 100 ROWS ONLY;



SELECT A_CLIENTE.IDENTIFICACION IDENTIFICACION,
A_CLIENTE.NOMBRE NOMBRE,
A_CLIENTE.TIPOVINCULO TIPOVINCULO,
A_CLIENTE.CORREOELECTRONICO CORREOELECTRONICO,
A_CLIENTE.TELEFONO TELEFONO,
A_RESERVA.ID ID,
A_RESERVA.FECHAINI FECHAINI,
A_RESERVA.FECHAFIN FECHAFIN,
A_RESERVA.IDALOJAMIENTO IDALOJAMIENTO,
A_RESERVA.ESTADO ESTADO
FROM A_CLIENTE, A_RESERVA, A_ALOJAMIENTO
WHERE A_RESERVA.IDALOJAMIENTO = A_ALOJAMIENTO.ID
    AND A_CLIENTE.IDENTIFICACION = A_RESERVA.IDENTIFICACIONCLIENTE 
    AND A_RESERVA.FECHAINI BETWEEN ? AND ?
    AND A_RESERVA.FECHAFIN BETWEEN ? AND ?
    AND A_ALOJAMIENTO.IDOPERADOR = ?
    AND ROWNUM <= 100;
GROUP BY A_CLIENTE.IDENTIFICACION ,
A_CLIENTE.NOMBRE ,
A_CLIENTE.TIPOVINCULO ,
A_CLIENTE.CORREOELECTRONICO ,
A_CLIENTE.TELEFONO ,
A_RESERVA.ID ,
A_RESERVA.FECHAINI ,
A_RESERVA.FECHAFIN ,
A_RESERVA.IDALOJAMIENTO ,
A_RESERVA.ESTADO ,
A_ALOJAMIENTO.TIPOALOJAMIENTO
ORDER BY ?
FETCH FIRST 100 ROWS ONLY;
