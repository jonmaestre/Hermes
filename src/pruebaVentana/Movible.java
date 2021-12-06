package pruebaVentana;

public interface Movible {
	/** Devuelve la coordenada X
	 * @return	posición horizontal (x)
	 */
	public int getX();
	/** Modifica la coordenada X
	 * @param x	nueva posición horizontal (x)
	 */
	public void setX(int x);
	/** Devuelve la coordenada Y
	 * @return	posición horizontal (y)
	 */
	public int getY();	
	/** Modifica la coordenada Y
	 * @param y	nueva posición vertical (y)
	 */
	public void setY(int y);
	/** Cambia la coordenada x,y
	 * @param x	Nueva posición horizontal (x)
	 * @param y	Nueva posición vertical (y)
	 */
	public void mover( int x, int y );
}