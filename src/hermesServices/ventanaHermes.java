package hermesServices;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	private List<Producto> todoProd;
	private List<Producto> display;
	private JButton btnVender= new JButton("Vender Producto");
	private JButton btnFiltrar= new JButton("Filtrar Productos");
	private BDynamic bd;
	private JComboBox<tipoMueble> comBoxMueble= new JComboBox<>();;
	private JComboBox<tematica> comBoxTematica= new JComboBox<>();;
	private JComboBox<color> comBoxColor= new JComboBox<>();;
	private JComboBox<material> comBoxMaterial= new JComboBox<>();;
	private ArrayList<Producto> listaProd;
	
	public ventanaHermes( String nombre) {
		
		JFrame  v= new JFrame("Tienda Hermes");	
		JPanel panBotones=new JPanel();
		JPanel filtro=new JPanel();
		this.setSize(1900, 800);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		panBotones.add(btnVender);
		panBotones.add(btnFiltrar);
		this.add(panBotones,BorderLayout.SOUTH);
		
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
		
		

		//usu cargar usuarios de la bdd en la lista
		bd = new BDynamic();
		
		tablaVenta = new JTable();
		this.add(new JScrollPane(tablaVenta), BorderLayout.CENTER);
		
		
		
		try {
			bd.abrirBD();
			//todosJugadores = bd.getUsuarios();
			jugador = bd.selectUsuario();
			almacenProd=bd.selectProducto();
			todoProd=almacenProd;
		} catch (Exception e) {
			e.printStackTrace();
		}
//		tablaVenta.getColumnModel().getColumn(0).setMinWidth(100);
//		tablaVenta.getColumnModel().getColumn(1).setMinWidth(200);
//		tablaVenta.getColumnModel().getColumn(2).setMinWidth(100);
//		tablaVenta.getColumnModel().getColumn(3).setMinWidth(100);
//		tablaVenta.getColumnModel().getColumn(4).setMinWidth(100);
//		tablaVenta.getColumnModel().getColumn(5).setMinWidth(100);
//		tablaVenta.getColumnModel().getColumn(6).setMinWidth(100);
//		tablaVenta.getColumnModel().getColumn(7).setMinWidth(100);
//		tablaVenta.getColumnModel().getColumn(8).setMinWidth(100);
//		tablaVenta.getColumnModel().getColumn(9).setMinWidth(100);
		
		
		display=displayTienda(todoProd,jugador,nombre);
		actualizarTienda(display);
		
		btnFiltrar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					listaProd=new ArrayList<>();
					Object tm=comBoxMueble.getSelectedItem();
					Object tematica=comBoxMueble.getSelectedItem();
					Object c=comBoxColor.getSelectedItem();
					Object mat=comBoxMaterial.getSelectedItem();
					
					for (Producto producto : almacenProd) {
						if(tm.equals(producto.getTipoMueble()) & tematica.equals(producto.getTematica())
								& c.equals(producto.getColor()) & mat.equals(producto.getMaterial())) {
							listaProd.add(producto);
						}else {
							JOptionPane.showMessageDialog(v, "No se han encontrado productos de esas características");
						}
					}
					
				}catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
		
		v.addKeyListener(new KeyAdapter() {//Evento cerrar la ventana para saltar a la siguiente

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
					v.dispose();
				}
			}
			
	});
	
	
	private void actualizarTienda(List<Producto> productos) {
		tablaVenta.setModel(new LaunchTienda(productos));
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
		bd.insertarVenta(venta, jugador);
	}
	
	private List<Producto> displayTienda(List<Producto> productos, Jugador jugador,String tienda) {
		return productos;
		
	}
	
	
	
public class LaunchTienda  extends AbstractTableModel {
		
		
		private static final long serialVersionUID = 1L;
		private final List<String> headers = Arrays.asList("Codigo", "Tipo de Mueble", "Tematica", "Color", "Material","PV","Precio Compra","Dia","Tienda","Id. Jugador");
		private List<Producto> productos;
		
		public LaunchTienda(List<Producto> productos1) {
			this.productos = productos1;
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
