package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;
import java.util.List;
import org.apache.log4j.Logger;
import com.google.gson.JsonObject;
import uniandes.isis2304.parranderos.persistencia.PersistenciaAlohandes;

public class Alohandes {
	/*
	 * ****************************************************************
	 * Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(Alohandes.class.getName());

	/*
	 * ****************************************************************
	 * Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaAlohandes pa;

	/*
	 * ****************************************************************
	 * Métodos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public Alohandes() {
		pa = PersistenciaAlohandes.getInstance();
	}

	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * 
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad
	 *                    de persistencia
	 */
	public Alohandes(JsonObject tableConfig) {
		pa = PersistenciaAlohandes.getInstance(tableConfig);
	}

	/**
	 * Cierra la conexión con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia() {
		pa.cerrarUnidadPersistencia();
	}

	/*
	 * ****************************************************************
	 * Métodos para manejar los OPERADOR USUARIO
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un tipo de bebida
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public OperadorUsuario adicionarOperadorUsuario(String identificacion, String nombre, String tipoVinculo,
			String correoElectronico, String telefono) {
		log.info("Adicionando Tipo de operador usuario: " + nombre);
		OperadorUsuario operadorUsuario = pa.adicionarOperadorUsuario(identificacion, nombre, tipoVinculo,
				correoElectronico, telefono);
		log.info("Adicionando Tipo de operador usuario: " + operadorUsuario);
		return operadorUsuario;
	}

	/**
	 * Encuentra el operadorUsuario en Alohandes con el identificador solicitado
	 * Adiciona entradas al log de la aplicación
	 * @param identificacion - El identificador del operadorUsuario
	 * @return Un objeto operadorUsuario 
	 */
	public OperadorUsuario darOperadorUsuarioPorIdentificacion(String identificacion)
	{
		log.info ("Buscando operadorUsuario por identificador: " + identificacion);
		OperadorUsuario tb = pa.darOperadorUsuarioPorIdentificacion(identificacion);
		log.info ("Buscando operadorUsuario por Identificador: " + tb != null ? tb : "NO EXISTE");
		return tb;
	}

	/**
	 * Encuentra todos los proveedores con sus respectivas ganancias del anio y del anio corrido
	 * @return Una lista de parejas [ID_PROVEEDOR, DINERO_RECIBIDO_ANIO_ACTUAL, DINERO_RECIBIDO_ANIO_CORRIDO]
	 */
	public List<Object []> darDineroRecibido ()
	{
        log.info ("Listando dinero recibido anio actual y anio corrido");
        List<Object []> tuplas = pa.darDineroRecibido();
        log.info ("Listando dinero recibido anio actual y anio corrido: Listo!");
        return tuplas;
	}

	/*
	 * ****************************************************************
	 * Métodos para manejar los CLIENTES
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente un cliente
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param identificacion    - El identificador del cliente
	 * @param nombre            - El nombre del cliente
	 * @param tipoVinculo       - El tipo de vinculo del cliente
	 * @param correoElectronico - El correo electronico del cliente
	 * @param telefono          - El telefono del cliente
	 * @return El objeto Cliente adicionado. null si ocurre alguna Excepción
	 */
	public Cliente adicionarCliente(String identificacion, String nombre, String tipoVinculo, String correoElectronico,
			String telefono, Timestamp ultimaFechaReserva) {
		log.info("Adicionando Cliente: " + nombre);
		Cliente cliente = pa.adicionarCliente(identificacion, nombre, tipoVinculo, correoElectronico, telefono, ultimaFechaReserva);
		log.info("Adicionando Cliente: " + cliente);
		return cliente;
	}

	/**
	 * Encuentra el Cliente en Alohandes con el identificador solicitado
	 * Adiciona entradas al log de la aplicación
	 * @param identificacion - El identificador del Cliente
	 * @return Un objeto Cliente 
	 */
	public Cliente darClientePorIdentificacion(String identificacion)
	{
		log.info ("Buscando cliente por identificador: " + identificacion);
		Cliente tb = pa.darClientePorIdentificacion(identificacion);
		log.info ("Buscando cliente por Identificador: " + tb != null ? tb : "NO EXISTE");
		return tb;
	}

