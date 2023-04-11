package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;

public class Cliente implements VOCliente {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * La identificacion de un cliente
	 */
	private String identificacion;
	
	/**
	 * El nombre de un cliente
	 */
	private String nombre;

	/**
	 * El tipo vinculo de un cliente ('profesor', 'empleado', 'egresado', 'estudiante', 'padre de estudiante', 'externo')
	 */
	private String tipoVinculo; 
	
	/**
	 * El correo electronico de un cliente
	 */
	private String correoElectronico;
	
	/**
	 * El telefono de un cliente
	 */
	private String telefono;

	/**
	 * El telefono de un UltimaFechaReserva
	 */
	private Timestamp ultimaFechaReserva;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Cliente() 
    {
    	this.identificacion = "";
		this.nombre = "";
		this.tipoVinculo = "";
		this.correoElectronico = "";
		this.telefono = "";
		this.ultimaFechaReserva = new Timestamp(0);
	}

	/**
	 * Constructor con valores
	 * @param identificacion - La identificacion de un cliente
	 * @param nombre - El nombre de un cliente
	 * @param tipoVinculo - El tipo vinculo de un cliente ('profesor', 'empleado', 'egresado', 'estudiante', 'padre de estudiante')
	 * @param correoElectronico - El correo electronico de un cliente
	 * @param telefono - El telefono de un cliente
	 */
    public Cliente(String identificacion, String nombre, String tipoVinculo, String correoElectronico, String telefono, Timestamp ultimaFechaReserva) 
    {
    	this.identificacion = identificacion;
		this.nombre = nombre;
		this.tipoVinculo = tipoVinculo;
		this.correoElectronico = correoElectronico;
		this.telefono = telefono;
		this.ultimaFechaReserva = ultimaFechaReserva;
	}

    /**
	 * @return La identificacion del cliente
	 */
	public String getIdentificacion() 
	{
		return identificacion;
	}
	
	/**
	 * @param a - La nueva identificacion del cliente
	 */
	public void setIdentificacion(String identificacion) 
	{
		this.identificacion = identificacion;
	}
	
	/**
	 * @return El nombre del cliente
	 */
	public String getNombre() 
	{
		return nombre;
	}
	
	/**
	 * @param nombre - El nuevo nombre del cliente
	 */
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	/**
	 * @return El tipoVinculo del cliente
	 */
	public String getTipoVinculo() 
	{
		return tipoVinculo;
	}
	
	/**
	 * @param tipoVinculo - El nuevo tipoVinculo del cliente
	 */
	public void setTipoVinculo(String tipoVinculo) 
	{
		this.tipoVinculo = tipoVinculo;
	}
	
	/**
	 * @return El correoElectronico del cliente
	 */
	public String getCorreoElectronico() 
	{
		return correoElectronico;
	}
	
	/**
	 * @param correoElectronico - El nuevo correoElectronico del cliente
	 */
	public void setCorreoElectronico(String correoElectronico) 
	{
		this.correoElectronico = correoElectronico;
	}
	
	/**
	 * @return El telefono del cliente
	 */
	public String getTelefono() 
	{
		return telefono;
	}
	
	/**
	 * @param telefono - El nuevo telefono del cliente
	 */
	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
	}

	/**
	 * @return El ultimaFechaReserva del cliente
	 */
	public Timestamp getUltimaFechaReserva() 
	{
		return ultimaFechaReserva;
	}
	
	/**
	 * @param telefono - El nuevo ultimaFechaReserva del cliente
	 */
	public void setUltimaFechaReserva(Timestamp ultimaFechaReserva) 
	{
		this.ultimaFechaReserva = ultimaFechaReserva;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del Cliente
	 */
	public String toString() 
	{
		return "Cliente [identificacion=" + identificacion + ", nombre=" + nombre + ", tipoVinculo=" + tipoVinculo + ", correoElectronico=" + correoElectronico
				+ ", telefono=" + telefono + "]";
	}
}
