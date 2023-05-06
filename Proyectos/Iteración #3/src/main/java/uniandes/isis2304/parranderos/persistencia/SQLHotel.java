package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Hotel;

public class SQLHotel {
    /* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaAlohandes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaAlohandes pa;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pa - El Manejador de persistencia de la aplicación
	 */
	public SQLHotel(PersistenciaAlohandes pa)
	{
		this.pa = pa;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un HOTEL a la base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @param regComercio - El regComercio del Hotel
	 * @param nit - El nit del Hotel
	 * @param nombre - La nombre del Hotel
	 * @param ganancias - Las ganancias del Hotel 
	 * @param restaurante - Si tiene restaurante el Hotel
     * @param parqueadero - Si tiene parqueadero el Hotel
     * @param piscina - Si tiene piscina el Hotel
	 * @return El número de tuplas insertadas
	 */
	public long adicionarHotel(PersistenceManager pm, String regComercio, String nit, String nombre, String restaurante, String parqueadero, String piscina) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaHotel() + "(regComercio, nit, nombre, restaurante, parqueadero, piscina) values (?, ?, ?, ?, ?, ?) COMMIT");
            
        q.setParameters(regComercio, nit, nombre, restaurante, parqueadero, piscina);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN Hotel de la base de datos de Alohandes, por su regComercio
	 * @param pm - El manejador de persistencia
	 * @param regComercio - El registro de comercio del Hotel
	 * @return El objeto Hotel que tiene el regComercio dado
	 */
	public Hotel darHotelPorRegComercio(PersistenceManager pm, String regComercio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHotel() + " WHERE regcomercio = ?");
		q.setResultClass(Hotel.class);
		q.setParameters(regComercio);
		return (Hotel) q.executeUnique();
	}
}