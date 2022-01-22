package datos.Hermes;

public class Jugador extends Datos {
	
	protected int idJugador;
	protected String nombre;
	protected int dia = 1;
	protected int exp = 0;
	protected float cartera =500; 
	
	
	
	
	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public float getCartera() {
		return cartera;
	}
	
	public void setCartera(float f) {
		this.cartera = f;
	}
	
	public int getDia() {
		return dia;
	}
	
	public void setDia(int dia) {
		this.dia = dia;
	}

	public Jugador(int idJugador, String nombre, int dia, int exp, float cartera) {
		super();
		this.idJugador = idJugador;
		this.nombre = nombre;
		this.dia = dia;
		this.exp = exp;
		this.cartera = cartera;
		
	}
}
