package uniandes.isis2304.parranderos.negocio;

public class ApartamentoAlquiler implements VOApartamentoAlquiler {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * La idAlojamiento del ApartamentoAlquiler
	 */
	private long idAlojamiento;
	
	/**
	 * El servPublico del ApartamentoAlquiler
	 */
	private String servPublico;
	
	/**
	 * El administracion del ApartamentoAlquiler
	 */
	private String administracion;

	/**
	 * El IdentificacionOperadorUsuario 
	 */
	private String identificacionOperadorUsuario;


	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public ApartamentoAlquiler() 
    {
    	this.idAlojamiento = 0;
		this.servPublico = "";
		this.administracion = "";
	}

	/**
	 * Constructor con valores
	 * @param idAlojamiento - La idAlojamiento del ApartamentoAlquiler
	 * @param servPublico - El servPublico del ApartamentoAlquiler
	 * @param administracion - EL administracion del ApartamentoAlquiler
	 */
    public ApartamentoAlquiler(long idAlojamiento, String servPublico, String administracion, String identificacionOperadorUsuario) 
    {
    	this.idAlojamiento = idAlojamiento;
		this.servPublico = servPublico;
		this.administracion = administracion;
		this.identificacionOperadorUsuario = identificacionOperadorUsuario;
	}

    /**
	 * @return El idAlojamiento del ApartamentoAlquiler
	 */
	public long getIdAlojamiento() 
	{
		return idAlojamiento;
	}
	
	/**
	 * @param id - El nuevo idAlojamiento del ApartamentoAlquiler
	 */
	public void setIdAlojamiento(long idAlojamiento) 
	{
		this.idAlojamiento = idAlojamiento;
	}
	
	/**
	 * @return El servPublico del ApartamentoAlquiler
	 */
	public String getServPublico() 
	{
		return servPublico;
	}
	
	/**
	 * @param servPublico - El nuevo servPublico del ApartamentoAlquiler
	 */
	public void setServPublico(String servPublico) 
	{
		this.servPublico = servPublico;
	}
	
	/**
	 * @return El tipoHabitacion del ApartamentoAlquiler
	 */
	public String getAdministracion() 
	{
		return administracion;
	}
	
	/**
	 * @param administracion - La nueva administracion del ApartamentoAlquiler
	 */
	public void setAdministracion(String administracion) 
	{
		this.administracion = administracion;
	}

	/**
	 * @return El IdentificacionOperadorUsuario 
	 */
	public String getIdentificacionOperadorUsuario() 
	{
		return identificacionOperadorUsuario;
	}
	
	/**
	 * @param identificacionOperadorUsuario - El nuevo IdentificacionOperadorUsuario 
	 */
	public void setIdentificacionOperadorUsuario(String identificacionOperadorUsuario) 
	{
		this.identificacionOperadorUsuario = identificacionOperadorUsuario;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del ApartamentoAlquiler
	 */
	public String toString() 
	{
		return "ApartamentoAlquiler [idAlojamiento=" + idAlojamiento + ", servPublico=" + servPublico + ", administracion=" + administracion + ", identificacionOperadorUsuario=" + identificacionOperadorUsuario + "]";
	}
}
