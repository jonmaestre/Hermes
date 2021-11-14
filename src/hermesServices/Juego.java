package hermesServices;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import datos.Hermes.Jugador;

public class Juego{
	
	protected ArrayList<Jugador> jugadores;
	private HashMap<Integer,String> clasJugador;
	
	public Juego(ArrayList<Jugador> jugadores,HashMap<Integer,String> clasJugador) {
		super();
		this.jugadores = jugadores;
		this.clasJugador = clasJugador;
	}
	public Juego() {
		super();
		this.jugadores = new ArrayList<Jugador>();
		this.clasJugador = new HashMap<Integer,String>();
	}
	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}
	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	public void cargarDatosJugadores() {
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader("saveslot.csv");
			br = new BufferedReader(fr);
			
			String linea = br.readLine();
			
			while (linea != null) {
				String[] campos = linea.split(",");
				
				Jugador nuevo = new Jugador(0, linea, 0, 0, 0, 0);
				nuevo.setIdJugador(Integer.parseInt(campos[0]));
				nuevo.setNombre(campos[1]);
				nuevo.setNivel(Integer.parseInt(campos[2]));
				nuevo.setExp(Integer.parseInt(campos[3]));
				nuevo.setCartera(Integer.parseInt(campos[4]));
				nuevo.setDia(Integer.parseInt(campos[5]));
				
				anyadirJugador(nuevo);
				
				linea = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void anyadirJugador(Jugador j) {
		jugadores.add(j);
		clasJugador.put(j.getIdJugador(), j.getNombre());
	}
	
	
	public void eliminarJugador(Jugador j) {
		jugadores.remove(j);
		clasJugador.remove(j.getIdJugador());

	}
	
	public void guardarDatosJugadores() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter("saveslot.csv");
			
			for (Jugador j : jugadores) {
				pw.println(j);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}
	}
	

	
}
