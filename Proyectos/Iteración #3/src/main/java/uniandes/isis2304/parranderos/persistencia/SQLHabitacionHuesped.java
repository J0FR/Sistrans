package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.HabitacionHuesped;

public class SQLHabitacionHuesped {
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
	public SQLHabitacionHuesped(PersistenciaAlohandes pa)
	{
		this.pa = pa;
	}

    /**
     * Crea y ejecuta la sentencia SQL para adicionar un HabitacionHuesped a la base de datos de Alohandes
     * @param pm - El manejador de persistencia
     * @param idAlojamiento - El idAlojamiento de la HabitacionHuesped
     * @param comidas - El comidas de la HabitacionHuesped
     * @param tipoBanio - El tipoBanio de la HabitacionHuesped
     * @param tipoHabitacion - El tipoHabitacion de la HabitacionHuesped
     * @param dtoMesExtra - El dtoMesExtra de la HabitacionHuesped
     * @return El número de tuplas insertadas
     */
    public long adicionarHabitacionHuesped(PersistenceManager pm, long idAlojamiento, int comidas, String tipoBanio, String tipoHabitacion, int dtoMesExtra, String identificacionOperadorUsuario) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaHabitacionHuesped() + "(idAlojamiento, comidas, tipoBanio, tipoHabitacion, dtoMesExtra, identificacionOperadorUsuario) values (?, ?, ?, ?, ?, ?)");
            
        q.setParameters(idAlojamiento, comidas, tipoBanio, tipoHabitacion, dtoMesExtra, identificacionOperadorUsuario);
        return (long) q.executeUnique();
    }

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN HabitacionHuesped de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idAlojamiento - El identificador del alojamiento
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarHabitacionHuesped(PersistenceManager pm, long idAlojamiento)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaHabitacionHuesped() + " WHERE idalojamiento = ?");
        q.setParameters(idAlojamiento);
        return (long) q.executeUnique();
	}

	/**
     * Crea y ejecuta la sentencia SQL para encontrar la información de UNA HabitacionHuesped de la 
     * base de datos de Alohandes, por su identificador
     * @param pm - El manejador de persistencia
     * @param idHabHuesped - El identificador de la HabitacionHuesped
     * @return El objeto HabitacionHuesped que tiene el identificador dado
     */
    public HabitacionHuesped darHabitacionHuespedPorId(PersistenceManager pm, long idHabHuesped) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHabitacionHuesped() + " WHERE idAlojamiento = ?");
        q.setResultClass(HabitacionHuesped.class);
        q.setParameters(idHabHuesped);
        return (HabitacionHuesped) q.executeUnique();
    }
}
