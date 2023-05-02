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

	/**
	 * El estado de un alojamiento si esta activo o no ( 'Y' || 'N' )
	 */
	private String estatus;


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
		this.estatus = "";
	}

	/**
	 * Constructor con valores
	 * @param id - La id de un alojamiento
	 * @param tipo - El ubicacion de un alojamiento
	 * @param costo - El duracionMin de un alojamiento
	 */
    public Alojamiento(long id, String ubicacion, int duracionMin, int costo, String estatus) 
    {
    	this.id = id;
		this.ubicacion = ubicacion;
		this.duracionMin = duracionMin;
		this.costo = costo;
		this.estatus = estatus;
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
	 * @return El estatus del alojamiento
	 */
	public String getEstatus() 
	{
		return estatus;
	}
	
	/**
	 * @param costo - El nuevo costo del alojamiento
	 */
	public void setCosto(int costo) 
	{
		this.costo = costo;
	}

	/**
	 * @param estatus - El nuevo estatus del alojamiento
	 */
	public void setEstatus(String estatus)
	{
		this.estatus = estatus;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del Alojamiento
	 */
	public String toString() 
	{
		return "Alojamiento [id=" + id + ", ubicacion=" + ubicacion + ", duracionMin=" + duracionMin  + ", costo=" + costo  + ", estatus=" + estatus + "]";
	}
}
