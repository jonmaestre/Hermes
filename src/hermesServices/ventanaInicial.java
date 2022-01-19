package hermesServices;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ventanaInicial extends Main {

	JLabel textoInicio=new JLabel(" ",JLabel.CENTER);
	private JLabel imagen;


public ventanaInicial(int ancho, int altura, String titulo) {
	JFrame  v= new JFrame(titulo);
	textoInicio.setText("PRESS ENTER TO START");
	textoInicio.setForeground(Color.BLUE);
	imagen= new JLabel("", JLabel.CENTER);
	imagen.setIcon(new ImageIcon("src/recursosAudioVisuales/fondoVentanaInicial.png"));

	v.setSize(ancho, altura);
	v.setLayout(new BorderLayout());
	v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	v.add(imagen, BorderLayout.CENTER);
	v.add(textoInicio, BorderLayout.SOUTH);
	v.setVisible(true);




	v.addWindowListener(new WindowAdapter() {
		
		public void windowOpened(WindowEvent e) {//Hilo parpadeo del texto
			// TODO Auto-generated method stub
			(new Thread() {
				public void run() {
					textoInicio.setVisible(true);
					try {
						Thread.sleep( 500 );
					} catch (InterruptedException e) {}
					textoInicio.setVisible(false);
				}
			}).start();
		}
	});


	v.addKeyListener(new KeyAdapter() {//Evento cerrar la ventana para saltar a la siguiente

		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				v.dispose();
				ventanaSaveSlots v= new ventanaSaveSlots(1900,800,"Hermes");
				//ventanaSaveSlots v1= ventanaSaveSlots();
			}
		}

	});





}




}

