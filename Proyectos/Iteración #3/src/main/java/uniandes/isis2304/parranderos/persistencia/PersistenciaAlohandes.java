package uniandes.isis2304.parranderos.persistencia;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import uniandes.isis2304.parranderos.negocio.Alojamiento;
import uniandes.isis2304.parranderos.negocio.AlojamientoServicio;
import uniandes.isis2304.parranderos.negocio.ApartamentoAlquiler;
import uniandes.isis2304.parranderos.negocio.Cliente;
import uniandes.isis2304.parranderos.negocio.HabitacionHostal;
import uniandes.isis2304.parranderos.negocio.HabitacionHotel;
import uniandes.isis2304.parranderos.negocio.HabitacionHuesped;
import uniandes.isis2304.parranderos.negocio.HabitacionViviendaUniversitaria;
import uniandes.isis2304.parranderos.negocio.Hostal;
import uniandes.isis2304.parranderos.negocio.Hotel;
import uniandes.isis2304.parranderos.negocio.OperadorUsuario;
import uniandes.isis2304.parranderos.negocio.Reserva;
import uniandes.isis2304.parranderos.negocio.Servicio;
import uniandes.isis2304.parranderos.negocio.ViviendaTemporal;
import uniandes.isis2304.parranderos.negocio.ViviendaUniversitaria;

