package hermesServices;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import datos.Hermes.Cliente;
import datos.Hermes.Jugador;
import datos.Hermes.Producto;
import datos.Hermes.Venta;
import datos.Hermes.color;
import datos.Hermes.material;
import datos.Hermes.tematica;
import datos.Hermes.tipoMueble;
import hermesServices.ventanaTiendas.LaunchTienda;

public class ventanaHermes extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTable tablaVenta;
	private Jugador jugador;
	private List<Producto> almacenProd;
	private List<Producto> display;
	private JButton btnVender= new JButton("Vender Producto");
	private JButton btnFiltrar= new JButton("Filtrar Productos");
	private JButton btnVista= new JButton("Ense�ar Todos Los Producto");
	private BDynamic bd;
	private JComboBox<tipoMueble> comBoxMueble= new JComboBox<>();;
	private JComboBox<tematica> comBoxTematica= new JComboBox<>();;
	private JComboBox<color> comBoxColor= new JComboBox<>();;
	private JComboBox<material> comBoxMaterial= new JComboBox<>();;
	private ArrayList<Producto> listaProd;
	private JLabel texto=new JLabel("Clientes Entrantes: PulsE ENTER PARA ABRIR LAS PUERTAS DE TU TIENDA");
	
	public ventanaHermes( String nombre) {
		
		JFrame  v= new JFrame("Tienda Hermes");	
		JPanel panBotones=new JPanel();
		JPanel filtro=new JPanel();
		JPanel panelAbajo=new JPanel();
		panelAbajo.add(texto);
		this.setSize(1900, 800);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		panBotones.setLayout(new GridLayout(3,1));
		panBotones.add(btnVender);
		panBotones.add(btnFiltrar);
		panBotones.add(btnVista);
		this.add(panBotones,BorderLayout.EAST);
		this.add(panelAbajo,BorderLayout.SOUTH);
		
		for(tipoMueble tm:tipoMueble.values()) {
			comBoxMueble.addItem(tm);
			
		}
		
		for(tematica tematica:tematica.values()) {
			comBoxTematica.addItem(tematica);
		}
		
		for (color color : color.values()) {
			comBoxColor.addItem(color);
			
		}
		
		
		for(material mat: material.values()) {
			comBoxMaterial.addItem(mat);
		}
	
		filtro.setLayout(new GridLayout(2,4));
		filtro.add(new JLabel("MUEBLE:"));
		filtro.add(new JLabel("TEMATICA:"));
		filtro.add(new JLabel("COLOR:"));
		filtro.add(new JLabel("MATERIAL:"));
		filtro.add(comBoxMueble);
		filtro.add(comBoxTematica);
		filtro.add(comBoxColor);
		filtro.add(comBoxMaterial);
		this.add(filtro,BorderLayout.NORTH);
		this.setVisible(true);
		
		Cliente c11=new Cliente("Nico", tipoMueble.SILLA, tematica.RUSTICO, color.AMARILLO, material.M_PINO, "Buenos d�as, quiero una silla de colores chillones. A�oro mi juventud en el campo, con ese olor a pino tan caracter�stico.");
		Cliente c12=new Cliente("Sara",tipoMueble.CAMA, tematica.OTO�O, color.NARANJA, material.M_ROBLE,"�Quiero dormir mejor!Lo quiero de un estilo Oto�al, y de esos colores que recuerdan al Oto�o.�El material? �Siempre Madera!");
		Cliente c13=new Cliente("Andoni", tipoMueble.MESA,tematica.PRIMAVERA,color.ROJO,material.M_ROBLE,"Me apetece tener un sitio donde comer que me recuerde a la primavera y a esos colores vivos!.");
		ArrayList<Cliente> listaCliente1= new ArrayList<>();
		listaCliente1.add(c11);
		listaCliente1.add(c12);
		listaCliente1.add(c13);

		//usu cargar usuarios de la bdd en la lista
		bd = new BDynamic();
		
		tablaVenta = new JTable();
		this.add(new JScrollPane(tablaVenta), BorderLayout.CENTER);
		
		
		
		try {
			bd.abrirBD();
			//todosJugadores = bd.getUsuarios();
			jugador = bd.selectUsuario();
			almacenProd=bd.selectProducto();
		} catch (Exception e) {
			e.printStackTrace();
		}
	

		
		
		actualizarTabla(almacenProd);
		
		//if(jugador.getDia()==1) { SI NOS RUNEASE EL PROGRAMA AL COMPLETO LANZAR�AMOS DISTINTOS HILOS CORRESPONDIENTES AL D�A EN EL QUE NOS ENCONTREMOS.
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					(new Thread() {
						public void run() {
							for (Cliente cliente : listaCliente1) {
								texto.setText(cliente.getNombre() + ":   " + cliente.getDescripcion());
								try {
									Thread.sleep(60000);
								}catch(InterruptedException e) {
									
								}
							}
							texto.setText("Cuando finalices el �ltimo pedido, puedes dar por concluido el d�a. Pulse ESC para terminar el d�a.");
						}
					}).start();
				}
			}
			
		});
		//}
			
			
			
			
			
			
	
		
		btnFiltrar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					listaProd=new ArrayList<>();
					Object tm=comBoxMueble.getSelectedItem();
					Object tematica=comBoxMueble.getSelectedItem();
					Object c=comBoxColor.getSelectedItem();
					Object mat=comBoxMaterial.getSelectedItem();
					filtrarTabla(tm,tematica,c,mat);
					
				}catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		
		});
		btnVista.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			actualizarTabla(almacenProd);
			}
		});
		
		btnVender.addMouseListener(new MouseAdapter()	{	
			@Override
			public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
				try {
				int row = tablaVenta.getSelectedRow();
				Producto p= new Producto((Integer) tablaVenta.getValueAt(row, 0), tipoMueble.valueOf(tablaVenta.getValueAt(row, 1).toString()), tematica.valueOf(tablaVenta.getValueAt(row, 2).toString()), color.valueOf(tablaVenta.getValueAt(row, 3).toString()), material.valueOf(tablaVenta.getValueAt(row, 4).toString()), (Integer) tablaVenta.getValueAt(row, 5), (Integer) tablaVenta.getValueAt(row, 6), (Integer) tablaVenta.getValueAt(row, 7), tablaVenta.getValueAt(row, 8).toString(), (Integer) tablaVenta.getValueAt(row, 9));
				venderProducto(p, jugador);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					
		}
		
	});
		
		this.addKeyListener(new KeyAdapter() {//Evento cerrar la ventana para saltar a la siguiente

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
					ventanaStat vs=new ventanaStat(1900, 800);
					
				}
			}
		});
	}

	
	
	private void actualizarTabla(List<Producto> productos) {
		tablaVenta.setModel(new LaunchTabla(productos));
	}
	private int satisfaccion(Cliente cliente, Venta v) {
		int satis=0;
		if(cliente.getTipoMueble().equals(v.getTipoMueble())) {
			satis++;
		}
		if(cliente.getTematica().equals(v.getTematica())) {
			satis++;
		}
		if(cliente.getColor().equals(v.getColor())) {
			satis++;
		}
		if(cliente.getMaterial().equals(v.getMaterial())) {
			satis++;
		}
		return satis;
	}
	
	private void valoracion(int satisfaccion) {
		int antExp=jugador.getExp();
		if(satisfaccion==0) {
			JOptionPane.showMessageDialog(this, "�Qu� horrible! No vuelvo a esta tienda");
			if(antExp>200) {
				jugador.setExp(antExp - 200);
			}else {
				jugador.setExp(0);
			}
			
		}else if(satisfaccion==1) {
			JOptionPane.showMessageDialog(this, "No me ha gustado tu elecci�n. No s� si volver�");
			jugador.setExp(antExp + 100);
		}else if(satisfaccion==2) {
			JOptionPane.showMessageDialog(this, "Mmmm.... No est� mal. Hasta la pr�xima");
			jugador.setExp(antExp + 200);
		}else if(satisfaccion==3) {
			JOptionPane.showMessageDialog(this, "S� se asemeja a lo que quer�a.�Muchas gracias!");
			jugador.setExp(antExp + 300);
		}else if(satisfaccion==4) {
			JOptionPane.showMessageDialog(this, "�Es justo lo que ven�a buscando!. Pronto me ver�s por aqu� otra vez te lo aseguro.");
			jugador.setExp(antExp + 400);
		}
	}
	private void venderProducto(Producto p, Jugador jugador) {
		double cartera= jugador.getCartera();
		int dia=jugador.getDia();
		double precio= p.getPrecioVenta();	
		jugador.setCartera(cartera + precio);
		Venta venta=new Venta(bd.generadorCodV(null),p.getTipoMueble(),p.getTematica(),p.getColor(),p.getMaterial(),
			precio,p.getPrecioCompra(),p.getDiaCompra(),dia,p.getTienda(),p.getCodU());
		int satis=satisfaccion(null, venta);
		valoracion(satis);
		bd.updateUsuario(jugador);
		bd.eliminarProducto(p, jugador);
		bd.insertarVenta(venta, jugador);
		almacenProd.remove(p);
		actualizarTabla(almacenProd);
	}

	public void filtrarTabla(Object a, Object b, Object c, Object d) {
		listaProd= new ArrayList<Producto>();
		for (Producto producto : almacenProd) {
		if(a.equals(producto.getTipoMueble()) & b.equals(producto.getTematica())
		& c.equals(producto.getColor()) & d.equals(producto.getMaterial())) {
		listaProd.add(producto);
		actualizarTabla(listaProd);
		}else {
		JOptionPane.showMessageDialog(this, "No se han encontrado productos de esas caracter�sticas");
		}
		}

		}
	
	public class LaunchTabla  extends AbstractTableModel {
		
		
		private static final long serialVersionUID = 1L;
		private final List<String> headers = Arrays.asList("Codigo", "Tipo de Mueble", "Tematica", "Color", "Material","PV","Precio Compra","Dia","Tienda","Id. Jugador");
		private List<Producto> productos=new ArrayList<Producto>();
		
		public LaunchTabla(List<Producto> productos) {
			this.productos = productos;
		}
		
		@Override
		public String getColumnName(int column) {
			return headers.get(column);
		}

		@Override
		public int getRowCount() {
			return productos.size();
		}

		@Override
		public int getColumnCount() {
			return headers.size(); 
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Producto p = productos.get(rowIndex);
			switch (columnIndex) {
				case 0: return p.getCodigoObjeto();
				case 1: return p.getTipoMueble();
				case 2: return p.getTematica();
				case 3: return p.getColor();
				case 4: return p.getMaterial();
				case 5: return p.getPrecioVenta();
				case 6: return p.getPrecioCompra();
				case 7: return p.getDiaCompra();
				case 8: return p.getTienda();
				case 9: return p.getCodU();
				default: return null;
			}
		}

	}
}