package Sprites;

import static org.junit.Assert.assertArrayEquals;

import java.awt.image.BufferedImage;

import Herramientas.CargadorRecursos;

public class HojaSprite {
	
	private final int anchoHojaPixeles;
	private final int altoHojaPixeles;
	
	private final int anchoHojaSprites;
	private final int altoHojaSprites;
	
	private final int anchoSprites;
	private final int altoSprites;

	private final Sprite[] sprites;

	public HojaSprite(final String ruta, final int tamanoSprite, final boolean hojaOpaca) {
		final BufferedImage imagen;
		if(hojaOpaca) {
			imagen = CargadorRecursos.cargarImagenTranslucida(ruta);
		}else {
			imagen = CargadorRecursos.cargarImagenTranslucida(ruta);
		}
		
		anchoHojaPixeles = imagen.getWidth();
		altoHojaPixeles = imagen.getHeight();
		
		anchoHojaSprites = anchoHojaPixeles / tamanoSprite;
		altoHojaSprites = altoHojaPixeles / tamanoSprite;
		
		anchoSprites = tamanoSprite;
		altoSprites = tamanoSprite;
		sprites = new Sprite[anchoHojaSprites * altoHojaSprites];
		
		rellenarSpriteDesdeImagen(imagen);
				
	}
	
	
	private void rellenarSpriteDesdeImagen(BufferedImage imagen) {
		
		for(int y = 0; y <altoHojaSprites;y++) {
			for(int x = 0; x <anchoHojaSprites; x++) {
				final int posicionX = x*anchoSprites;
				final int posicionY = y*anchoSprites;
				
				sprites [x+y*anchoHojaSprites] = new Sprite(imagen.getSubimage(posicionX, posicionY, anchoHojaSprites , altoHojaSprites));
				
			}
		}
		
	}
	public Sprite obtenerSprite(final int indice) {
		return sprites[indice]; 
	}
	public Sprite obtenerSprite(final int x, final int y) {
		return sprites[x+y*anchoHojaSprites];
	}
	
	
	
}
