package uniandes.isis2304.parranderos.negocio;

public interface VOViviendaUniversitaria {
    
    /* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
    

	/**
	 * @return El registro de comercio de la vivienda universitaria
	 */
	public String getRegComercio();

	/**
	 * @return El nit de la vivienda universitaria
	 */
	public String getNit();

	/**
	 * @return El nombre de la vivienda universitaria
	 */
	public String getNombre();

	/**
	 * @return El precio de la sala de estudio
	 */
	public int getPrecioSalaEstudio();

	/**
	 * @return El precio de la sala de esparcimiento
	 */
	public int getPrecioSalaEsparcimiento();

	/**
	 * @return El precio del gimnasio
	 */
	public int getPrecioGimnasio();

	/**
	 * @return El indicador de si la vivienda universitaria tiene restaurante ('Y' o 'N')
	 */
	public String getRestaurante();

	/**
	 * @return Una cadena de caracteres con la información de la vivienda universitaria
	 */
	@Override
	public String toString();

}
