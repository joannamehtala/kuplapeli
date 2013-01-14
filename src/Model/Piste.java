package Model;


/**
 * Java Point-luokka ottaa parametreina int-tyyppisen x:n ja y:n, mutta
 * tarvitsin käyttööni double-tyyppiset, joten tein oman luokan pisteitä varten.
 * @author 345480
 *
 */
public class Piste {
	
	/** x- ja y-koordinaatit. */
	private double x;
	private double y;
	
	/**
	 * Pisteen konstruktori, joka ottaa parametreinaan x:n ja y:n.
	 * Nämä alustetaan.
	 * @param x, pisteen x-koordinaatti.
	 * @param y, pisteen y-koordinaatti.
	 */
	public Piste(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Metodi palauttaa pisteen x-koordinaatin.
	 * @return
	 */
	public double annaX(){
		return this.x;
	}
	
	/**
	 * Metodi palauttaa pisteen y-koordinaatin.
	 * @return
	 */
	public double annaY(){
		return this.y;
	}
	
	/**
	 * Asettaa pisteelle sijainnin.
	 */
	public void asetaSijainti(double x, double y){
		this.x = x;
		this.y = y;
	}
}
