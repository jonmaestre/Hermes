package datos.Hermes;

public class Jugador{
	
	protected int idJugador;
	protected String nombre;
	protected int dia = 1;
	protected int exp = 0;
	protected double cartera =500; 
	
	
	
	
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

	public double getCartera() {
		return cartera;
	}
	
	public void setCartera(double f) {
		this.cartera = f;
	}
	
	public int getDia() {
		return dia;
	}
	
	public void setDia(int dia) {
		this.dia = dia;
	}

	public Jugador(int idJugador, String nombre, int dia, int exp, double cartera) {
		super();
		this.idJugador = idJugador;
		this.nombre = nombre;
		this.dia = dia;
		this.exp = exp;
		this.cartera = cartera;
		
	}

	@Override
	public String toString() {
		return nombre + "(" + idJugador  + ", dia=" + dia + ", exp=" + exp + ", cartera="
				+ cartera + ")";
	}
	
	
}
