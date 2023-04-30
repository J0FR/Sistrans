-- RF2 | REGISTRAR PROPUESTAS DE ALOJAMIENTOS PARA ALOHANDES

-- Alojamiento
INSERT INTO A_Alojamiento (id, ubicacion, duracionMin, costo) values (?, ?, ?, ?)

-- ApartamentoAlquiler
INSERT INTO A_ApartamentoAlquiler (idAlojamiento, servPublico, administracion, identificacionOperadorUsuario) values (?, ?, ?, ?)

-- HabitacionHostal
INSERT INTO A_HabitacionHostal (id, aforo, idHostal) values (?, ?, ?)

-- ViviendaTemporal
INSERT INTO A_ViviendaTemporal (idAlojamiento, numeroHabitaciones, precioSeguroArrendamiento, caractSeguro, diasAlquilado, identificacionOperadorUsuario) values (?, ?, ?, ?, ?, ?)

-- HabitacionViviendaUniversitaria
INSERT INTO A_HabitacionViviendaUniversitaria (id, tipoHabitacion, capacidad, idViviendaUniversitaria) values (?, ?, ?, ?)

-- HabitacionHuesped
INSERT INTO A_HabitacionHuesped (idAlojamiento, comidas, tipoBanio, tipoHabitacion, dtoMesExtra, identificacionOperadorUsuario) values (?, ?, ?, ?, ?, ?)

-- HabitacionHotel
INSERT INTO A_HabitacionHotel (id, tipoHabitacion, tamanio, idHotel) values (?, ?, ?, ?)