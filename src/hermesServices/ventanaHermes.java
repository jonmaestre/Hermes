package hermesServices;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import datos.Hermes.Cliente;
import datos.Hermes.Jugador;
import datos.Hermes.Producto;
import datos.Hermes.Venta;
import datos.Hermes.color;
import datos.Hermes.material;
import datos.Hermes.tematica;
import datos.Hermes.tipoMueble;

public class ventanaHermes extends JFrame{
	private static final long serialVersionUID = 1L;
	private JFrame v= new JFrame("Tienda Hermes");
	private JTable tablaVenta;
	private Jugador jugador;
	private List<Producto> almacenProd;
	private List<Venta> todoVentas;
	private JButton btnVender= new JButton("Vender Producto");
	private JButton btnFiltrar= new JButton("Filtrar Productos");
	private JButton btnVista= new JButton("Enseñar Todos Los Producto");
	private JButton btnAbrir= new JButton("ABRIR LAS PUERTAS DE TU TIENDA");
	private BDynamic bd;
	private JComboBox<tipoMueble> comBoxMueble= new JComboBox<>();;
	private JComboBox<tematica> comBoxTematica= new JComboBox<>();;
	private JComboBox<color> comBoxColor= new JComboBox<>();;
	private JComboBox<material> comBoxMaterial= new JComboBox<>();;
	private JComboBox<Cliente> comBoxCliente= new JComboBox<>();;
	private ArrayList<Producto> listaProd;
	private JLabel texto=new JLabel("Clientes Entrantes: ");
	
