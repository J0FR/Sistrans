-- RFC4 | MOSTRAR LOS ALOJAMIENTOS DISPONIBLES EN UN RANGO DE FECHAS, QUE CUMPLEN CON UN CONJUNTO DE REQUERIMIENTOS SERVICIOS
SELECT DISTINCT A_ALOJAMIENTO.ID ID_0, A_ALOJAMIENTO.UBICACION UBICACION, A_ALOJAMIENTO.COSTO COSTO
FROM ((A_ALOJAMIENTO LEFT JOIN A_RESERVA ON A_ALOJAMIENTO.ID = A_RESERVA.IDALOJAMIENTO)
    LEFT JOIN A_ALOJAMIENTOSERVICIO ON A_ALOJAMIENTOSERVICIO.IDALOJAMIENTO = A_ALOJAMIENTO.ID)
    LEFT JOIN A_SERVICIO ON A_ALOJAMIENTOSERVICIO.IDSERVICIO = A_SERVICIO.ID
WHERE A_ALOJAMIENTO.ID NOT IN (Select A_RESERVA.IDALOJAMIENTO
                                FROM A_RESERVA
                                WHERE ('01/MAY/2023' BETWEEN FECHAINI AND FECHAFIN
                                        OR '11/MAY/2023' BETWEEN FECHAINI AND FECHAFIN) AND (ESTADO = 'Y'))
                                        
    AND A_ALOJAMIENTO.ESTATUS = 'Y'
--    AND A_SERVICIO.TIPO IN('tvCable')
GROUP BY A_ALOJAMIENTO.ID,UBICACION, A_ALOJAMIENTO.COSTO, FECHAINI, FECHAFIN, ESTADO, A_ALOJAMIENTO.TIPOALOJAMIENTO

HAVING ((('01/MAY/2023' < COALESCE(A_RESERVA.FECHAINI, TO_DATE('11-11-1111', 'DD-MM-YYYY')) 
    AND '11/MAY/2023' < COALESCE(A_RESERVA.FECHAINI, TO_DATE('11-11-1111', 'DD-MM-YYYY')))
    OR
    ('01/MAY/2023' > COALESCE(A_RESERVA.FECHAFIN, TO_DATE('11-11-1111', 'DD-MM-YYYY')) 
    AND '11/MAY/2023' > COALESCE(A_RESERVA.FECHAFIN, TO_DATE('11-11-1111', 'DD-MM-YYYY')))) OR (ESTADO = 'N')) AND A_ALOJAMIENTO.TIPOALOJAMIENTO = 'HabitacionHotel';