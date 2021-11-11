package datos.Hermes;

public class Jugador extends Datos {
	
	private int Id = 0;
	private String nombre = "";
	private int nivel = 0;
	private int cartera = 0; // Monedas que posee el jugador
	// Hacer lista de entidades objeto
	private int dia = 0;
	
	public int getId() {
		return Id;
	}
	
	public void setId(int id) {
		Id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getNivel() {
		return nivel;
	}
	
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	public int getCartera() {
		return cartera;
	}
	
	public void setCartera(int cartera) {
		this.cartera = cartera;
	}
	
	public int getDia() {
		return dia;
	}
	
	public void setDia(int dia) {
		this.dia = dia;
	}
	
}
