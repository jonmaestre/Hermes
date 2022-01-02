package Control;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.MouseInfo;

import Principal.Constantes;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

import Grafico.SuperficieDibujo;
import Herramientas.CargadorRecursos;
import Principal.Constantes;

public class Raton extends MouseAdapter {
	
	private final Cursor cursor;
	private Point posicion;
	private boolean click;
	
	public Raton (final SuperficieDibujo sd) {
		Toolkit configuracion = Toolkit.getDefaultToolkit();
		
		BufferedImage icono = CargadorRecursos.cargarImagenTranslucida(Constantes.RUTA_ICONO_RATON);
		
		Constantes.LADO_CURSOR = icono.getWidth();
		
		Point punta = new Point (icono.getWidth()/2, icono.getHeight()/2);
		
		this.cursor = configuracion.createCustomCursor(icono, punta, "CursorDefecto");
		
		posicion = new Point ();
		
		actualizarPosicion(sd);
		
		click = false;
		
		
	}
	

	public Cursor getCursor() {
		return cursor;
	}
	
	public void actualizar(final SuperficieDibujo sd) {
		actualizarPosicion(sd);
	}
	
	public void dibujar(Graphics g) {
		g.setColor(Color.red);
		g.drawString("POSICION CURSOR" + posicion.getLocation(), 10, 200);
	}

	private void actualizarPosicion(SuperficieDibujo sd) {
		final Point posicionIncial = MouseInfo.getPointerInfo().getLocation();
		SwingUtilities.convertPointFromScreen(posicionIncial, sd);
		posicion.setLocation(posicionIncial);
		
		
	}
	
	public Point obtenerPosicionPunto() {
		return posicion;
	}
	public Rectangle obtenerRectanguloRaton() {
		final Rectangle area = new Rectangle (posicion.x,posicion.y,1,1);
		return area;
	}
	
	public void mouseClicked(MouseEvent e) {
		if(!click) {
			click = !click;
		}
		
	}
	public boolean isClick() {
		
		return click;
	
	}
	public void reiniciarClick() {
		if(click) {
			click = false;
		}
	}
	
}
