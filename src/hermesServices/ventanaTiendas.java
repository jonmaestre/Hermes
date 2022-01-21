package hermesServices;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import hermesServices.*;
import hermesServices.ventanaSaveSlots.LaunchTab;
import datos.Hermes.*;

public class ventanaTiendas {
	
	private static final long serialVersionUID = 1L;
	private JTable tablaVenta;
	private List<Producto> todoProd;
	private JButton btnComprar= new JButton("Comprar producto");
	private BDynamic bd;
	
	public ventanaTiendas(int ancho, int altura, String nombre) {
		
		JFrame  v= new JFrame("Hermes: Tienda");	
		
		v.setSize(ancho, altura);
		v.setLayout(new BorderLayout());
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.setVisible(true);
		
		v.add(btnComprar,BorderLayout.SOUTH);
		

		//usu cargar usuarios de la bdd en la lista
		bd = new BDynamic();
		
		tablaVenta = new JTable();
		v.add(new JScrollPane(tablaVenta), BorderLayout.CENTER);
		
		try {
			bd.abrirBD();
			//todosJugadores = bd.getUsuarios();
		} catch (Exception e) {}
		
		actualizarTienda(todoProd);
		
		
		
	}
	
	private void actualizarTienda(List<Producto> productos) {
		tablaVenta.setModel(new LaunchTienda(productos));
		//infoLabel.setText(String.format("%d launches", launches.size()));
	}
	
public class LaunchTienda  extends AbstractTableModel {
		
		
		private static final long serialVersionUID = 1L;
		private final List<String> headers = Arrays.asList("Codigo", "Tipo deMueble", "Tematica", "Color", "Material",);
		//producto (codigoProducto, tipoMueble, tematica, color, material, precioVenta, precioCompra,diaCompra, String, cod_u)
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
				case 0: return j.getIdJugador();
				case 1: return j.getNombre();
				case 2: return j.getDia();
				case 3: return j.getExp();
				case 4: return j.getCartera();
				default: return null;
			}
		}

	}
	
}
