ALTER SESSION SET CURRENT_SCHEMA = PARRANDEROS;

SELECT
    Bares1.nombre AS nombre_bar,
    Bares1.ciudad AS ciudad,
    COUNT(*) AS num_bebidas
FROM
    Bares Bares1
JOIN Sirven Sirven1 ON Bares1.id = Sirven1.id_bar
JOIN Bebidas Bebidas1 ON Sirven1.id_bebida = Bebidas1.id
WHERE
    Bares1.presupuesto = 'Alto' AND
    Bebidas1.grado_alcohol > 10
GROUP BY Bares1.nombre, Bares1.ciudad
HAVING
    COUNT(*) BETWEEN 5 AND 10
ORDER BY
    Bares1.ciudad ASC,
    Bares1.nombre ASC,
    num_bebidas ASC;