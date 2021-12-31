package datos.Hermes;

import java.util.ArrayList;

public class Tiendas extends Datos {
	protected int codTienda;
	protected tematica estilo;
	protected String nombreTienda;
	
	public Tiendas(int codTienda, tematica estilo, String nombreTienda) {
		super();
		this.codTienda = codTienda;
		this.estilo = estilo;
		this.nombreTienda = nombreTienda;
		
	}

	public int getCodTienda() {
		return codTienda;
	}

	public void setCodTienda(int codTienda) {
		this.codTienda = codTienda;
	}

	public tematica getEstilo() {
		return estilo;
	}

	public void setEstilo(tematica estilo) {
		this.estilo = estilo;
	}

	public String getNombreTienda() {
		return nombreTienda;
	}

	public void setNombreTienda(String nombreTienda) {
		this.nombreTienda = nombreTienda;
	}	
}
