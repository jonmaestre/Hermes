package juego;

import java.awt.Font;

import herramientas.CargadorRecursos;

public class Constantes {

	// Hay que meter toda la informacion AQUI !!!!!!! IMPORTANTE NS DIBUJAR EN PHOTOSOP XD
	public static final int LADO_SPRITE = 32;

	public static int ANCHO_JUEGO = 640;
	public static int ALTO_JUEGO = 360;

	// public static int ANCHO_PANTALLA_COMPLETA = 1920;
	// public static int ALTO_PANTALLA_COMPLETA = 1080;

	public static int ANCHO_PANTALLA_COMPLETA = 1280;
	public static int ALTO_PANTALLA_COMPLETA = 720;

	// 4:3

	public static double FACTOR_ESCALADO_X = (double) ANCHO_PANTALLA_COMPLETA / (double) ANCHO_JUEGO;
	public static double FACTOR_ESCALADO_Y = (double) ALTO_PANTALLA_COMPLETA / (double) ALTO_JUEGO;

	public static int CENTRO_VENTANA_X = ANCHO_JUEGO / 2;
	public static int CENTRO_VENTANA_Y = ALTO_JUEGO / 2;

	public static int MARGEN_X = ANCHO_JUEGO / 2 - LADO_SPRITE / 2;
	public static int MARGEN_Y = ALTO_JUEGO / 2 - LADO_SPRITE / 2;

	public static String RUTA_MAPA = "/ImagenAudio/mapaHermes.json";
	public static String RUTA_ICONO_RATON = "/ImagenAudio/New Piskel.png";
	public static String RUTA_PERSONAJE = "";
	public static String RUTA_ICONO_VENTANA = "";
	public static String RUTA_LOGOTIPO = "";
	public static String RUTA_OBJETOS = "";

	public static Font FUENTE_PIXEL = CargadorRecursos.cargarFuente("/ImagenAudio/px10.ttf");
}