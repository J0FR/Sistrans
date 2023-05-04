package uniandes.isis2304.parranderos.persistencia;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Alojamiento;

public class SQLAlojamiento {
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
	public SQLAlojamiento(PersistenciaAlohandes pa)
	{
		this.pa = pa;
	}

    /**
     * Crea y ejecuta la sentencia SQL para adicionar un alojamiento a la base de datos de Alohandes
     * @param pm - El manejador de persistencia
     * @param id - La id de un alojamiento
	 * @param ubicacion - La ubicacion de un alojamiento
	 * @param duracionMin - La duracionMin de un alojamiento
	 * @param costo - El costo de un alojamiento
     * @return El número de tuplas insertadas
     */
    public long adicionarAlojamiento(PersistenceManager pm, long id, String ubicacion, int duracionMin, int costo, String estatus) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaAlojamiento() + "(id, ubicacion, duracionMin, costo, estatus) values (?, ?, ?, ?, ?)");
            
        q.setParameters(id, ubicacion, duracionMin, costo, estatus);
        return (long) q.executeUnique();
    }

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN ALOJAMIENTO de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador del alojamiento
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarAlojamiento (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaAlojamiento () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar las 20 ofertas mas populares
	 * @param pm - El manejador de persistencia
	 * @return Una lista de arreglos de objetos
	 */
	public List<Object> darTop20Ofertas(PersistenceManager pm)
	{
	    String sql = "SELECT A_ALOJAMIENTO.ID, A_ALOJAMIENTO.UBICACION, A_ALOJAMIENTO.DURACIONMIN, A_ALOJAMIENTO.COSTO, COUNT(A_RESERVA.IDALOJAMIENTO) AS TOTAL_RESERVAS FROM A_ALOJAMIENTO JOIN A_RESERVA ON A_ALOJAMIENTO.ID = A_RESERVA.IDALOJAMIENTO GROUP BY A_ALOJAMIENTO.ID, A_ALOJAMIENTO.UBICACION, A_ALOJAMIENTO.DURACIONMIN, A_ALOJAMIENTO.COSTO ORDER BY TOTAL_RESERVAS DESC FETCH FIRST 20 ROWS ONLY";
		
	    Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}
	
	/**
	 * RFC4
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los alojamientos que no se encuentran reservados en un rango de fechas 
	 * y que tienen un tipo de servicio dado
	 * @param pm  - El manejador de persistencia
	 * @param fechaIni - La fecha inicial
	 * @param fechaFin - La fecha final
	 * @param tiposServicio - Los tipos de servicio que debe tener el alojamiento
	 * @return Una lista de tuplas con la información de los alojamientos que cumplen con la condición
	 */
	public List<Object []> darAlojamientosConCondicion(PersistenceManager pm, Timestamp fechaIni, Timestamp fechaFin, List<String> tiposServicio)
	{

		String sql = "SELECT DISTINCT A_ALOJAMIENTO.ID ID_0, A_ALOJAMIENTO.UBICACION UBICACION, A_ALOJAMIENTO.COSTO COSTO ";
		sql+= " FROM ((A_ALOJAMIENTO LEFT JOIN A_RESERVA ON A_ALOJAMIENTO.ID = A_RESERVA.IDALOJAMIENTO) ";
		sql+= " LEFT JOIN A_ALOJAMIENTOSERVICIO ON A_ALOJAMIENTOSERVICIO.IDALOJAMIENTO = A_ALOJAMIENTO.ID) ";
		sql+= "	LEFT JOIN A_SERVICIO ON A_ALOJAMIENTOSERVICIO.IDSERVICIO = A_SERVICIO.ID ";
		sql+= " WHERE A_ALOJAMIENTO.ID NOT IN (Select A_RESERVA.IDALOJAMIENTO ";
		sql+= "	FROM A_RESERVA ";
		sql+= " WHERE ? BETWEEN FECHAINI AND FECHAFIN ";
		sql+= "	OR ? BETWEEN FECHAINI AND FECHAFIN) ";
		sql+= "	AND A_ALOJAMIENTO.ESTATUS = 'Y' ";
		if (!tiposServicio.isEmpty()) {
			String tiposServicioParam = String.join(",", Collections.nCopies(tiposServicio.size(), "?"));
			sql += " AND A_SERVICIO.TIPO IN (" + tiposServicioParam + ")";
		}
			
		sql+= " GROUP BY A_ALOJAMIENTO.ID,UBICACION, A_ALOJAMIENTO.COSTO, FECHAINI, FECHAFIN ";
		sql+= " HAVING ( ? < COALESCE(A_RESERVA.FECHAINI, TO_DATE('11-11-1111', 'DD-MM-YYYY')) ";
		sql+= " AND ? < COALESCE(A_RESERVA.FECHAINI, TO_DATE('11-11-1111', 'DD-MM-YYYY'))) ";
		sql+= " OR ";
		sql+= " ( ? > COALESCE(A_RESERVA.FECHAFIN, TO_DATE('11-11-1111', 'DD-MM-YYYY')) ";
		sql+= " AND ? > COALESCE(A_RESERVA.FECHAFIN, TO_DATE('11-11-1111', 'DD-MM-YYYY'))) ";

		Query q = pm.newQuery(SQL, sql);
		List<Object> parameters = new ArrayList<>();
		
		
		parameters.add(fechaIni);
		parameters.add(fechaFin);

		if(!tiposServicio.isEmpty()) {
			parameters.addAll(tiposServicio);
		}

		parameters.add(fechaIni);
		parameters.add(fechaFin);

		parameters.add(fechaIni);
		parameters.add(fechaFin);
		
		q.setParameters(parameters.toArray());
		return q.executeList();
	}

	public List<Object[]> darAlojamientosDisponiblesSegunTipo(PersistenceManager pm, Timestamp fechaIni, Timestamp fechaFin, String tipo)
	{

		String tipoId = "ID";
		
		if(tipo.equals("A_HABITACIONHUESPED") || tipo.equals("A_VIVIENDATEMPORAL") || tipo.equals("A_APARTAMENTOALQUILER"))
		{
			tipoId = "IDALOJAMIENTO";
		}

		String sql = "SELECT DISTINCT A_ALOJAMIENTO.ID ID_0, A_ALOJAMIENTO.UBICACION UBICACION, A_ALOJAMIENTO.COSTO COSTO ";
		sql+= " FROM (A_ALOJAMIENTO LEFT JOIN A_RESERVA ON A_ALOJAMIENTO.ID = A_RESERVA.IDALOJAMIENTO) ";
		sql+= " WHERE A_ALOJAMIENTO.ID NOT IN (Select A_RESERVA.IDALOJAMIENTO ";
		sql+= "	FROM A_RESERVA ";
		sql+= " WHERE ? BETWEEN FECHAINI AND FECHAFIN ";
		sql+= "	OR ? BETWEEN FECHAINI AND FECHAFIN) ";
		sql+= " AND A_ALOJAMIENTO.ID IN ( SELECT " + tipo + "." + tipoId + " ";
		sql+= " FROM " + tipo + " ) ";
		sql+= "	AND A_ALOJAMIENTO.ESTATUS = 'Y' ";
		sql+= " GROUP BY A_ALOJAMIENTO.ID,UBICACION, A_ALOJAMIENTO.COSTO, FECHAINI, FECHAFIN ";
		sql+= " HAVING ( ? < COALESCE(A_RESERVA.FECHAINI, TO_DATE('11-11-1111', 'DD-MM-YYYY')) ";
		sql+= " AND ? < COALESCE(A_RESERVA.FECHAINI, TO_DATE('11-11-1111', 'DD-MM-YYYY'))) ";
		sql+= " OR ";
		sql+= " ( ? > COALESCE(A_RESERVA.FECHAFIN, TO_DATE('11-11-1111', 'DD-MM-YYYY')) ";
		sql+= " AND ? > COALESCE(A_RESERVA.FECHAFIN, TO_DATE('11-11-1111', 'DD-MM-YYYY'))) ";

		Query q = pm.newQuery(SQL, sql);
		List<Object> parameters = new ArrayList<>();
		
		
		parameters.add(fechaIni);
		parameters.add(fechaFin);

		parameters.add(fechaIni);
		parameters.add(fechaFin);

		parameters.add(fechaIni);
		parameters.add(fechaFin);
		
		q.setParameters(parameters.toArray());
		return q.executeList();

	}

	/**
	 * RFC3 - Crea y ejecuta la sentencia SQL para encontrar el indice de ocupación de los alojamientos
	 * 
	 * @param pm - El manejador de persistencia
	 * @return Una lista de tuplas con la información de los alojamientos y su indice de ocupación
	 */
	public List<Object[]> darIndiceOcupacion(PersistenceManager pm)
	{

		String sql = "SELECT A.ID, A.UBICACION, NVL(RA.DIAS_RESERVADOS, 0) AS DIAS_RESERVADOS, ROUND((NVL(RA.DIAS_RESERVADOS, 0) / 365) * 100, 2) AS INDICE_OCUPACION";
		sql += " FROM ";
		sql += pa.darTablaAlojamiento() + " A ";
		sql += " LEFT JOIN ";
		sql += "( SELECT IDALOJAMIENTO, SUM(FECHAFIN - FECHAINI) AS DIAS_RESERVADOS ";
		sql += " FROM ";
		sql += pa.darTablaReserva();
		sql += "  HAVING SUM(FECHAFIN - FECHAINI)<=365 ";
		sql += "  GROUP BY IDALOJAMIENTO ) RA ";
		sql += " ON A.ID = RA.IDALOJAMIENTO ";
		sql += " ORDER BY A.ID ";
		
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}

	/**
	 * RF9 - Deshabilitar un alojamiento, cambiar su estado a inactivo(N)
	 * @param pm
	 * @param id
	 */
	public long deshabilitarAlojamiento(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaAlojamiento() + " SET ESTATUS = 'N' WHERE ID = ?");
		q.setParameters(id);
		return (long) q.executeUnique();
	}



	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UNA RESERVA de la base de datos de Alohandes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id - El id de la reserva
	 * @return El objeto Reserva que tiene el id dado
	 */
	public Alojamiento darAlojamientoPorId(PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaAlojamiento() + " WHERE id = ?");
		q.setResultClass(Alojamiento.class);
		q.setParameters(id);
		return (Alojamiento) q.executeUnique();
	}
}
