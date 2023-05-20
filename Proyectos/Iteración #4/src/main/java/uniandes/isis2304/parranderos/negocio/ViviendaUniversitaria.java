package uniandes.isis2304.parranderos.negocio;

public class ViviendaUniversitaria implements VOViviendaUniversitaria{
    /* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
    
    /**
     * El identificador ÚNICO de la vivienda universitaria
     * RUE
     */
    private String regComercio;

    /**
     * El nit de la vivienda universitaria
     */
    private String nit;

    /**
     * El nombre de la vivienda universitaria
     */
    private String nombre;

    /**
     * El precio de la sala de estudio de la vivienda universitaria
     */
    private int precioSalaEstudio;

    /**
     * El precio de la sala de esparcimiento de la vivienda universitaria
     */
    private int precioSalaEsparcimiento;

    /**
     * El precio del gimnasio de la vivienda universitaria
     */
    private int precioGimnasio;

    /**
     * El restaurante de la vivienda universitaria ('Y' o 'N')
     */
    private String restaurante;

    /* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
    public ViviendaUniversitaria()
    {
        this.regComercio = "";
        this.nit = "";
        this.nombre = "";
        this.precioSalaEstudio = 0;
        this.precioSalaEsparcimiento = 0;
        this.precioGimnasio = 0;
        this.restaurante = "";
    }

    /**
     * Constructor con valores
     * @param regComercio - El registro de comercio de la vivienda universitaria
     * @param nit - El nit de la vivienda universitaria
     * @param nombre - El nombre de la vivienda universitaria
     * @param precioSalaEstudio - El precio de la sala de estudio de la vivienda universitaria
     * @param precioSalaEsparcimiento - El precio de la sala de esparcimiento de la vivienda universitaria
     * @param precioGimnasio - El precio del gimnasio de la vivienda universitaria
     * @param restaurante - El restaurante de la vivienda universitaria
     */
    public ViviendaUniversitaria(String regComercio, String nit, String nombre, int precioSalaEstudio, int precioSalaEsparcimiento, int precioGimnasio, String restaurante)
    {
        this.regComercio = regComercio;
        this.nit = nit;
        this.nombre = nombre;
        this.precioSalaEstudio = precioSalaEstudio;
        this.precioSalaEsparcimiento = precioSalaEsparcimiento;
        this.precioGimnasio = precioGimnasio;
        this.restaurante = restaurante;
    }

    /**
     * @return El registro de comercio de la vivienda universitaria (RUE)
     */
    public String getRegComercio() {
        return regComercio;
    }

    /**
     * @param regComercio - El nuevo registro de comercio de la vivienda universitaria (RUE)
     */
    public String setRegComercio(String regComercio) {
        return regComercio;
    }

    /**
     * @return El nit de la vivienda universitaria
     */
    public String getNit() {
        return nit;
    }

    /**
     * @param nit - El nuevo nit de la vivienda universitaria
     */
    public void setNit(String nit) {
        this.nit = nit;
    }

    /**
     * @return El nombre de la vivienda universitaria
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre - El nuevo nombre de la vivienda universitaria
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return El precio de la sala de estudio de la vivienda universitaria
     */
    public int getPrecioSalaEstudio() {
        return precioSalaEstudio;
    }

    /**
     * @param precioSalaEstudio - El nuevo precio de la sala de estudio de la vivienda universitaria
     */
    public void setPrecioSalaEstudio(int precioSalaEstudio) {
        this.precioSalaEstudio = precioSalaEstudio;
    }

    /**
     * @return El precio de la sala de esparcimiento de la vivienda universitaria
     */
    public int getPrecioSalaEsparcimiento() {
        return precioSalaEsparcimiento;
    }

    /**
     * @param precioSalaEsparcimiento - El nuevo precio de la sala de esparcimiento de la vivienda universitaria
     */
    public void setPrecioSalaEsparcimiento(int precioSalaEsparcimiento) {
        this.precioSalaEsparcimiento = precioSalaEsparcimiento;
    }

    /**
     * @return El precio del gimnasio de la vivienda universitaria
     */
    public int getPrecioGimnasio() {
        return precioGimnasio;
    }

    /**
     * @param precioGimnasio - El nuevo precio del gimnasio de la vivienda universitaria
     */
    public void setPrecioGimnasio(int precioGimnasio) {
        this.precioGimnasio = precioGimnasio;
    }

    /**
     * @return El indicador de si la vivienda universitaria tiene restaurante ('Y' o 'N')
     */
    public String getRestaurante() {
        return restaurante;
    }

    /**
     * @param restaurante - El nuevo indicador de si la vivienda universitaria tiene restaurante ('Y' o 'N')
     */
    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }

    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos de la vivienda universitaria
     */
    public String toString() {
        return "ViviendaUniversitaria [regComercio=" + regComercio + ", nit=" + nit + ", nombre=" + nombre 
                + ", precioSalaEstudio=" + precioSalaEstudio 
                + ", precioSalaEsparcimiento=" + precioSalaEsparcimiento + ", precioGimnasio=" + precioGimnasio + ", restaurante=" + restaurante + "]";
    }
}
