package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Hostal;

public class SQLHostal {
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
	public SQLHostal(PersistenciaAlohandes pa)
	{
		this.pa = pa;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un HOSTAL a la base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @param regComercio - El regComercio del Hostal
	 * @param nit - El nit del Hostal
	 * @param nombre - La nombre del Hostal
     * @param horaAperturaRecepcion - La horaAperturaRecepcion del Hostal
     * @param horaCierreRecepcion - La horaCierreRecepcion del Hostal
	 * @return El número de tuplas insertadas
	 */
	public long adicionarHostal(PersistenceManager pm, String regComercio, String nit, String nombre, String horaAperturaRecepcion, String horaCierreRecepcion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaHostal() + "(regComercio, nit, nombre, horaAperturaRecepcion, horaCierreRecepcion) values (?, ?, ?, ?, ?)");
            
        q.setParameters(regComercio, nit, nombre, horaAperturaRecepcion, horaCierreRecepcion);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN hostal de la base de datos de Alohandes, por su regComercio
	 * @param pm - El manejador de persistencia
	 * @param regComercio - El registro de comercio del Hostal
	 * @return El objeto Hostal que tiene el regComercio dado
	 */
	public Hostal darHostalPorRegComercio(PersistenceManager pm, String regComercio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHostal() + " WHERE regcomercio = ?");
		q.setResultClass(Hostal.class);
		q.setParameters(regComercio);
		return (Hostal) q.executeUnique();
	}
}
