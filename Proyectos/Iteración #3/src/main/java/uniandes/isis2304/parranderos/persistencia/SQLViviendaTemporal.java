package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.ViviendaTemporal;


public class SQLViviendaTemporal {
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
	public SQLViviendaTemporal(PersistenciaAlohandes pa)
	{
		this.pa = pa;
	}

    /**
     * Crea y ejecuta la sentencia SQL para adicionar un Vivienda Temporal a la base de datos de Alohandes
     * @param pm - El manejador de persistencia
     * @param idAlojamiento - El idAlojamiento de la Vivienda Temporal
     * @param numeroHabitaciones - El numeroHabitaciones de la Vivienda Temporal
     * @param precioSeguroArrendamiento - El precioSeguroArrendamiento de la Vivienda Temporal
     * @param caractSeguro - Las caracteristicas del seguro de la Vivienda Temporal
     * @param diasAlquilado - Los dias que lleva Alquilado de la Vivienda Temporal
     * @return El número de tuplas insertadas
     */
    public long adicionarViviendaTemporal(PersistenceManager pm, long idAlojamiento, int numeroHabitaciones, int precioSeguroArrendamiento, String caractSeguro, int diasAlquilado, String identificacionOperadorUsuario) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaViviendaTemporal() + "(idAlojamiento, numeroHabitaciones, precioSeguroArrendamiento, caractSeguro, diasAlquilado, identificacionOperadorUsuario) values (?, ?, ?, ?, ?, ?)");
            
        q.setParameters(idAlojamiento, numeroHabitaciones, precioSeguroArrendamiento, caractSeguro, diasAlquilado, identificacionOperadorUsuario);
        return (long) q.executeUnique();
    }

    /**
	 * Crea y ejecuta la sentencia SQL para eliminar UN Vivienda Temporal de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idAlojamiento - El identificador del alojamiento
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarViviendaTemporal(PersistenceManager pm, long idAlojamiento)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaViviendaTemporal() + " WHERE idalojamiento = ?");
        q.setParameters(idAlojamiento);
        return (long) q.executeUnique();
	}

	/**
     * Crea y ejecuta la sentencia SQL para encontrar la información de UNA ViviendaTemporal de la 
     * base de datos de Alohandes, por su identificador
     * @param pm - El manejador de persistencia
     * @param idViviendaTemporal - El identificador de la ViviendaTemporal
     * @return El objeto ViviendaTemporal que tiene el identificador dado
     */
    public ViviendaTemporal darViviendaTemporalPorId(PersistenceManager pm, long idViviendaTemporal) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaViviendaTemporal() + " WHERE idAlojamiento = ?");
        q.setResultClass(ViviendaTemporal.class);
        q.setParameters(idViviendaTemporal);
        return (ViviendaTemporal) q.executeUnique();
    }
}
