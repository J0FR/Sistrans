package uniandes.isis2304.parranderos.negocio;

public interface VOApartamentoAlquiler {
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return El idAlojamiento
	 */
	public long getIdAlojamiento();
	
	/**
	 * @return El servPublico
	 */
	public String getServPublico();
	
	/**
	 * @return El administracion
	 */
	public String getAdministracion();

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
