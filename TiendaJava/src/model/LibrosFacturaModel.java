package model;

public class LibrosFacturaModel {
	LibrosModel IDLibro;
	FacturaModel IDFactura;
	Double Precio;
	Integer Cantidad;
	
	public LibrosFacturaModel() {
		
	}

	public LibrosModel getIDLibro() {
		return IDLibro;
	}

	public void setIDLibro(LibrosModel iDLibro) {
		IDLibro = iDLibro;
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
