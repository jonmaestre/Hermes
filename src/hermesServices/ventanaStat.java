package hermesServices;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import datos.Hermes.*;
import juego.Constantes;
import juego.Juego;

public class ventanaStat {
	
	private static final long serialVersionUID = 1L;
	private JFrame  v= new JFrame("Hermes: Estadisticas");
	private Jugador jugador;
	private List<Producto> todoProductos;
	private List<Venta> todoVentas;
	private BDynamic bd;
	private JFreeChart chart;
	private ChartPanel cp = new ChartPanel(null);
	private JTextField tf = new JTextField(30);
	private JComboBox combo = new JComboBox<String>();
	private JButton btnDatos= new JButton("Más Datos");
	
	public ventanaStat() {
		
		//JFreeChart chart= createDayAreaBegFin();
		
		//cp = new ChartPanel(chart);

		combo.addItem("G. Dinero invertido/Producido");
		combo.addItem("Crecimiento de dinero/dia");
		combo.addItem("Demanda por tipo de mueble");
		combo.addItem("Demanda por tematica");
		combo.addItem("Demanda por color");
		combo.addItem("Demanda por material");
		
			
		
		v.setSize(1900, 800);
		v.setLayout(new BorderLayout());
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.add(cp,BorderLayout.CENTER);

		JPanel u= new JPanel();
		v.add(u,BorderLayout.SOUTH);
		u.add(tf);
		u.add(combo);
		u.add(btnDatos);
		v.setVisible(true);
		
		
		
		
//		//usu cargar usuarios de la bdd en la lista
		bd = new BDynamic();
		try {
			bd.abrirBD();
			jugador = bd.selectUsuario();
			todoProductos=bd.selectProducto();
			todoVentas=bd.selectVenta();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		 todoProductos= new ArrayList<Producto>();
//		 todoVentas= new ArrayList<Venta>();
//		
////		jugador= new Jugador(0, "Aitor", 1, 2000, 800);
////		
////		Producto p1= new Producto(0, tipoMueble.SOFA, tematica.INVIERNO, color.GRIS, material.PLASTICO, 835.12, 500.00, 1, "Apolo´s", 0);
////		Producto p2= new Producto(1, tipoMueble.SILLA, tematica.HALLOWEEN, color.BLANCO, material.M_ABEDUL, 100.13, 80.14, 1, "Polo´s", 0);
////		Producto p3= new Producto(2, tipoMueble.SILLA, tematica.HALLOWEEN, color.BLANCO, material.M_ABEDUL, 100.13, 80.14, 1, "Polo´s", 0);
////		todoProductos.add(p1);
////		todoProductos.add(p2);
////		todoProductos.add(p3);
////
////		Venta v1= new Venta(0, tipoMueble.SOFA, tematica.INVIERNO, color.GRIS, material.PLASTICO, 835.12, 500.00, 1, 1, "Apolo´s", 0);
////		Venta v2= new Venta(1, tipoMueble.SILLA, tematica.HALLOWEEN, color.BLANCO, material.M_ABEDUL, 100.13, 80.14, 1, 1, "Polo´s", 0);
////		Venta v3= new Venta(2, tipoMueble.SILLA, tematica.HALLOWEEN, color.BLANCO, material.M_ABEDUL, 100.13, 80.14, 1, 1, "Polo´s", 0);
////		todoVentas.add(v1);
////		todoVentas.add(v2);
////		todoVentas.add(v3);
		
		
		
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
				} else if (combo.getSelectedItem().toString()=="Demanda por tipo de mueble") {
					JFreeChart charts= createSBTipoMueble();
					refreshChartpanel(charts);
				} else if (combo.getSelectedItem().toString()=="Demanda por tematica") {
					JFreeChart charts= createSBTematica();
					refreshChartpanel(charts);
				} else if (combo.getSelectedItem().toString()=="Demanda por color") {
					JFreeChart charts= createSBColor();
					refreshChartpanel(charts);
				} else if (combo.getSelectedItem().toString()=="Demanda por material") {
					JFreeChart charts= createSBMaterial();
					refreshChartpanel(charts);
				}
			}
		});
		
		btnDatos.addMouseListener(new MouseAdapter()	{	
			@Override
			public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub				
				ventanamasDatos();
			}

		});
	
		v.addKeyListener(new KeyAdapter() {//Evento cerrar la ventana para saltar a la siguiente

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					
					try {
						finDelDia();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ventanaSaveSlots vss= new ventanaSaveSlots();
					

					
					//Para OpenGL en Mac/Linux
					//System.setProperty("sun.java2d.opengl", "True");
					
					
					//  Para Directx en Windows
					  System.setProperty("sun.java2d.d3d", "True");
					  System.setProperty("sun.java2d.ddforcevram", "True");
					 
					
					//System.setProperty("sun.java2d.transaccel", "True");
					
					Juego gp = new Juego("JUEGO", Constantes.ANCHO_PANTALLA_COMPLETA,
							Constantes.ALTO_PANTALLA_COMPLETA);
					
					gp.iniciarJuego();
					gp.iniciarBuclePrincipal();
						
					v.setVisible(false);
					
				}
			}

		});
	}
	
	public void refreshChartpanel(JFreeChart charts) {
		chart=charts;
		cp = new ChartPanel(chart);
		cp.setVisible(false);
		cp.setVisible(true);
	}
	
	public void ventanamasDatos() {
		JFrame  v= new JFrame("Hermes: Estadisticas");
		v.setSize(1900, 800);
		v.setLayout(new BorderLayout());
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel n= new JPanel();
		JPanel c= new JPanel();
		JPanel s= new JPanel();
		v.add(n,BorderLayout.NORTH);
		v.add(c,BorderLayout.NORTH);
		v.add(s,BorderLayout.NORTH);
		
		

		Double ingresoNeto1=jugador.getCartera();
		Double ingresoNeto2=jugador.getCartera();
		int contp=0;
		int contv=0;
		for (Venta ve : todoVentas) {
			if(ve.getDiaCompra()==jugador.getDia()) {
				ingresoNeto2=ingresoNeto2+ve.getPrecioCompra();
				contp++;
			}
			if (ve.getDiaVenta()==jugador.getDia()) {
				ingresoNeto2=ingresoNeto2-ve.getPrecioVenta();
				contv++;
			}
		}
		for (Producto p : todoProductos) {
			if(p.getDiaCompra()==jugador.getDia()) {
				ingresoNeto2=ingresoNeto2+p.getPrecioCompra();
				contp++;
			}
		}
		JLabel textoDineroActual=new JLabel("Actualmente tienes: "+jugador.getCartera()+"k");
		JLabel textoExperienciaActual=new JLabel("Actualmente tienes: "+jugador.getExp()+" exp");
		JLabel textoIngresoNeto=new JLabel("El ingreso neto de hoy es: "+(ingresoNeto1-ingresoNeto2)+"k");		
		JLabel textoNumCompras=new JLabel("El numero de productos comprados hoy es: "+contp);		
		JLabel textoNumVentas=new JLabel("El numero de productos vendidos hoy es: "+contv);
		JLabel textoEscape=new JLabel("Pulsa Esc para salir de la ventana");
		
		n.add(textoEscape);
		n.add(textoDineroActual);
		n.add(textoExperienciaActual);
		c.add(textoIngresoNeto);
		c.add(textoNumCompras);
		c.add(textoNumVentas);
		
		v.setVisible(true);
		
		v.addKeyListener(new KeyAdapter() {//Evento cerrar la ventana para saltar a la siguiente

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
					v.dispose();					
				}
			}

		});
	}
	
	public JFreeChart createDayAreaBegFin() {
		
		Double[][] data = new Double[2][jugador.getDia()-1];
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
		for (int i = 0; i < iniciales.length; i++) {
			data[0][i]=iniciales[i];
		}
		for (int i = 0; i < finales.length; i++) {
			data[1][i]=finales[i];
		}

        CategoryDataset dataset = DatasetUtilities.createCategoryDataset("Kromer","Dia ", data);
        return ChartFactory.createStackedAreaChart("Dinero invertido/Producido","Kromer", "Dia",dataset,PlotOrientation.VERTICAL,true,true,true);
    }
	
	public JFreeChart createLineaCrecimiento() {
		
		
		XYSeriesCollection dataset = new XYSeriesCollection();
	    XYSeries series1 = new XYSeries("Crecimiento Económico");
	
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
			series1.add(jugador.getDia(), dinero);
		}
		

	    dataset.addSeries(series1);

        return ChartFactory.createXYLineChart("Crecimiento de dinero/dia", "Dia", "Kromer", dataset, PlotOrientation.VERTICAL, true, true, true);
        		
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
	
