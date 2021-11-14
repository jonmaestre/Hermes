package hermesServices;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.junit.jupiter.api.Test;

import datos.Hermes.Jugador;

class JuegoTest {

	@Test
	public void cargarDatosJugadores() {
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
