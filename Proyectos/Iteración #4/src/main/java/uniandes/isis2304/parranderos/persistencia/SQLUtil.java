/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLUtil
{
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
	public SQLUtil (PersistenciaAlohandes pa)
	{
		this.pa = pa;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pa.darSeqAlohandes() + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}

	public Object[] analizarOperacionesDeAlohandes(PersistenceManager pm, int cantidadDeDias, String tipoHabitacion)
	{

		String sql = "SELECT MAX(DECODE(ranking_reservas, 1, year)) AS year_max_reservas, MAX(DECODE(ranking_reservas, 1, month)) AS month_max_reservas, MAX(DECODE(ranking_ganancias, 1, year)) AS year_max_ganancias, MAX(DECODE(ranking_ganancias, 1, month)) AS month_max_ganancias, MIN(DECODE(ranking_reservas, total_months, year)) AS year_min_reservas, MIN(DECODE(ranking_reservas, total_months, month)) AS month_min_reservas FROM ( SELECT EXTRACT(YEAR FROM A_RESERVA.FECHAINI) AS year, EXTRACT(MONTH FROM A_RESERVA.FECHAINI) AS month, COUNT(A_RESERVA.ID) AS total_reservas, SUM(GANANCIA) AS total_ganancias, DENSE_RANK() OVER (ORDER BY COUNT(A_RESERVA.ID) DESC) AS ranking_reservas, DENSE_RANK() OVER (ORDER BY SUM(GANANCIA) DESC) AS ranking_ganancias, COUNT(*) OVER () AS total_months FROM A_RESERVA JOIN A_Alojamiento ON A_RESERVA.IDALOJAMIENTO = A_Alojamiento.ID WHERE A_RESERVA.FECHAFIN - A_RESERVA.FECHAINI < ? AND A_Alojamiento.TIPOALOJAMIENTO = ? GROUP BY EXTRACT(YEAR FROM A_RESERVA.FECHAINI), EXTRACT(MONTH FROM A_RESERVA.FECHAINI) )";
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(cantidadDeDias, tipoHabitacion);
		return (Object[]) q.executeUnique();
	}

	// /**
	//  * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	//  * @param pm - El manejador de persistencia
	//  * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	//  * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	//  */
	// public long [] limpiarParranderos (PersistenceManager pm)
	// {
    //     Query qGustan = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaGustan ());          
    //     Query qSirven = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaSirven ());
    //     Query qVisitan = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaVisitan ());
    //     Query qBebida = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaBebida ());
    //     Query qTipoBebida = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaTipoBebida ());
    //     Query qBebedor = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaBebedor ());
    //     Query qBar = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaBar ());

    //     long gustanEliminados = (long) qGustan.executeUnique ();
    //     long sirvenEliminados = (long) qSirven.executeUnique ();
    //     long visitanEliminadas = (long) qVisitan.executeUnique ();
    //     long bebidasEliminadas = (long) qBebida.executeUnique ();
    //     long tiposBebidaEliminados = (long) qTipoBebida.executeUnique ();
    //     long bebedoresEliminados = (long) qBebedor.executeUnique ();
    //     long baresEliminados = (long) qBar.executeUnique ();
    //     return new long[] {gustanEliminados, sirvenEliminados, visitanEliminadas, bebidasEliminadas, 
    //     		tiposBebidaEliminados, bebedoresEliminados, baresEliminados};
	// }

}
