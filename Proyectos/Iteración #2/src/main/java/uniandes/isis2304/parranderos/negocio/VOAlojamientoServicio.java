package uniandes.isis2304.parranderos.negocio;

public interface VOAlojamientoServicio {
    
    /* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

    /**
     * @return El id del alojamiento
     */
    public long getIdAlojamiento();

    /**
     * @return El id del servicio
     */
    public long getIdServicio();

    /**
     * @return El costo del servicio
     */
    public int getCosto();

    /**
     * @return Una cadena de caracteres con la información del alojamiento y el servicio
     */
    @Override
    public String toString();


}
