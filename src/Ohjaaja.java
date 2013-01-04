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
	private Pelimaailma pelimaailma;
	private AktiivinenKupla nykyinen;

	/**
	 * Ohjaajan konstruktori. Parametreina annetaan pelimaailma ja aktiivinen-
	 * kupla. Konstruktorissa k‰ynnistet‰‰n ajastin.
	 * @param pelimaailma
	 * @param aktiivinenkupla
	 */
	public Ohjaaja(Pelimaailma pelimaailma, AktiivinenKupla nykyinen){
		this.timer = new Timer(10, this);
		this.pelimaailma = pelimaailma;
		this.asetaNykyinen(nykyinen);
		this.timer.start();
	}
	
	public void asetaNykyinen(AktiivinenKupla uusi){
		this.nykyinen = uusi;
	}

	/**
	 * Tapahtumankuuntelijametodi, jolla reagoidaan ajastimen toimintaan. 
	 * Lasketaan nykyisen hetken ja edellisen erotus.
	 * Ajanhetkien erotusta tarvitaan kuplien sulavaan liikuttamiseen
	 * (n‰in kuplan liikkuminen ei ole riippuvaista koneen suoritustehoista,
	 * vaan se liikkuu tasaisesti huolimatta jumituksista). T‰m‰n j‰lkeen
	 * liikutetaan kuplaa parametrilla muutos ja piirret‰‰n uusi n‰kym‰
	 * k‰ytt‰j‰lle. Lis‰ksi asetetaan kuplan uudeksi liikkumiskulmaksi se
	 * kulma, johon on liiku-metodissa p‰‰dytty mahdollisten tˆrm‰ilyjen
	 * seurauksena.
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (this.edellinenhetki == 0){
			this.edellinenhetki = System.currentTimeMillis();
			return;
		}
		long muutos = System.currentTimeMillis() - this.edellinenhetki;
		this.edellinenhetki = System.currentTimeMillis();

		if (this.pelimaailma.onKlikattu()){
			this.nykyinen.liiku(muutos);
		}
		
		this.pelimaailma.repaint();
		if (this.nykyinen.annaY() < 409){
			this.pelimaailma.repaint();
		}
	}
}