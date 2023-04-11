package uniandes.isis2304.parranderos.negocio;

public class Hotel implements VOHotel {
    /* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

     /*
      * El identificador ÚNICO de los hoteles
      * RUE(Registro en la cámara de comercio) es de 9 digitos
      */
    private String regComercio;

    /*
     * El nit del hotel
     */
    private String nit;

    /* 
     * El nombre del hotel
    */
    private String nombre;

    /*
     * El restaurante del hotel ('Y' o 'N')
     */
    private String restaurante;

    /*
     * El parqueadero del hotel ('Y' o 'N')
     */
    private String parqueadero;

    /*
     * La piscina del hotel ('Y' o 'N')
     */
    private String piscina;
    
    /* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */

    public Hotel() 
    {
        this.regComercio = "";
        this.nombre = "";
        this.nit = "";
        this.restaurante = "";
        this.parqueadero = "";
        this.piscina = "";
    }

    /*
     * Constructor con valores
     */
    
    public Hotel(String regComercio, String nit, String nombre, String restaurante, String parqueadero, String piscina)
    {
        this.regComercio = regComercio;
        this.nit = nit;
        this.nombre = nombre;
        this.restaurante = restaurante;
        this.parqueadero = parqueadero;
        this.piscina = piscina;
    }

    /**
     * @return El regComercio del hotel
     */
    public String getRegComercio()
    {
        return regComercio;
    }

    /**
     * @param regComercio - El nuevo regComercio del hotel
     */
    public void setRegComercio(String regComercio)
    {
        this.regComercio = regComercio;
    }

    /**
     * @return El nit del hotel
     */
    public String getNit()
    {
        return nit;
    }

    /**
     * @param nit - El nuevo nit del hotel
     */

    public void setNit(String nit)
    {
        this.nit = nit;
    }

    /**
     * @return El nombre del hotel
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * @param nombre - El nuevo nombre del hotel
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * @return El indicador de si el hotel tiene restaurante ('Y' o 'N')
     */
    public String getRestaurante()
    {
        return restaurante;
    }

    /**
     * @param restaurante - El nuevo indicador de si el hotel tiene restaurante ('Y' o 'N')
     */
    public void setRestaurante(String restaurante)
    {
        this.restaurante = restaurante;
    }

    /**
     * @return El indicador de si el hotel tiene parqueadero ('Y' o 'N')
     */
    public String getParqueadero()
    {
        return parqueadero;
    }

    /**
     * @param parqueadero - El nuevo indicador de si el hotel tiene parqueadero ('Y' o 'N')
     */
    public void setParqueadero(String parqueadero)
    {
        this.parqueadero = parqueadero;
    }

    /**
     * @return El indicador de si el hotel tiene piscina ('Y' o 'N')
     */
    public String getPiscina()
    {
        return piscina;
    }

    /**
     * @param piscina - El nuevo indicador de si el hotel tiene piscina ('Y' o 'N')
     */
    public void setPiscina(String piscina)
    {
        this.piscina = piscina;
    }

    
    @Override
    /**
     * @return Una cadena de caracteres con la información del hotel
     */
    public String toString() 
    {
        return "Hotel [regComercio=" + regComercio + ", nit=" + nit + ", nombre=" + nombre 
                + ", restaurante=" + restaurante 
                + ", parqueadero=" + parqueadero + ", piscina=" + piscina + "]";
    }

  



}
