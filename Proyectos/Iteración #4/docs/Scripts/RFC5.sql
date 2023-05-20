--El uso de alohandes para cada tipo de usuario se mide segun la cantidad de reservas que haya realizado

SELECT A_CLIENTE.TIPOVINCULO, COUNT(A_RESERVA.ID) numReservas 
FROM A_CLIENTE 
LEFT JOIN A_RESERVA ON A_CLIENTE.IDENTIFICACION = A_RESERVA.IDENTIFICACIONCLIENTE 
GROUP BY A_CLIENTE.TIPOVINCULO;