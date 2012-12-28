import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

/**
 * Luokka pelin t�rkeimmille elementeille, kuplille. Luokassa m��ritell��n
 * kuplille ominaisuuksia, kuten koko, v�ri, sijainti, tieto siit� onko kupla 
 * ehj� ja onko se aktiivinen. Lis�ksi m��ritell��n metodeja.
 * @author 345480
 *
 */
public class Kupla {
	private final int sade;
	private Vari vari;
	private boolean ehja;
	public static final Random rand = new Random();
	private double x;
	private double y;
	private ArrayList<Kupla> ryhma; 
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
	public Kupla(double x, double y){
		this.sade = 30;
		this.ehja = true;
		this.ryhma = new ArrayList<Kupla>();
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

	public boolean koskeeToista(Kupla kohde){
		//Jos kahden kuplan keskipisteiden et�isyys toisistaan on sama kuin
		//kuplien halkaisija (kaikki kuplat samankokoisia), kuplat koskevat
		//toisiaan ja palauttaa true. Jos et�isyys on suurempi kuin halkaisija,
		//eiv�t koske toisiaan ja palauttaa false. Nyt palauttaa true ettei
		//herjaisi.
		return true;
	}

	/**
	 * Jos samanv�rinen kupla osuu toiseen, se lis�t��n kyseisen kuplan naapuri-
	 * listaan. Kun naapurilistan koko kasvaa kolmeen tai suuremmaksi, kaikki
	 * naapurilistan kuplat poksahtavat.
	 */
	public void kosketa(int x, int y){
		Kupla kohde = new Kupla(x, y);
		//Oikeasti kosketettava kupla t�ytyy antaa jotenkin koordinaateilla tms,
		//on laskettava se x- ja y-koordinaatti mihin vektori p��ttyy. ei voi
		//vaan luoda uutta
		if (this.koskeeToista(kohde)){
			if (this.annaVari() == kohde.annaVari()){
				this.ryhma.add(kohde);
				if (this.ryhma.size() >= 3){
					for (int i = 0; i < this.ryhma.size(); i++){
						this.ryhma.get(i).poksahda();
					}
					kohde.kosketa(x, y); //Kaydaan rekursiolla lapi myos
					//kosketettavan kuplan naapurit ja poksautetaan ne.
					//Nyt en tied� toimiiko mutta.........
				}
			}
		}
	}

	public void poksahda(){
		this.ehja = false;
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

	/**
	 * Testailumetodi.
	 * @param args
	 */
	public static void main (String[] args){
		Kupla kupla1 = new Kupla(1, 1);
		System.out.println("Kuplan v�ri on " + kupla1.annaVari() + ".");
	}
}
