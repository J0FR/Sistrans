package uniandes.isis2304.parranderos.negocio;


public interface VOHostal {

    /* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

    /**
	 * @return El reg comercio (RUE) del hostal
	 */
    public String getRegComercio();

	/**
	 * @return El nit del hostal
	 */
	public String getNit();

	/**
	 * @return El nombre del hostal
	 */
	public String getNombre();

	/**
	 * @return La hora de apertura de la recepción del hostal
	 */
	public String getHoraAperturaRecepcion();

	/**
	 * @return La hora de cierre de la recepción del hostal
	 */
	public String getHoraCierreRecepcion();

	/**
	 * @return Una cadena de caracteres con la información del hostal
	 */
	@Override
	public String toString();
}
