package uniandes.isis2304.parranderos.negocio;

public interface VOViviendaTemporal {
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return El idAlojamiento
	 */
	public long getIdAlojamiento();
	
	/**
	 * @return El numeroHabitaciones
	 */
	public int getNumeroHabitaciones();
	
	/**
	 * @return El seguroArrendamiento
	 */
	public int getPrecioSeguroArrendamiento();
	
	/**
	 * @return El caractSeguro
	 */
	public String getCaractSeguro();
	
	/**
	 * @return El diasAlquilado
	 */
	public int getDiasAlquilado();

	/**
	 * @return El IdentificacionOperadorUsuario
	 */
	public String getIdentificacionOperadorUsuario();

	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString();
}
