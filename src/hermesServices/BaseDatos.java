	package hermesServices;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.jdi.connect.spi.Connection;

import datos.Hermes.Almacen;
import datos.Hermes.Producto;
import datos.Hermes.Tiendas;




public class BaseDatos {
	private static Connection conn;
	private static Logger logger = Logger.getLogger( "BaseDatos" );
	
	//nombre sirve para buscar el nombre de la base de datos deseada y reiniciar si lo pones a true borra lo que haya dentro
	//de la base de datos. 
	public static boolean abrirConexion( String nombre, boolean reiniciar ) {
		try {
			logger.log( Level.INFO, "Carga de librerÃ­a org.sqlite.JDBC" );
			Class.forName("org.sqlite.JDBC");  // Carga la clase de BD para sqlite
			logger.log( Level.INFO, "Abriendo conexiÃ³n con " + nombre );
			conn = (Connection) DriverManager.getConnection("jdbc:sqlite:" + nombre );
			if (reiniciar) {
				Statement statement =  ((java.sql.Connection) conn).createStatement();
				String sent = "DROP TABLE IF EXISTS tienda";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "CREATE TABLE compra (codTienda INTEGER PRIMARY KEY AUTOINCREMENT, estilo varchar (50), nombreTienda varchar(50));";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "DROP TABLE IF EXISTS almacen";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "CREATE TABLE almacen (codAlmacen INTEGER PRIMARY KEY AUTOINCREMENT, nombre varchar (50), stock int, capacidad int);";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "DROP TABLE IF EXISTS producto";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "CREATE TABLE producto (codigoObjeto INTEGER PRIMARY KEY AUTOINCREMENT, tipoMueble varchar(50), tematica varchar(50), color varchar(50), material varchar(50), precioCompra dec, precioVenta dec, codTienda int, codAlmacen int ;";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				try {
					//El tiendas.txt no esta creado pero lo dejo ahí para saber como funcionaría- En los siguientes días lo crearé.
					Scanner scanner = new Scanner( BaseDatos.class.getResourceAsStream("tiendas.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						sent = "insert into producto (codTienda, estilo, nombreTienda) values (" + datos[0] + ",'" + datos[1] + "', '" + datos[2] + "');";
						logger.log( Level.INFO, "Statement: " + sent );
						statement.executeUpdate( sent );
					}
					scanner.close();
					//El almacen.txt no esta creado pero lo dejo ahí para saber como funcionaría- En los siguientes días lo crearé.
					scanner = new Scanner( BaseDatos.class.getResourceAsStream("almacen.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						sent = "insert into compra (codAlmacen, nombre, stock, capacidad) values (" + datos[0] + ",'" + datos[1] + "'," + datos[2] + "," + datos[3] + "," + datos[4] + ");";
						logger.log( Level.INFO, "Statement: " + sent );
						statement.executeUpdate( sent );
					}
					scanner.close();
					//El productos.txt no esta creado pero lo dejo ahí para saber como funcionaría- En los siguientes días lo crearé.
					scanner = new Scanner( BaseDatos.class.getResourceAsStream("productos.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						sent = "insert into producto (codigoObjeto, tipoMueble, tematica, color, material, precioVenta, precioCompra, codTienda, codAlmacen) values ("
						+ datos[0] + ",'" + datos[1] + "','" + datos[2] + "','" + datos[3] + "','" + datos[4] + "'," + datos[5] + "," + datos[6] + "," + datos[7] + "," + datos[8] +" );";
						logger.log( Level.INFO, "Statement: " + sent );
						statement.executeUpdate( sent );
					}
					scanner.close();
				} catch(Exception e) {
					logger.log( Level.SEVERE, "ExcepciÃ³n", e );
				}
			}
			return true;
		} catch(Exception e) {
			logger.log( Level.SEVERE, "ExcepciÃ³n", e );
			return false;
		}
	}	
	
	public static void cerrarConexion() throws IOException, SQLException {
		logger.log( Level.INFO, "Cerrando conexiÃ³n" );
		conn.close();
	}
	
	//Al subir de nivel nuevas tiendas van apareciendo y se insertan a la base de datos. Aquí se presenta la inserción de éstas.
	public static boolean insertarTienda( Tiendas tienda ) {
		try (Statement statement = ((java.sql.Connection) conn).createStatement()) {
			String sent = "insert into tienda (codTienda, estilo, nombreTienda) values (" + tienda.getCodTienda() + ",'" + tienda.getEstilo() + "'," + tienda.getNombreTienda() + "');";
			logger.log( Level.INFO, "Statement: " + sent );
			int insertado = statement.executeUpdate( sent );
			if (insertado!=1) {
				return false;  
			};
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "ExcepciÃ³n", e );
			return false;
		}
	}
	//A medida que avanzas de niveles en las nuevas tiendas y en las antiguas puedes encontrar nuevos productos.
	//Con este método logras agregar estos nuevos productos a la BD.
	public static boolean insertarProducto( Producto producto ) {
		try (Statement statement = ((java.sql.Connection) conn).createStatement()) {
			String sent = "insert into producto (codigoObjeto, tipoMueble, tematica, color, material, precioVenta, precioCompra, codTienda, codAlmacen) values (" 
				+ producto.getCodigoObjeto() + ",'" + producto.getTipoMueble() + "'," + producto.getTematica() + ",'" 
				+ producto.getColor() + ",'" + producto.getMaterial() + "," + producto.getPrecioVenta() + "," + producto.getPrecioCompra() + "," 
				+ producto.getTienda().getCodTienda() + "," + producto.getAlmacen().getCodAlm() +");";
			logger.log( Level.INFO, "Statement: " + sent );
			int insertado = statement.executeUpdate( sent );
			if (insertado!=1) {
				return false;  
			};
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "ExcepciÃ³n", e );
			return false;
		}
	}
		//Se actualiza el stock de un producto del almacén cuando compramos o vendemos ese objeto.
		public static void actualizarAlmacen( Almacen almacen ) {
			try (Statement statement = ((java.sql.Connection) conn).createStatement()) {
				String sent = "update almacen set stock = " + almacen.getStock() +  ";";
				logger.log( Level.INFO, "Statement: " + sent );
			} catch (Exception e) {
				logger.log( Level.SEVERE, "ExcepciÃ³n", e );
			}
		
	}
}
