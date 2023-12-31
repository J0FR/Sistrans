package uniandes.isis2304.parranderos.negocio;

public class Alojamiento implements VOAlojamiento {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * La identificacion de un alojamiento
	 */
	private long id;
	
	/**
	 * El nombre de un alojamiento 
	 */
	private String ubicacion;
	
	/**
	 * El costo de un alojamiento 
	 */
	private int duracionMin;
	
	/**
	 * El costo de un alojamiento 
	 */
	private int costo;


	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Alojamiento() 
    {
    	this.id = 0;
		this.ubicacion = "";
		this.duracionMin = 0;
		this.costo = 0;
	}

	/**
	 * Constructor con valores
	 * @param id - La id de un alojamiento
	 * @param tipo - El ubicacion de un alojamiento
	 * @param costo - El duracionMin de un alojamiento
	 */
    public Alojamiento(long id, String ubicacion, int duracionMin, int costo) 
    {
    	this.id = id;
		this.ubicacion = ubicacion;
		this.duracionMin = duracionMin;
		this.costo = costo;
	}

    /**
	 * @return El id del alojamiento
	 */
	public long getId() 
	{
		return id;
	}
	
	/**
	 * @param id - El nuevo id del alojamiento
	 */
	public void setId(long id) 
	{
		this.id = id;
	}
	
	/**
	 * @return La ubicacion del alojamiento
	 */
	public String getUbicacion() 
	{
		return ubicacion;
	}
	
	/**
	 * @param tipo - La nueva ubicacion del alojamiento
	 */
	public void setUbicacion(String ubicacion) 
	{
		this.ubicacion = ubicacion;
	}
	
	/**
	 * @return La duracionMin del alojamiento
	 */
	public int getDuracionMin() 
	{
		return duracionMin;
	}
	
	/**
	 * @param costo - La nueva duracionMin del alojamiento
	 */
	public void setDuracionMin(int duracionMin) 
	{
		this.duracionMin = duracionMin;
	}
	
	/**
	 * @return El costo del alojamiento
	 */
	public int getCosto() 
	{
		return costo;
	}
	
	/**
	 * @param costo - El nuevo costo del alojamiento
	 */
	public void setCosto(int costo) 
	{
		this.costo = costo;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del Alojamiento
	 */
	public String toString() 
	{
		return "Alojamiento [id=" + id + ", ubicacion=" + ubicacion + ", duracionMin=" + duracionMin  + ", costo=" + costo  + "]";
	}
}