public JFreeChart createSBTipoMueble() {
		
	double[][] data = new double[jugador.getDia()-1][7];

	for (int i = 1; i < jugador.getDia(); i++) {
		double cod1=0.0;	//SOFA
		double cod2=0.0;	//ARMARIO
		double cod3=0.0;	//SILLA
		double cod4=0.0;	//LAMPARA
		double cod5=0.0;	//CAMA
		double cod6=0.0;	//MESA
		double cod7=0.0;	//PUERTA
		double cod8=0.0;	//ESTANTERIA
		for (Venta v : todoVentas) {
			if (v.getDiaVenta()==i) {
				if (v.getTipoMueble().toString()== "SOFA") {
					cod1++;
				
				} else if (v.getTipoMueble().toString()== "ARMARIO") {
					cod2++;
				} else if (v.getTipoMueble().toString()== "SILLA") {
					cod3++;
				} else if (v.getTipoMueble().toString()== "LAMPARA") {
					cod4++;
				} else if (v.getTipoMueble().toString()== "CAMA") {
					cod5++;
				} else if (v.getTipoMueble().toString()== "MESA") {
					cod6++;
				} else if (v.getTipoMueble().toString()== "PUERTA") {
					cod7++;
				} else if (v.getTipoMueble().toString()== "ESTANTERIA") {
					cod7++;
				} else {}	
			}
	
		}
		data[i-1][0]=cod1;
		data[i-1][1]=cod2;
		data[i-1][2]=cod3;
		data[i-1][3]=cod4;
		data[i-1][4]=cod5;
		data[i-1][5]=cod6;
		data[i-1][6]=cod7;
		data[i-1][7]=cod8;	
	}    
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset("Dia ","Tipo de Mueble  ", data);
        return ChartFactory.createStackedBarChart("Demanda por tipo de mueble", "", "Cantidad",dataset, PlotOrientation.HORIZONTAL, true, true, true);
        		
	}

