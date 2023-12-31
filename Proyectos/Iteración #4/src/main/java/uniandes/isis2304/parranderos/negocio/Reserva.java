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

	/**
	 * La estado de una reserva 
	 */
	private String estado;

	/**
	 * El id de un grupo de alojamientos
	 */
	private long idGrupo;

	/**
	 * La Ganancia de un grupo de alojamientos
	 */
	private int ganancia;

	/**
	 * La NumOcupamiento de un grupo de alojamientos
	 */
	private int numOcupamiento;

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
		this.estado = "";
		this.idGrupo = 0;
		this.ganancia = 0;
		this.numOcupamiento = 0;
	}

	/**
	 * Constructor con valores
	 * @param id - La id de una reserva
	 * @param fechaIni - La fechaIni de una reserva
	 * @param fechaFin - La fechaFin de una reserva
	 * @param identificacionCliente - La identificacionCliente de una reserva
	 * @param idAlojamiento - La idAlojamiento de una reserva
	 * @param estado - La estado de una reserva (Y si esta activa o N si esta cancelada)
	 * @param idGrupo - La idGrupo de una reserva
	 * @param ganancia - La ganancia de una reserva
	 * @param numOcupamiento - La numOcupamiento de una reserva
	 */
    public Reserva(long id, Timestamp fechaIni, Timestamp fechaFin, String identificacionCliente, long idAlojamiento, String estado, long idGrupo, int ganancia, int numOcupamiento) 
    {
    	this.id = id;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.identificacionCliente = identificacionCliente;
		this.idAlojamiento = idAlojamiento;
		this.estado = estado;
		this.idGrupo = idGrupo;
		this.ganancia = ganancia;
		this.numOcupamiento = numOcupamiento;
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

	/**
	 * @return El estado de la reserva
	 */
	public String getEstado() 
	{
		return estado;
	}
	
	/**
	 * @param estado - El nuevo estado de la reserva
	 */
	public void setEstado(String estado) 
	{
		this.estado = estado;
	}

	/**
	 * @return El idGrupo de la reserva
	 */
	public long getIdGrupo() 
	{
		return idGrupo;
	}
	
	/**
	 * @param idGrupo - El nuevo idGrupo de la reserva
	 */
	public void setIdGrupo(long idGrupo) 
	{
		this.idGrupo = idGrupo;
	}

	/**
	 * @return El ganancia de la reserva
	 */
	public int getGanancia() 
	{
		return ganancia;
	}
	
	/**
	 * @param ganancia - El nuevo ganancia de la reserva
	 */
	public void setGanancia(int ganancia) 
	{
		this.ganancia = ganancia;
	}

	/**
	 * @return El numOcupamiento de la reserva
	 */
	public int getNumOcupamiento() 
	{
		return numOcupamiento;
	}
	
	/**
	 * @param ganancia - El nuevo ganancia de la reserva
	 */
	public void setNumOcupamiento(int numOcupamiento) 
	{
		this.numOcupamiento = numOcupamiento;
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
