package uniandes.isis2304.parranderos.negocio;

public class Servicio implements VOServicio{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * La identificacion de un cliente
	 */
	private long id;
	
	/**
	 * El nombre de un cliente ('salaEsparcimiento', 'tvCable', 'gimnasio', 'salaEstudio', 'wifi', 'baniera', 'sala', 'yacuzzi', 'menaje', 'luz', 'telefono', 'cocina', 'agua')
	 */
	private String tipo;


	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Servicio() 
    {
    	this.id = 0;
		this.tipo = "";
	}

	/**
	 * Constructor con valores
	 * @param id - La id de un servicio
	 * @param tipo - El tipo de un servicio
	 */
    public Servicio(long id, String tipo) 
    {
    	this.id = id;
		this.tipo = tipo;
	}

    /**
	 * @return El id del servicio
	 */
	public long getId() 
	{
		return id;
	}
	
	/**
	 * @param id - El nuevo id del servicio
	 */
	public void setIdentificacion(long id) 
	{
		this.id = id;
	}
	
	/**
	 * @return El tipo del servicio
	 */
	public String getTipo() 
	{
		return tipo;
	}
	
	/**
	 * @param tipo - El nuevo tipo del servicio
	 */
	public void setTipo(String tipo) 
	{
		this.tipo = tipo;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del Servicio
	 */
	public String toString() 
	{
		return "Servicio [id=" + id + ", tipo=" + tipo + "]";
	}
}
