package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.ApartamentoAlquiler;

public class SQLApartamentoAlquiler {
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
	public SQLApartamentoAlquiler(PersistenciaAlohandes pa)
	{
		this.pa = pa;
	}

    /**
     * Crea y ejecuta la sentencia SQL para adicionar un ApartamentoAlquiler a la base de datos de Alohandes
     * @param pm - El manejador de persistencia
     * @param idAlojamiento - El idAlojamiento de la ApartamentoAlquiler
     * @param servPublico - El servPublico de la ApartamentoAlquiler
     * @param administracion - El administracion de la ApartamentoAlquiler
     * @return El número de tuplas insertadas
     */
    public long adicionarApartamentoAlquiler(PersistenceManager pm, long idAlojamiento, String servPublico, String administracion, String identificacionOperadorUsuario) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaApartamentoAlquiler() + "(idAlojamiento, servPublico, administracion, identificacionOperadorUsuario) values (?, ?, ?, ?) COMMIT");
            
        q.setParameters(idAlojamiento, servPublico, administracion, identificacionOperadorUsuario);
        return (long) q.executeUnique();
    }

    /**
	 * Crea y ejecuta la sentencia SQL para eliminar UN ApartamentoAlquiler de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idAlojamiento - El identificador del alojamiento
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarApartamentoAlquiler(PersistenceManager pm, long idAlojamiento)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaApartamentoAlquiler() + " WHERE idalojamiento = ? COMMIT");
        q.setParameters(idAlojamiento);
        return (long) q.executeUnique();
	}

	/**
     * Crea y ejecuta la sentencia SQL para encontrar la información de UN ApartamentoAlquiler de la 
     * base de datos de Alohandes, por su identificador
     * @param pm - El manejador de persistencia
     * @param idApartamentoAlquiler - El identificador del ApartamentoAlquiler
     * @return El objeto ApartamentoAlquiler que tiene el identificador dado
     */
    public ApartamentoAlquiler darApartamentoAlquilerPorId(PersistenceManager pm, long idApartamentoAlquiler) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaApartamentoAlquiler() + " WHERE idAlojamiento = ?");
        q.setResultClass(ApartamentoAlquiler.class);
        q.setParameters(idApartamentoAlquiler);
        return (ApartamentoAlquiler) q.executeUnique();
    }
}
