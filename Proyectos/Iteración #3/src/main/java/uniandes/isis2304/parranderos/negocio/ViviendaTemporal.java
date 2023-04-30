package uniandes.isis2304.parranderos.negocio;

public class ViviendaTemporal implements VOViviendaTemporal {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * La idAlojamiento de la ViviendaTemporal
	 */
	private long idAlojamiento;
	
	/**
	 * El numeroHabitaciones de la ViviendaTemporal
	 */
	private int numeroHabitaciones;
	
	/**
	 * El precioSeguroArrendamiento de la ViviendaTemporal
	 */
	private int precioSeguroArrendamiento;
	
	/**
	 * El caractSeguro de la ViviendaTemporal
	 */
	private String caractSeguro;
	
	/**
	 * El diasAlquilado de la ViviendaTemporal
	 */
	private int diasAlquilado;

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
	public ViviendaTemporal() 
    {
    	this.idAlojamiento = 0;
		this.numeroHabitaciones = 0;
		this.precioSeguroArrendamiento = 0;
		this.caractSeguro = "";
		this.diasAlquilado = 0;
	}

	/**
	 * Constructor con valores
	 * @param idAlojamiento - La idAlojamiento de la ViviendaTemporal
	 * @param numeroHabitaciones - El numeroHabitaciones de la ViviendaTemporal
	 * @param precioSeguroArrendamiento - EL precioSeguroArrendamiento de la ViviendaTemporal
	 * @param caractSeguro - Las caractSeguro de la ViviendaTemporal
	 * @param diasAlquilado - El numero de diasAlquilado de la ViviendaTemporal
	 */
    public ViviendaTemporal(long idAlojamiento, int numeroHabitaciones, int precioSeguroArrendamiento, String caractSeguro, int diasAlquilado, String identificacionOperadorUsuario) 
    {
    	this.idAlojamiento = idAlojamiento;
		this.numeroHabitaciones = numeroHabitaciones;
		this.precioSeguroArrendamiento = precioSeguroArrendamiento;
		this.caractSeguro = caractSeguro;
		this.diasAlquilado = diasAlquilado;
		this.identificacionOperadorUsuario = identificacionOperadorUsuario;
	}

    /**
	 * @return El idAlojamiento de la ViviendaTemporal
	 */
	public long getIdAlojamiento() 
	{
		return idAlojamiento;
	}
	
	/**
	 * @param id - El nuevo idAlojamiento de la ViviendaTemporal
	 */
	public void setIdAlojamiento(long idAlojamiento) 
	{
		this.idAlojamiento = idAlojamiento;
	}
	
	/**
	 * @return El numeroHabitaciones de la ViviendaTemporal
	 */
	public int getNumeroHabitaciones() 
	{
		return numeroHabitaciones;
	}
	
	/**
	 * @param numeroHabitaciones - El nuevo numeroHabitaciones de la ViviendaTemporal
	 */
	public void setNumeroHabitaciones(int numeroHabitaciones) 
	{
		this.numeroHabitaciones = numeroHabitaciones;
	}
	
	/**
	 * @return El precioSeguroArrendamiento de la ViviendaTemporal
	 */
	public int getPrecioSeguroArrendamiento() 
	{
		return precioSeguroArrendamiento;
	}
	
	/**
	 * @param precioSeguroArrendamiento - El nuevo precioSeguroArrendamiento de la ViviendaTemporal
	 */
	public void setPrecioSeguroArrendamiento(int precioSeguroArrendamiento) 
	{
		this.precioSeguroArrendamiento = precioSeguroArrendamiento;
	}
	
	/**
	 * @return El caractSeguro de la ViviendaTemporal
	 */
	public String getCaractSeguro() 
	{
		return caractSeguro;
	}
	
	/**
	 * @param caractSeguro - El nuevo caractSeguro de la ViviendaTemporal
	 */
	public void setCaractSeguro(String caractSeguro) 
	{
		this.caractSeguro = caractSeguro;
	}
	
	/**
	 * @return El diasAlquilado de la ViviendaTemporal
	 */
	public int getDiasAlquilado() 
	{
		return diasAlquilado;
	}
	
	/**
	 * @param diasAlquilado - El nuevo diasAlquilado de la ViviendaTemporal
	 */
	public void setDiasAlquilado(int diasAlquilado) 
	{
		this.diasAlquilado = diasAlquilado;
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
	 * @return Una cadena de caracteres con todos los atributos de la ViviendaTemporale
	 */
	public String toString() 
	{
		return "ViviendaTemporal [idAlojamiento=" + idAlojamiento + ", numeroHabitaciones=" + numeroHabitaciones + ", precioSeguroArrendamiento=" + precioSeguroArrendamiento  + ", caractSeguro=" + caractSeguro + ", diasAlquilado=" + diasAlquilado  +  ", identificacionOperadorUsuario=" + identificacionOperadorUsuario + "]";
	}
}