public JFreeChart createSBTematica() {
	
	double[][] data = new double[jugador.getDia()-1][10];

	for (int i = 1; i < jugador.getDia(); i++) {
		double cod1=0.0;	//PRIMAVERA
		double cod2=0.0;	//VERANO
		double cod3=0.0;	//OTOÑO
		double cod4=0.0;	//INVIERNO
		double cod5=0.0;	//HALLOWEEN
		double cod6=0.0;	//NAVIDAD
		double cod7=0.0;	//ANYO_NUEVO_CHINO
		double cod8=0.0;	//ST_PATRICKS_DAY
		double cod9=0.0;	//RUSTICO
		double cod10=0.0;	//MEDIEVAL
		double cod11=0.0;	//INDUSTRIAL
		for (Venta v : todoVentas) {
			if (v.getDiaVenta()==i) {
				if (v.getTematica().toString()== "PRIMAVERA") {
					cod1++;
				} else if (v.getTematica().toString()== "VERANO") {
					cod2++;
				} else if (v.getTematica().toString()== "OTOÑO") {
					cod3++;
				} else if (v.getTematica().toString()== "INVIERNO") {
					cod4++;
				} else if (v.getTematica().toString()== "HALLOWEEN") {
					cod5++;
				} else if (v.getTematica().toString()== "NAVIDAD") {
					cod6++;
				} else if (v.getTematica().toString()== "ANYO_NUEVO_CHINO") {
					cod7++;
				} else if (v.getTematica().toString()== "ST_PATRICKS_DAY") {
					cod8++;
				} else if (v.getTematica().toString()== "RUSTICO") {
					cod9++;
				} else if (v.getTematica().toString()== "MEDIEVAL") {
					cod10++;
				} else if (v.getTematica().toString()== "INDUSTRIAL") {
					cod11++;
				}
				else {}	
			}
		}
		data[i-1][0]=cod1;
		data[i-1][1]=cod2;
		data[i-1][2]=cod3;
		data[i-1][3]=cod4;
		data[i-1][4]=cod5;
		data[i-1][5]=cod6;
		data[i-1][6]=cod7;
		data[i-1][7]=cod8;
		data[i-1][8]=cod9;
		data[i-1][9]=cod10;
		data[i-1][10]=cod11;
	}    
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset("Dia ","Tematica  ", data);
        return ChartFactory.createStackedBarChart("Demanda por tematica", "", "Cantidad",dataset, PlotOrientation.HORIZONTAL, true, true, true);
        		
	}

