package uniandes.isis2304.parranderos.negocio;

public interface VOHabitacionHostal {

    /* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * @return El id de la habitación del hostal
	 */
	public long getId();

	/**
	 * @return El aforo de la habitación del hostal
	 */
	public int getAforo();

	/**
	 * @return El identificador del hostal al que pertenece la habitación
	 */
	public String getIdHostal();

	/**
	 * @return Una cadena de caracteres con la información de la habitación del hostal
	 */
	@Override
	public String toString();

}
