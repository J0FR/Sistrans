package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.HabitacionHotel;

class SQLHabitacionHotel {
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
    public SQLHabitacionHotel(PersistenciaAlohandes pa)
    {
        this.pa = pa;
    }

    /**
     * Crea y ejecuta la sentencia SQL para adicionar una habitacioon de hotel a la base de datos de Alohandes
     * @param pm - El manejador de persistencia
     * @param idHabHotel - Es el identificador de la habitacion del hotel
     * @param tipoHabitacion - Es el tipo de habitacion del hotel
     * @param tamanio - Es el tamanio de la habitacion del hotel
     * @param idHotel - Es el identificador del hotel al que pertenece la habitacion
     * @return El número de tuplas insertadas
     */
    public long adicionarHabitacionHotel(PersistenceManager pm, long idHabHotel, String tipoHabitacion, String tamanio, String idHotel ) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaHabitacionHotel() + "(id, tipoHabitacion, tamanio, idHotel) values (?, ?, ?, ?) ");
            
        q.setParameters(idHabHotel, tipoHabitacion, tamanio, idHotel );
        return (long) q.executeUnique();
    }


    /**
     * Crea y ejecuta la sentencia SQL para eliminar HabitacionHotel de la base de datos de Alohandes, por su identificador
     * @param pm - El manejador de persistencia
     * @param idHabHotel - El identificador de la HabitacionHotel
     * @return EL número de tuplas eliminadas
     */
    public long eliminarHabitacionHotelPorId(PersistenceManager pm, long idHabHotel) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaHabitacionHotel() + " WHERE id = ? ");
        q.setParameters(idHabHotel);
        return (long) q.executeUnique();
    }


    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de UNA HabitacionHotel de la 
     * base de datos de Alohandes, por su identificador
     * @param pm - El manejador de persistencia
     * @param idHabHotel - El identificador de la HabitacionHotel
     * @return El objeto HabitacionHotel que tiene el identificador dado
     */
    public HabitacionHotel darHabitacionHotelPorId(PersistenceManager pm, long idHabHotel) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHabitacionHotel() + " WHERE id = ?");
        q.setResultClass(HabitacionHotel.class);
        q.setParameters(idHabHotel);
        return (HabitacionHotel) q.executeUnique();
    }
        
    
}
