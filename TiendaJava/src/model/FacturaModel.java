package model;

import java.util.Date;

public class FacturaModel {
	Integer IDFactura;
	Date FechaHora;
	UsuarioModel IDUsuario;
	EstadoModel IDEstado;
	
	public FacturaModel() {
		
	}

	public Integer getIDFactura() {
		return IDFactura;
	}

	public void setIDFactura(Integer iDFactura) {
		IDFactura = iDFactura;
	}

	public Date getFechaHora() {
		return FechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		FechaHora = fechaHora;
	}

	public UsuarioModel getIDUsuario() {
		return IDUsuario;
	}

	public void setIDUsuario(UsuarioModel iDUsuario) {
		IDUsuario = iDUsuario;
	}

	public EstadoModel getIDEstado() {
		return IDEstado;
	}

	public void setIDEstado(EstadoModel iDEstado) {
		IDEstado = iDEstado;
	}
	
	
	
	
	
}
