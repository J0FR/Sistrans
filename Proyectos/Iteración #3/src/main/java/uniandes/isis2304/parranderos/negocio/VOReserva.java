package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;


public interface VOReserva {
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return El id
	 */
	public long getId();

	/**
	 * @return La fechaIni
	 */
	public Timestamp getFechaIni();
	
	/**
	 * @return La fechaFin
	 */
	public Timestamp getFechaFin();
	
	/**
	 * @return El identificacionCliente
	 */
	public String getIdentificacionCliente();
	
	/**
	 * @return El idAlojamiento
	 */
	public long getIdAlojamiento();

	/**
	 * @return El estado
	 */
	public String getEstado();

	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString();
}
