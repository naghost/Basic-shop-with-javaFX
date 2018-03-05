package model;

public class ProductosFacturaModel {
	ProductoModel productoModel;
	FacturaModel IDFactura;
	Double Precio;
	Integer Cantidad;
	
	public ProductosFacturaModel() {
		
	}

	public ProductoModel getProductoModel() {
		return productoModel;
	}

	public void setProductoModel(ProductoModel productoModel) {
		this.productoModel = productoModel;
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
