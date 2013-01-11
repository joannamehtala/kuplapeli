
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


/**
 * Luokka pelin tärkeimmille elementeille, kuplille. Luokassa määritellään
 * kuplille ominaisuuksia, kuten koko, väri, sijainti, tieto siitä onko kupla 
 * ehjä ja onko se aktiivinen. Lisäksi määritellään metodeja.
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
	 * Kuplan konstruktori. Kaikki kuplat ovat samankokokisia, joten säde
	 * alustetaan. Kuplalle annetaan myös väri ja se saa tiedon siitä, että
	 * se on ehjä. Jokainen kupla on luotaessa aktiivinen, sillä se on tällöin
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
	 * Metodi kertoo kuplan värin.
	 * @return kuplan väri
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
	 * Palauttaa kuplan keskipisteen x-koordinaatin.
	 * @return double keskipisteen x-koordinaatti
	 */
	public double annaKeskiX(){
		return this.x + 22.5;
	}

	/**
	 * Palauttaa kuplan keskipisteen y-koordinaatin.
	 * @return double keskipisteen y-koordinaatti
	 */
	public double annaKeskiY(){
		return this.y + 22.5;
	}

	/**
	 * Kertoo, onko kupla ehjä.
	 * @return true, jos kupla on ehjä, false, jos se on mennyt rikki.
	 */
	public boolean onEhja(){
		if (this.ehja){
			return true;
		} return false;
	}

	/**
	 * Palauttaa kuplan säteen.
	 * @return sade
	 */
	public double annaSade(){
		return this.sade;
	}

	public void poksahda(){
		this.ehja = false;
		this.vari = null;
		//Pitää myös asettaa sijainti nulliksi tms.
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
		return "Kuplan väri on " + this.annaVari() + ", kuplan sijainti on: "
				+ this.annaX() + ", " + this.annaY();
	}

	public ArrayList<Kupla> annaNaapurit(){
		this.naapurit = new ArrayList<Kupla>();

		if (!(this.maailma == null)){
			System.out.println("menee tänne");
			for(int i = 0; i < this.maailma.annaKuplat().size(); i++){
				Kupla tutkittava = this.maailma.annaKuplat().get(i);

				double deltaX = tutkittava.annaKeskiX() - this.annaKeskiX();
				double deltaY = tutkittava.annaKeskiY() - this.annaKeskiY();

				double etaisyysNelio = (deltaX * deltaX) + (deltaY * deltaY);

				double sade = 2 * this.annaSade();

				if (etaisyysNelio <= (sade * sade + 10)){
					this.naapurit.add(tutkittava);
					System.out.println(tutkittava);
				}
				System.out.println(this.naapurit.size());
			}

		}
		return this.naapurit;
	}

}
