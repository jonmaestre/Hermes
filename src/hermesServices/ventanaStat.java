package hermesServices;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
import org.jfree.data.xy.XYDataset;

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
	private JComboBox combo = new JComboBox<String>();
	private JButton btnDatos= new JButton("Más Datos");
	
	public ventanaStat(int ancho, int altura) {
		
		JFreeChart chart= createDayAreaBegFin();
		
		cp = new ChartPanel(chart);

		combo.addItem("G. Dinero invertido/Producido");
		combo.addItem("Crecimiento de dinero/dia");
		combo.addItem("Movimiento de stock a través del tiempo");		
		
			
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
					
				} else if (combo.getSelectedItem().toString()=="Crecimiento de dinero/dia") {
					JFreeChart charts= createLineaCrecimiento();
					refreshChartpanel(charts);
				} else if (combo.getSelectedItem().toString()=="Crecimiento de dinero/dia") {
					JFreeChart charts= createLineaCrecimiento();
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
        CategoryDataset dataset = DatasetUtilities.createCategoryDataset("Kromer","Dia ", data);
        return ChartFactory.createStackedAreaChart("Dinero invertido/Producido","Kromer", "Dia",dataset,PlotOrientation.VERTICAL,true,true,true);
    }
	
	public JFreeChart createLineaCrecimiento() {
		
		Double[][] data = new Double[0][jugador.getDia()-1];
		Double dinero=500.0;
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
			dinero=dinero-dineroCompra+dineroVenta;
			data[0][i-1]=dinero;
			
		}
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset("Kromer","Dia ", data);
        return ChartFactory.createXYLineChart("Crecimiento de dinero/dia", "Dia", "Kromer", (XYDataset) dataset, PlotOrientation.VERTICAL, true, true, true);
        		
	}
	
public JFreeChart createStockMovimiento() {
		
		Double[][] data = new Double[1][jugador.getDia()-1];
		
		for (int i = 1; i < jugador.getDia(); i++) {
			Double contCompra=0.0;
			Double contVenta=0.0;
			for (Venta v : todoVentas) {
				if (v.getDiaVenta()==i) {
					contVenta++;
				}
			}
			for (Producto p : todoProductos) {
				if(p.getDiaCompra()==i) {
					contCompra++;
				}
			}
			data[0][i-1]=contCompra;
			data[1][i-1]=contVenta;
		}
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset("Kromer","Dia ", data);
        return ChartFactory.createBarChart("Movimiento de stock a través del tiempo", "Dia", "Stock", dataset);
        		
	}
	
		
	
}
