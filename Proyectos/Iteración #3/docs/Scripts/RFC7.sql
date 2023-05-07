SELECT
  MAX(DECODE(ranking_reservas, 1, year)) AS year_max_reservas,
  MAX(DECODE(ranking_reservas, 1, month)) AS month_max_reservas,
  MAX(DECODE(ranking_ganancias, 1, year)) AS year_max_ganancias,
  MAX(DECODE(ranking_ganancias, 1, month)) AS month_max_ganancias,
  MIN(DECODE(ranking_reservas, total_months, year)) AS year_min_reservas,
  MIN(DECODE(ranking_reservas, total_months, month)) AS month_min_reservas
FROM (
  SELECT
    EXTRACT(YEAR FROM A_RESERVA.FECHAINI) AS year,
    EXTRACT(MONTH FROM A_RESERVA.FECHAINI) AS month,
    COUNT(A_RESERVA.ID) AS total_reservas,
    SUM(GANANCIA) AS total_ganancias,
    DENSE_RANK() OVER (ORDER BY COUNT(A_RESERVA.ID) DESC) AS ranking_reservas,
    DENSE_RANK() OVER (ORDER BY SUM(GANANCIA) DESC) AS ranking_ganancias,
    COUNT(*) OVER () AS total_months
  FROM A_RESERVA
  JOIN A_Alojamiento ON A_RESERVA.IDALOJAMIENTO = A_Alojamiento.ID
  WHERE A_RESERVA.FECHAFIN - A_RESERVA.FECHAINI < ? AND A_Alojamiento.TIPOALOJAMIENTO = ?
  GROUP BY EXTRACT(YEAR FROM A_RESERVA.FECHAINI), EXTRACT(MONTH FROM A_RESERVA.FECHAINI)
);


