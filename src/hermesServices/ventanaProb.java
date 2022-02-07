package hermesServices;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import datos.Hermes.Producto;
import datos.Hermes.color;
import datos.Hermes.material;
import datos.Hermes.tematica;
import datos.Hermes.tipoMueble;


public class ventanaProb {
	private static final long serialVersionUID = 1L;
	private JFrame v;
	private JLabel info= new JLabel("Haz click en la tienda que quieres entrar");
	private JButton btnCarmele= new JButton("Ir a Muebles Carmele");
	private JButton btnIñaki= new JButton("Colchones Iñaki");
	private JButton btnAlex= new JButton("Interiores Alex");
	private JButton btnNext= new JButton("Volver al almacén");
	private JPanel panelSouth= new JPanel();

	public ventanaProb() {

		v= new JFrame("Hermes: Interfaz de tiendas");	

		v.setSize(1900, 800);
		v.setLayout(new BorderLayout());
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.setVisible(true);
		v.add(info,BorderLayout.NORTH);
		v.add(btnCarmele,BorderLayout.WEST);
		v.add(btnIñaki,BorderLayout.CENTER);
		v.add(btnAlex,BorderLayout.EAST);
		v.add(btnNext,BorderLayout.SOUTH);
		
		btnCarmele.addMouseListener(new MouseAdapter()	{	
			@Override
			public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
				ventanaTiendas vc = new ventanaTiendas("Muebles Carmele");
				v.dispose();
			}

		});
		btnIñaki.addMouseListener(new MouseAdapter()	{	
			@Override
			public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
				ventanaTiendas vi = new ventanaTiendas("Colchones Iñaki");
				v.dispose();
			}

		});
		btnAlex.addMouseListener(new MouseAdapter()	{	
			@Override
			public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
				ventanaTiendas va = new ventanaTiendas("Interiores Alex");
				v.dispose();
			}

		});
		
		btnNext.addMouseListener(new MouseAdapter()	{	
			@Override
			public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
				ventanaHermes vh= new ventanaHermes();
				v.dispose();
			}

		});

	}

}
