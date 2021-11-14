package hermesServices;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ventanaInicial extends Main {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelCentro= new JPanel();
	private JButton b1= new JButton("Crear Partida");
	private JButton b2= new JButton("Borrar Partida");
	private JButton b3= new JButton("Elegir Partida");

	public ventanaInicial(int ancho, int altura, String titulo) {
		JFrame  v= new JFrame(titulo);
		panelCentro.setLayout(new GridLayout(6,4));
		
		
		v.setSize(ancho, altura);
		v.setLayout(new BorderLayout());
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.setVisible(true);
		
		panelCentro.add(b1);
		panelCentro.add(b2);
		panelCentro.add(b3);
		v.add(panelCentro,BorderLayout.CENTER);
		
		b1.addMouseListener(new MouseAdapter()	{

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//juego.empezar();
				System.out.println("Nueva");
			}

		});
		
		b2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//juego.borrar();
				System.out.println("Borrar");
			}
		});
		b3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//juego.cargar();
				System.out.println("Carga");
			}
		});
	}
	
		
	
	
	
	
	

}


