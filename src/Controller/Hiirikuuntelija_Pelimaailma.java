package Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Model.Maailma;
import View.Pelimaailma;

public class Hiirikuuntelija_Pelimaailma implements MouseListener,
MouseMotionListener {
	
	private Pelimaailma pelimaailma;
	private Maailma maailma;
	private double kulma;
	
	public Hiirikuuntelija_Pelimaailma(Pelimaailma pelimaailma, Maailma maailma){
		this.pelimaailma = pelimaailma;
		this.maailma = maailma;
	}
	
	/**
	 * Kun klikataan pelimaailmaa, ammutaan kupla.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.maailma.ammuNykyinen(this.kulma);
	}

	/**
	 * Metodi m��ritt��, mit� tapahtuu, kun hiirt� liikutetaan ruudulla.
	 * Seurataan hiiren koordinaatteja ja tallennetaan ne muuttujiin. Niiden
	 * avulla lasketaan kulma, jonka mukaisesti hiirt� seuraava suuntaviiva 
	 * liikkuu (ja jossa kupla l�htee liikkeelle).
	 */
	@Override
	public void mouseMoved(MouseEvent arg0) {
		int hiirix = arg0.getX();
		int hiiriy = arg0.getY();

		double radkulma = Math.atan2(hiiriy - Pelimaailma.LAHTO_Y, hiirix - 
				Pelimaailma.LAHTO_X );
		this.kulma = Math.toDegrees(radkulma);
		
		if (this.kulma > 0 && this.kulma < 90){
			this.kulma = -5;
		}
		
		if (this.kulma > 90 && this.kulma < 180){
			this.kulma = -175;
		}
		
		this.pelimaailma.repaint();
	}
	
	public double annaKulma(){
		return this.kulma;
	}

	/**
	 * MouseListenerin implementointi vaatii t�m�n metodin, vaikkei se teek��n
	 * mit��n.
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// tyhj� metodi

	}

	/**
	 * MouseListenerin implementointi vaatii t�m�n metodin, vaikkei se teek��n
	 * mit��n.
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// tyhj� metodi

	}

	/**
	 * MouseListenerin implementointi vaatii t�m�n metodin, vaikkei se teek��n
	 * mit��n.
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// tyhj� metodi

	}

	/**
	 * MouseListenerin implementointi vaatii t�m�n metodin, vaikkei se teek��n
	 * mit��n.
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// tyhj� metodi

	}

	/**
	 * MouseMotionListenerin implementointi vaatii t�m�n metodin, vaikkei se
	 * teek��n mit��n.
	 */
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// tyhj� metodi

	}
}
