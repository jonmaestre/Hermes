package Sprites;

import java.awt.Rectangle;

public class Tile {

	private final Sprite sprite;
	private final int id;
	private boolean solido;
	
	
	
	public Tile(final Sprite sprite, int id) {
		super();
		this.sprite = sprite;
		this.id = id;
		solido = false;
		
		
	}

	public Tile(Sprite sprite, int id, boolean solido) {
		super();
		this.sprite = sprite;
		this.id = id;
		this.solido = solido;
	}
	
	public Sprite obtenerSprite() {
		return sprite;
		
	}
	public int obtenerId() {
		return id;
	
	}
	public void establecersolido(final boolean solido) {
		this.solido = solido;
	}
	public Rectangle obtenerLimites(final int x, final int y) {
		return new Rectangle(x,y, sprite.obtenerAncho(), sprite.obtenerAlto());
	}
	
}
