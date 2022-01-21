package hermesServices;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ventanaI {
	
	JLabel textoInicio=new JLabel(" ",JLabel.CENTER);
	private JLabel imagen;


	public ventanaI(int ancho, int altura) {
		JFrame  v= new JFrame("Hermes: Pantalla inicial");
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
		v.addKeyListener(new KeyAdapter() {//Evento cerrar la ventana para saltar a la siguiente

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					v.dispose();
					ventanaSaveSlots v= new ventanaSaveSlots(1900,800);
					//ventanaSaveSlots v1= ventanaSaveSlots();
				}
			}

		});
	}
}