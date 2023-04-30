package uniandes.isis2304.parranderos.negocio;

public class HabitacionHotel implements VOHabitacionHotel{
    /* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

    /*
     * El identificador ÚNICO de las habitaciones de hotel
     */ 
    private long id;

    /*
     * El tipo de habitacion ('estandar', 'semisuite', 'suite')
     */
    private String tipoHabitacion;

    /*
     * El tamanio de la habitacion
     */
    private String tamanio;

    /*
     * El identificador del hotel al que pertenece la habitacion
     */
    private String idHotel;

    /* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

     /*
      * Constructor por defecto
      */
    public HabitacionHotel()
    {
        this.id = 0;
        this.tipoHabitacion = "";
        this.tamanio = "";
        this.idHotel = "";
    }

    /*
     * Constructor con valores
     */

    /**
     * 
     * @param id - El id de la habitacion
     * @param tipoHabitacion - El tipo de habitacion
     * @param tamanio - El tamanio de la habitacion
     * @param idHotel - El identificador del hotel al que pertenece la habitacion
     */
    public HabitacionHotel(long id, String tipoHabitacion, String tamanio, String idHotel)
    {
        this.id = id;
        this.tipoHabitacion = tipoHabitacion;
        this.tamanio = tamanio;
        this.idHotel = idHotel;
    }

    /**
     * @return El id de la habitacion
     */
    public long getId()
    {
        return id;
    }

    /**
     * @param id - El nuevo id de la habitacion
     */
    public void setId(long id)
    {
        this.id = id;
    }

    /**
     * @return El tipo de habitacion
     */
    public String getTipoHabitacion()
    {
        return tipoHabitacion;
    }

    /**
     * @param tipoHabitacion - El nuevo tipo de habitacion
     */
    public void setTipoHabitacion(String tipoHabitacion)
    {
        this.tipoHabitacion = tipoHabitacion;
    }

    /**
     * @return El tamanio de la habitacion
     */
    public String getTamanio()
    {
        return tamanio;
    }

    /**
     * @param tamanio - El nuevo tamanio de la habitacion
     */
    public void setTamanio(String tamanio)
    {
        this.tamanio = tamanio;
    }

    /**
     * @return El id del hotel al que pertenece la habitacion
     */
    public String getIdHotel()
    {
        return idHotel;
    }

    /**
     * @param idHotel - El nuevo id del hotel al que pertenece la habitacion
     */
    public void setIdHotel(String idHotel)
    {
        this.idHotel = idHotel;
    }

    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos de la habitacion de hotel
     */
    public String toString() {
        return "HabitacionHotel [id=" + id + ", tipoHabitacion=" + tipoHabitacion 
                + ", tamanio=" + tamanio + ", idHotel=" + idHotel   + "]";
    }

}
