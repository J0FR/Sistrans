package uniandes.isis2304.parranderos.negocio;

public interface VOAlojamiento {
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return El id
	 */
	public long getId();

	/**
	 * @return El ubicacion
	 */
	public String getUbicacion();
	
	/**
	 * @return El durancionMin
	 */
	public int getDuracionMin();
	
	/**
	 * @return El costoBase
	 */
	public int getCosto();

	/**
	 * @return El estatus
	 */
	public String getEstatus();

	/**
	 * @return El tipo del alojamiento
	 */
	public String getTipoAlojamiento();
	

	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString();
}
