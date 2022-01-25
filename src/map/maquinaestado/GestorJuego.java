package maquinaestado;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import juego.Constantes;
import juego.ElementosPrincipales;
import herramientas.CargadorRecursos;
import herramientas.DatosDebug;
import herramientas.DibujoDebug;
import maquinaestado.MenuInferior;
import mapas.Mapa;
import maquinaestado.EstadoJuego;

public class GestorJuego implements EstadoJuego {

	BufferedImage logo;
	MenuInferior menuInferior;

	public GestorJuego() {
		menuInferior = new MenuInferior();
		logo = CargadorRecursos.cargarImagenCompatibleTranslucida(Constantes.RUTA_LOGOTIPO);

	}

	public void actualizar() {

		ElementosPrincipales.jugador.actualizar();
		ElementosPrincipales.mapa.actualizar();
	}

	public void dibujar(Graphics g) {
		ElementosPrincipales.mapa.dibujar(g);
		ElementosPrincipales.jugador.dibujar(g);
		menuInferior.dibujar(g);

		DibujoDebug.dibujarImagen(g, logo, 640 - logo.getWidth() - 5, 0 + 5);

		DatosDebug.enviarDato("X = " + ElementosPrincipales.jugador.obtenerPosicionXInt());
		DatosDebug.enviarDato("Y = " + ElementosPrincipales.jugador.obtenerPosicionYInt());
	}
}