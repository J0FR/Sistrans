package uniandes.isis2304.parranderos.negocio;

public class OperadorUsuario implements VOOperadorUsuario {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * La identificacion de un operador usuario
	 */
	private String identificacion;
	
	/**
	 * El nombre de un operador usuario
	 */
	private String nombre;

	/**
	 * El tipo vinculo de un operador usuario ('profesor', 'empleado', 'egresado', 'estudiante', 'padre de estudiante', 'externo')
	 */
	private String tipoVinculo; 
	
	/**
	 * El correo electronico de un operador usuario
	 */
	private String correoElectronico;
	
	/**
	 * El telefono de un operador usuario
	 */
	private String telefono;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public OperadorUsuario() 
    {
    	this.identificacion = "";
		this.nombre = "";
		this.tipoVinculo = "";
		this.correoElectronico = "";
		this.telefono = "";
	}

	/**
	 * Constructor con valores
	 * @param identificacion - La identificacion de un operador usuario
	 * @param nombre - El nombre de un operador usuario
	 * @param tipoVinculo - El tipo vinculo de un operador usuario ('profesor', 'empleado', 'egresado', 'estudiante', 'padre de estudiante', 'externo')
	 * @param correoElectronico - El correo electronico de un operador usuario
	 * @param telefono - El telefono de un operador usuario
	 * @param ganancias - Las ganancias de un operador usuario (Mayor o igual a 0)
	 */
    public OperadorUsuario(String identificacion, String nombre, String tipoVinculo, String correoElectronico, String telefono) 
    {
    	this.identificacion = identificacion;
		this.nombre = nombre;
		this.tipoVinculo = tipoVinculo;
		this.correoElectronico = correoElectronico;
		this.telefono = telefono;
	}

    /**
	 * @return La identificacion del operador usuario
	 */
	public String getIdentificacion() 
	{
		return identificacion;
	}
	
	/**
	 * @param a - La nueva identificacion del operador usuario
	 */
	public void setIdentificacion(String identificacion) 
	{
		this.identificacion = identificacion;
	}
	
	/**
	 * @return El nombre del operador usuario
	 */
	public String getNombre() 
	{
		return nombre;
	}
	
	/**
	 * @param nombre - El nuevo nombre del operador usuario
	 */
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	/**
	 * @return El tipoVinculo del operador usuario
	 */
	public String getTipoVinculo() 
	{
		return tipoVinculo;
	}
	
	/**
	 * @param tipoVinculo - El nuevo tipoVinculo del operador usuario
	 */
	public void setTipoVinculo(String tipoVinculo) 
	{
		this.tipoVinculo = tipoVinculo;
	}
	
	/**
	 * @return El correoElectronico del operador usuario
	 */
	public String getCorreoElectronico() 
	{
		return correoElectronico;
	}
	
	/**
	 * @param correoElectronico - El nuevo correoElectronico del operador usuario
	 */
	public void setCorreoElectronico(String correoElectronico) 
	{
		this.correoElectronico = correoElectronico;
	}
	
	/**
	 * @return El telefono del operador usuario
	 */
	public String getTelefono() 
	{
		return telefono;
	}
	
	/**
	 * @param telefono - El nuevo telefono del operador usuario
	 */
	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del OperadorUsuario
	 */
	public String toString() 
	{
		return "OperadorUsuario [identificacion=" + identificacion + ", nombre=" + nombre + ", tipoVinculo=" + tipoVinculo + ", correoElectronico=" + correoElectronico
				+ ", telefono=" + telefono + "]";
	}
}



