package hermesServices;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ventanaInicial extends Main {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelbotones= new JPanel();
	private JLabel panelFondo;
	private JButton btnCrear= new JButton("Crear Partida");
	private JButton btnElegir= new JButton("Elegir Partida");
	

	public ventanaInicial(int ancho, int altura, String titulo) {
		JFrame  v= new JFrame(titulo);
		panelbotones.setLayout(new GridLayout(1,2));
		
		
		v.setSize(ancho, altura);
		v.setLayout(new BorderLayout());
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.setVisible(true);
		
		
		panelbotones.add(btnCrear);
		panelbotones.add(btnElegir);
		v.add(panelbotones,BorderLayout.SOUTH);
		v.add(panelFondo);
		
		btnCrear.addMouseListener(new MouseAdapter()	{

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//juego.empezar();
				System.out.println("Nueva");
			}

		});
		
		
		btnElegir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//juego.cargar();
				System.out.println("Carga");
			}
		});
		
	}
	

	

}



