package model;

public class DiscosFacturaModel {
	DiscosModel IDDisco;
	FacturaModel IDFactura;
	Double Precio;
	Integer Cantidad;
	
	public DiscosFacturaModel() {
		
	}

	public DiscosModel getIDDisco() {
		return IDDisco;
	}

	public void setIDDisco(DiscosModel iDDisco) {
		IDDisco = iDDisco;
	}

	public FacturaModel getIDFactura() {
		return IDFactura;
	}

	public void setIDFactura(FacturaModel iDFactura) {
		IDFactura = iDFactura;
	}

	public Double getPrecio() {
		return Precio;
	}

	public void setPrecio(Double precio) {
		Precio = precio;
	}

	public Integer getCantidad() {
		return Cantidad;
	}

	public void setCantidad(Integer cantidad) {
		Cantidad = cantidad;
	}
	
}
