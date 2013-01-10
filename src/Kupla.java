
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


/**
 * Luokka pelin t�rkeimmille elementeille, kuplille. Luokassa m��ritell��n
 * kuplille ominaisuuksia, kuten koko, v�ri, sijainti, tieto siit� onko kupla 
 * ehj� ja onko se aktiivinen. Lis�ksi m��ritell��n metodeja.
 * @author 345480
 *
 */
public class Kupla {
	private final double sade = 22.5;
	private Vari vari;
	private boolean ehja;
	public static final Random rand = new Random();
	private double x;
	private double y;
	private ArrayList<Kupla> ryhma;
	private ArrayList<Kupla> naapurit;
	private Maailma maailma;
	private static final Image punainen = 
			Toolkit.getDefaultToolkit().createImage("media/punainenkupla.png");
	private static final Image sininen =
			Toolkit.getDefaultToolkit().createImage("media/sininenkupla.png");
	private static final Image vihrea =
			Toolkit.getDefaultToolkit().createImage("media/vihreakupla.png");
	private static final Image liila =
			Toolkit.getDefaultToolkit().createImage("media/liilakupla.png");
	private static final Image keltainen =
			Toolkit.getDefaultToolkit().createImage("media/keltainenkupla.png");

	/**
	 * Kuplan konstruktori. Kaikki kuplat ovat samankokokisia, joten s�de
	 * alustetaan. Kuplalle annetaan my�s v�ri ja se saa tiedon siit�, ett�
	 * se on ehj�. Jokainen kupla on luotaessa aktiivinen, sill� se on t�ll�in
	 * seuraavaksi ammuttavana.
	 * @param vari
	 */
	public Kupla(double x, double y, Maailma maailma){
		this.ehja = true;
		this.ryhma = new ArrayList<Kupla>();
		this.maailma = maailma;
		this.x = x;
		this.y = y;

		Vari[] varit = Vari.values();
		int i = rand.nextInt(varit.length);
		this.vari = varit[i];

	}

	/**
	 * Metodi kertoo kuplan v�rin.
	 * @return kuplan v�ri
	 */
	public Vari annaVari(){
		return this.vari;
	}

	/**
	 * Palauttaa kuplan nykyisen x-koordinaatin.
	 * @return x-koord
	 */
	public double annaX(){
		return this.x;
	}

	/**
	 * Palauttaa kuplan nykyisen y-koordinaatin.
	 * @return y-koord
	 */
	public double annaY(){
		return this.y;
	}

	/**
	 * Kertoo, onko kupla ehj�.
	 * @return true, jos kupla on ehj�, false, jos se on mennyt rikki.
	 */
	public boolean onEhja(){
		if (this.ehja){
			return true;
		} return false;
	}

	/**
	 * Palauttaa kuplan s�teen.
	 * @return sade
	 */
	public double annaSade(){
		return this.sade;
	}

	public void poksahda(){
		this.ehja = false;
		this.vari = null;
		//Pit�� my�s asettaa sijainti nulliksi tms.
	}

	public Image annaKuva(){
		switch (this.vari){
		case PUNAINEN:
			return punainen;
		case SININEN:
			return sininen;
		case VIHREA:
			return vihrea;
		case LIILA:
			return liila;
		case KELTAINEN:
			return keltainen;
		}
		return null;
	}

	protected void asetaSijainti(double x, double y){
		this.x = x;
		this.y = y;
	}

	public ArrayList<Kupla> annaRyhma(){
		return this.ryhma;
	}
	
	public String toString(){
		return "Kuplan v�ri on " + this.annaVari();
	}

	public ArrayList<Kupla> annaNaapurit(){
		this.naapurit = new ArrayList<Kupla>();
		
		if (!(this.maailma == null)){
			for(int i = 0; i < this.maailma.annaKuplat().size(); i++){
				Kupla mahdollinenNaapuri = this.maailma.annaKuplat().get(i);

				double etaisyys = Math.sqrt(Math.pow(((mahdollinenNaapuri.annaX() + 
						mahdollinenNaapuri.annaSade()) - (this.annaX() + 
								this.annaSade())), 2) + 
								Math.pow(((mahdollinenNaapuri.annaY() + 
										mahdollinenNaapuri.annaSade()) -
										(this.annaY() + this.annaSade())), 2));

				if (etaisyys <= 48){
					this.naapurit.add(mahdollinenNaapuri);
				}
			}

		}
		return this.naapurit;
	}

}
