package hermesServices;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import datos.Hermes.Jugador;
import datos.Hermes.Producto;
import datos.Hermes.Tiendas;
import datos.Hermes.color;
import datos.Hermes.material;
import datos.Hermes.tematica;
import datos.Hermes.tipoMueble;

public class BDStatic {
	private Connection conn;
	
	public void abrirConexion() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:basedatossta.bd");
		
	}
	public void cerrarConexion() throws SQLException {
		conn.close();
	}
	
	public ArrayList<Producto> seleccionarProducto(Jugador usuario) throws SQLException{
		ArrayList<Producto> listaProds= new ArrayList<>();
		try(Statement stmnt=conn.createStatement()){
			ResultSet rs= stmnt.executeQuery("SELECT * FROM producto WHERE codU= " + usuario.getIdJugador() + ";");
			while(rs.next()) {
				Producto p=new Producto(
						rs.getString("codigoObjeto"),
						tipoMueble.valueOf(rs.getString("tipoMueble")),
						tematica.valueOf(rs.getString("tematica")),
						color.valueOf(rs.getString("color")),
						material.valueOf(rs.getString("material")),
						rs.getFloat("precioVenta"),
						rs.getFloat("precioCompra"),
						rs.getInt("diaCompra"),
						rs.getInt("tienda"),
						rs.getInt("codU")
						);
				listaProds.add(p);
			}
		}
		return listaProds;
	}
}
