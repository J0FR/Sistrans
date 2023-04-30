package uniandes.isis2304.parranderos.negocio;

public interface VOHabitacionHuesped {
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return El id
	 */
	public long getIdAlojamiento();

	/**
	 * @return Las comidas
	 */
	public int getComidas();
	
	/**
	 * @return El tipoBanio
	 */
	public String getTipoBanio();
	
	/**
	 * @return El tipoHabitacion
	 */
	public String getTipoHabitacion();
	
	/**
	 * @return El dtoMesExtra
	 */
	public int getDtoMesExtra();

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
