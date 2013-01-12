
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;


/**
 * Luokka pelin t‰rkeimmille elementeille, kuplille. Luokassa m‰‰ritell‰‰n
 * kuplille ominaisuuksia, kuten koko, v‰ri, sijainti, tieto siit‰ onko kupla 
 * ehj‰ ja onko se aktiivinen. Lis‰ksi m‰‰ritell‰‰n metodeja.
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
	 * Kuplan konstruktori. Kaikki kuplat ovat samankokokisia, joten s‰de
	 * alustetaan. Kuplalle annetaan myˆs v‰ri ja se saa tiedon siit‰, ett‰
	 * se on ehj‰. Jokainen kupla on luotaessa aktiivinen, sill‰ se on t‰llˆin
	 * seuraavaksi ammuttavana.
	 * @param vari
	 */
	public Kupla(double x, double y, Maailma maailma){
		this.ehja = true;
		this.maailma = maailma;
		this.x = x;
		this.y = y;

		Vari[] varit = Vari.values();
		int a = rand.nextInt(varit.length);
		this.vari = varit[a];

	}

	/**
	 * Metodi kertoo kuplan v‰rin.
	 * @return kuplan v‰ri
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
	 * Kertoo, onko kupla ehj‰.
	 * @return true, jos kupla on ehj‰, false, jos se on mennyt rikki.
	 */
	public boolean onEhja(){
		if (this.ehja){
			return true;
		} return false;
	}

	/**
	 * Palauttaa kuplan s‰teen.
	 * @return sade
	 */
	public double annaSade(){
		return this.sade;
	}

	public void poksahda(){
		this.ehja = false;
		System.out.println("Seuraavan kupla poksahti: " + this);
		//Pit‰‰ myˆs asettaa sijainti nulliksi tms.
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
		return "Kuplan v‰ri on " + this.annaVari() + ", kuplan sijainti on: "
				+ this.annaX() + ", " + this.annaY();
	}

	private ArrayList<Kupla> annaNaapurit(){
		ArrayList<Kupla> naapurit = new ArrayList<Kupla>();
		if (!(this.maailma == null)){
			//System.out.println("menee t‰nne");
			for (int i = 0; i < this.maailma.annaKuplat().size(); i++){
				Kupla tutkittava = this.maailma.annaKuplat().get(i);

				double sade = this.annaSade();

				if (Point2D.distance(this.annaKeskiX(), this.annaKeskiY(), 
						tutkittava.annaKeskiX(), tutkittava.annaKeskiY()) 
						<= 2 * sade + 6 && tutkittava.onEhja()) {
					naapurit.add(tutkittava);
					//System.out.println(tutkittava);
				}
				//System.out.println(naapurit.size());
			}
		}
		return naapurit;
	}
	
	public boolean onSamanVarinen(Kupla toinen){
		if (this.annaVari() == toinen.annaVari()){
			return true;
		}
		return false;
	}

	public void tarkistaSamanvariset(){

		//Alustetaan tarkisteltavien kuplien lista kuplan v‰littˆm‰ss‰
		//l‰heisyydessa olevilla naapureilla.
		ArrayList<Kupla> samanvariset = new ArrayList<Kupla>();
		samanvariset.add(this);
		HashSet<Kupla> tarkastetut = new HashSet<Kupla>();
		tarkastetut.add(this);
		
		//K‰yd‰‰n l‰pi samanv‰riset kuplat, joita tulee lis‰‰ kun silmukkaa
		//k‰yd‰‰n l‰pi
		for (int i = 0; i < samanvariset.size(); i++){
			Kupla t = samanvariset.get(i);
			ArrayList<Kupla> tempNaapurit = t.annaNaapurit();
			//K‰yd‰‰n l‰pi ‰sken listatut kuplan t naapurit yksi kerrallaan,
			//ja jokaisen kohdalla tarkistetaan, onko sit‰ tutkittu aiemmin
			//ja jos ei, se lis‰t‰‰n tutkittujen listalle ja tarkistetaan,
			//onko se samanv‰rinen kuin ammuttu kupla.
			for (Kupla n : tempNaapurit){
				if (!tarkastetut.contains(n)){
					tarkastetut.add(n);
					if (this.onSamanVarinen(n)){
						//Lis‰t‰‰n samanv‰risiin n, jolle tehd‰‰n uudestaan
						//koko tarkastelu silmukan alusta l‰htien.
						samanvariset.add(n);
					}
				}
			}
		}

		//Nyt samanv‰riset sis‰lt‰‰ kaikki ne samanv‰riset kuplat, joihin
		//kupla ja sen naapurit ja naapurin naapurit jne. koskevat.
		
		//Poks!!!
		
		if (samanvariset.size() >= 3){
			for (int i = 0; i < samanvariset.size(); i++){
				samanvariset.get(i).poksahda();
			}
		}
	}
}
