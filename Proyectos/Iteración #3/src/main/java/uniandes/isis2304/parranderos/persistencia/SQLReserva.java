package uniandes.isis2304.parranderos.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Reserva;

class SQLReserva {
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
    public SQLReserva(PersistenciaAlohandes pa)
    {
        this.pa = pa;
    }

    /**
     * Crea y ejecuta la sentencia SQL para adicionar una RESERVA a la base de datos de Alohandes
     * @param pm - El manejador de persistencia
     * @param idReserva - El identificador de la reserva
     * @param fechaInicio - La fecha de inicio de la reserva
     * @param fechaFin - La fecha de fin de la reserva
     * @param identificacionCliente - La identificacion del cliente
     * @param idAlojamiento - El identificador del alojamiento
     * @param estado - El estado de la reserva
     * @return El número de tuplas insertadas
     */
    public long adicionarReserva (PersistenceManager pm, long idReserva, Timestamp fechaInicio, Timestamp fechaFin, String identificacionCliente, long idAlojamiento, String estado)
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaReserva () + "(id, fechaIni, fechaFin, identificacionCliente, idAlojamiento, estado) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(idReserva, fechaInicio, fechaFin, identificacionCliente, idAlojamiento, estado);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar una RESERVA de la base de datos de Alohandes, por su identificador
     * @param pm
     * @param idReserva
     * @return El número de tuplas eliminadas
     */
    public long eliminarReservaPorId (PersistenceManager pm, long idReserva)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaReserva () + " WHERE id = ?");
        q.setParameters(idReserva);
        return (long) q.executeUnique();
    }

    /**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UNA RESERVA de la base de datos de Alohandes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id - El id de la reserva
	 * @return El objeto Reserva que tiene el id dado
	 */
	public Reserva darReservaPorId(PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaReserva() + " WHERE id = ?");
		q.setResultClass(Reserva.class);
		q.setParameters(id);
		return (Reserva) q.executeUnique();
	}

    public List<Reserva> darReservasPorIdAlojamiento(PersistenceManager pm, long idAlojamiento)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaReserva() + " WHERE idAlojamiento = ?");
        q.setResultClass(Reserva.class); 
        q.setParameters(idAlojamiento);
        return q.executeList();
    }

    public Timestamp darUltimaFechaPorIdAlojamiento(PersistenceManager pm, long idAlojamiento)
    {
        Query q = pm.newQuery(SQL, "SELECT MAX(FechaFin) FROM " + pa.darTablaReserva() + " WHERE idAlojamiento = ?");
        q.setParameters(idAlojamiento);
        return (Timestamp) q.executeUnique();
    }

    public long actualizarReservaPorIdAlojamiento(PersistenceManager pm, long idReserva, long idAlojamiento)
    {
        Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaReserva() + " SET IDALOJAMIENTO = ? WHERE ID = ?");
        q.setParameters(idAlojamiento, idReserva);
        return (long) q.executeUnique();
    }

    public long actualizarEstadoReservaPorIdReserva(PersistenceManager pm, String estado, long idReserva)
    {
        Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaReserva() + " SET ESTADO = ? WHERE ID = ?");
        q.setParameters(estado, idReserva);
        return (long) q.executeUnique();
    }
}