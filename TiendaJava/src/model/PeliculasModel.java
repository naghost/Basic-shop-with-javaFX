package model;

public class PeliculasModel {
	Integer IDPeliculas;
	String Titulo;
	String Director;
	String Genero;
	Integer  Año;
	Double Precio;
	Integer Stock;
	
	public PeliculasModel() {
		
	}

	public Integer getIDPeliculas() {
		return IDPeliculas;
	}

	public void setIDPeliculas(Integer iDPeliculas) {
		IDPeliculas = iDPeliculas;
	}

	public String getTitulo() {
		return Titulo;
	}

	public void setTitulo(String titulo) {
		Titulo = titulo;
	}

	public String getDirector() {
		return Director;
	}

	public void setDirector(String director) {
		Director = director;
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
