package hermesServices;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.jdi.connect.spi.Connection;

import datos.Hermes.*;

public class BDynamic {
	
	private Connection conn;
	private Logger logger = Logger.getLogger( "BaseDatos" );
	
	//nombre sirve para buscar el nombre de la base de datos deseada y reiniciar si lo pones a true borra lo que haya dentro
	//de la base de datos. 
	public void abrirBD()  throws IOException, SQLException {
		try {
			logger.log( Level.INFO, "Carga de libreria org.sqlite.JDBC" );
			Class.forName("org.sqlite.JDBC");  // Carga la clase de BD para sqlite
			logger.log( Level.INFO, "Abriendo conexion con basedatosdyn.bd");
			conn = (Connection) DriverManager.getConnection("jdbc:sqlite:basedatosdyn.bd");
		} catch(Exception e) {
			logger.log( Level.SEVERE, "Excepcion al abrir conexion", e );
		}
	}	
	public void cerrarConexion() throws IOException, SQLException {
		try {
			logger.log( Level.INFO, "Cerrando conexion" );
			conn.close();
		}catch(Exception e) {
			logger.log( Level.SEVERE, "Excepcion al cerrar conexion", e );
		}
	}
	
	public void reiniciarBD()  throws IOException, SQLException {
		try {
		Statement statement =  ((java.sql.Connection) conn).createStatement();
		String sent = "DROP TABLE IF EXISTS usuario";
		logger.log( Level.INFO, "Statement: " + sent );
		statement.executeUpdate( sent );
		sent = "CREATE TABLE usuario (cod_u INT(3) NOT NULL, nombre_u varchar (20), dia INT(2), exp INT(9), kromer INT(9), PRIMARY KEY(cod_u));";
		logger.log( Level.INFO, "Statement: " + sent );
		statement.executeUpdate( sent );
		
		sent = "DROP TABLE IF EXISTS producto";
		logger.log( Level.INFO, "Statement: " + sent );
		statement.executeUpdate( sent );
		sent = "CREATE TABLE producto (codigoProducto INT(3) NOT NULL, tipoMueble varchar(20), tematica varchar(30), color varchar(30), material varchar(30), precioVenta dec, precioCompra dec, diaCompra int(2), codTienda int(3),cod_u INT(3) NOT NULL, PRIMARY KEY(codigoProducto),FOREIGN KEY(cod_u) REFERENCES usuario(cod_u));";
		logger.log( Level.INFO, "Statement: " + sent );
		statement.executeUpdate( sent );
		
		
		sent = "DROP TABLE IF EXISTS ventas";
		logger.log( Level.INFO, "Statement: " + sent );
		statement.executeUpdate( sent );
		sent = "CREATE TABLE ventas (codigoObjeto INTEGER PRIMARY KEY AUTOINCREMENT, tipoMueble varchar(50), tematica varchar(50), color varchar(50), material varchar(50), precioCompra dec, precioVenta dec, codTienda int, codAlmacen int ;";
		logger.log( Level.INFO, "Statement: " + sent );
		statement.executeUpdate( sent );
		
		
		//Venta(String codigoVenta, datos.Hermes.tipoMueble tipoMueble, datos.Hermes.tematica tematica,datos.Hermes.color color, datos.Hermes.material material, float precioVenta, float precioCompra,int diaCompra, int diaVenta, Tiendas tienda, int codU)
		
	
		} catch (Exception e) {
			logger.log(Level.SEVERE,"Excepcion en el reinicio de la BD",e);
		}
	}
	
	
	
	//Al subir de nivel nuevas tiendas van apareciendo y se insertan a la base de datos. Aquí se presenta la inserción de éstas.
	public boolean insertarTienda( Tiendas tienda ) {
		try (Statement statement = ((java.sql.Connection) conn).createStatement()) {
			String sent = "insert into tienda (codTienda, estilo, nombreTienda) values (" + tienda.getCodTienda() + ",'" + "'," + tienda.getNombreTienda() + "');";
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
	public boolean insertarProducto( Producto producto ) {
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
}
