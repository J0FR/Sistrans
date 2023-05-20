package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;

public interface VOCliente {
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return El identificacion
	 */
	public String getIdentificacion();

	/**
	 * @return El nombre
	 */
	public String getNombre();

	/**
	 * @return El tipoVinculo
	 */
	public String getTipoVinculo();
	
	/**
	 * @return El telefono
	 */
	public String getTelefono();
	
	/**
	 * @return El correoElectronico
	 */
	public String getCorreoElectronico();

	/**
	 * @return El UltimaFechaReserva
	 */
	public Timestamp getUltimaFechaReserva();

	/**
	 * @return El saldo
	 */
	public Integer getSaldo();

	/**
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString();
}
