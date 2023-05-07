SELECT *
FROM A_RESERVA
WHERE (A_RESERVA.IDENTIFICACIONCLIENTE = (SELECT A_RESERVA.IDENTIFICACIONCLIENTE
                                        FROM A_RESERVA
                                        WHERE (A_RESERVA.IDALOJAMIENTO = '45')
                                        GROUP BY A_RESERVA.IDENTIFICACIONCLIENTE
                                        HAVING COUNT(A_RESERVA.IDENTIFICACIONCLIENTE) >= 3)
        OR A_RESERVA.IDENTIFICACIONCLIENTE = (SELECT A_RESERVA.IDENTIFICACIONCLIENTE
                    FROM A_RESERVA
                    WHERE A_RESERVA.IDALOJAMIENTO = '45'
                    GROUP BY A_RESERVA.IDENTIFICACIONCLIENTE
                    HAVING 15 <= SUM(A_RESERVA.FECHAFIN - A_RESERVA.FECHAINI)))
        AND A_RESERVA.IDALOJAMIENTO = '45'