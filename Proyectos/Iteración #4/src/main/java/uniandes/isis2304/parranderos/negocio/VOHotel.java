package uniandes.isis2304.parranderos.negocio;

public interface VOHotel {
    
    /* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * @return El registro de comercio del hotel
	 */
	public String getRegComercio();

	/**
	 * @return El nit del hotel
	 */
	public String getNit();

	/**
	 * @return El nombre del hotel
	 */
	public String getNombre();

	/**
	 * @return El indicador de si el hotel tiene restaurante ('Y' o 'N')
	 */
	public String getRestaurante();
	
	/**
	 * @return El indicador de si el hotel tiene parqueadero ('Y' o 'N')
	 */
	public String getParqueadero();

	/**
	 * @return El indicador de si el hotel tiene piscina ('Y' o 'N')
	 */
	public String getPiscina();

	/**
	 * @return Una cadena de caracteres con la información del hotel
	 */
	@Override
	public String toString();

}
