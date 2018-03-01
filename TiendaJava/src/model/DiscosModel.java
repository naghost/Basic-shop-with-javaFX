package model;

public class DiscosModel {
	Integer IDDiscos;
	String Titulo;
	String Autor;
	String Genero;
	Integer Año;
	Double Precio;
	Integer Stock;
	
	public DiscosModel() {
		
	}

	public Integer getIDDiscos() {
		return IDDiscos;
	}

	public void setIDDiscos(Integer iDDiscos) {
		IDDiscos = iDDiscos;
	}

	public String getTitulo() {
		return Titulo;
	}

	public void setTitulo(String titulo) {
		Titulo = titulo;
	}

	public String getAutor() {
		return Autor;
	}

	public void setAutor(String autor) {
		Autor = autor;
	}

	public String getGenero() {
		return Genero;
	}

	public void setGenero(String genero) {
		Genero = genero;
	}

	public Integer getAño() {
		return Año;
	}

	public void setAño(Integer año) {
		Año = año;
	}

	public Double getPrecio() {
		return Precio;
	}

	public void setPrecio(Double precio) {
		Precio = precio;
	}

	public Integer getStock() {
		return Stock;
	}

	public void setStock(Integer stock) {
		Stock = stock;
	}
	
}
