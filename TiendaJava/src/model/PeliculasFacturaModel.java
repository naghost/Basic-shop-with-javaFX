package model;

public class PeliculasFacturaModel {
	PeliculasModel IDPelicua;
	FacturaModel IDFactura;
	Double Precio;
	Integer Cantidad;
	
	public PeliculasFacturaModel() {
		
	}

	public PeliculasModel getIDPelicua() {
		return IDPelicua;
	}

	public void setIDPelicua(PeliculasModel iDPelicua) {
		IDPelicua = iDPelicua;
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
