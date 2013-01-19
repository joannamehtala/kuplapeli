package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Model.Maailma;
import View.Pelimaailma;

/**
 * Hiirikuuntelija pelimaailmaa varten. T�m� hiirikuuntelija on pelin
 * hiirikuuntelijoista t�rkein, sill� se tunnistaa k�ytt�j�n hiiren sijainnit
 * ja antaa kulman, jossa kupla ammutaan k�ytt�j�n klikkaamaan suuntaan.
 * @author 345480
 *
 */
public class Hiirikuuntelija_Pelimaailma extends MouseAdapter {

	/** Pelimaailma, johon hiirikuuntelija asetetaan. */
	private Pelimaailma pelimaailma;
	
	/** Maailma, pelimaailman looginen vastine. */
	private Maailma maailma;

	/** Kulma, jossa kupla ammutaan ja jonka mukaan suuntaviiva liikkuu. */
	private double kulma;

	/**
	 * Hiirikuuntelijan konstruktori, jossa alustetaan attribuutit.
	 * @param pelimaailma
	 * @param maailma
	 */
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

	/**
	 * Palauttaa mouseMoved-metodissa lasketun kulman.
	 * @return kulma
	 */
	public double annaKulma(){
		return this.kulma;
	}
}
