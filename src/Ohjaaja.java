import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * T‰m‰ luokka toimii MVC-mallin mukaisesti controllerina, eli t‰m‰n luokan
 * avulla ohjataan pelin toimintaa.
 * @author 345480
 *
 */
public class Ohjaaja implements ActionListener{

	/**
	 * Asetetaan attribuuteiksi edellinenhetki, joka kertoo aikaisemman
	 * ajanhetken (t‰t‰ tarvitaan timeria ja kuplien sulavaa liikkumista
	 * varten), ajastin sek‰ attribuutit pelimaailmalle ja aktiiviselle
	 * kuplalle tiedonkulkua helpottamaan.
	 */
	private long edellinenhetki;
	private Timer timer;
	private Maailma maailma;
	private Pelimaailma pelimaailma;

	/**
	 * Ohjaajan konstruktori. Parametreina annetaan maailma, pelimaailma ja 
	 * aktiivinenkupla. Konstruktorissa k‰ynnistet‰‰n ajastin.
	 * @param maailma
	 * @param pelimaailma
	 * @param aktiivinenkupla
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

		/** 
		 * Lasketaan nykyisen hetken ja edellisen erotus.
		 * Ajanhetkien erotusta tarvitaan kuplien sulavaan liikuttamiseen
		 * (n‰in kuplan liikkuminen ei ole riippuvaista koneen suoritustehoista,
		 * vaan se liikkuu tasaisesti huolimatta jumituksista). */

		long muutos = System.currentTimeMillis() - this.edellinenhetki;
		this.edellinenhetki = System.currentTimeMillis();

		if (!this.maailma.peliOhi()){
			//System.out.println("peli ei ole ohi");
			/**
			 * Jos nykyist‰ ammuttavaa kuplaa ei viel‰ ole ammuttu, piirret‰‰n
			 * uudestaan eik‰ tehd‰ muuta.
			 */
			if (!this.maailma.annaNykyinen().onAmmuttu()){
				this.pelimaailma.repaint();
				return;
			}

			/**
			 * Jos kupla on ammuttu, sit‰ liikutetaan parametrina aiemmin laskettu
			 * ajanhetkien erotus ja piirret‰‰n uudestaan.
			 */
			this.maailma.liikutaNykyista(muutos);
			this.pelimaailma.repaint();
		}
	}
}