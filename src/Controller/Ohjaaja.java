package Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Model.Maailma;
import View.Pelimaailma;

/**
 * T‰m‰ luokka ohjaa pelin toimintaa.
 * @author 345480
 *
 */
public class Ohjaaja implements ActionListener{

	/**
	 * Edellinenhetki, joka kertoo aikaisemman ajanhetken 
	 * (t‰t‰ tarvitaan timeria ja kuplien sulavaa liikkumista varten). */
	private long edellinenhetki;

	/** Timer, jonka avulla maailmaa piirret‰‰n uudestaan. */
	private Timer timer;

	/** Maailma */
	private Maailma maailma;

	/** Pelimaailma */
	private Pelimaailma pelimaailma;

	/**
	 * Ohjaajan konstruktori. Parametreina annetaan maailma ja pelimaailma.
	 * K‰ynnistet‰‰n ajastin.
	 * @param maailma
	 * @param pelimaailma
	 */
	public Ohjaaja(Maailma maailma, Pelimaailma pelimaailma){
		this.timer = new Timer(5, this);
		this.maailma = maailma;
		this.pelimaailma = pelimaailma;
		this.timer.start();
	}

	/**
	 * Tapahtumankuuntelijametodi, jolla reagoidaan ajastimen toimintaan.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (this.edellinenhetki == 0){
			this.edellinenhetki = System.currentTimeMillis();
			return;
		}

		/*
		 * Lasketaan nykyisen hetken ja edellisen erotus.
		 * Ajanhetkien erotusta tarvitaan kuplien sulavaan liikuttamiseen
		 * (n‰in kuplan liikkuminen ei ole riippuvaista koneen suoritustehoista,
		 * vaan se liikkuu tasaisesti huolimatta jumituksista). */

		long muutos = System.currentTimeMillis() - this.edellinenhetki;
		this.edellinenhetki = System.currentTimeMillis();

		if (!this.maailma.peliOhi()){

			/* Ensin tutkitaan, onko pudotettavia yksin‰isi‰ kuplia. */
			this.maailma.pudota(muutos);

			/*
			 * Jos nykyist‰ ammuttavaa kuplaa ei viel‰ ole ammuttu, piirret‰‰n
			 * uudestaan eik‰ tehd‰ muuta.
			 */
			if (!this.maailma.annaNykyinen().onAmmuttu()){
				this.pelimaailma.repaint();
				return;
			}

			/*
			 * Jos kupla on ammuttu, sit‰ liikutetaan parametrina aiemmin 
			 * laskettu ajanhetkien erotus ja piirret‰‰n uudestaan.
			 */

			this.maailma.liikutaNykyista(muutos);

			this.pelimaailma.repaint();
		}
	}
}