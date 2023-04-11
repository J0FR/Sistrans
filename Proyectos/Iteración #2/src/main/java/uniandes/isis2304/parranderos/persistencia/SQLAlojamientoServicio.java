package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLAlojamientoServicio {
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
	public SQLAlojamientoServicio(PersistenciaAlohandes pa)
	{
		this.pa = pa;
	}

    /**
     * Crea y ejecuta la sentencia SQL para adicionar un AlojamientoServicio a la base de datos de Alohandes
     * @param pm - El manejador de persistencia
     * @param idAlojamiento - El idAlojamiento de la AlojamientoServicio
     * @param idServicio - El idServicio de la AlojamientoServicio
     * @param costo - El costo de la AlojamientoServicio
     * @return El número de tuplas insertadas
     */
    public long adicionarAlojamientoServicio(PersistenceManager pm, long idAlojamiento, long idServicio, int costo) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaAlojamientoServicio() + "(idAlojamiento, idServicio, costo) values (?, ?, ?)");
            
        q.setParameters(idAlojamiento, idServicio, costo);
        return (long) q.executeUnique();
    }

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN AlojamientoServicio de la base de datos de Alhandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idAlojamiento - El identificador del alojamiento
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarAlojamientoServicioPorIdAlojamiento(PersistenceManager pm, long idAlojamiento)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaAlojamientoServicio() + " WHERE idalojamiento = ?");
        q.setParameters(idAlojamiento);
        return (long) q.executeUnique();
	}
}