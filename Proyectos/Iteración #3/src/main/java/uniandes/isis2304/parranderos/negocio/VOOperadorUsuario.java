package uniandes.isis2304.parranderos.negocio;

public interface VOOperadorUsuario {
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return El identificacion
	 */
	public String getIdentificacion();

	/**
	 * @return El nombre
	 */
	public String getNombre();

	/**
	 * @return El tipoVinculo
	 */
	public String getTipoVinculo();
	
	/**
	 * @return El correoElectronico
	 */
	public String getCorreoElectronico();
	
	/**
	 * @return El telefono
	 */
	public String getTelefono();

	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString();
}