	/**
	 * Cambia la ciudad de un cliente dado su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param identificacion - El identificador del cliente que va a cambiar de ciudad
	 * @param ultimaFechaReserva - La nueva ciudad del cliente
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que un cliente con ese identificador no existe
	 */
	public long cambiarCiudadBebedor (String identificacion, Timestamp ultimaFechaReserva)
	{
        log.info ("Cambiando ultimaFechaReserva de cliente: " + identificacion);
        long cambios = pa.cambiarUltimaFechaReservaBebedor(identificacion, ultimaFechaReserva);
        return cambios;
	}

	/*
	 * ****************************************************************
	 * Métodos para manejar las RESERVAS
	 *****************************************************************/

	public Reserva adicionarReserva(Timestamp fechaInicio, Timestamp fechaFin, String identificacionCliente, long idAlojamiento) {
		log.info("Adicionando reserva del cliente " + identificacionCliente + " al alojamiento " + idAlojamiento);
		Reserva reserva = pa.adicionarReserva(fechaInicio, fechaFin, identificacionCliente, idAlojamiento);
		log.info("Adicionando reserva del cliente " + identificacionCliente + " al alojamiento " + idAlojamiento);
		return reserva;
	}

	/**
	 * Elimina una reserva por su identificador
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param idReserva - El identificador de la reserva
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarReservaPorId(long idReserva) {
		log.info("Eliminando reserva por id: " + idReserva);
		long resp = pa.eliminarReservaPorId(idReserva);
		log.info("Eliminando reserva por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Actualizar estado de una reserva por su identificador
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param estado - El estado de la reserva
	 * @param idReserva - El identificador de la reserva
	 * @return El id de confirmacion de la actualizacion 
	 */
	public long actualizarEstadoReservaPorIdReserva(String estado, long idReserva) {
		log.info("Actualizando reserva por id: " + idReserva);
		long resp = pa.actualizarEstadoReservaPorIdReserva(estado, idReserva);
		log.info("Actualizando reserva por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra el Reserva en Alohandes con el id solicitado
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del Reserva
	 * @return Un objeto Reserva 
	 */
	public Reserva darReservaPorId(long id)
	{
		log.info ("Buscando Reserva por id: " + id);
		Reserva tb = pa.darReservaPorId(id);
		log.info ("Buscando Reserva por id: " + tb != null ? tb : "NO EXISTE");
		return tb;
	}

	/**
	 * Encuentra el Reserva en Alohandes con el id solicitado
	 * Adiciona entradas al log de la aplicación
	 * @param idAlojamiento - El idAlojamiento del Reserva
	 * @return Un objeto Reserva 
	 */
	public Timestamp darUltimaFechaPorIdAlojamiento(long idAlojamiento)
	{
		log.info ("Buscando ultimaFechaReserva por idAlojamiento: " + idAlojamiento);
		Timestamp tb = pa.darUltimaFechaPorIdAlojamiento(idAlojamiento);
		log.info ("Buscando ultimaFechaReserva por idAlojamiento: " + tb != null ? tb : "NO EXISTE");
		return tb;
	}

	/**
	 * Encuentra todas las reservas que tengan es idAlojamiento
	 * @param idAlojamiento - El id del alojamiento al que corresponde la reserva
	 * @return Una lista de objetos Reserva con todos las reservas que tienen ese idAlojamiento
	 */
	public List<Reserva>  darReservasPorIdAlojamiento(long idAlojamiento)
	{
		log.info ("Buscando reservas por id alojamiento: " + idAlojamiento);
		List<Reserva> reservas = pa.darReservasPorIdAlojamiento(idAlojamiento);
		log.info ("Buscando reservas por id alojamiento: " + reservas.size() + " reservas encontradas" );
		return reservas;
	}


	/*
	 * ****************************************************************
	 * Métodos para manejar los HOTELES
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un tipo de bebida
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param regComercio - El regComercio del Hotel
	 * @param nit         - El nit del Hotel
	 * @param nombre      - La nombre del Hotel
	 * @param ganancias   - Las ganancias del Hotel
	 * @param restaurante - Si tiene restaurante el Hotel
	 * @param parqueadero - Si tiene parqueadero el Hotel
	 * @param piscina     - Si tiene piscina el Hotel
	 * @return El objeto hotel adicionado. null si ocurre alguna Excepción
	 */
	public Hotel adicionarHotel(String regComercio, String nit, String nombre, String restaurante,
			String parqueadero, String piscina) {
		log.info("Adicionando Hotel: " + nombre);
		Hotel hotel = pa.adicionarHotel(regComercio, nit, nombre, restaurante, parqueadero, piscina);
		log.info("Adicionando Hotel: " + hotel);
		return hotel;
	}

	/*
	 * ****************************************************************
	 * Métodos para manejar los HOSTALES
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un tipo de bebida
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param regComercio           - El regComercio del Hostal
	 * @param nit                   - El nit del Hostal
	 * @param nombre                - La nombre del Hostal
	 * @param ganancias             - Las ganancias del Hostal
	 * @param horaAperturaRecepcion - La horaAperturaRecepcion del Hostal
	 * @param horaCierreRecepcion   - La horaCierreRecepcion del Hostal
	 * @return El objeto hostal adicionado. null si ocurre alguna Excepción
	 */
	public Hostal adicionarHostal(String regComercio, String nit, String nombre, 
			String horaAperturaRecepcion, String horaCierreRecepcion) {
		log.info("Adicionando Hotel: " + nombre);
		Hostal hostal = pa.adicionarHostal(regComercio, nit, nombre, horaAperturaRecepcion,
				horaCierreRecepcion);
		log.info("Adicionando Hotel: " + hostal);
		return hostal;
	}

	/**
	 * Encuentra el hostal en Alohandes con el regComercio solicitado
	 * Adiciona entradas al log de la aplicación
	 * @param regComercio - El regComercio del hostal
	 * @return Un objeto hostal 
	 */
	public Hostal darHostalPorRegComercio(String regComercio)
	{
		log.info ("Buscando Hostal por regComercio: " + regComercio);
		Hostal tb = pa.darHostalPorRegComercio(regComercio);
		log.info ("Buscando Hostal por regComercio: " + tb != null ? tb : "NO EXISTE");
		return tb;
	}

	/*
	 * ****************************************************************
	 * Métodos para manejar las VIVIENDAS UNIVERSITARIAS
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un tipo de bebida
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param regComercio             - El regComercio del ViviendaUniversitaria
	 * @param nit                     - El nit del ViviendaUniversitaria
	 * @param nombre                  - La nombre del ViviendaUniversitaria
	 * @param ganancias               - Las ganancias del ViviendaUniversitaria
	 * @param precioSalaEstudio       - El precioSalaEstudio del
	 *                                ViviendaUniversitaria
	 * @param precioSalaEsparcimiento - El precioSalaEsparcimiento del
	 *                                ViviendaUniversitaria
	 * @param precioGimnasio          - El precioGimnasio del ViviendaUniversitaria
	 * @param restaurante             - Si tiene restaurante el
	 *                                ViviendaUniversitaria
	 * @return El objeto viviendaUniversitaria adicionado. null si ocurre alguna
	 *         Excepción
	 */
	public ViviendaUniversitaria adicionarViviendaUniversitaria(String regComercio, String nit, String nombre,
			int precioSalaEstudio, int precioSalaEsparcimiento, int precioGimnasio, String restaurante) {
		log.info("Adicionando vivienda universitaria: " + nombre);
		ViviendaUniversitaria viviendaUniversitaria = pa.adicionarViviendaUniversitaria(regComercio, nit, nombre,
				precioSalaEstudio, precioSalaEsparcimiento, precioGimnasio, restaurante);
		log.info("Adicionando vivienda universitaria: " + viviendaUniversitaria);
		return viviendaUniversitaria;
	}

	/**
	 * Encuentra la ViviendaUniversitaria en Alohandes con el regComercio solicitado
	 * Adiciona entradas al log de la aplicación
	 * @param regComercio - El regComercio del ViviendaUniversitaria
	 * @return Un objeto ViviendaUniversitaria 
	 */
	public ViviendaUniversitaria darViviendaUniversitariaPorRegComercio(String regComercio)
	{
		log.info ("Buscando ViviendaUniversitaria por regComercio: " + regComercio);
		ViviendaUniversitaria tb = pa.darViviendaUniversitariaPorRegComercio(regComercio);
		log.info ("Buscando ViviendaUniversitaria por regComercio: " + tb != null ? tb : "NO EXISTE");
		return tb;
	}

	/*
	 * ****************************************************************
	 * Métodos para manejar los Alojamientos
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un alojamiento
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param ubicacion   - La ubicacion de un alojamiento
	 * @param duracionMin - La duracionMin de un alojamiento
	 * @param costo       - El costo de un alojamiento
	 * @return El objeto Alojamiento adicionado. null si ocurre alguna Excepción
	 */
	public Alojamiento adicionarAlojamiento(String ubicacion, int duracionMin, int costo, String tipoAlojamiento) {
		log.info("Adicionando alojamiento: " + ubicacion);
		Alojamiento alojamiento = pa.adicionarAlojamiento(ubicacion, duracionMin, costo, tipoAlojamiento);
		log.info("Adicionando alojamiento: " + alojamiento);
		return alojamiento;
	}

	/**
	 * Elimina un alojamiento por su identificador
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param idAlojamiento - El identificador del bar a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarAlojamiento(long idAlojamiento) {
		log.info("Eliminando alojamiento por id: " + idAlojamiento);
		long resp = pa.eliminarAlojamiento(idAlojamiento);
		log.info("Eliminando alojamiento: " + resp);
		return resp;
	}

	/**
	 * Encuentra el top 20 ofertas de alojamientos
	 * @return Una lista de parejas [SELECT A_ALOJAMIENTO.ID, A_ALOJAMIENTO.UBICACION, A_ALOJAMIENTO.DURACIONMIN, A_ALOJAMIENTO.COSTO]
	 */
	public List<Object []> darTop20Ofertas ()
	{
        log.info ("Listando de top 20 ofertas");
        List<Object []> tuplas = pa.darTop20Ofertas();
        log.info ("Listando de top 20 ofertas: Listo!");
		return tuplas;
	}

	/**
	 * RFC4 - MOSTRAR LOS ALOJAMIENTOS DISPONIBLES EN UN RANGO DE FECHAS, 
	 * QUE CUMPLEN CON UN CONJUNTO DE REQUERIMIENTOS DE DOTACIÓN O SERVICIOS.
	 * 
	 * @param fechaIni - La fecha inicial 
	 * @param fechaFin - La fecha final
	 * @param tipoServicio - Los servicios que se quiere que tenga el alojamiento
	 * @return Una lista de tuplas con los alojamientos que cumplen las condiciones
	 */
	public List<Object[]> darAlojamientosConCondicion(Timestamp fechaIni, Timestamp fechaFin, List<String> tipoServicio)
	{
		log.info ("Listando alojamientos que cumplen las condiciones:");
        List<Object []> tuplas = pa.darAlojamientosConCondicion(fechaIni, fechaFin, tipoServicio);
        log.info ("Listando alojamientos que cumplen las condiciones: Listo!");
        return tuplas;
	}


	/**
	 * 	RFC3 - MOSTRAR EL ÍNDICE DE OCUPACIÓN DE CADA UNA DE LAS OFERTAS DE ALOJAMIENTO REGISTRADAS
	 * @return Una lista de tuplas con el indice de ocupacion de cada oderta de alojamiento
	 */
	public List<Object[]> darIndiceOcupacion()
	{
		log.info ("Listando indice de ocupacion:");
		List<Object []> tuplas = pa.darIndiceOcupacion();
		log.info ("Listando indice de ocupacion: Listo!");
		return tuplas;
	}

	/**
	 * Encuentra el Alojamiento en Alohandes con el identificador solicitado
	 * Adiciona entradas al log de la aplicación
	 * @param id - El identificador del operadorUsuario
	 * @return Un objeto operadorUsuario 
	 */
	public Alojamiento darAlojamientoPorId(long id)
	{
		log.info ("Buscando alojamiento por identificador: " + id);
		Alojamiento tb = pa.darAlojamientoPorId(id);
		log.info ("Buscando alojamiento por Identificador: " + tb != null ? tb : "NO EXISTE");
		return tb;
	}

	/*
	 * ****************************************************************
	 * Métodos para manejar los HabitacionHuesped
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un HabitacionHuesped
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param idAlojamiento  - El idAlojamiento de la HabitacionHuesped
	 * @param comidas        - El comidas de la HabitacionHuesped
	 * @param tipoBanio      - El tipoBanio de la HabitacionHuesped
	 * @param tipoHabitacion - El tipoHabitacion de la HabitacionHuesped
	 * @param dtoMesExtra    - El dtoMesExtra de la HabitacionHuesped
	 * @return El objeto HabitacionHuesped adicionado. null si ocurre alguna
	 *         Excepción
	 */
	public HabitacionHuesped adicionarHabitacionHuesped(long idAlojamiento, int comidas, String tipoBanio,
			String tipoHabitacion, int dtoMesExtra, String identificacionOperadorUsuario) {
		log.info("Adicionando HabitacionHuesped: " + idAlojamiento);
		HabitacionHuesped habitacionHuesped = pa.adicionarHabitacionHuesped(idAlojamiento, comidas, tipoBanio,
				tipoHabitacion, dtoMesExtra, identificacionOperadorUsuario);
		log.info("Adicionando HabitacionHuesped: " + habitacionHuesped);
		return habitacionHuesped;
	}

	/**
	 * Elimina un HabitacionHuesped por su identificador
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param idAlojamiento - El identificador del bar a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarHabitacionHuesped(long idAlojamiento) {
		log.info("Eliminando HabitacionHuesped por id: " + idAlojamiento);
		long resp = pa.eliminarHabitacionesHuesped(idAlojamiento);
		log.info("Eliminando HabitacionHuesped: " + resp);
		return resp;
	}


	/**
	 * Encuentra una HabitacionHuesped por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idHabitacionHuesped - El identificador de la HabitacionHuesped
	 * @return El objeto HabitacionHuesped
	 */
	public HabitacionHuesped darHabitacionHuespedPorId(long idHabitacionHuesped)
	{
		log.info("Dar HabitacionHuesped por id: " + idHabitacionHuesped);
		HabitacionHuesped habitacionHuesped = pa.darHabitacionHuespedPorId(idHabitacionHuesped);
		log.info("Dar HabitacionHuesped: " + habitacionHuesped);
		return habitacionHuesped;
	}


	/*
	 * ****************************************************************
	 * Métodos para manejar los AlojamientoServicio
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un AlojamientoServicio
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param idAlojamiento  - El idAlojamiento de la AlojamientoServicio
	 * @param idServicio        - El idServicio de la AlojamientoServicio
	 * @param costo      - El costo de la AlojamientoServicio
	 * @return El objeto AlojamientoServicio adicionado. null si ocurre alguna
	 *         Excepción
	 */
	public AlojamientoServicio adicionarAlojamientoServicio(long idAlojamiento, long idServicio, int costo) {
		log.info("Adicionando AlojamientoServicio: " + idAlojamiento + " - " + idServicio);
		AlojamientoServicio habitacionHuesped = pa.adicionarAlojamientoServicio(idAlojamiento, idServicio, costo);
		log.info("Adicionando AlojamientoServicio: " + habitacionHuesped);
		return habitacionHuesped;
	}

	/**
	 * Elimina una AlojamientoServicio por su identificador
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param idAlojamiento - El identificador de la reserva
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarAlojamientoServicioPorIdAlojamiento(long idAlojamiento) {
		log.info("Eliminando AlojamientoServicio por id: " + idAlojamiento);
		long resp = pa.eliminarAlojamientoServicioPorIdAlojamiento(idAlojamiento);
		log.info("Eliminando AlojamientoServicio por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/*
	 * ****************************************************************
	 * Métodos para manejar los Servicio
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un AlojamientoServicio
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param id  - El id de la AlojamientoServicio
	 * @param tipo        - El tipo de la AlojamientoServicio
	 * @return El objeto AlojamientoServicio adicionado. null si ocurre alguna
	 *         Excepción
	 */
	public Servicio adicionarServicio(long id, String tipo) {
		log.info("Adicionando AlojamientoServicio: " + id + " - " + tipo);
		Servicio habitacionHuesped = pa.adicionarServicio(id, tipo);
		log.info("Adicionando AlojamientoServicio: " + habitacionHuesped);
		return habitacionHuesped;
	}

	/*
	 * ****************************************************************
	 * Métodos para manejar los ApartamentoAlquiler
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un ApartamentoAlquiler
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param idAlojamiento  - El idAlojamiento de la ApartamentoAlquiler
	 * @param servPublico    - El servPublico de la ApartamentoAlquiler
	 * @param administracion - El administracion de la ApartamentoAlquiler
	 * @return El objeto ApartamentoAlquiler adicionado. null si ocurre alguna
	 *         Excepción
	 */
	public ApartamentoAlquiler adicionarApartamentoAlquiler(long idAlojamiento, String servPublico,
			String administracion, String identificacionOperadorUsuario) {
		log.info("Adicionando HabitacionHuesped: " + idAlojamiento);
		ApartamentoAlquiler apartamentoAlquiler = pa.adicionarApartamentoAlquiler(idAlojamiento, servPublico,
				administracion, identificacionOperadorUsuario);
		log.info("Adicionando HabitacionHuesped: " + apartamentoAlquiler);
		return apartamentoAlquiler;
	}

	/**
	 * Elimina un apartamento alquiler por su identificador
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param idAlojamiento - El identificador del bar a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarApartamentoAlquiler(long idAlojamiento) {
		log.info("Eliminando apartamento alquiler por id: " + idAlojamiento);
		long resp = pa.eliminarApartamentoAlquiler(idAlojamiento);
		log.info("Eliminando apartamento alquiler: " + resp);
		return resp;
	}

	/**
	 * Encuentra una ApartamentoAlquiler por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idApartamentoAlquiler - El identificador de la ApartamentoAlquiler
	 * @return El objeto ApartamentoAlquiler
	 */
	public ApartamentoAlquiler darApartamentoAlquilerPorId(long idApartamentoAlquiler)
	{
		log.info("Dar ApartamentoAlquiler por id: " + idApartamentoAlquiler);
		ApartamentoAlquiler ApartamentoAlquiler = pa.darApartamentoAlquilerPorId(idApartamentoAlquiler);
		log.info("Dar ApartamentoAlquiler: " + ApartamentoAlquiler);
		return ApartamentoAlquiler;
	}

	/*
	 * ****************************************************************
	 * Métodos para manejar los ViviendaTemporal
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un ViviendaTemporal
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param idAlojamiento  - El idAlojamiento de la ViviendaTemporal
	 * @param servPublico    - El servPublico de la ViviendaTemporal
	 * @param administracion - El administracion de la ViviendaTemporal
	 * @return El objeto ViviendaTemporal adicionado. null si ocurre alguna
	 *         Excepción
	 */
	public ViviendaTemporal adicionarViviendaTemporal(long idAlojamiento, int numeroHabitaciones,
			int precioSeguroArrendamiento, String caractSeguro, int diasAlquilado, String identificacionOperadorUsuario) {
		log.info("Adicionando HabitacionHuesped: " + idAlojamiento);
		ViviendaTemporal viviendaTemporal = pa.adicionarViviendaTemporal(idAlojamiento, numeroHabitaciones,
				precioSeguroArrendamiento, caractSeguro, diasAlquilado, identificacionOperadorUsuario);
		log.info("Adicionando HabitacionHuesped: " + viviendaTemporal);
		return viviendaTemporal;
	}

	/**
	 * Elimina un ViviendaTemporal por su identificador
	 * Adiciona entradas al log de la aplicación
	 * 
	 * @param idAlojamiento - El identificador del bar a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarViviendaTemporal(long idAlojamiento) {
		log.info("Eliminando ViviendaTemporal por id: " + idAlojamiento);
		long resp = pa.eliminarViviendaTemporal(idAlojamiento);
		log.info("Eliminando ViviendaTemporal: " + resp);
		return resp;
	}

	/**
	 * Encuentra una ViviendaTemporal por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idViviendaTemporal - El identificador de la ViviendaTemporal
	 * @return El objeto ViviendaTemporal
	 */
	public ViviendaTemporal darViviendaTemporalPorId(long idViviendaTemporal)
	{
		log.info("Dar ViviendaTemporal por id: " + idViviendaTemporal);
		ViviendaTemporal ViviendaTemporal = pa.darViviendaTemporalPorId(idViviendaTemporal);
		log.info("Dar ViviendaTemporal: " + ViviendaTemporal);
		return ViviendaTemporal;
	}

	/*
	 * ****************************************************************
	 * Métodos para manejar los HabitacionHotel
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente una HabitacionHotel
	 * Adiciona entradas al log de la aplicación
	 * @param idHabHotel - El idHabHotel de la HabitacionHotel
	 * @param tipoHabitacion - El tipoHabitacion de la HabitacionHotel
	 * @param tamanio - El tamanio de la HabitacionHotel
	 * @param idHotel - El id (RegDeComercio) del hotel al que pertenece la HabitacionHotel
	 * @return El objeto HabitacionHotel adicionado. null si ocurre alguna Excepción
	 */
	public HabitacionHotel adicionarHabitacionHotel(long idHabHotel, String tipoHabitacion, String tamanio,
			String idHotel) {

		log.info("Adicionando HabitacionHotel: " + idHabHotel);
		HabitacionHotel habitacionHotel = pa.adicionarHabitacionHotel(idHabHotel, tipoHabitacion, tamanio, idHotel);
		log.info("Adicionando HabitacionHotel: " + idHabHotel);
		return habitacionHotel;
	}

	/**
	 * Encuentra el hotel en Alohandes con el regComercio solicitado
	 * Adiciona entradas al log de la aplicación
	 * @param regComercio - El regComercio del hotel
	 * @return Un objeto hotel 
	 */
	public Hotel darHotelPorRegComercio(String regComercio)
	{
		log.info ("Buscando Hotel por regComercio: " + regComercio);
		Hotel tb = pa.darHotelPorRegComercio(regComercio);
		log.info ("Buscando Hotel por regComercio: " + tb != null ? tb : "NO EXISTE");
		return tb;
	}

	/**
	 * Elimina una HabitacionHotel por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idHabHotel - El identificador de la HabitacionHotel a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarHabitacionHotelPorId(long idHabHotel) {

		log.info("Eliminando HabitacionHotel por id: " + idHabHotel);
		long resp = pa.eliminarHabitacionHotelPorId(idHabHotel);
		log.info("Eliminando HabitacionHotel: " + resp);
		return resp;
	}

	/**
	 * Encuentra una HabitacionHotel por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idHabitacionHotel - El identificador de la HabitacionHotel
	 * @return El objeto HabitacionHotel
	 */
	public HabitacionHotel darHabitacionHotelPorId(long idHabitacionHotel)
	{
		log.info("Dar HabitacionHotel por id: " + idHabitacionHotel);
		HabitacionHotel HabitacionHotel = pa.darHabitacionHotelPorId(idHabitacionHotel);
		log.info("Dar HabitacionHotel: " + HabitacionHotel);
		return HabitacionHotel;
	}

	/*
	 * ****************************************************************
	 * Métodos para manejar los HabitacionHostal
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente una HabitacionHostal
	 * Adiciona entradas al log de la aplicación
	 * @param idHabHostal - El idHabHostal de la HabitacionHostal
	 * @param aforo - El aforo de la HabitacionHostal
	 * @param idHostal - El id (RegDeComercio) del hostal al que pertenece la HabitacionHostal
	 * @return El objeto HabitacionHostal adicionado. null si ocurre alguna Excepción
	 */
	public HabitacionHostal adicionarHabitacionHostal(long idHabHostal, int aforo, String idHostal ) {

		log.info("Adicionando HabitacionHostal: " + idHabHostal);
		HabitacionHostal habitacionHostal = pa.adicionarHabitacionHostal(idHabHostal, aforo, idHostal );
		log.info("Adicionando HabitacionHostal: " + idHabHostal);
		return habitacionHostal;
	}

	/**
	 * Elimina una HabitacionHostal por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idHabHostal - El identificador de la HabitacionHostal a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarHabitacionHostalPorId(long idHabHostal) {

		log.info("Eliminando HabitacionHostal por id: " + idHabHostal);
		long resp = pa.eliminarHabitacionHostalPorId(idHabHostal);
		log.info("Eliminando HabitacionHostal: " + resp);
		return resp;
	}

	/**
	 * Encuentra una HabitacionHostal por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idHabitacionHostal - El identificador de la HabitacionHostal
	 * @return El objeto HabitacionHostal
	 */
	public HabitacionHostal darHabitacionHostalPorId(long idHabitacionHostal)
	{
		log.info("Dar HabitacionHostal por id: " + idHabitacionHostal);
		HabitacionHostal HabitacionHostal = pa.darHabitacionHostalPorId(idHabitacionHostal);
		log.info("Dar HabitacionHostal: " + HabitacionHostal);
		return HabitacionHostal;
	}

	/*
	 * ****************************************************************
	 * Métodos para manejar los HabitacionViviendaUniversitaria
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente una HabitacionViviendaUniversitaria
	 * Adiciona entradas al log de la aplicación
	 * @param idHabVivUni - El idHabVivUni de la HabitacionViviendaUniversitaria
	 * @param tipoHabitacion - El tipoHabitacion de la HabitacionViviendaUniversitaria
	 * @param capacidad - La capacidad de la HabitacionViviendaUniversitaria
	 * @param idViviendaUniversitaria - El id (RegDeComercio) de la vivienda universitaria a la que pertenece la HabitacionViviendaUniversitaria
	 * @return El objeto HabitacionViviendaUniversitaria adicionado. null si ocurre alguna Excepción
	 */
	public HabitacionViviendaUniversitaria adicionarHabitacionViviendaUniversitaria(long idHabVivUni, String tipoHabitacion, int capacidad, String idViviendaUniversitaria) {

		log.info("Adicionando HabitacionViviendaUniversitaria: " + idHabVivUni);
		HabitacionViviendaUniversitaria habitacionViviendaUniversitaria = pa.adicionarHabitacionViviendaUniversitaria(idHabVivUni, tipoHabitacion, capacidad, idViviendaUniversitaria);
		log.info("Adicionando HabitacionViviendaUniversitaria: " + idHabVivUni);
		return habitacionViviendaUniversitaria;
	}

	/**
	 * Elimina una HabitacionViviendaUniversitaria por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idHabVivUni - El identificador de la HabitacionViviendaUniversitaria a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarHabitacionViviendaUniversitariaPorId(long idHabVivUni) {

		log.info("Eliminando HabitacionViviendaUniversitaria por id: " + idHabVivUni);
		long resp = pa.eliminarHabitacionViviendaUniversitariaPorId(idHabVivUni);
		log.info("Eliminando HabitacionViviendaUniversitaria: " + resp);
		return resp;
	}

	/**
	 * Encuentra una HabitacionViviendaUniversitaria por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idHabitacionViviendaUniversitaria - El identificador de la HabitacionViviendaUniversitaria
	 * @return El objeto HabitacionViviendaUniversitaria
	 */
	public HabitacionViviendaUniversitaria darHabitacionViviendaUniversitariaPorId(long idHabitacionViviendaUniversitaria)
	{
		log.info("Dar HabitacionViviendaUniversitaria por id: " + idHabitacionViviendaUniversitaria);
		HabitacionViviendaUniversitaria HabitacionViviendaUniversitaria = pa.darHabitacionViviendaUniversitariaPorId(idHabitacionViviendaUniversitaria);
		log.info("Dar HabitacionViviendaUniversitaria: " + HabitacionViviendaUniversitaria);
		return HabitacionViviendaUniversitaria;
	}


	/*
	 * ****************************************************************
	 * Métodos para manejar los RF9
	 *****************************************************************/

	public String deshabilitarAlojamiento(long idAlojamiento, String tipo){
		log.info("Inicia proceso de deshabilitar alojamiento por id: " + idAlojamiento);

		long numAlojamientosDeshablitados = pa.deshabilitarAlojamiento(idAlojamiento);
		
		log.info("Se deshabilitaron : " + numAlojamientosDeshablitados + " alojamientos");
		String resp = "Se ha deshabilitado " + numAlojamientosDeshablitados + " alojamiento \n";

		log.info("Inicia proceso de relocalizar las reservas del alojamiento: " + idAlojamiento);


		List<Reserva> reservas = pa.darReservasPorIdAlojamiento(idAlojamiento);
		resp+= "Se han encontrado " + reservas.size() + " reservas asociadas al alojamiento \n";


		for(Reserva reserva : reservas){
			log.info("Inicia proceso para la reserva con id: " + reserva.getId() + " que pertenecia al alojamiento con id: "+ idAlojamiento);

			resp += pa.RelocalizarReserva(reserva, idAlojamiento, tipo);

			log.info("Termino proceso para la reserva con id: " + reserva.getId() + " que pertenecia al alojamiento con id: "+ idAlojamiento);
		}

		log.info("Termina proceso de deshabilitar alojamiento por id: " + idAlojamiento);
		return resp;
	}


}
