-- RF6 | RETIRAR UNA OFERTA DE ALOJAMIENTO

-- Alojamiento
DELETE FROM A_Alojamiento WHERE id = ?

-- ApartamentoAlquiler
DELETE FROM A_ApartamentoAlquiler WHERE idalojamiento = ?

-- HabitacionHostal
DELETE FROM A_HabitacionHostal WHERE id = ?

-- ViviendaTemporal
DELETE FROM A_ViviendaTemporal WHERE idalojamiento = ?

-- HabitacionViviendaUniversitaria
DELETE FROM  A_HabitacionViviendaUniversitaria WHERE id = ?

-- HabitacionHuesped
DELETE FROM A_HabitacionHuesped WHERE idalojamiento = ?

-- HabitacionHotel
DELETE FROM A_HabitacionHotel WHERE id = ?

-- Verificacion
SELECT MAX(FechaFin) FROM A_Reserva WHERE IdAlojamiento = ?