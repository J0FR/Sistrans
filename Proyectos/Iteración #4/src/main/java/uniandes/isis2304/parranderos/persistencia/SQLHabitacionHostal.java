package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.HabitacionHostal;

class SQLHabitacionHostal {
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
    public SQLHabitacionHostal(PersistenciaAlohandes pa)
    {
        this.pa = pa;
    }

    /**
     * Crea y ejecuta la sentencia SQL para adicionar una Habitacion de Hostal a la base de datos de Alohandes
     * @param pm - El manejador de persistencia
     * @param idHabHostal - El identificador de la HabitacionHostal
     * @param aforo - El aforo de la HabitacionHostal
     * @param idHostal - El Reg de comercio del hostal al que pertenece la habitacion 
     * @return El número de tuplas insertadas
     */
    public long adicionarHabitacionHostal(PersistenceManager pm, long idHabHostal, int aforo, String idHostal ) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaHabitacionHostal() + "(id, aforo, idHostal) values (?, ?, ?) ");
            
        q.setParameters(idHabHostal, aforo, idHostal);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar HabitacionHostal de la base de datos de Alohandes, por su identificador
     * @param pm - El manejador de persistencia
     * @param idHabHostal - El identificador de la HabitacionHostal
     * @return EL número de tuplas eliminadas
     */

    public long eliminarHabitacionHostalPorId(PersistenceManager pm, long idHabHostal) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaHabitacionHostal() + " WHERE id = ? ");
        q.setParameters(idHabHostal);
        return (long) q.executeUnique();
    }



    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de UNA HabitacionHostal de la
     * base de datos de Alohandes, por su identificador
     * @param pm - El manejador de persistencia
     * @param idHabHostal - El identificador de la HabitacionHostal
     * @return El objeto HabitacionHostal que tiene el identificador dado
     */
    public HabitacionHostal darHabitacionHostalPorId(PersistenceManager pm, long idHabHostal)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHabitacionHostal() + " WHERE id = ?");
        q.setResultClass(HabitacionHostal.class);
        q.setParameters(idHabHostal);
        return (HabitacionHostal) q.executeUnique();
    }

    
}
