import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.awt.event.KeyListener;

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
	private AktiivinenKupla aktiivinenkupla;
	//private boolean klikattu;

	/**
	 * Ohjaajan konstruktori. Parametreina annetaan pelimaailma ja aktiivinen-
	 * kupla. Konstruktorissa k‰ynnistet‰‰n ajastin.
	 * @param pelimaailma
	 * @param aktiivinenkupla
	 */
	public Ohjaaja(Pelimaailma pelimaailma, AktiivinenKupla aktiivinenkupla){
		this.aktiivinenkupla = aktiivinenkupla;
		this.timer = new Timer(10, this);
		this.pelimaailma = pelimaailma;
		this.timer.start();
	}

	/**
	 * Tapahtumankuuntelijametodi, jolla reagoidaan ajastimen toimintaan. 
	 * Lasketaan nykyisen hetken ja edellisen erotus.
	 * Ajanhetkien erotusta tarvitaan kuplien sulavaan liikuttamiseen
	 * (n‰in kuplan liikkuminen ei ole riippuvaista koneen suoritustehoista,
	 * vaan se liikkuu tasaisesti huolimatta jumituksista). T‰m‰n j‰lkeen
	 * liikutetaan kuplaa parametrilla muutos ja piirret‰‰n uusi n‰kym‰
	 * k‰ytt‰j‰lle.
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

		if (Pelimaailma.onKlikattu()){
			this.aktiivinenkupla.liiku(muutos);
			this.pelimaailma.repaint();
		}
	}

	/*
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT){
			//vasenta nuolin‰pp‰int‰ painettaessa
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT){
			//oikeata nuolin‰pp‰int‰ painettaessa
		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE){
			//spacebaria painettaessa
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}*/
}
