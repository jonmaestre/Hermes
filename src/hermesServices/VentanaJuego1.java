package hermesServices;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class VentanaJuego1 extends Main {
	private JFrame Ventana;
	
	public VentanaJuego1( int altura, int anchura ) {
		super();
		//this.boton = boton;
		JFrame Ventana = new JFrame("Fondo1");
		//JButton Boton = new JButton ("Boton");
		Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Ventana.setSize(altura,anchura);
		Ventana.setVisible(true);
		Ventana.setContentPane(new JLabel(new ImageIcon("src/recursosAudiovisuales/Pueblo_Lavanda_RFVh.png")));
		 
		//boton.add("North", boton);
		//boton.show();
		}

	
}
