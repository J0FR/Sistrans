package uniandes.isis2304.parranderos.negocio;

public interface VOHabitacionHotel {
    
    /* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * @return El id de la habitación del hotel
	 */
	public long getId();

	/**
	 * @return El tipo de la habitación del hotel
	 */
	public String getTipoHabitacion();

	/**
	 * @return El tamanio de la habitación del hotel
	 */
	public String getTamanio();

	/**
	 * @return El id del hotel al que pertenece la habitación
	 */
	public String getIdHotel();

	/**
	 * @return Una cadena de caracteres con la información de la habitación del hotel
	 */
	@Override
	public String toString();
}