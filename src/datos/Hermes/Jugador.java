package datos.Hermes;

public class Jugador extends Datos {
	
	protected int idJugador;
	protected String nombre;
	protected int nivel;
	protected int exp = 0;
	protected int cartera = 0; 
	// Monedas que posee el jugador
	// Hacer lista de entidades objeto
	private int dia = 0;
	
	
	
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
	
	public int getNivel() {
		return nivel;
	}
	
	public void setNivel(int nivel) {
		if (this.exp <= 10) {
			this.nivel = 0;
			
		}
		else if (this.exp <= 100) {
			this.nivel = 1;
		}
		else if (this.exp <= 200) {
			this.nivel = 2;
		}
		else if (this.exp <= 300) {
			this.nivel = 3;
		}
		else if (this.exp <= 400) {
			this.nivel = 4;
		}
		else if (this.exp <= 500) {
			this.nivel = 5;
		}
		else if (this.exp <= 600) {
			this.nivel = 6;
		}
		else if (this.exp <= 700) {
			this.nivel = 7;
		}
		else if (this.exp <= 800) {
			this.nivel = 7;
		}
		else if (this.exp <= 900) {
			this.nivel = 9;
		}
		else {
			this.nivel = 10;
		}
	}
	
	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
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

	public Jugador(int idJugador, String nombre, int nivel, int exp, int cartera, int dia) {
		super();
		this.idJugador = idJugador;
		this.nombre = nombre;
		this.nivel = nivel;
		this.exp = exp;
		this.cartera = cartera;
		this.dia = dia;
	}

	@Override
	public String toString() {
		return "Jugador [ID="+idJugador +"nombre=" + nombre + ", nivel=" + nivel + ", exp=" + exp
				+ ", cartera=" + cartera + ", dia=" + dia + "]";
	}
	
	
}