public class PersistenciaAlohandes {
    	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaAlohandes.class.getName());
	
	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaAlohandes instance;
	
	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;
	
	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * Secuenciador, tipoBebida, bebida, bar, bebedor, gustan, sirven y visitan
	 */
	private List <String> tablas;
	
	/**
	 * Atributo para el acceso a las sentencias SQL propias a PersistenciaAlohandes
	 */
	private SQLUtil sqlUtil;
	
	/**
	 * Atributo para el acceso a la tabla sqlOperadorUsuario de la base de datos
	 */
	private SQLOperadorUsuario sqlOperadorUsuario;
	
	/**
	 * Atributo para el acceso a la tabla sqlHostal de la base de datos
	 */
	private SQLHostal sqlHostal;
	
	/**
	 * Atributo para el acceso a la tabla sqlHotel de la base de datos
	 */
	private SQLHotel sqlHotel;
	
	/**
	 * Atributo para el acceso a la tabla sqlViviendaUniversitaria de la base de datos
	 */
	private SQLViviendaUniversitaria sqlViviendaUniversitaria;
	
	/**
	 * Atributo para el acceso a la tabla cliente de la base de datos
	 */
	private SQLCliente sqlCliente;

	/**
	 * Atributo para el acceso a la tabla Reserva de la base de datos
	 */
	private SQLReserva sqlReserva;

	/**
	 * Atributo para el acceso a la tabla Alojamiento de la base de datos
	 */
	private SQLAlojamiento sqlAlojamiento;

	/**
	 * Atributo para el acceso a la tabla HabitacionHuesped de la base de datos
	 */
	private SQLHabitacionHuesped sqlHabitacionHuesped;

	/**
	 * Atributo para el acceso a la tabla ApartamentoAlquiler de la base de datos
	 */
	private SQLApartamentoAlquiler sqlApartamentoAlquiler;

	/**
	 * Atributo para el acceso a la tabla ViviendaTemporal de la base de datos
	 */
	private SQLViviendaTemporal sqlViviendaTemporal;

	/**
	 * Atributo para el acceso a la tabla HabitacionHotel de la base de datos
	 */
	private SQLHabitacionHotel sqlHabitacionHotel;

	/**
	 * Atributo para el acceso a la tabla HabitacionViviendaUniversitaria de la base de datos
	 */
	private SQLHabitacionViviendaUniversitaria sqlHabitacionViviendaUniversitaria;

	/**
	 * Atributo para el acceso a la tabla HabitacionHostal de la base de datos
	 */
	private SQLHabitacionHostal sqlHabitacionHostal;

	/**
	 * Atributo para el acceso a la tabla HabitacionHostal de la base de datos
	 */
	private SQLAlojamientoServicio sqlAlojamientoServicio;
	
	/**
	 * Atributo para el acceso a la tabla HabitacionHostal de la base de datos
	 */
	private SQLServicio sqlServicio;

	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaAlohandes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("Alohandes");	// aca iba "Parranderos"	
		crearClasesSQL ();
		
		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add("alohandes_sequence");
		tablas.add("A_OPERADORUSUARIO");
		tablas.add("A_CLIENTE");
		tablas.add("A_ALOJAMIENTO");
		tablas.add("A_HABITACIONHUESPED");
		tablas.add("A_APARTAMENTOALQUILER");
		tablas.add("A_VIVIENDATEMPORAL");
		tablas.add("A_RESERVA");
		tablas.add("A_SERVICIO");
		tablas.add("A_HOTEL");
		tablas.add("A_VIVIENDAUNIVERSITARIA");
		tablas.add("A_HOSTAL");
		tablas.add("A_HABITACIONHOTEL");
		tablas.add("A_HABITACIONVIVIENDAUNIVERSITARIA");
		tablas.add("A_HABITACIONHOSTAL");
		tablas.add("A_ALOJAMIENTOSERVICIO");
}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaAlohandes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el único objeto PersistenciaAlohandes existente - Patrón SINGLETON
	 */
	public static PersistenciaAlohandes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaAlohandes ();
		}
		return instance;
	}
	
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaAlohandes existente - Patrón SINGLETON
	 */
	public static PersistenciaAlohandes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaAlohandes (tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	
	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	
	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		sqlOperadorUsuario = new SQLOperadorUsuario(this);
		sqlCliente = new SQLCliente(this);
		sqlHostal = new SQLHostal(this);
		sqlHotel = new SQLHotel(this);
		sqlViviendaUniversitaria = new SQLViviendaUniversitaria(this);
		sqlHabitacionHuesped = new SQLHabitacionHuesped(this);
		sqlAlojamiento = new SQLAlojamiento(this);
		sqlReserva = new SQLReserva(this);
		sqlUtil = new SQLUtil(this);
		sqlApartamentoAlquiler = new SQLApartamentoAlquiler(this);
		sqlViviendaTemporal = new SQLViviendaTemporal(this);
		sqlHabitacionHostal = new SQLHabitacionHostal(this);
		sqlHabitacionHotel = new SQLHabitacionHotel(this);
		sqlHabitacionViviendaUniversitaria = new SQLHabitacionViviendaUniversitaria(this);
		sqlAlojamientoServicio = new SQLAlojamientoServicio(this);
		
	}

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador de Alohandes
	 */
	public String darSeqAlohandes ()
	{
		return tablas.get (0);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de OperadorUsuario de Alohandes
	 */
	public String darTablaOperadorUsuario ()
	{
		return tablas.get (1);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Cliente de Alohandes
	 */
	public String darTablaCliente ()
	{
		return tablas.get (2);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Alojamiento de Alohandes
	 */
	public String darTablaAlojamiento ()
	{
		return tablas.get (3);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de HabitacionHuesped de Alohandes
	 */
	public String darTablaHabitacionHuesped ()
	{
		return tablas.get (4);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de ApartamentoAlquiler de Alohandes
	 */
	public String darTablaApartamentoAlquiler ()
	{
		return tablas.get (5);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de ViviendaTemporal de Alohandes
	 */
	public String darTablaViviendaTemporal ()
	{
		return tablas.get (6);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Reserva de Alohandes
	 */
	public String darTablaReserva ()
	{
		return tablas.get (7);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Servicio de Alohandes
	 */
	public String darTablaServicio ()
	{
		return tablas.get (8);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Hotel de Alohandes
	 */
	public String darTablaHotel ()
	{
		return tablas.get (9);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de ViviendaUniversitaria de Alohandes
	 */
	public String darTablaViviendaUniversitaria ()
	{
		return tablas.get (10);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Hostal de Alohandes
	 */
	public String darTablaHostal ()
	{
		return tablas.get (11);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de HabitacionHotel de Alohandes
	 */
	public String darTablaHabitacionHotel ()
	{
		return tablas.get (12);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de HabitacionViviendaUniversitaria de Alohandes
	 */
	public String darTablaHabitacionViviendaUniversitaria ()
	{
		return tablas.get (13);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de HabitacionHostal de Alohandes
	 */
	public String darTablaHabitacionHostal ()
	{
		return tablas.get (14);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de AlojamientoServicio de Alohandes
	 */
	public String darTablaAlojamientoServicio ()
	{
		return tablas.get (15);
	}
	
	/**
	 * Transacción para el generador de secuencia de Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de Parranderos
	 */
	private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }
	
	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los OPERADORES DE USUARIO
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla OperadorUsuario
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto OperadorUsuario adicionado. null si ocurre alguna Excepción
	 */
	public OperadorUsuario adicionarOperadorUsuario(String identificacion, String nombre, String tipoVinculo, String correoElectronico, String telefono)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlOperadorUsuario.adicionarOperadorUsuario(pm, identificacion, nombre, tipoVinculo, correoElectronico, telefono);
            tx.commit();
            
            log.trace ("Inserción de tipo de operador usuario: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new OperadorUsuario(identificacion, nombre, tipoVinculo, correoElectronico, telefono);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla CLIENTE que tienen el identificador dado
	 * @param identificacion - El identificador del cliente
	 * @return El objeto OPERADORUSUARIO
	 */
	public OperadorUsuario darOperadorUsuarioPorIdentificacion(String identificacion)
	{
		return sqlOperadorUsuario.darOperadorUsuarioPorIdentificacion(pmf.getPersistenceManager(), identificacion);
	}


	/**
	 * Encuentra todos los proveedores con sus respectivas ganancias del anio y del anio corrido
	 * @return La lista de parejas de objetos. 
	 */
	public List<Object []> darDineroRecibido ()
	{
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object> tuplas = sqlOperadorUsuario.darDineroRecibido(pmf.getPersistenceManager());
        for ( Object tupla : tuplas)
        {
			Object [] datos = (Object []) tupla;	
			
			respuesta.add(datos);
        }

		return respuesta;
	}


	/* ****************************************************************
	 * 			Métodos para manejar los CLIENTES
	 *****************************************************************/
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Cliente
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto Cliente adicionado. null si ocurre alguna Excepción
	 */
	public Cliente adicionarCliente(String identificacion, String nombre, String tipoVinculo, String correoElectronico, String telefono, Timestamp ultimaFechaReserva)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {		
            tx.begin();
            long tuplasInsertadas = sqlCliente.adicionarCliente(pm, identificacion, nombre, tipoVinculo, correoElectronico, telefono, ultimaFechaReserva);
            tx.commit();
            
            log.trace ("Inserción de cliente " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Cliente(identificacion, nombre, tipoVinculo, correoElectronico, telefono, ultimaFechaReserva);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que actualiza, de manera transaccional, la ultimaFechaReserva de un  Cliente
	 * @param idCliente - El identificador del Cliente
	 * @param ultimaFechaReserva - La nueva ultimaFechaReserva del Cliente
	 * @return El número de tuplas modificadas. -1 si ocurre alguna Excepción
	 */
	public long cambiarUltimaFechaReservaBebedor (String idCliente, Timestamp ultimaFechaReserva)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlCliente.cambiarUltimaFechaReservaBebedor(pm, idCliente, ultimaFechaReserva);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar las RESERVAS
	 *****************************************************************/
	public Reserva adicionarReserva(Timestamp fechaInicio, Timestamp fechaFin, String identificacionCliente, long idAlojamiento)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idReserva = nextval ();
            long tuplasInsertadas = sqlReserva.adicionarReserva(pm, idReserva, fechaInicio, fechaFin, identificacionCliente, idAlojamiento, "Y");
            tx.commit();
            
            log.trace ("Inserción de la Reserva con id " + idReserva + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Reserva(idReserva, fechaInicio, fechaFin, identificacionCliente, idAlojamiento, "Y");
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	// /**
	//  * Método que consulta RF7
	//  * @param id - El id de la reserva
	//  * @return El id de confirmacion
	//  */

	// 	public String RelocalizarReservaA(Timestamp fechaIni, Timestamp fechaFin, List<String> tipoServicio, String tipoAlojamiento, long idAlojamiento, String identificacionCliente, long idGrupo, int cantidadAlojamientos)
	// 	{
	// 		PersistenceManager pm = pmf.getPersistenceManager();

	// 		List<Object[]> alojamientos = sqlAlojamiento.darAlojamientosConCondicionRF7(pm, fechaIni, fechaFin, tipoServicio, tipoAlojamiento);

	// 		Transaction tx=pm.currentTransaction();
	// 		try
	// 		{
	// 			tx.begin();
	// 			long reservasActualizadas = sqlReserva.actualizarReservaPorIdAlojamiento(pm, reserva.getId(), Long.parseLong(alojamientos.get(0)[0].toString()));
	// 			tx.commit();
	// 			String resp = "Se relocalizo " + reservasActualizadas + " " + reserva  + " con id " +  reserva.getId() + " al alojamiento con id " + alojamientos.get(0)[0].toString() + "\n";
	// 			return resp;
	// 		//  adicionarReserva(Timestamp fechaInicio, Timestamp fechaFin, String identificacionCliente, long idAlojamiento)
	// 		}
	// 		catch (Exception e)
	// 		{
	// //        	e.printStackTrace();
	// 			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
	// 			String resp = "No fue posible realizar la reserva colectiva " + reserva.getId()  + " dado que no se encontraron alojamientos disponibles" + "\n";
	// 			return resp;
	// 		}
	// 		finally
	// 		{
	// 			if (tx.isActive())
	// 			{
	// 				tx.rollback();
	// 			}
	// 			pm.close();
	// 		}
	// }

	/**
	 * Método que consulta la resrva en la tabla RESERVA que tienen el identificador dado
	 * @param id - El id de la reserva
	 * @return El id de confirmacion
	 */
	public Reserva darReservaPorId(long id)
	{
		return sqlReserva.darReservaPorId(pmf.getPersistenceManager(), id);
	}
	
	/**
	 * Método que actualizar la reserva en la tabla RESERVA que tienen el identificador dado
	 * @param estado - El estado de la reserva
	 * @param idReserva - El id de la reserva
	 * @return El objeto Reserva
	 */
	public long actualizarEstadoReservaPorIdReserva(String estado, long idReserva)
	{
		return sqlReserva.actualizarEstadoReservaPorIdReserva(pmf.getPersistenceManager(), estado, idReserva);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla CLIENTE que tienen el identificador dado
	 * @param identificacion - El identificador del cliente
	 * @return El objeto CLIENTE
	 */
	public Cliente darClientePorIdentificacion(String identificacion)
	{
		return sqlCliente.darClientePorIdentificacion(pmf.getPersistenceManager(), identificacion);
	}

	/**
	 * Método que consulta el Timestamp en la tabla RESERVA que tienen el identificador dado y que es la ultima fecha de reserva
	 * @param idAlojamiento - El idAlojamiento de la reserva
	 * @return El objeto Timestamp
	 */
	public Timestamp darUltimaFechaPorIdAlojamiento(long idAlojamiento)
	{
		return sqlReserva.darUltimaFechaPorIdAlojamiento(pmf.getPersistenceManager(), idAlojamiento);
	}

	/**
	 * 
	 * @param idReserva
	 * @return
	 */
	public long eliminarReservaPorId(long idReserva)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlReserva.eliminarReservaPorId (pm, idReserva);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public List<Reserva> darReservasPorIdAlojamiento(long idAlojamiento)
	{
		return sqlReserva.darReservasPorIdAlojamiento(pmf.getPersistenceManager() ,idAlojamiento);
	}

	/* ****************************************************************
	 * 			Métodos para manejar los HOTELES
	 *****************************************************************/
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Hotel
	 * Adiciona entradas al log de la aplicación
	 * @param regComercio - El regComercio del Hotel
	 * @param nit - El nit del Hotel
	 * @param nombre - La nombre del Hotel
	 * @param ganancias - Las ganancias del Hotel 
	 * @param restaurante - Si tiene restaurante el Hotel
     * @param parqueadero - Si tiene parqueadero el Hotel
     * @param piscina - Si tiene piscina el Hotel
	 * @return El objeto Hotel adicionado. null si ocurre alguna Excepción
	 */
	public Hotel adicionarHotel(String regComercio, String nit, String nombre, String restaurante, String parqueadero, String piscina)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlHotel.adicionarHotel(pm, regComercio, nit, nombre, restaurante, parqueadero, piscina);
            tx.commit();
            
            log.trace ("Inserción de tipo de hotel: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Hotel(regComercio, nit, nombre, restaurante, parqueadero, piscina);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta el hotel en la tabla HOTEL que tienen el identificador dado
	 * @param regComercio - El regComercio del Hotel
	 * @return El objeto Hotel
	 */
	public Hotel darHotelPorRegComercio(String regComercio)
	{
		return sqlHotel.darHotelPorRegComercio(pmf.getPersistenceManager(), regComercio);
	}

	/* ****************************************************************
	 * 			Métodos para manejar los HOSTAL
	 *****************************************************************/
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Hostal
	 * Adiciona entradas al log de la aplicación
	 * @param regComercio - El regComercio del Hostal
	 * @param nit - El nit del Hostal
	 * @param nombre - La nombre del Hostal
     * @param horaAperturaRecepcion - La horaAperturaRecepcion del Hostal
     * @param horaCierreRecepcion - La horaCierreRecepcion del Hostal
	 * @return El objeto Hostal adicionado. null si ocurre alguna Excepción
	 */
	public Hostal adicionarHostal(String regComercio, String nit, String nombre, String horaAperturaRecepcion, String horaCierreRecepcion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlHostal.adicionarHostal(pm, regComercio, nit, nombre, horaAperturaRecepcion, horaCierreRecepcion);
            tx.commit();
            
            log.trace ("Inserción de tipo de hostal: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Hostal(regComercio, nit, nombre, horaAperturaRecepcion, horaCierreRecepcion);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta el hostal en la tabla HOSTAL que tienen el identificador dado
	 * @param regComercio - El regComercio del Hostal
	 * @return El objeto Hostal
	 */
	public Hostal darHostalPorRegComercio(String regComercio)
	{
		return sqlHostal.darHostalPorRegComercio(pmf.getPersistenceManager(), regComercio);
	}

	/* ****************************************************************
	 * 			Métodos para manejar los ViveUniversitario
	 *****************************************************************/
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla ViviendaUniversitaria
	 * Adiciona entradas al log de la aplicación
	 * @param regComercio - El regComercio del ViviendaUniversitaria
	 * @param nit - El nit del ViviendaUniversitaria
	 * @param nombre - La nombre del ViviendaUniversitaria
     * @param precioSalaEstudio - El precioSalaEstudio del ViviendaUniversitaria
     * @param precioSalaEsparcimiento - El precioSalaEsparcimiento del ViviendaUniversitaria
     * @param precioGimnasio - El precioGimnasio del ViviendaUniversitaria
	 * @param restaurante - Si tiene restaurante el ViviendaUniversitaria
	 * @return El objeto ViviendaUniversitaria adicionado. null si ocurre alguna Excepción
	 */
	public ViviendaUniversitaria adicionarViviendaUniversitaria(String regComercio, String nit, String nombre, int precioSalaEstudio, int precioSalaEsparcimiento, int precioGimnasio, String restaurante)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlViviendaUniversitaria.adicionarViviendaUniversitaria(pm, regComercio, nit, nombre, precioSalaEstudio, precioSalaEsparcimiento, precioGimnasio, restaurante);
            tx.commit();
            
            log.trace ("Inserción de vivienda universitaria: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new ViviendaUniversitaria(regComercio, nit, nombre, precioSalaEstudio, precioSalaEsparcimiento, precioGimnasio, restaurante);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta el ViviendaUniversitaria en la tabla VIVIENDAUNIVERSITARIA que tienen el identificador dado
	 * @param regComercio - El regComercio del ViviendaUniversitaria
	 * @return El objeto ViviendaUniversitaria
	 */
	public ViviendaUniversitaria darViviendaUniversitariaPorRegComercio(String regComercio)
	{
		return sqlViviendaUniversitaria.darViviendaUniversitariaPorRegComercio(pmf.getPersistenceManager(), regComercio);
	}

	/* ****************************************************************
	 * 			Métodos para manejar los Alojamientos
	 *****************************************************************/
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Alojamientos
	 * Adiciona entradas al log de la aplicación
	 * @param duracionMin - La duracionMin de un alojamiento
	 * @param ubicacion - El ubicacion de un alojamiento
	 * @param costo - El costo de un alojamiento\
	 * @param estatus - El estatus de un alojamiento (activo(Y) o inactivo(N))
	 * @param tipoAlojamiento - El tipoAlojamiento de un alojamiento
	 * @return El objeto adicionarAlojamiento adicionado. null si ocurre alguna Excepción
	 */
	public Alojamiento adicionarAlojamiento(String ubicacion, int duracionMin, int costo, String tipoAlojamiento)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
			long idAlojamiento = nextval();
			long idGrupo = nextval();
            long tuplasInsertadas = sqlAlojamiento.adicionarAlojamiento(pm, idAlojamiento, ubicacion, duracionMin, costo, idGrupo, "Y", tipoAlojamiento);
            tx.commit();
            
            log.trace ("Inserción de Alojamiento con id: " + idAlojamiento + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Alojamiento(idAlojamiento, ubicacion, duracionMin, costo, idGrupo, "Y", tipoAlojamiento);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Encuentra todos los proveedores con sus respectivas ganancias del anio y del anio corrido
	 * @return La lista de parejas de objetos. 
	 */
	public List<Object []> darTop20Ofertas ()
	{
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object> tuplas = sqlAlojamiento.darTop20Ofertas(pmf.getPersistenceManager());
        for ( Object tupla : tuplas)
        {
			Object [] datos = (Object []) tupla;
			
			respuesta.add(datos);
        }

		return respuesta;
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Alojamiento, dado el identificador del alojamiento
	 * Adiciona entradas al log de la aplicación
	 * @param idAlojamiento - El identificador del alojamiento
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarAlojamiento(long idAlojamiento) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlAlojamiento.eliminarAlojamiento(pm, idAlojamiento);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta Alojamiento con un identificador dado
	 * @param id - El identificador del alojamiento
	 * @return El objeto Alojamiento
	 */
	public Alojamiento darAlojamientoPorId(long id) 
	{
		return sqlAlojamiento.darAlojamientoPorId(pmf.getPersistenceManager(), id);
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
		return sqlAlojamiento.darAlojamientosConCondicion(pmf.getPersistenceManager(), fechaIni, fechaFin, tipoServicio);
	}

	/**
	 * 	RFC3 - MOSTRAR EL ÍNDICE DE OCUPACIÓN DE CADA UNA DE LAS OFERTAS DE ALOJAMIENTO REGISTRADAS
	 * @return Una lista de tuplas con el indice de ocupacion de cada oderta de alojamiento
	 */
	public List<Object[]> darIndiceOcupacion()
	{
		return sqlAlojamiento.darIndiceOcupacion(pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			Métodos para manejar los HabitacionesHuesped
	 *****************************************************************/
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla HabitacionesHuesped
	 * Adiciona entradas al log de la aplicación
	 * @param idAlojamiento - El idAlojamiento de la HabitacionHuesped
     * @param comidas - El comidas de la HabitacionHuesped
     * @param tipoBanio - El tipoBanio de la HabitacionHuesped
     * @param tipoHabitacion - El tipoHabitacion de la HabitacionHuesped
     * @param dtoMesExtra - El dtoMesExtra de la HabitacionHuesped
	 * @return El objeto HabitacionesHuesped adicionado. null si ocurre alguna Excepción
	 */
	public HabitacionHuesped adicionarHabitacionHuesped(long idAlojamiento, int comidas, String tipoBanio, String tipoHabitacion, int dtoMesExtra, String identificacionOperadorUsuario)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlHabitacionHuesped.adicionarHabitacionHuesped(pm, idAlojamiento, comidas, tipoBanio, tipoHabitacion, dtoMesExtra, identificacionOperadorUsuario);
            tx.commit();
            
            log.trace ("Inserción de Habitacion huesped: " + idAlojamiento + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new HabitacionHuesped(idAlojamiento, comidas, tipoBanio, tipoHabitacion, dtoMesExtra, identificacionOperadorUsuario);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla HabitacionesHuesped, dado el identificador del alojamiento
	 * Adiciona entradas al log de la aplicación
	 * @param idAlojamiento - El identificador del alojamiento
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarHabitacionesHuesped(long idAlojamiento) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacionHuesped.eliminarHabitacionHuesped(pm, idAlojamiento);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla HabitacionesHuesped con un identificador dado
	 * @param idHabHuesped - El identificador de la HabitacionHuesped
	 * @return El objeto HabitacionesHuesped, construido con base en las tuplas de la tabla HabitacionesHuesped con el identificador dado
	 */
	public HabitacionHuesped darHabitacionHuespedPorId(long idHabHuesped) 
	{
		return sqlHabitacionHuesped.darHabitacionHuespedPorId(pmf.getPersistenceManager(), idHabHuesped);
	}

	/* ****************************************************************
	 * 			Métodos para manejar los ApartamentoAlquiler
	 *****************************************************************/
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla ApartamentoAlquiler
	 * Adiciona entradas al log de la aplicación
	 * @param idAlojamiento - El idAlojamiento de la ApartamentoAlquiler
     * @param servPublico - El servPublico de la ApartamentoAlquiler
     * @param administracion - El administracion de la ApartamentoAlquiler
	 * @return El objeto ApartamentoAlquiler adicionado. null si ocurre alguna Excepción
	 */
	public ApartamentoAlquiler adicionarApartamentoAlquiler(long idAlojamiento, String servPublico, String administracion, String identificacionOperadorUsuario)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlApartamentoAlquiler.adicionarApartamentoAlquiler(pm, idAlojamiento, servPublico, administracion, identificacionOperadorUsuario);
            tx.commit();
            
            log.trace ("Inserción de Apartamento Alquiler: " + idAlojamiento + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new ApartamentoAlquiler(idAlojamiento, servPublico, administracion, identificacionOperadorUsuario);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla ApartamentoAlquiler, dado el identificador del alojamiento
	 * Adiciona entradas al log de la aplicación
	 * @param idAlojamiento - El identificador del alojamiento
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarApartamentoAlquiler(long idAlojamiento) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlApartamentoAlquiler.eliminarApartamentoAlquiler(pm, idAlojamiento);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla ApartamentoAlquiler con un identificador dado
	 * @param idApartamentoAlquiler - El identificador de la ApartamentoAlquiler
	 * @return El objeto ApartamentoAlquiler, construido con base en las tuplas de la tabla ApartamentoAlquiler con el identificador dado
	 */
	public ApartamentoAlquiler darApartamentoAlquilerPorId(long idApartamentoAlquiler) 
	{
		return sqlApartamentoAlquiler.darApartamentoAlquilerPorId(pmf.getPersistenceManager(), idApartamentoAlquiler);
	}


	/* ****************************************************************
	 * 			Métodos para manejar los AlojamientoServicio
	 *****************************************************************/
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla AlojamientoServicio
	 * Adiciona entradas al log de la aplicación
	 * @param idAlojamiento - El idAlojamiento de la AlojamientoServicio
     * @param idServicio - El idServicio de la AlojamientoServicio
     * @param costo - El costo de la AlojamientoServicio
	 * @return El objeto AlojamientoServicio adicionado. null si ocurre alguna Excepción
	 */
	public AlojamientoServicio adicionarAlojamientoServicio(long idAlojamiento, long idServicio, int costo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlAlojamientoServicio.adicionarAlojamientoServicio(pm, idAlojamiento, idServicio, costo);
            tx.commit();
            
            log.trace ("Inserción de Apartamento Alquiler: " + idAlojamiento + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new AlojamientoServicio(idAlojamiento, idServicio, costo);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla AlojamientoServicio, dado el identificador del alojamiento
	 * Adiciona entradas al log de la aplicación
	 * @param idAlojamiento - El identificador del alojamiento
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarAlojamientoServicioPorIdAlojamiento(long idAlojamiento) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlAlojamientoServicio.eliminarAlojamientoServicioPorIdAlojamiento(pm, idAlojamiento);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/* ****************************************************************
	 * 			Métodos para manejar los Servicios
	 *****************************************************************/
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Servicios
	 * Adiciona entradas al log de la aplicación
	 * @param idAlojamiento - El idAlojamiento de la Servicios
     * @param tipo - El tipo de la Servicios
	 * @return El objeto Servicios adicionado. null si ocurre alguna Excepción
	 */
	public Servicio adicionarServicio(long id, String tipo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlServicio.adicionarServicio(pm, id, tipo);
            tx.commit();
            
            log.trace ("Inserción de Servicio: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Servicio(id, tipo);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/* ****************************************************************
	 * 			Métodos para manejar los ViviendaTemporal
	 *****************************************************************/
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla ViviendaTemporal
	 * Adiciona entradas al log de la aplicación
	 * @param idAlojamiento - El idAlojamiento de la Vivienda Temporal
     * @param numeroHabitaciones - El numeroHabitaciones de la Vivienda Temporal
     * @param precioSeguroArrendamiento - El precioSeguroArrendamiento de la Vivienda Temporal
     * @param caractSeguro - Las caracteristicas del seguro de la Vivienda Temporal
     * @param diasAlquilado - Los dias que lleva Alquilado de la Vivienda Temporal
	 * @return El objeto ApartamentoAlquiler adicionado. null si ocurre alguna Excepción
	 */
	public ViviendaTemporal adicionarViviendaTemporal(long idAlojamiento, int numeroHabitaciones, int precioSeguroArrendamiento, String caractSeguro, int diasAlquilado, String identificacionOperadorUsuario)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlViviendaTemporal.adicionarViviendaTemporal(pm, idAlojamiento, numeroHabitaciones, precioSeguroArrendamiento, caractSeguro, diasAlquilado, identificacionOperadorUsuario);
            tx.commit();
            
            log.trace ("Inserción de Vivienda Temporal: " + idAlojamiento + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new ViviendaTemporal(idAlojamiento, numeroHabitaciones, precioSeguroArrendamiento, caractSeguro, diasAlquilado, identificacionOperadorUsuario);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla ViviendaTemporal, dado el identificador del alojamiento
	 * Adiciona entradas al log de la aplicación
	 * @param idAlojamiento - El identificador del alojamiento
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarViviendaTemporal(long idAlojamiento) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlViviendaTemporal.eliminarViviendaTemporal(pm, idAlojamiento);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla ViviendaTemporal con un identificador dado
	 * @param idViviendaTemporal - El identificador de la ViviendaTemporal
	 * @return El objeto ViviendaTemporal, construido con base en las tuplas de la tabla ViviendaTemporal con el identificador dado
	 */
	public ViviendaTemporal darViviendaTemporalPorId(long idViviendaTemporal) 
	{
		return sqlViviendaTemporal.darViviendaTemporalPorId(pmf.getPersistenceManager(), idViviendaTemporal);
	}
	

	/* ****************************************************************
	 * 			Métodos para manejar las Habitaciones de Hotel
	 *****************************************************************/

	/**
	 * Metodo que inserta, de manera transaccional, una tupla en la tabla HabitacionHotel
	 * Adiciona entradas al log de la aplicación
	 * @param tipoHabitacion - El tipo de habitacion
	 * @param tamanio - El tamaño de la habitacion
	 * @param idHotel - El id del hotel al que pertenece la habitacion
	 * @return El objeto HabitacionHotel adicionado. null si ocurre alguna Excepción
	 */
	public HabitacionHotel adicionarHabitacionHotel(long idHabHotel, String tipoHabitacion, String tamanio, String idHotel)
	 {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlHabitacionHotel.adicionarHabitacionHotel(pm, idHabHotel, tipoHabitacion, tamanio, idHotel);
            tx.commit();
            
            log.trace ("Inserción de la habitacion de hotel: " + idHabHotel + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new HabitacionHotel(idHabHotel, tipoHabitacion, tamanio, idHotel);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}


	/**
	 * Metodo que elimina, de manera transaccional, una tupla en la tabla HabitacionHotel, dado el identificador de la habitacion
	 * Adiciona entradas al log de la aplicación
	 * @param idHabHotel - El identificador de la habitacion de hotel
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarHabitacionHotelPorId(long idHabHotel) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacionHotel.eliminarHabitacionHotelPorId(pm, idHabHotel);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla HabitacionHotel con un identificador dado
	 * @param idHabitacionHotel - El identificador de la HabitacionHotel
	 * @return El objeto HabitacionHotel, construido con base en las tuplas de la tabla HabitacionHotel con el identificador dado
	 */
	public HabitacionHotel darHabitacionHotelPorId(long idHabitacionHotel) 
	{
		return sqlHabitacionHotel.darHabitacionHotelPorId(pmf.getPersistenceManager(), idHabitacionHotel);
	}

	/* ****************************************************************
	 * 			Métodos para manejar las Habitaciones de Hostal
	 *****************************************************************/

	/**
	 * Metodo que inserta, de manera transaccional, una tupla en la tabla HabitacionHostal
	 * Adiciona entradas al log de la aplicación
	 * @param idHabHostal - El identificador de la habitacion de hostal
	 * @param aforo - El aforo de la habitacion
	 * @param idHostal - El id del hostal al que pertenece la habitacion
	 * @return El objeto HabitacionHostal adicionado. null si ocurre alguna Excepción
	 */
	public HabitacionHostal adicionarHabitacionHostal(long idHabHostal, int aforo, String idHostal)
	 {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlHabitacionHostal.adicionarHabitacionHostal(pm, idHabHostal, aforo, idHostal);
            tx.commit();
            
            log.trace ("Inserción de la habitacion de hotel: " + idHabHostal + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new HabitacionHostal(idHabHostal, aforo, idHostal);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Metodo que elimina, de manera transaccional, una tupla en la tabla HabitacionHostal, dado el identificador de la habitacion
	 * Adiciona entradas al log de la aplicación
	 * @param idHabHostal
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarHabitacionHostalPorId(long idHabHostal) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacionHostal.eliminarHabitacionHostalPorId(pm, idHabHostal);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla HabitacionHostal con un identificador dado
	 * @param idHabitacionHostal - El identificador de la HabitacionHostal
	 * @return El objeto HabitacionHostal, construido con base en las tuplas de la tabla HabitacionHostal con el identificador dado
	 */
	public HabitacionHostal darHabitacionHostalPorId(long idHabitacionHostal) 
	{
		return sqlHabitacionHostal.darHabitacionHostalPorId(pmf.getPersistenceManager(), idHabitacionHostal);
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar las Habitaciones de vivenda universitaria
	 *****************************************************************/

	/**
	 * Metodo que inserta, de manera transaccional, una tupla en la tabla HabitacionViviendaUniversitaria
	 * Adiciona entradas al log de la aplicación
	 * @param idHabVivUni - El identificador de la habitacion de vivienda universitaria
	 * @param tipoHabitacion - El tipo de habitacion
	 * @param capacidad - La capacidad de la habitacion
	 * @param idViviendaUniversitaria - El id de la vivienda universitaria a la que pertenece la habitacion
	 * @return El objeto HabitacionViviendaUniversitaria adicionado. null si ocurre alguna Excepción
	 */
	public HabitacionViviendaUniversitaria adicionarHabitacionViviendaUniversitaria(long idHabVivUni, String tipoHabitacion, int capacidad, String idViviendaUniversitaria)
	 {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlHabitacionViviendaUniversitaria.adicionarHabitacionViviendaUniversitaria(pm, idHabVivUni, tipoHabitacion, capacidad, idViviendaUniversitaria);
            tx.commit();
            
            log.trace ("Inserción de la habitacion de vivienda universitaria: " + idHabVivUni + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new HabitacionViviendaUniversitaria(idHabVivUni, tipoHabitacion, capacidad, idViviendaUniversitaria);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Metodo que elimina, de manera transaccional, una tupla en la tabla HabitacionViviendaUniversitaria, dado el identificador de la habitacion
	 * Adiciona entradas al log de la aplicación
	 * @param idHabVivUni - El identificador de la habitacion de vivienda universitaria
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarHabitacionViviendaUniversitariaPorId(long idHabVivUni) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacionViviendaUniversitaria.eliminarHabitacionViviendaUniversitariaPorId(pm, idHabVivUni);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	/**
	 * Método que consulta todas las tuplas en la tabla HabitacionViviendaUniversitaria con un identificador dado
	 * @param idHabitacionViviendaUniversitaria - El identificador de la HabitacionViviendaUniversitaria
	 * @return El objeto HabitacionViviendaUniversitaria, construido con base en las tuplas de la tabla HabitacionViviendaUniversitaria con el identificador dado
	 */
	public HabitacionViviendaUniversitaria darHabitacionViviendaUniversitariaPorId(long idHabitacionViviendaUniversitaria) 
	{
		return sqlHabitacionViviendaUniversitaria.darHabitacionViviendaUniversitariaPorId(pmf.getPersistenceManager(), idHabitacionViviendaUniversitaria);
	}

	/* ****************************************************************
	 * 			Métodos para manejar RF9
	 *****************************************************************/

	public String RelocalizarReserva(Reserva reserva, long idAlojamiento, String tipo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();

		List<Object[]> alojamientos = sqlAlojamiento.darAlojamientosDisponiblesSegunTipo(pm, reserva.getFechaIni(), reserva.getFechaFin(), tipo);

		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long reservasActualizadas = sqlReserva.actualizarReservaPorIdAlojamiento(pm, reserva.getId(), Long.parseLong(alojamientos.get(0)[0].toString()));
			tx.commit();
			String resp = "Se relocalizo " + reservasActualizadas + " " + reserva  + " con id " +  reserva.getId() + " al alojamiento con id " + alojamientos.get(0)[0].toString() + "\n";
			return resp;
		}
		catch (Exception e)
		{
//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			String resp = "No fue posible relocalizar la reserva " + reserva.getId()  + " dado que no se encontraron alojamientos disponibles" + "\n";
			return resp;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
}
	/**
	 * Método que actualiza, de manera transaccional, el estatus de una oferta de alojamiento
	 * a inactivo (N)
	 * @param idAlojamiento - El identificador del alojamiento
	 * @return El número de tuplas modificadas. -1 si ocurre alguna Excepción
	 */
	public long deshabilitarAlojamiento(long idAlojamiento)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
        {
            tx.begin();
            long resp = sqlAlojamiento.deshabilitarAlojamiento(pm, idAlojamiento);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }


	}






}

