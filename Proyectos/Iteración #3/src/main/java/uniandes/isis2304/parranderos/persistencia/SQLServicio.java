package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLServicio {
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
    public SQLServicio(PersistenciaAlohandes pa)
    {
        this.pa = pa;
    }

    /**
     * Crea y ejecuta la sentencia SQL para adicionar una SERVICIO a la base de datos de Alohandes
     * @param pm - El manejador de persistencia
     * @param id - El id de la SERVICIO
     * @param tipo - La tipo de la SERVICIO
     * @return El número de tuplas insertadas
     */
    public long adicionarServicio (PersistenceManager pm, long id, String tipo)
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaServicio() + "(id, tipo) values (?, ?)");
        q.setParameters(id, tipo);
        return (long) q.executeUnique();
    }
}
