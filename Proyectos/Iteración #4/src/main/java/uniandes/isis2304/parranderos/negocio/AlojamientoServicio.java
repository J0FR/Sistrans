package uniandes.isis2304.parranderos.negocio;

public class AlojamientoServicio implements VOAlojamientoServicio{
    /* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

    /**
     * El idetificador del alojamiento que da el servicio
     */
    private long idAlojamiento;

    /**
     * El identificador del servicio que ofrece el alojamiento
     */
    private long idServicio;

    /**
     * El costo del servicio
     */
    private int costo;

    /* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

    /**
     * Constructor por defecto
     */
    public AlojamientoServicio()
    {
        this.idAlojamiento = 0;
        this.idServicio = 0;
        this.costo = 0;
    }

    /**
     * Constructor con valores
     * @param idAlojamiento - El idetificador del alojamiento que da el servicio
     * @param idServicio - El identificador del servicio que ofrece el alojamiento
     * @param costo - El costo del servicio
     */
    public AlojamientoServicio(long idAlojamiento, long idServicio, int costo)
    {
        this.idAlojamiento = idAlojamiento;
        this.idServicio = idServicio;
        this.costo = costo;
    }

    /**
     * @return El idetificador del alojamiento que da el servicio
     */
    public long getIdAlojamiento() {
        return idAlojamiento;
    }

    /**
     * @param idAlojamiento - El nuevo idetificador del alojamiento que da el servicio
     */
    public void setIdAlojamiento(long idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
    }

    /**
     * @return El identificador del servicio que ofrece el alojamiento
     */
    public long getIdServicio() {
        return idServicio;
    }

    /**
     * @param idServicio - El nuevo identificador del servicio que ofrece el alojamiento
     */
    public void setIdServicio(long idServicio) {
        this.idServicio = idServicio;
    }

    /**
     * @return El costo del servicio
     */
    public int getCosto() {
        return costo;
    }

    /**
     * @param costo - El nuevo costo del servicio
     */
    public void setCosto(int costo) {
        this.costo = costo;
    }

    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos del alojamiento que da el servicio con su costo
     */
    public String toString() {
        return "AlojamientoServicio [idAlojamiento=" + idAlojamiento + ", idServicio=" + idServicio 
                + ", costo=" + costo + "]";
    }
}
