package uniandes.isis2304.parranderos.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.OperadorUsuario;

public class SQLOperadorUsuario {
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
	public SQLOperadorUsuario(PersistenciaAlohandes pa)
	{
		this.pa = pa;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un BAR a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param identificacionCliente - El identificacionCliente del OperadorUsuario
	 * @param nombre - El nombre del OperadorUsuario
	 * @param tipoVinculo - La tipoVinculo del OperadorUsuario
	 * @param correoElectronico - El correoElectronico del OperadorUsuario 
	 * @param telefono - El número de telefono del OperadorUsuario
     * @param ganancias - Las ganancias del OperadorUsuario 
	 * @return El número de tuplas insertadas
	 */
	public long adicionarOperadorUsuario (PersistenceManager pm, String identificacion, String nombre, String tipoVinculo, String correoElectronico, String telefono) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaOperadorUsuario() + "(identificacion, nombre, tipoVinculo, correoElectronico, telefono) values (?, ?, ?, ?, ?)");
            
        q.setParameters(identificacion, nombre, tipoVinculo, correoElectronico, telefono);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de el dinero recibido por cada proveedor de alojamiento durante
	 * el anio actual y el anio corrido
	 * @param pm - El manejador de persistencia
	 * @return Una lista de arreglos de objetos
	 */
	public List<Object> darDineroRecibido(PersistenceManager pm)
	{
	    String sql = "SELECT ID_PROVEEDOR, DINERO_RECIBIDO_ANIO_ACTUAL, DINERO_RECIBIDO_ANIO_CORRIDO FROM ( SELECT ID_PROVEEDOR, SUM(CASE WHEN A_RESERVA.FECHAINI >= TRUNC(SYSDATE, 'YEAR') AND A_RESERVA.FECHAINI < ADD_MONTHS(TRUNC(SYSDATE, 'YEAR'), 12) THEN COSTO ELSE 0 END) AS DINERO_RECIBIDO_ANIO_ACTUAL, SUM(CASE WHEN A_RESERVA.FECHAINI >= ADD_MONTHS(SYSDATE, -12) AND A_RESERVA.FECHAINI < SYSDATE THEN COSTO ELSE 0 END) AS DINERO_RECIBIDO_ANIO_CORRIDO FROM ( SELECT A_ALOJAMIENTO.ID, COALESCE(A_HABITACIONHOTEL.IDHOTEL, A_HABITACIONHOSTAL.IDHOSTAL, A_HABITACIONVIVIENDAUNIVERSITARIA.IDVIVIENDAUNIVERSITARIA, A_HABITACIONHUESPED.IDENTIFICACIONOPERADORUSUARIO, A_VIVIENDATEMPORAL.IDENTIFICACIONOPERADORUSUARIO, A_APARTAMENTOALQUILER.IDENTIFICACIONOPERADORUSUARIO) AS ID_PROVEEDOR, A_ALOJAMIENTO.COSTO FROM " + pa.darTablaAlojamiento() + " LEFT JOIN " + pa.darTablaHabitacionHotel() + " ON A_ALOJAMIENTO.ID = A_HABITACIONHOTEL.ID LEFT JOIN " + pa.darTablaHabitacionHostal() + " ON A_ALOJAMIENTO.ID = A_HABITACIONHOSTAL.ID LEFT JOIN " + pa.darTablaHabitacionViviendaUniversitaria() + " ON A_ALOJAMIENTO.ID = A_HABITACIONVIVIENDAUNIVERSITARIA.ID LEFT JOIN " + pa.darTablaHabitacionHuesped() + " ON A_ALOJAMIENTO.ID = A_HABITACIONHUESPED.IDALOJAMIENTO LEFT JOIN " + pa.darTablaViviendaTemporal() + " ON A_ALOJAMIENTO.ID = A_VIVIENDATEMPORAL.IDALOJAMIENTO LEFT JOIN " + pa.darTablaApartamentoAlquiler() + " ON A_ALOJAMIENTO.ID = A_APARTAMENTOALQUILER.IDALOJAMIENTO ) AP JOIN A_RESERVA ON AP.ID = A_RESERVA.IDALOJAMIENTO GROUP BY ID_PROVEEDOR ) GANANCIAS ORDER BY ID_PROVEEDOR";
		
	    Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN OPERADORUSUARIO de la base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idOperadorUsuario - El identificador del operador usuario
	 * @return El objeto OPERADORUSUARIO que tiene el identificador dado
	 */
	public OperadorUsuario darOperadorUsuarioPorIdentificacion(PersistenceManager pm, String idOperadorUsuario) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOperadorUsuario() + " WHERE identificacion = ?");
		q.setResultClass(OperadorUsuario.class);
		q.setParameters(idOperadorUsuario);
		return (OperadorUsuario) q.executeUnique();
	}
}
