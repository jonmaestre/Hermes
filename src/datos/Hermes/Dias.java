package datos.Hermes;

import java.util.ArrayList;

public class Dias extends Datos {
	
	
	private int contador = 0 ;
	// esto es para cuando lo relacionemos con los eventos
	private ArrayList<String> festivo;
	
	
	
	public Dias(String tipo, int contador, ArrayList<String> festivo) {
		super(tipo);
		this.contador = contador;
		this.festivo = festivo;
	}



	public int getContador() {
		return contador;
	}



	public void setContador(int contador) {
		this.contador = contador;
	}



	public ArrayList<String> getFestivo() {
		return festivo;
	}



	public void setFestivo(ArrayList<String> festivo) {
		this.festivo = festivo;
	}
	
	
	}


