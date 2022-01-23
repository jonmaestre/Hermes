package hermesServices;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ventanaI extends JFrame{
	
	private JLabel textoInicio;
	private JLabel imagen;


	public ventanaI(){
		JFrame  v= new JFrame("Hermes: Pantalla inicial");
		textoInicio=new JLabel("PRESS ENTER TO START");
		textoInicio.setForeground(Color.BLUE);
		imagen= new JLabel("", JLabel.CENTER);
		imagen.setIcon(new ImageIcon("src/recursosAudioVisuales/fondoVentanaInicial.png"));
		this.setSize(1900, 800);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(imagen, BorderLayout.CENTER);
		this.add(textoInicio, BorderLayout.SOUTH);
		this.addKeyListener(new KeyAdapter() {//Evento cerrar la ventana para saltar a la siguiente

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					ventanaSaveSlots vss= new ventanaSaveSlots(1900, 800);
					vss.setVisible(true);
					v.setVisible(false);
					
					
					
					
					//ventanaSaveSlots v1= ventanaSaveSlots();
				}
			}

		});
	}
}
