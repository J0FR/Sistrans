package uniandes.isis2304.parranderos.persistencia;

import java.sql.Timestamp;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Cliente;

class SQLCliente {
    
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
	public SQLCliente(PersistenciaAlohandes pa)
	{
		this.pa = pa;
	}

    /**
     * Crea y ejecuta la sentencia SQL para adicionar un CLIENTE a la base de datos de Alohandes
     * @param pm - El manejador de persistencia
     * @param identificacion - El identificador del cliente
     * @param nombre - El nombre del cliente
     * @param tipoVinculo - El tipo de vinculo del cliente
     * @param correoElectronico - El correo electronico del cliente
     * @param telefono - El telefono del cliente
     * @return El número de tuplas insertadas
     */
    public long adicionarCliente(PersistenceManager pm, String identificacion, String nombre, String tipoVinculo, String correoElectronico, String telefono, Timestamp ultimaFechaReserva) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaCliente() + "(identificacion, nombre, tipoVinculo, correoElectronico, telefono, ultimaFechaReserva) values (?, ?, ?, ?, ?, ?)");
            
        q.setParameters(identificacion, nombre, tipoVinculo, correoElectronico, telefono, ultimaFechaReserva);
        return (long) q.executeUnique();
    }
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN CLIENTE de la base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idCliente - El identificador del cliente
	 * @return El objeto CLIENTE que tiene el identificador dado
	 */
	public Cliente darClientePorIdentificacion(PersistenceManager pm, String idCliente) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaCliente () + " WHERE identificacion = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(idCliente);
		return (Cliente) q.executeUnique();
	}

	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar la ciudad de un Cliente en la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @param idCliente - El idCliente del Cliente
	 * @param ultimaFechaReserva - La nueva ultimaFechaReserva del Cliente
	 * @return El número de tuplas modificadas
	 */
	public long cambiarUltimaFechaReservaBebedor (PersistenceManager pm, String identificacion, Timestamp ultimaFechaReserva) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaCliente() + " SET ultimaFechaReserva = ? WHERE identificacion = ?");
	     q.setParameters(ultimaFechaReserva, identificacion);
	     return (long) q.executeUnique();            
	}
}
