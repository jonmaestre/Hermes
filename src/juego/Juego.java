package juego;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import control.Teclado;

public class Juego extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;

	private static final int ANCHO= 800;
	private static final int ALTO= 800;
	
	private static volatile boolean enfuncionamineto = false;
	
	private static final String NOMBRE= "JUEGO";
	
	private static int aps = 0;
	private static int fps = 0;
	
	private static JFrame ventana;
	private Thread hilo;
	private static Teclado teclado;
	
	private Juego() {
		setPreferredSize(new Dimension(ANCHO,ALTO));
		
		teclado = new Teclado();
		addKeyListener(teclado);
		
		ventana = new JFrame(NOMBRE);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);
		ventana.setLayout(new BorderLayout());
		ventana.add(this, BorderLayout.CENTER);
		ventana.pack();
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		
	}
	public static void main(String[] args) {
		Juego juego = new Juego();
		juego.iniciar();
	}
	
	public synchronized void iniciar () {
		enfuncionamineto = true;
		hilo = new Thread(this, "Graficos");
		hilo.start();
		
	}
	
	public synchronized void detener () {
		enfuncionamineto = false;
		
		try {
			hilo.join();
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		
	}
	
	private void actualizar () {
		teclado.actualizar();
		
		if(teclado.arriba) {
			System.out.println("arriba");
		}
		if(teclado.abajo) {
			System.out.println("abajo");
		}
		if(teclado.izquierda) {
			System.out.println("izquierda");
		}
		if(teclado.derecha) {
			System.out.println("derecha");
		}
		
		aps++;
		
	}
	
	private void mostrar () {
		fps++;
	}

	public void run() {
		final int NS_POR_SEGUNDO = 1000000000;
		final byte APS_OBJETIVO = 60;
		final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;
		
		long referenciaActualizacion = System.nanoTime();
		long referenciaContador = System.nanoTime();
		
		double tiempoTranscurrido;
		double delta = 0;
		
		requestFocus();
		
		System.nanoTime();
		while (enfuncionamineto) {
			final long incioBucle = System.nanoTime();
			
			tiempoTranscurrido = incioBucle - referenciaActualizacion;
			referenciaActualizacion = incioBucle;
			
			delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;
			
			while(delta >= 1) {
				actualizar();
				delta--;
			}
			
			mostrar();
			//System.out.println("2HILO ejecutandose");
			if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {
				ventana.setTitle(NOMBRE + " // APS: " + aps + " // FPS: " + fps );
				aps = 0;
				fps = 0;
				referenciaContador = System.nanoTime();
				
			}
		}

	}
	
}
