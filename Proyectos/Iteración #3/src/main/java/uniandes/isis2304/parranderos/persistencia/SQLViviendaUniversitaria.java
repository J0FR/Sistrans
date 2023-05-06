package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.ViviendaUniversitaria;

public class SQLViviendaUniversitaria {
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
	public SQLViviendaUniversitaria(PersistenciaAlohandes pa)
	{
		this.pa = pa;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un VIVIENDAUNIVERSITARIA a la base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @param regComercio - El regComercio del ViviendaUniversitaria
	 * @param nit - El nit del ViviendaUniversitaria
	 * @param nombre - La nombre del ViviendaUniversitaria
	 * @param ganancias - Las ganancias del ViviendaUniversitaria 
     * @param precioSalaEstudio - El precioSalaEstudio del ViviendaUniversitaria
     * @param precioSalaEsparcimiento - El precioSalaEsparcimiento del ViviendaUniversitaria
     * @param precioGimnasio - El precioGimnasio del ViviendaUniversitaria
	 * @param restaurante - Si tiene restaurante el ViviendaUniversitaria
	 * @return El número de tuplas insertadas
	 */
	public long adicionarViviendaUniversitaria(PersistenceManager pm, String regComercio, String nit, String nombre, int precioSalaEstudio, int precioSalaEsparcimiento, int precioGimnasio, String restaurante) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaViviendaUniversitaria() + "(regComercio, nit, nombre, precioSalaEstudio, precioSalaEsparcimiento, precioGimnasio, restaurante) values (?, ?, ?, ?, ?, ?, ?) COMMIT");
            
        q.setParameters(regComercio, nit, nombre, precioSalaEstudio, precioSalaEsparcimiento, precioGimnasio, restaurante);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UNa ViviendaUniversitaria de la base de datos de Alohandes, por su regComercio
	 * @param pm - El manejador de persistencia
	 * @param regComercio - El registro de comercio del ViviendaUniversitaria
	 * @return El objeto ViviendaUniversitaria que tiene el regComercio dado
	 */
	public ViviendaUniversitaria darViviendaUniversitariaPorRegComercio(PersistenceManager pm, String regComercio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaViviendaUniversitaria() + " WHERE regcomercio = ?");
		q.setResultClass(ViviendaUniversitaria.class);
		q.setParameters(regComercio);
		return (ViviendaUniversitaria) q.executeUnique();
	}
}
