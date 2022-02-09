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
	private JComboBox<Object> comBoxMueble= new JComboBox<>();;
	private JComboBox<Object> comBoxTematica= new JComboBox<>();;
	private JComboBox<Object> comBoxColor= new JComboBox<>();;
	private JComboBox<Object> comBoxMaterial= new JComboBox<>();;
	private JComboBox<Cliente> comBoxCliente= new JComboBox<>();;
	private ArrayList<Producto> listaProd;
	private JLabel texto=new JLabel("Clientes Entrantes: ");
	
	public ventanaHermes() {
		
		JPanel panBotones=new JPanel();
		JPanel filtro=new JPanel();
		JPanel panelAbajo=new JPanel();
		JPanel panelInfo=new JPanel();
		
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
		
		Cliente c1=new Cliente("Nico", tipoMueble.SILLA, tematica.RUSTICO, color.AMARILLO, material.M_PINO, "Buenos días, quiero una silla de colores chillones. Añoro mi juventud en el campo, con ese olor a pino tan característico.",1);
		Cliente c2=new Cliente("Sara",tipoMueble.CAMA, tematica.OTOÑO, color.NARANJA, material.M_ROBLE,"¡Quiero dormir mejor!Lo quiero de un estilo Otoñal, y de esos colores que recuerdan al Otoño.¿El material? ¡Siempre Madera!",1);
		Cliente c3=new Cliente("Andoni", tipoMueble.MESA,tematica.PRIMAVERA,color.ROJO,material.M_ROBLE,"Me apetece tener un sitio donde comer que me recuerde a la primavera y a esos colores vivos!.",1);
		Cliente c4=new Cliente("Beñat", tipoMueble.MESA,tematica.PRIMAVERA,color.ROJO,material.M_ROBLE,"Me apetece tener un sitio donde comer que me recuerde a la primavera y a esos colores vivos!.",2);
		Cliente c5=new Cliente("Mikel", tipoMueble.MESA,tematica.PRIMAVERA,color.ROJO,material.M_ROBLE,"Me apetece tener un sitio donde comer que me recuerde a la primavera y a esos colores vivos!.",2);
		Cliente c6=new Cliente("Maider", tipoMueble.MESA,tematica.PRIMAVERA,color.ROJO,material.M_ROBLE,"Me apetece tener un sitio donde comer que me recuerde a la primavera y a esos colores vivos!.",2);
		Cliente c7=new Cliente("Lara", tipoMueble.MESA,tematica.PRIMAVERA,color.ROJO,material.M_ROBLE,"Me apetece tener un sitio donde comer que me recuerde a la primavera y a esos colores vivos!.",2);
		
		ArrayList<Cliente> listaCliente= new ArrayList<>();
		listaCliente.add(c1);
		listaCliente.add(c2);
		listaCliente.add(c3);
		listaCliente.add(c4);
		listaCliente.add(c5);
		listaCliente.add(c6);
		listaCliente.add(c7);
		panelInfo.setLayout(new GridLayout(2,5));
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
		
		JLabel cartera=new JLabel(String.valueOf(jugador.getCartera()) + "(-)");
		JLabel exp= new JLabel(String.valueOf(jugador.getExp()) + "(-)");
		panelInfo.add(new JLabel("CARTERA(Última):"));
		panelInfo.add(new JLabel());
		panelInfo.add(new JLabel("DIA:"));
		panelInfo.add(new JLabel());
		panelInfo.add(new JLabel("EXP(Última):"));
		panelInfo.add(new JLabel());
		panelInfo.add(cartera);
		panelInfo.add(new JLabel());
		panelInfo.add(new JLabel(String.valueOf(jugador.getDia())));
		panelInfo.add(new JLabel());
		panelInfo.add(exp);
		panelAbajo.add(panelInfo,BorderLayout.WEST);
		

		
		actualizarTabla(almacenProd);
			
		btnAbrir.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				(new Thread() {
					public void run() {
						for (Cliente cliente : listaClientesDelDia(jugador.getDia(), listaCliente)) {
							texto.setText(cliente.getNombre() + ":   " + cliente.getDescripcion());
							comBoxCliente.addItem(cliente);
							try {
								Thread.sleep(15000);
							}catch(InterruptedException e) {
								e.printStackTrace();
							}
							
						}
						JOptionPane.showMessageDialog(v, "No tienes más clientes.\nCerraremos la tienda por hoy.");
			    		cerrarTienda();
						texto.setText("Cuando finalices el último pedido, puedes dar por concluido el día. Pulse ESC para terminar el día.");
						
					}
				}).start();
				
				btnAbrir.setVisible(false);
			}});
			
			
		comBoxCliente.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	Cliente c1= (Cliente) comBoxCliente.getSelectedItem();
		    	
		    	if(c1==null){
		    		texto.setText("Espera a que aparezca el siguiente cliente");
		    	} else {
		    		Cliente cliente= (Cliente) comBoxCliente.getSelectedItem();
			    	texto.setText(cliente.getNombre() + ":   " + cliente.getDescripcion());
		    	}
		    	
		    }
		});	
		
		
		btnFiltrar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					
					String tm=comBoxMueble.getSelectedItem().toString();
					String tematica=comBoxTematica.getSelectedItem().toString();
					String c=comBoxColor.getSelectedItem().toString();
					String mat=comBoxMaterial.getSelectedItem().toString();
					filtrarTabla(tm,tematica,c,mat,almacenProd);
					
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
				int expAnt=jugador.getExp();
				int row = tablaVenta.getSelectedRow();
				Producto p= new Producto((Integer) tablaVenta.getValueAt(row, 0), tipoMueble.valueOf(tablaVenta.getValueAt(row, 1).toString()), tematica.valueOf(tablaVenta.getValueAt(row, 2).toString()), color.valueOf(tablaVenta.getValueAt(row, 3).toString()), material.valueOf(tablaVenta.getValueAt(row, 4).toString()), (Double) tablaVenta.getValueAt(row, 5), (Double) tablaVenta.getValueAt(row, 6), (Integer) tablaVenta.getValueAt(row, 7), tablaVenta.getValueAt(row, 8).toString(), (Integer) tablaVenta.getValueAt(row, 9));
				venderProducto(p, jugador);
				todoVentas=bd.selectVenta();
				cartera.setText(String.valueOf(jugador.getCartera()) + "(" + todoVentas.get(todoVentas.size()-1).getPrecioVenta() + ")");
				exp.setText(String.valueOf(jugador.getExp())  + "(" + String.valueOf(jugador.getExp()- expAnt) + ")");
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
					cerrarTienda();
				}
			}
		});
	}
	
	private void actualizarTabla(List<Producto> productos) {
		if (productos.isEmpty()) {
			List<Producto> temp = new ArrayList<Producto>();
			temp.add(new Producto(0, null, null, null, null, 0.0, 0.0, 0, "", 0));
			JOptionPane.showMessageDialog(v, "No tienes más productos que vender.\nCerraremos la tienda por hoy.");
			cerrarTienda();
			
		} else {
			tablaVenta.setModel(new LaunchTabla(productos));
		}
		
	}
	
	private void cerrarTienda() {
		try {
			bd.cerrarConexion();
		} catch (IOException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ventanaStat vs=new ventanaStat();
		v.setVisible(false);
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
		for (int i = 0; i < comBoxCliente.getSize().getWidth(); i++) {
			Cliente c1= comBoxCliente.getItemAt(i);
			Cliente c2= (Cliente) comBoxCliente.getSelectedItem();
			if(c1.getNombre()==c2.getNombre()) {
				comBoxCliente.removeItem(comBoxCliente.getSelectedItem());
				break;
			}
		}
		comBoxCliente.setVisible(false);
		comBoxCliente.setVisible(true);
		actualizarTabla(almacenProd);
	}

	public void filtrarTabla(String a, String b, String c, String d,List<Producto> lista) {
		listaProd= new ArrayList<Producto>();
		for (Producto producto : lista) {
		if(producto.getTipoMueble().toString().equals(a) & producto.getTematica().toString().equals(b) 
				& producto.getColor().toString().equals(c) & producto.getMaterial().toString().equals(d)) {
		listaProd.add(producto);
		}
		
		}
		if(listaProd.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No se han encontrado productos de esas características");
		}else{
			actualizarTabla(listaProd);
		}
		

		}
	public ArrayList<Cliente> listaClientesDelDia(int dia,ArrayList<Cliente>listaClientes){
		ArrayList<Cliente> listaC=new ArrayList<>();
		for(Cliente c:listaClientes) {
			if(c.getDia()==dia) {
				listaC.add(c);
			}
		}
		return listaC;
		
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
