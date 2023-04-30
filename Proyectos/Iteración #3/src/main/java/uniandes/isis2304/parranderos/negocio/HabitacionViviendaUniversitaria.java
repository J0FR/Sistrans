package uniandes.isis2304.parranderos.negocio;

public class HabitacionViviendaUniversitaria implements VOHabitacionViviendaUniversitaria {

    /* ****************************************************************
	 * 			Atributos
	 *****************************************************************/


    /**
     * El identificador ÚNICO de las habitaciones de vivienda universitaria
     */
    private long id;

    /**
     * El tipo de habitacion de la vivienda universitaria ('individual','compartida')
     */
    private String tipoHabitacion;

    /**
     * La capacidad de la habitacion de la vivienda universitaria
     */
    private int capacidad;

    /**
     * El identificador de la vivienda universitaria a la que pertenece la habitacion
     */
    private String idViviendaUniversitaria;
    
    /* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

    /**
     * Constructor por defecto
     */
    public HabitacionViviendaUniversitaria()
    {
        this.id = 0;
        this.tipoHabitacion = "";
        this.capacidad = 0;
        this.idViviendaUniversitaria = "";
    }

    /**
     * Constructor con valores
     * @param id - El id de la habitacion de la vivienda universitaria
     * @param tipoHabitacion - El tipo de habitacion de la vivienda universitaria
     * @param capacidad - La capacidad de la habitacion de la vivienda universitaria
     * @param idViviendaUniversitaria - El identificador de la vivienda universitaria a la que pertenece la habitacion
     */
    public HabitacionViviendaUniversitaria(long id, String tipoHabitacion, int capacidad, String idViviendaUniversitaria)
    {
        this.id = id;
        this.tipoHabitacion = tipoHabitacion;
        this.capacidad = capacidad;
        this.idViviendaUniversitaria = idViviendaUniversitaria;
    }

    /**
     * @return El id de la habitacion de la vivienda universitaria
     */
    public long getId() {
        return id;
    }

    /**
     * @param id - El nuevo id de la habitacion de la vivienda universitaria
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return El tipo de habitacion de la vivienda universitaria
     */
    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    /**
     * @param tipoHabitacion - El nuevo tipo de habitacion de la vivienda universitaria
     */
    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    /**
     * @return La capacidad de la habitacion de la vivienda universitaria
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * @param capacidad - La nueva capacidad de la habitacion de la vivienda universitaria
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * @return El id de la vivienda universitaria a la que pertenece la habitacion
     */
    public String getIdViviendaUniversitaria() {
        return idViviendaUniversitaria;
    }
    
    /**
     * @param idViviendaUniversitaria - El nuevo id de la vivienda universitaria a la que pertenece la habitacion
     */
    public void setIdViviendaUniversitaria(String idViviendaUniversitaria) {
        this.idViviendaUniversitaria = idViviendaUniversitaria;
    }

    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos de la habitacion de vivienda universitaria
     */
    public String toString() {
        return "HabitacionViviendaUniversitaria [id=" + id + ", capacidad=" + capacidad
                + ", tipoHabitacion=" + tipoHabitacion + ", idViviendaUniversitaria=" + idViviendaUniversitaria + "]";
    }



}
