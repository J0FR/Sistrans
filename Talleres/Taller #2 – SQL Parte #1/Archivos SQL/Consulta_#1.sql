ALTER SESSION SET CURRENT_SCHEMA = PARRANDEROS;

SELECT Bares1.ciudad AS nombre_ciudad,
       (SELECT COUNT(*)
        FROM Bares Bares2
        WHERE Bares2.ciudad = Bares1.ciudad AND Bares2.presupuesto = 'Alto') AS numero_bares_alto,
       (SELECT COUNT(*)
        FROM Bares Bares3
        WHERE Bares3.ciudad = Bares1.ciudad AND Bares3.presupuesto = 'Bajo') AS numero_bares_bajo
FROM Bares Bares1
GROUP BY Bares1.ciudad;