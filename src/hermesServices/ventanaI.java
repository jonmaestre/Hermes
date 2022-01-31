package hermesServices;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ventanaI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JLabel textoInicio;
	private JLabel imagen;


	public ventanaI(){
		
		JFrame  v= new JFrame("Hermes: Pantalla inicial");
		textoInicio=new JLabel("PRESS ENTER TO START");
		textoInicio.setForeground(Color.BLUE);
		imagen= new JLabel("", JLabel.CENTER);
		imagen.setIcon(new ImageIcon("mapsrc/recursosAudioVisuales/fondoVentanaInicial.png"));
		v.setSize(1900, 800);
		v.setLayout(new BorderLayout());
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.add(imagen, BorderLayout.CENTER);
		JPanel c= new JPanel();
		v.add(c, BorderLayout.SOUTH);
		c.add(textoInicio, BorderLayout.CENTER);
		
		v.setVisible(true);
		
		v.addKeyListener(new KeyAdapter() {//Evento cerrar la ventana para saltar a la siguiente

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					ventanaSaveSlots vss= new ventanaSaveSlots();
					vss.setVisible(false);
					v.setVisible(false);
				}
			}

		});
	}
}