	public ventanaHermes() {
		
		JPanel panBotones=new JPanel();
		JPanel filtro=new JPanel();
		JPanel panelAbajo=new JPanel();
		
		v.setSize(1900, 800);
		v.setLayout(new BorderLayout());
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.setVisible(true);
		panBotones.setLayout(new GridLayout(3,1));
		panBotones.add(btnVender);
		panBotones.add(btnFiltrar);
		panBotones.add(btnVista);
		v.add(panBotones,BorderLayout.EAST);
		v.add(panelAbajo,BorderLayout.SOUTH);
		
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
		v.add(filtro,BorderLayout.NORTH);
		v.setVisible(true);
		
		Cliente c11=new Cliente("Nico", tipoMueble.SILLA, tematica.RUSTICO, color.AMARILLO, material.M_PINO, "Buenos días, quiero una silla de colores chillones. Añoro mi juventud en el campo, con ese olor a pino tan característico.");
		Cliente c12=new Cliente("Sara",tipoMueble.CAMA, tematica.OTOÑO, color.NARANJA, material.M_ROBLE,"¡Quiero dormir mejor!Lo quiero de un estilo Otoñal, y de esos colores que recuerdan al Otoño.¿El material? ¡Siempre Madera!");
		Cliente c13=new Cliente("Andoni", tipoMueble.MESA,tematica.PRIMAVERA,color.ROJO,material.M_ROBLE,"Me apetece tener un sitio donde comer que me recuerde a la primavera y a esos colores vivos!.");
		ArrayList<Cliente> listaCliente1= new ArrayList<>();
		listaCliente1.add(c11);
		listaCliente1.add(c12);
		listaCliente1.add(c13);
		panelAbajo.add(texto,BorderLayout.WEST);
		panelAbajo.add(comBoxCliente,BorderLayout.CENTER);
		panelAbajo.add(btnAbrir,BorderLayout.EAST);

		//usu cargar usuarios de la bdd en la lista
		bd = new BDynamic();
		
		tablaVenta = new JTable();
		v.add(new JScrollPane(tablaVenta), BorderLayout.CENTER);
		
		
		
		try {
			bd.abrirBD();
			//todosJugadores = bd.getUsuarios();
			jugador = bd.selectUsuario();
			almacenProd=bd.selectProducto();
			todoVentas=bd.selectVenta();
		} catch (Exception e) {
			e.printStackTrace();
		}
	

		
		
		actualizarTabla(almacenProd);
			
		btnAbrir.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				(new Thread() {
					public void run() {
						for (Cliente cliente : listaCliente1) {
							texto.setText(cliente.getNombre() + ":   " + cliente.getDescripcion());
							comBoxCliente.addItem(cliente);
							try {
								Thread.sleep(60000);
							}catch(InterruptedException e) {
								
							}
						}
						texto.setText("Cuando finalices el último pedido, puedes dar por concluido el día. Pulse ESC para terminar el día.");
					}
				}).start();
				
				btnAbrir.setVisible(false);
			}});
			
			
		comBoxCliente.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	Cliente cliente= (Cliente) comBoxCliente.getSelectedItem();
		    	texto.setText(cliente.getNombre() + ":   " + cliente.getDescripcion());
		    }
		});	
		
		
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
				Producto p= new Producto((Integer) tablaVenta.getValueAt(row, 0), tipoMueble.valueOf(tablaVenta.getValueAt(row, 1).toString()), tematica.valueOf(tablaVenta.getValueAt(row, 2).toString()), color.valueOf(tablaVenta.getValueAt(row, 3).toString()), material.valueOf(tablaVenta.getValueAt(row, 4).toString()), (Double) tablaVenta.getValueAt(row, 5), (Double) tablaVenta.getValueAt(row, 6), (Integer) tablaVenta.getValueAt(row, 7), tablaVenta.getValueAt(row, 8).toString(), (Integer) tablaVenta.getValueAt(row, 9));
				venderProducto(p, jugador);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					
		}
		
	});
		
		v.addKeyListener(new KeyAdapter() {//Evento cerrar la ventana para saltar a la siguiente

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
					try {
						bd.cerrarConexion();
					} catch (IOException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ventanaStat vs=new ventanaStat();
					v.setVisible(false);
				}
			}
		});
	}

	
	
	private void actualizarTabla(List<Producto> productos) {
		if (productos.isEmpty()) {
			List<Producto> temp = new ArrayList<Producto>();
			temp.add(new Producto(0, null, null, null, null, 0.0, 0.0, 0, "", 0));
			JOptionPane.showMessageDialog(v, "No tienes más productos que vender.\nCerraremos la tienda por hoy.");
			try {
				bd.cerrarConexion();
			} catch (IOException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ventanaStat vs=new ventanaStat();
			v.setVisible(false);
		} else {
			tablaVenta.setModel(new LaunchTabla(productos));
		}
		
	}
	private int satisfaccion(Object object, Venta v) {
		int satis=0;
		if(((Cliente) object).getTipoMueble().equals(v.getTipoMueble())) {
			satis++;
		}
		if(((Cliente) object).getTematica().equals(v.getTematica())) {
			satis++;
		}
		if(((Cliente) object).getColor().equals(v.getColor())) {
			satis++;
		}
		if(((Cliente) object).getMaterial().equals(v.getMaterial())) {
			satis++;
		}
		return satis;
	}
	
	private void valoracion(int satisfaccion) {
		int antExp=jugador.getExp();
		if(satisfaccion==0) {
			JOptionPane.showMessageDialog(this, "¡Qué horrible! No vuelvo a esta tienda");
			if(antExp>200) {
				jugador.setExp(antExp - 200);
			}else {
				jugador.setExp(0);
			}
			
		}else if(satisfaccion==1) {
			JOptionPane.showMessageDialog(this, "No me ha gustado tu elección. No sé si volveré");
			jugador.setExp(antExp + 100);
		}else if(satisfaccion==2) {
			JOptionPane.showMessageDialog(this, "Mmmm.... No está mal. Hasta la próxima");
			jugador.setExp(antExp + 200);
		}else if(satisfaccion==3) {
			JOptionPane.showMessageDialog(this, "Sí se asemeja a lo que quería.¡Muchas gracias!");
			jugador.setExp(antExp + 300);
		}else if(satisfaccion==4) {
			JOptionPane.showMessageDialog(this, "¡Es justo lo que venía buscando!. Pronto me verás por aquí otra vez te lo aseguro.");
			jugador.setExp(antExp + 400);
		}
	}
	private void venderProducto(Producto p, Jugador jugador) throws SQLException {
		double cartera= jugador.getCartera();
		int dia=jugador.getDia();
		double precio= p.getPrecioVenta();	
		jugador.setCartera(cartera + precio);
		todoVentas=bd.selectVenta();
		Venta venta=new Venta(bd.generadorCodV(todoVentas),p.getTipoMueble(),p.getTematica(),p.getColor(),p.getMaterial(),
		precio,p.getPrecioCompra(),p.getDiaCompra(),dia,p.getTienda(),p.getCodU());
		int satis=satisfaccion(comBoxCliente.getSelectedItem(), venta);
		valoracion(satis);
		bd.updateUsuario(jugador);
		bd.eliminarProducto(p, jugador);
		bd.insertarVenta(venta, jugador);
		almacenProd=bd.selectProducto();
		comBoxCliente.remove(comBoxCliente.getSelectedIndex());
		comBoxCliente.setVisible(false);
		comBoxCliente.setVisible(true);
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
		JOptionPane.showMessageDialog(this, "No se han encontrado productos de esas características");
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
