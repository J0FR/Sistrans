package uniandes.isis2304.parranderos.negocio;

public class HabitacionHostal implements VOHabitacionHostal{

    /* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

    /**
     * El identificador ÚNICO de la habitación del hostal
     */
    private long id;

    /**
     * El aforo de la habitación del hostal
     */
    private int aforo;

    /**
     * El identificador del hostal al que pertenece la habitación
     */
    private String idHostal;

    /* ****************************************************************
	 * 			Métodos
	 *****************************************************************/


    /**
     * Constructor por defecto
     */
    public HabitacionHostal()
    {
        this.id = 0;
        this.aforo = 0;
        this.idHostal = "";
    }

    /**
     * Constructor con valores
     * @param id - El id de la habitación del hostal
     * @param aforo - El aforo de la habitación del hostal
     * @param idHostal - El identificador del hostal al que pertenece la habitación
     */
    public HabitacionHostal(long id, int aforo, String idHostal)
    {
        this.id = id;
        this.aforo = aforo;
        this.idHostal = idHostal;
    }

    /**
     * @return El id de la habitación del hostal
     */
    public long getId() {
        return id;
    }

    /**
     * @param id - El nuevo id de la habitación del hostal
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return El aforo de la habitación del hostal
     */
    public int getAforo() {
        return aforo;
    }

    /**
     * @param aforo - El nuevo aforo de la habitación del hostal
     */
    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    /**
     * @return El id del hostal al que pertenece la habitación
     */
    public String getIdHostal() {
        return idHostal;
    }

    /**
     * @param idHostal - El nuevo id del hostal al que pertenece la habitación
     */
    public void setIdHostal(String idHostal) {
        this.idHostal = idHostal;
    }

    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos de la habitación del hostal
     */
    public String toString() {
        return "HabitacionHostal [id=" + id + ", aforo=" + aforo + ", idHostal=" + idHostal + "]";
    }
    
}
