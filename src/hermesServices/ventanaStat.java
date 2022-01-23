package hermesServices;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

import datos.Hermes.*;

public class ventanaStat {
	
	private static final long serialVersionUID = 1L;
	private JFrame  v= new JFrame("Hermes: Estadisticas");
	private Jugador jugador;
	private List<Producto> todoProductos;
	private List<Venta> todoVentas;
	private BDynamic bd;
	private JFreeChart chart;
	private ChartPanel cp;
	private JTextField tf = new JTextField(30);
	private JComboBox combo = new JComboBox();
	private JButton btnDatos= new JButton("Más Datos");
	
	public ventanaStat(int ancho, int altura) {
		
		JFreeChart chart= createDayAreaBegFin();
		
		cp = new ChartPanel(chart);
		
		ArrayList<String> comboBoxItems=new ArrayList<String>();
	    comboBoxItems.add("A");
	    comboBoxItems.add("B");
	    comboBoxItems.add("C");
	    comboBoxItems.add("D");
	    comboBoxItems.add("E");
	    final DefaultComboBoxModel model = new DefaultComboBoxModel();
	    model.addAll(comboBoxItems);
	    combo = new JComboBox(model);
		
		combo.addItem("G. Dinero invertido/Producido");
		.addItem("G. Dinero invertido/Producido");
		combo.addItem("dos");
		combo.addItem("tres");		
		
			
		v.setSize(ancho, altura);
		v.setLayout(new BorderLayout());
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.setVisible(true);
		v.add(cp,BorderLayout.CENTER);
		v.add(tf,BorderLayout.SOUTH);
		v.add(combo,BorderLayout.SOUTH);
		v.add(btnDatos,BorderLayout.SOUTH);
		
		combo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tf.setText(combo.getSelectedItem().toString());
				if (combo.getSelectedItem().toString()=="G. Dinero invertido/Producido") {
					JFreeChart charts= createDayAreaBegFin();
					refreshChartpanel(charts);
					
				}
			}
		});
		
		
		

//		//usu cargar usuarios de la bdd en la lista
		bd = new BDynamic();
		try {
			bd.abrirBD();
			//todosJugadores = bd.getUsuarios();
			jugador = bd.selectUsuario();
			todoProductos=bd.selectProducto();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void refreshChartpanel(JFreeChart charts) {
		chart=charts;
		cp = new ChartPanel(chart);
		v.setVisible(false);
		v.setVisible(true);
	}
	
	
	public JFreeChart createDayAreaBegFin() {
		
		Double[][] data = new Double[1][jugador.getDia()-1];
		Double[] iniciales= new Double[jugador.getDia()-1];
		Double[] finales= new Double[jugador.getDia()-1];
		for (int i = 1; i < jugador.getDia(); i++) {
			Double dineroCompra=0.0;
			Double dineroVenta=0.0;
			for (Venta v : todoVentas) {
				if(v.getDiaCompra()==i) {
					dineroCompra=dineroCompra+v.getPrecioCompra();
				}
				if (v.getDiaVenta()==i) {
					dineroVenta=dineroVenta+v.getPrecioVenta();
				}
			}
			for (Producto p : todoProductos) {
				if(p.getDiaCompra()==i) {
					dineroCompra=dineroCompra+p.getPrecioCompra();
				}
			}
			iniciales[i-1]=dineroCompra;
			finales[i-1]=dineroVenta;
		}
		data[0]=iniciales;
		data[1]=finales;
        CategoryDataset dataset = DatasetUtilities.createCategoryDataset("S","C", data);
        return ChartFactory.createStackedAreaChart("Dinero invertido/Producido","Kromer", "Day",dataset,PlotOrientation.VERTICAL,true,true,true);

    }
	
}
