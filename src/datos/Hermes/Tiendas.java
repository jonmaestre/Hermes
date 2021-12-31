package datos.Hermes;

import java.util.ArrayList;

public class Tiendas extends Datos {
	protected int codTienda;
	protected tematica estilo;
	protected String nombreTienda;
	protected ArrayList<Producto> listaProds;
	
	public Tiendas(int codTienda, tematica estilo, String nombreTienda, ArrayList<Producto> listaProds) {
		super();
		this.codTienda = codTienda;
		this.estilo = estilo;
		this.nombreTienda = nombreTienda;
		this.listaProds = listaProds;
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

	public ArrayList<Producto> getListaProds() {
		return listaProds;
	}

	public void setListaProds(ArrayList<Producto> listaProds) {
		for(Producto i:listaProds) { //Para saber donde se venden los productos con el mismo estilo que esa tienda 
			if (estilo==i.getTematica()) {
				this.listaProds = listaProds;
			}
		}
		
	}
	
	
	
	
}
