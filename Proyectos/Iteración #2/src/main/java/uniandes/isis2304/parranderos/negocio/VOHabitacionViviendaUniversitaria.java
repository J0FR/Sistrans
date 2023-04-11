package uniandes.isis2304.parranderos.negocio;

public interface VOHabitacionViviendaUniversitaria {

    /* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * @return El id de la habitación de la vivienda universitaria
	 */
	public long getId();

	/**
	 * @return El tipo de la habitación de la vivienda universitaria
	 */
	public String getTipoHabitacion();

	/**
	 * @return La capacidad de la habitación de la vivienda universitaria
	 */
	public int getCapacidad();

	/**
	 * @return El id de la vivienda universitaria a la que pertenece la habitación
	 */
	public String getIdViviendaUniversitaria();

	/**
	 * @return Una cadena de caracteres con la información de la habitación de la vivienda universitaria
	 */
	@Override
	public String toString();
}
