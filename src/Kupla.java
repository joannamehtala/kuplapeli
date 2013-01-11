
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
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
	private ArrayList<Kupla> samanvariset;
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
		this.samanvariset = new ArrayList<Kupla>();
		this.maailma = maailma;
		this.x = x;
		this.y = y;

		Vari[] varit = Vari.values();
		int a = rand.nextInt(varit.length);
		this.vari = varit[a];

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
		System.out.println("Seuraavan kupla poksahti: " + this);
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

	public String toString(){
		return "Kuplan v�ri on " + this.annaVari() + ", kuplan sijainti on: "
				+ this.annaX() + ", " + this.annaY();
	}

	public ArrayList<Kupla> annaNaapurit(){
		this.naapurit = new ArrayList<Kupla>();
		if (!(this.maailma == null)){
			System.out.println("menee t�nne");
			for (int i = 0; i < this.maailma.annaKuplat().size(); i++){
				Kupla tutkittava = this.maailma.annaKuplat().get(i);

				double sade = this.annaSade();

				if (Point2D.distance(this.annaKeskiX(), this.annaKeskiY(), 
						tutkittava.annaKeskiX(), tutkittava.annaKeskiY()) 
						<= 2 * sade + 6) {
					this.naapurit.add(tutkittava);
					System.out.println(tutkittava);
				}
				System.out.println(this.naapurit.size());
			}
		}
		return this.naapurit;
	}
	
	public void tarkistaSamanvariset(){
		Kupla tarkasteltava;
		for (int i = 0; i < this.naapurit.size(); i++){
			tarkasteltava = this.naapurit.get(i);
			if (tarkasteltava.annaVari() == this.annaVari() 
					&& tarkasteltava.onEhja()){
				this.samanvariset.add(tarkasteltava);
				System.out.println("Samanv�risi�: " + tarkasteltava);
			}
		}
		
		if (this.samanvariset.size() >= 3){
			for (int i = 0; i < this.samanvariset.size(); i++){
				this.samanvariset.get(i).poksahda();
			}
		}
	}
	
	public ArrayList<Kupla> annaSamanvariset(){
		return this.samanvariset;
	}
}
