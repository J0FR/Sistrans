package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;


public class Reserva implements VOReserva {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * La identificacion de una reserva
	 */
	private long id;
	
	/**
	 * La fechaIni de una reserva 
	 */
	private Timestamp fechaIni;
	
	/**
	 * La fechaFin de una reserva 
	 */
	private Timestamp fechaFin;
	
	/**
	 * La identificacionCliente de una reserva 
	 */
	private String identificacionCliente;
	
	/**
	 * La idAlojamiento de una reserva 
	 */
	private long idAlojamiento;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Reserva() 
    {
    	this.id = 0;
    	this.fechaIni = null;
    	this.fechaFin = null;
		this.identificacionCliente = "";
		this.idAlojamiento = 0;
	}

	/**
	 * Constructor con valores
	 * @param id - La id de una reserva
	 * @param fechaIni - La fechaIni de una reserva
	 * @param fechaFin - La fechaFin de una reserva
	 * @param identificacionCliente - La identificacionCliente de una reserva
	 * @param idAlojamiento - La idAlojamiento de una reserva
	 */
    public Reserva(long id, Timestamp fechaIni, Timestamp fechaFin, String identificacionCliente, long idAlojamiento) 
    {
    	this.id = id;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.identificacionCliente = identificacionCliente;
		this.idAlojamiento = idAlojamiento;
	}

    /**
	 * @return El id de la reserva
	 */
	public long getId() 
	{
		return id;
	}
	
	/**
	 * @param id - El nuevo id de la reserva
	 */
	public void setId(long id) 
	{
		this.id = id;
	}
	
	/**
	 * @return La fechaIni de la resrva
	 */
	public Timestamp getFechaIni() 
	{
		return fechaIni;
	}

	/**
	 * @param fechaIni - La nueva fechaIni de la reserva
	 */
	public void setFechaIni(Timestamp fechaIni) 
	{
		this.fechaIni = fechaIni;
	}
	
	/**
	 * @return La fechaFin de la resrva
	 */
	public Timestamp getFechaFin() 
	{
		return fechaFin;
	}

	/**
	 * @param fechaIni - La nueva fechaIni de la reserva
	 */
	public void setFechaFin(Timestamp fechaFin) 
	{
		this.fechaFin = fechaFin;
	}
	
    /**
	 * @return El identificacionCliente de la reserva
	 */
	public String getIdentificacionCliente() 
	{
		return identificacionCliente;
	}
	
	/**
	 * @param id - El nuevo identificacionCliente de la reserva
	 */
	public void setIdentificacionCliente(String identificacionCliente) 
	{
		this.identificacionCliente = identificacionCliente;
	}
	
	/**
	 * @return El idAlojamiento de la reserva
	 */
	public long getIdAlojamiento() 
	{
		return idAlojamiento;
	}
	
	/**
	 * @param id - El nuevo identificacionAlojamiento de la reserva
	 */
	public void setIdAlojamiento(long idAlojamiento) 
	{
		this.idAlojamiento = idAlojamiento;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la reserva
	 */
	public String toString() 
	{
		return "Reserva [id=" + id + ", fechaIni=" + fechaIni + ", fechaFin=" + fechaFin  + ", identificacionCliente=" + identificacionCliente  + ", idAlojamiento=" + idAlojamiento  +"]";
	}
}
