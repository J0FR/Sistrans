package uniandes.isis2304.parranderos.negocio;

public interface VOServicio {
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return El id
	 */
	public long getId();

	/**
	 * @return El tipo
	 */
	public String getTipo();

	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString();
}
