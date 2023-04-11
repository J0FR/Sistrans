package uniandes.isis2304.parranderos.negocio;


public class Hostal implements VOHostal{

    /* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

    /**
     * El identificador ÚNICO de los hostales
     */
    private String regComercio;

    /**
     * El nit del hostal
     */
    private String nit;

    /**
     * El nombre del hostal
     */
    private String nombre;

    /**
     * La hora de apertura del hostal
     */
    private String horaAperturaRecepcion;
    
    /**
     * La hora de cierre del hostal
     */
    private String horaCierreRecepcion;

    /* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

    /**
    * Constructor por defecto
    */
    
    public Hostal() 
    {
        this.regComercio = "";
        this.nombre = "";
        this.nit = "";
        this.horaAperturaRecepcion = null;
        this.horaCierreRecepcion = null;
    }

    /**
     * Constructor con valores
     * @param regComercio - El identificador ÚNICO de los hostales
     * @param nit - El nit del hostal
     * @param nombre - El nombre del hostal
     * @param ganancias - Las ganancias del hostal
     * @param horaAperturaRecepcion - La hora de apertura del hostal
     * @param horaCierreRecepcion - La hora de cierre del hostal
     */
    public Hostal(String regComercio, String nit, String nombre, String horaAperturaRecepcion, String horaCierreRecepcion)
    {
        this.regComercio = regComercio;
        this.nit = nit;
        this.nombre = nombre;
        this.horaAperturaRecepcion = horaAperturaRecepcion;
        this.horaCierreRecepcion = horaCierreRecepcion;
    }

    /**
     * @return El identificador ÚNICO de los hostales
     */
    public String getRegComercio() {
        return regComercio;
    }

    /**
     * @param regComercio - El nuevo identificador ÚNICO de los hostales
     */
    public void setRegComercio(String regComercio) {
        this.regComercio = regComercio;
    }

    /**
     * @return El nit del hostal
     */
    public String getNit() {
        return nit;
    }

    /**
     * @param nit - El nuevo nit del hostal
     */
    public void setNit(String nit) {
        this.nit = nit;
    }

    /**
     * @return El nombre del hostal
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre - El nuevo nombre del hostal
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return La hora de apertura del hostal
     */
    public String getHoraAperturaRecepcion() {
        return horaAperturaRecepcion;
    }

    /**
     * @param horaAperturaRecepcion - La nueva hora de apertura del hostal
     */
    public void setHoraAperturaRecepcion(String horaAperturaRecepcion) {
        this.horaAperturaRecepcion = horaAperturaRecepcion;
    }

    /**
     * @return La hora de cierre del hostal
     */
    public String getHoraCierreRecepcion() {
        return horaCierreRecepcion;
    }

    /**
     * @param horaCierreRecepcion - La nueva hora de cierre del hostal
     */
    public void setHoraCierreRecepcion(String horaCierreRecepcion) {
        this.horaCierreRecepcion = horaCierreRecepcion;
    }

    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos del hostal
     */
    public String toString()
    {
        return "Hostal [regComercio=" + regComercio + ", nit=" + nit + ", nombre=" + nombre 
                + ", horaAperturaRecepcion=" + horaAperturaRecepcion 
                + ", horaCierreRecepcion=" + horaCierreRecepcion + "]";
    }


}
