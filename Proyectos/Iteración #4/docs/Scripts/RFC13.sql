WITH criteria AS (
    -- Criterio 1: Reserva alojamientos cada mes durante los tres meses anteriores
    SELECT DISTINCT a_cliente.IDENTIFICACION, 'Reserva mensual' AS CRITERIO
    FROM a_cliente
    JOIN a_reserva ON a_cliente.IDENTIFICACION = a_reserva.IDENTIFICACIONCLIENTE
    WHERE (
        a_reserva.FECHAINI BETWEEN ADD_MONTHS(SYSDATE, -3) AND SYSDATE
        OR a_reserva.FECHAFIN BETWEEN ADD_MONTHS(SYSDATE, -3) AND SYSDATE
        OR (
            EXISTS (
                SELECT 1 
                FROM a_reserva sub_reserva 
                WHERE sub_reserva.IDENTIFICACIONCLIENTE = a_cliente.IDENTIFICACION 
                AND sub_reserva.FECHAINI <= SYSDATE 
                AND sub_reserva.FECHAFIN >= SYSDATE
            )
            OR 
            a_reserva.IDENTIFICACIONCLIENTE IN (
                SELECT identificacioncliente 
                FROM (SELECT identificacioncliente, TRUNC(FECHAINI, 'MM') as MONTH
                      FROM a_reserva)
                GROUP BY identificacioncliente, MONTH
                HAVING COUNT(*) >= 1
            )
        )
    )
    
    UNION ALL
    
    -- Criterio 2: Reserva alojamientos costosos
    SELECT DISTINCT a_cliente.IDENTIFICACION, 'Alojamientos costosos' AS CRITERIO
    FROM a_cliente
    JOIN a_reserva ON a_cliente.IDENTIFICACION = a_reserva.IDENTIFICACIONCLIENTE
    WHERE (
        a_reserva.FECHAINI BETWEEN ADD_MONTHS(SYSDATE, -3) AND SYSDATE
        OR a_reserva.FECHAFIN BETWEEN ADD_MONTHS(SYSDATE, -3) AND SYSDATE
        OR (
            EXISTS (
                SELECT 1 
                FROM a_reserva sub_reserva 
                WHERE sub_reserva.IDENTIFICACIONCLIENTE = a_cliente.IDENTIFICACION 
                AND sub_reserva.FECHAINI <= SYSDATE 
                AND sub_reserva.FECHAFIN >= SYSDATE
                AND sub_reserva.GANANCIA >= 150
            )
            OR 
            a_cliente.IDENTIFICACION IN (
                SELECT a_reserva.IDENTIFICACIONCLIENTE
                FROM a_reserva
                WHERE a_reserva.GANANCIA >= 150
                GROUP BY a_reserva.IDENTIFICACIONCLIENTE
                HAVING AVG(a_reserva.GANANCIA) > 150
            )
        )
    )
    
    UNION ALL
    
    -- Criterio 3: Reserva al menos una vez al mes en suite
    SELECT DISTINCT a_cliente.IDENTIFICACION, 'Reserva en suite' AS CRITERIO
    FROM a_cliente
    JOIN a_reserva ON a_cliente.IDENTIFICACION = a_reserva.IDENTIFICACIONCLIENTE
    JOIN a_alojamiento ON a_reserva.IDALOJAMIENTO = a_alojamiento.ID
    JOIN a_habitacionhotel ON a_alojamiento.ID = a_habitacionhotel.ID
    WHERE (
        a_alojamiento.TIPOALOJAMIENTO = 'HabitacionHotel'
        AND a_habitacionhotel.TIPOHABITACION = 'suite'
        AND (
            a_reserva.FECHAINI BETWEEN ADD_MONTHS(SYSDATE, -3) AND SYSDATE
            OR a_reserva.FECHAFIN BETWEEN ADD_MONTHS(SYSDATE, -3) AND SYSDATE
            OR (
                a_reserva.FECHAINI <= SYSDATE 
                AND a_reserva.FECHAFIN >= SYSDATE
            )
        )
    )
)

SELECT a_cliente.IDENTIFICACION, a_cliente.NOMBRE, a_cliente.TIPOVINCULO, a_cliente.CORREOELECTRONICO, a_cliente.TELEFONO, LISTAGG(criteria.CRITERIO, ', ') WITHIN GROUP (ORDER BY criteria.CRITERIO) AS CRITERIOS
FROM a_cliente
JOIN criteria ON a_cliente.IDENTIFICACION = criteria.IDENTIFICACION
GROUP BY a_cliente.IDENTIFICACION, a_cliente.NOMBRE, a_cliente.TIPOVINCULO, a_cliente.CORREOELECTRONICO, a_cliente.TELEFONO;















































WITH criteria AS (
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
)

SELECT 
    a_cliente.IDENTIFICACION, 
    a_cliente.NOMBRE, 
    a_cliente.TIPOVINCULO, 
    a_cliente.CORREOELECTRONICO, 
    a_cliente.TELEFONO, 
    LISTAGG(criteria.CRITERIO, ', ') WITHIN GROUP (ORDER BY criteria.CRITERIO) AS CRITERIOS
FROM 
    a_cliente
    JOIN criteria ON a_cliente.IDENTIFICACION = criteria.IDENTIFICACION
WHERE
    criteria.CRITERIO IS NOT NULL
GROUP BY 
    a_cliente.IDENTIFICACION, 
    a_cliente.NOMBRE, 
    a_cliente.TIPOVINCULO, 
    a_cliente.CORREOELECTRONICO, 
    a_cliente.TELEFONO
FETCH FIRST 100 ROWS ONLY    ;
