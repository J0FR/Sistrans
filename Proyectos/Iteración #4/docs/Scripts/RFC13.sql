SELECT 
    a_cliente.IDENTIFICACION, 
    a_cliente.NOMBRE, 
    a_cliente.TIPOVINCULO, 
    a_cliente.CORREOELECTRONICO, 
    a_cliente.TELEFONO, 
    LISTAGG(criteria.CRITERIO, ', ') WITHIN GROUP (ORDER BY criteria.CRITERIO) AS CRITERIOS
FROM 
    a_cliente
    JOIN (
        SELECT DISTINCT 
            a_cliente.IDENTIFICACION, 
            CASE 
                WHEN COUNT(DISTINCT TRUNC(a_reserva.FECHAINI, 'MM')) >= 3 THEN 'Reserva mensual'
                WHEN AVG(a_reserva.GANANCIA) > 150 THEN 'Alojamientos costosos'
                WHEN COUNT(DISTINCT CASE WHEN a_alojamiento.TIPOALOJAMIENTO = 'HabitacionHotel' AND a_habitacionhotel.TIPOHABITACION = 'suite' THEN TRUNC(a_reserva.FECHAINI, 'MM') END) >= 1 THEN 'Reserva en suite'
                ELSE NULL
            END AS CRITERIO
        FROM 
            a_cliente
            JOIN a_reserva ON a_cliente.IDENTIFICACION = a_reserva.IDENTIFICACIONCLIENTE
            JOIN a_alojamiento ON a_reserva.IDALOJAMIENTO = a_alojamiento.ID
            LEFT JOIN a_habitacionhotel ON a_alojamiento.ID = a_habitacionhotel.ID
        WHERE
            a_reserva.FECHAINI BETWEEN ADD_MONTHS(SYSDATE, -3) AND SYSDATE
            OR a_reserva.FECHAFIN BETWEEN ADD_MONTHS(SYSDATE, -3) AND SYSDATE
        GROUP BY 
            a_cliente.IDENTIFICACION
    ) criteria ON a_cliente.IDENTIFICACION = criteria.IDENTIFICACION
WHERE
    criteria.CRITERIO IS NOT NULL
GROUP BY 
    a_cliente.IDENTIFICACION, 
    a_cliente.NOMBRE, 
    a_cliente.TIPOVINCULO, 
    a_cliente.CORREOELECTRONICO, 
    a_cliente.TELEFONO
FETCH FIRST 100 ROWS ONLY    ;
