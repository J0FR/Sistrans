package uniandes.isis2304.parranderos.negocio;

public class HabitacionHuesped implements VOHabitacionHuesped {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * La idAlojamiento de una Habitacion Huesped
	 */
	private long idAlojamiento;
	
	/**
	 * Las comidas de una Habitacion Huesped
	 */
	private int comidas;
	
	/**
	 * El tipoBanio de una Habitacion Huesped ('privado', 'compartido')
	 */
	private String tipoBanio;
	
	/**
	 * El tipoHabitacion de una Habitacion Huesped ('individual', 'compatida')
	 */
	private String tipoHabitacion;
	
	/**
	 * El dtoMesExtra de una Habitacion Huesped
	 */
	private int dtoMesExtra;

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
	public HabitacionHuesped() 
    {
    	this.idAlojamiento = 0;
		this.comidas = 0;
		this.tipoBanio = "";
		this.tipoHabitacion = "";
		this.dtoMesExtra = 0;
	}

	/**
	 * Constructor con valores
	 * @param idAlojamiento - La idAlojamiento de una Habitacion Huesped
	 * @param comidas - Las comidas de una Habitacion Huesped
	 * @param tipoBanio - El tipoBanio de una Habitacion Huesped
	 * @param tipoHabitacion - EL tipoHabitacion de una Habitacion Huesped
	 * @param dtoMesExtra - EL dtoMesExtra de una Habitacion Huesped
	 */
    public HabitacionHuesped(long idAlojamiento, int comidas, String tipoBanio, String tipoHabitacion, int dtoMesExtra, String identificacionOperadorUsuario) 
    {
    	this.idAlojamiento = idAlojamiento;
		this.comidas = comidas;
		this.tipoBanio = tipoBanio;
		this.tipoHabitacion = tipoHabitacion;
		this.dtoMesExtra = dtoMesExtra;
		this.identificacionOperadorUsuario = identificacionOperadorUsuario;
	}

    /**
	 * @return El idAlojamiento de la Habitacion Huesped
	 */
	public long getIdAlojamiento() 
	{
		return idAlojamiento;
	}
	
	/**
	 * @param idAlojamiento - El nuevo idAlojamiento de la Habitacion Huesped
	 */
	public void setIdAlojamiento(long idAlojamiento) 
	{
		this.idAlojamiento = idAlojamiento;
	}

	/**
	 * @return Las comidas de la Habitacion Huesped
	 */
	public int getComidas() 
	{
		return comidas;
	}
	
	/**
	 * @param comidas - La nueva cantidad de comidas de la Habitacion Huesped
	 */
	public void setComidas(int comidas) 
	{
		this.comidas = comidas;
	}
	
	/**
	 * @return El tipoBanio de la Habitacion Huesped
	 */
	public String getTipoBanio() 
	{
		return tipoBanio;
	}
	
	/**
	 * @param tipoBanio - El nuevo tipoBanio de la Habitacion Huesped
	 */
	public void setTipoBanio(String tipoBanio) 
	{
		this.tipoBanio = tipoBanio;
	}
	
	/**
	 * @return El tipoHabitacion de la Habitacion Huesped
	 */
	public String getTipoHabitacion() 
	{
		return tipoHabitacion;
	}
	
	/**
	 * @param tipoHabitacion - El nuevo tipoHabitacion de la Habitacion Huesped
	 */
	public void setTipoHabitacion(String tipoHabitacion) 
	{
		this.tipoHabitacion = tipoHabitacion;
	}
	
	/**
	 * @return El dtoMesExtra de la Habitacion Huesped
	 */
	public int getDtoMesExtra() 
	{
		return dtoMesExtra;
	}
	
	/**
	 * @param dtoMesExtra - El nuevo dtoMesExtra de la Habitacion Huesped
	 */
	public void setDtoMesExtra(int dtoMesExtra) 
	{
		this.dtoMesExtra = dtoMesExtra;
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
	 * @return Una cadena de caracteres con todos los atributos del Alojamiento
	 */
	public String toString() 
	{
		return "Alojamiento [idAlojamiento=" + idAlojamiento + ", comidas=" + comidas + ", tipoBanio=" + tipoBanio  + ", tipoHabitacion=" + tipoHabitacion  + ", dtoMesExtra=" + dtoMesExtra  + ", identificacionOperadorUsuario=" + identificacionOperadorUsuario + "]";
	}
}