public JFreeChart createSBColor() {
	
	double[][] data = new double[jugador.getDia()-1][13];

	for (int i = 1; i < jugador.getDia(); i++) {
		double cod1=0.0;	//NEGRO
		double cod2=0.0;	//ROJO
		double cod3=0.0;	//NARANJA
		double cod4=0.0;	//GRIS
		double cod5=0.0;	//AZUL
		double cod6=0.0;	//AMARILLO
		double cod7=0.0;	//VERDE
		double cod8=0.0;	//ROSA
		double cod9=0.0;	//BLANCO
		double cod10=0.0;	//CYAN
		double cod11=0.0;	//MAGENTA
		double cod12=0.0;	//MARRON
		double cod13=0.0;	//GRANATE
		double cod14=0.0;	//MORADO
		for (Venta v : todoVentas) {
			if (v.getDiaVenta()==i) {
				if (v.getColor().toString()== "NEGRO") {
					cod1++;	
				} else if (v.getColor().toString()== "ROJO") {
					cod2++;
				} else if (v.getColor().toString()== "NARANJA") {
					cod3++;
				} else if (v.getColor().toString()== "GRIS") {
					cod4++;
				} else if (v.getColor().toString()== "AZUL") {
					cod5++;
				} else if (v.getColor().toString()== "AMARILLO") {
					cod6++;
				} else if (v.getColor().toString()== "VERDE") {
					cod7++;
				} else if (v.getColor().toString()== "ROSA") {
					cod8++;
				} else if (v.getColor().toString()== "BLANCO") {
					cod9++;
				} else if (v.getColor().toString()== "CYAN") {
					cod10++;
				} else if (v.getColor().toString()== "MAGENTA") {
					cod11++;
				} else if (v.getColor().toString()== "MARRON") {
					cod12++;
				} else if (v.getColor().toString()== "GRANATE") {
					cod13++;
				} else if (v.getColor().toString()== "MORADO") {
					cod14++;
				}
				
				else {}	
			}
		}
		data[i-1][0]=cod1;
		data[i-1][1]=cod2;
		data[i-1][2]=cod3;
		data[i-1][3]=cod4;
		data[i-1][4]=cod5;
		data[i-1][5]=cod6;
		data[i-1][6]=cod7;
		data[i-1][7]=cod8;
		data[i-1][8]=cod9;
		data[i-1][9]=cod10;
		data[i-1][10]=cod11;
		data[i-1][11]=cod12;
		data[i-1][12]=cod13;
		data[i-1][13]=cod14;
	}    
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset("Dia ","Color  ", data);
        return ChartFactory.createStackedBarChart("Demanda por color", "", "Cantidad",dataset, PlotOrientation.HORIZONTAL, true, true, true);
        		
	}

public JFreeChart createSBMaterial() {
	double[][] data = new double[jugador.getDia()-1][7];
	for (int i = 1; i < jugador.getDia(); i++) {		
		double cod1=0.0;	//M_ABEDUL
		double cod2=0.0;	//M_PINO
		double cod3=0.0;	//M_ROBLE
		double cod4=0.0;	//ACERO
		double cod5=0.0;	//PLASTICO
		double cod6=0.0;	//ALUMINIO
		double cod7=0.0;	//MARMOL
		double cod8=0.0;	//GRANITO
		for (Venta v : todoVentas) {
			if (v.getDiaVenta()==i) {
				if (v.getMaterial().toString()== "M_ABEDUL") {
					cod1++;	
				} else if (v.getMaterial().toString()== "M_PINO") {
					cod2++;
				} else if (v.getMaterial().toString()== "M_ROBLE") {
					cod3++;
				} else if (v.getMaterial().toString()== "ACERO") {
					cod4++;
				} else if (v.getMaterial().toString()== "PLASTICO") {
					cod5++;
				} else if (v.getMaterial().toString()== "ALUMINIO") {
					cod6++;
				} else if (v.getMaterial().toString()== "MARMOL") {
					cod7++;
				} else if (v.getMaterial().toString()== "GRANITO") {
					cod8++;
				}
				else {}	
			}
		}
		data[i-1][0]=cod1;
		data[i-1][1]=cod2;
		data[i-1][2]=cod3;
		data[i-1][3]=cod4;
		data[i-1][4]=cod5;
		data[i-1][5]=cod6;
		data[i-1][6]=cod7;
		data[i-1][7]=cod8;
	}    
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset("Dia ","Material  ", data);
        return ChartFactory.createStackedBarChart("Demanda por material", "", "Cantidad",dataset, PlotOrientation.HORIZONTAL, true, true, true);
        		
	}
	public void finDelDia() throws SQLException, Exception {
		
		jugador.setDia(jugador.getDia()+1);
		bd.guardarDatos();
		bd.reiniciarBD();	
		bd.inicializarBD(jugador);
		
		
		
	
}
}
