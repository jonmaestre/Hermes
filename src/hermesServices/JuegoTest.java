package hermesServices;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

import org.junit.jupiter.api.Test;

import datos.Hermes.Jugador;

class JuegoTest {
	private Juego Juego;
	
	public void escenario() {
		Juego = new Juego();
	}

	@Test
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
		assertDoesNotThrow(Juego.guardarDatosJugadores(), "ERROR EN GUARDA DE DATOS");
	}
	

	private void assertDoesNotThrow(Object object, String string) {
		// TODO Auto-generated method stub
		
	}
	@Test
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
		assertDoesNotThrow(Juego.cargarDatosJugadores(), "ERROR EN CARGA DE DATOS");
	}

	
	private void anyadirJugador(Jugador nuevo) {
		// TODO Auto-generated method stub
		
	}
}
