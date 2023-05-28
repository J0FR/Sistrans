WITH semanas AS (
  SELECT TO_DATE('01/01/2023', 'DD/MM/YYYY') + ((ROWNUM - 1) * 7) AS semana
  FROM dual
  CONNECT BY ROWNUM <= 52
)
SELECT s.semana, COUNT(r.fechaini) AS cantidad_reservas,
       MAX(r.NUMOCUPAMIENTO) AS max_ocupacion,
       (SELECT r2.IDalojamiento
        FROM a_reserva r2
        WHERE TRUNC(r2.fechaini, 'IW') = TRUNC(s.semana, 'IW')
        ORDER BY r2.NUMOCUPAMIENTO DESC
        FETCH FIRST 1 ROW ONLY) AS max_id_alojamiento,
        MIN(R.NUMOCUPAMIENTO),
        (SELECT r2.IDalojamiento
        FROM a_reserva r2
        WHERE TRUNC(r2.fechaini, 'IW') = TRUNC(s.semana, 'IW')
        ORDER BY r2.NUMOCUPAMIENTO ASC
        FETCH FIRST 1 ROW ONLY) AS min_id_alojamiento
FROM semanas s
LEFT JOIN a_reserva r ON TRUNC(r.fechaini, 'IW') = TRUNC(s.semana, 'IW')
GROUP BY s.semana
ORDER BY s.semana;