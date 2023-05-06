package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.HabitacionViviendaUniversitaria;

class SQLHabitacionViviendaUniversitaria {
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
    public SQLHabitacionViviendaUniversitaria(PersistenciaAlohandes pa)
    {
        this.pa = pa;
    }

    /**
     * Crea y ejecuta la sentencia SQL para adicionar un alojamiento a la base de datos de Alohandes
     * @param pm - El manejador de persistencia
     * @param idHabVivUni - Es el identificador de la habitacion de la vivienda universitaria
     * @param tipoHabitacion - Es el tipo de habitacion de la vivienda universitaria
     * @param capacidad - Es la capacidad de la habitacion de la vivienda universitaria
     * @param idViviendaUniversitaria - Es el identificador de la vivienda universitaria a la que pertenece la habitacion
     * @return El número de tuplas insertadas
     */
    public long adicionarHabitacionViviendaUniversitaria(PersistenceManager pm, long idHabVivUni, String tipoHabitacion, int capacidad, String idViviendaUniversitaria ) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaHabitacionViviendaUniversitaria() + "(id, tipoHabitacion, capacidad, idViviendaUniversitaria) values (?, ?, ?, ?) COMMIT");
            
        q.setParameters(idHabVivUni, tipoHabitacion, capacidad, idViviendaUniversitaria);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar una RESERVA de la base de datos de Alohandes, por su identificador
     * @param pm - El manejador de persistencia
     * @param idHabVivUni - Es el identificador de la habitacion de la vivienda universitaria
     * @return El número de tuplas eliminadas
     */
    public long eliminarHabitacionViviendaUniversitariaPorId(PersistenceManager pm, long idHabVivUni) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaHabitacionViviendaUniversitaria() + " WHERE id = ? COMMIT");
        q.setParameters(idHabVivUni);
        return (long) q.executeUnique();
    }


    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de UNA HabitacionViviendaUniversitaria de la 
     * base de datos de Alohandes, por su identificador
     * @param pm - El manejador de persistencia
     * @param idHabVivUniversitaria - El identificador de la HabitacionViviendaUniversitaria
     * @return El objeto HabitacionViviendaUniversitaria que tiene el identificador dado
     */
    public HabitacionViviendaUniversitaria darHabitacionViviendaUniversitariaPorId(PersistenceManager pm, long idHabVivUniversitaria) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHabitacionViviendaUniversitaria() + " WHERE id = ?");
        q.setResultClass(HabitacionViviendaUniversitaria.class);
        q.setParameters(idHabVivUniversitaria);
        return (HabitacionViviendaUniversitaria) q.executeUnique();
    }
}
