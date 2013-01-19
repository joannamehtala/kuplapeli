package Model;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;


/**
 * Kupla-luokka, jossa m‰‰ritell‰‰n kuplille ominaisuuksia ja metodeja.
 * @author 345480
 *
 */
public class Kupla {

	/** Kuplan s‰de. */
	private double sade;

	/** Kuplan v‰ri. */
	private Vari vari;

	/** Kertoo, onko kupla ehj‰. */
	private boolean ehja;

	/** Kertoo, onko kupla t‰ll‰ hetkell‰ putoava. */
	private boolean putoava;

	/** Random arpomista varten. */
	public static final Random rand = new Random();

	/** Kuplan x-koordinaatti. */
	private double x;

	/** Kuplan y-koordinaatti. */
	private double y;

	/** Maailma, jossa kupla sijaitsee. */
	private Maailma maailma;

	/** Punaisen kuplan kuva. */
	private static final Image punainen = 
			Toolkit.getDefaultToolkit().createImage("media/punainenkupla.png");

	/** Sinisen kuplan kuva. */
	private static final Image sininen =
			Toolkit.getDefaultToolkit().createImage("media/sininenkupla.png");

	/** Vihre‰n kuplan kuva. */
	private static final Image vihrea =
			Toolkit.getDefaultToolkit().createImage("media/vihreakupla.png");

	/** Liilan kuplan kuva. */
	private static final Image liila =
			Toolkit.getDefaultToolkit().createImage("media/liilakupla.png");

	/** Keltaisen kuplan kuva. */
	private static final Image keltainen =
			Toolkit.getDefaultToolkit().createImage("media/keltainenkupla.png");

	/**
	 * Kuplan konstruktori. Alustetaan attribuutit; jokainen kupla on aluksi
	 * ehj‰ ja sille arvotaan v‰ri.
	 * @param x x-koordinaatti
	 * @param y y-koordinaatti
	 * @param maailma
	 */
	public Kupla(double x, double y, Maailma maailma){

		this.ehja = true;
		this.putoava = false;
		this.maailma = maailma;
		this.x = x;
		this.y = y;
		this.sade = 22.5;

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
	 * Palauttaa kuplan nykyisen x-koordinaatin (vasen yl‰kulma).
	 * @return x-koord
	 */
	public double annaX(){
		return this.x;
	}

	/**
	 * Palauttaa kuplan nykyisen y-koordinaatin (vasen yl‰kulma).
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
	 * Kertoo, onko kupla putoava.
	 * @return true, jos kupla on putoava, false, jos ei ole.
	 */
	public boolean onPutoava(){
		return this.putoava;
	}

	/**
	 * Asettaa kuplan putoavaksi.
	 */
	public void asetaPutoavaksi(){
		this.putoava = true;
	}

	/**
	 * Palauttaa kuplan s‰teen.
	 * @return sade
	 */
	public double annaSade(){
		return this.sade;
	}

	/**
	 * Poksautetaan kupla asettamalla sen attribuutti "ehja" falseksi.
	 */
	public void poksahda(){
		this.ehja = false;
	}

	/**
	 * Pudotetaan kuplaa alasp‰in ja poksautetaan, kun se osuu ruudun
	 * alareunaan.
	 */
	public void putoa(long muutos) {

		this.y += muutos*0.4;

		if (this.y >= 450){
			this.poksahda();
		}
	}

	/**
	 * Palauttaa kuplan kuvan v‰rist‰ riippuen.
	 * @return kuplan kuva
	 */
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

	/**
	 * Asetetaan kuplalle uusi sijainti.
	 * @param x x-koordinaatti
	 * @param y y-koordinaatti
	 */
	protected void asetaSijainti(double x, double y){
		this.x = x;
		this.y = y;
	}

	/**
	 * Palauttaa listan kuplan naapureista.
	 * @return naapurit
	 */
	public ArrayList<Kupla> annaNaapurit(){

		ArrayList<Kupla> naapurit = new ArrayList<Kupla>();

		/* Tarkastelu tehd‰‰n vain, jos maailma ei ole null. */

		if (!(this.maailma == null)){

			/*
			 * Superkuplalle asetetaan isompi s‰de, jotta sen avulla saadaan
			 * helposti poksautettua enemm‰n kuplia. 
			 */
			if (this instanceof Superkupla){
				this.sade = 48;
			}

			/* K‰yd‰‰n l‰pi kaikki maailman kuplat. */

			for (int i = 0; i < this.maailma.annaKuplat().size(); i++){
				Kupla tutkittava = this.maailma.annaKuplat().get(i);

				/* 
				 * Jos kuplien keskipisteiden v‰linen et‰isyys on yht‰ kuin
				 * halkaisija (plus 6 jotta aina varmasti toimii), niin 
				 * lis‰t‰‰n kupla naapureihin. 
				 */
				if (Point2D.distance(this.annaKeskiX(), this.annaKeskiY(), 
						tutkittava.annaKeskiX(), tutkittava.annaKeskiY()) 
						<= 2 * this.sade + 6 && tutkittava.onEhja() &&
						tutkittava.onEhja()) {
					naapurit.add(tutkittava);
				}
			}
		}
		return naapurit;
	}

	/**
	 * Metodi palauttaa boolean-arvon true, jos parametrina annettu kupla
	 * on samanv‰rinen, ja false, jos se on eri v‰rinen.
	 * @param toinen Kupla, johon verrataan
	 * @return true, jos samanv‰rinen, false, jos eri v‰rinen
	 */
	public boolean onSamanVarinen(Kupla toinen){
		if (this.annaVari() == toinen.annaVari()){
			return true;
		}
		return false;
	}

	/**
	 * Metodi tarkistaa, mitk‰ kaikki kuplat on poksautettava tˆrm‰yksen
	 * tapahtuessa. Kaikki samanv‰riset kuplat on poksautettava jos niit‰ on
	 * v‰hint‰‰n 3 ja superkuplanctapauksessa poksautetaan naapureita 
	 * suuremmalla alueella kuin muiden kuplien tapauksessa, riippumatta
	 * kuplien v‰reist‰.
	 */
	public void tarkistaPoksautettavat(){

		/* 
		 * Luodaan poksautettavien kuplien lista ja lis‰t‰‰n siihen t‰m‰ kupla.
		 */
		ArrayList<Kupla> poksautettavat = new ArrayList<Kupla>();
		poksautettavat.add(this);

		/*
		 * Luodaan jo tarkastettujen kuplien lista (ilman t‰t‰ saadaan aikaan
		 * StackOverFlowError) ja lis‰t‰‰n tarkastettuihin t‰m‰ kupla.
		 */
		HashSet<Kupla> tarkastetut = new HashSet<Kupla>();
		tarkastetut.add(this);

		/*
		 * Jos kyseess‰ on superkupla, poksautetaan kaikki naapurit.
		 */
		if (this instanceof Superkupla){
			poksautettavat = this.annaNaapurit();

			/*
			 * Muulloin...
			 */
		} else {

			/*
			 * K‰yd‰‰n l‰pi poksautettavat kuplat, joita tulee lis‰‰ kun
			 * silmukkaa k‰yd‰‰n l‰pi
			 */

			for (int i = 0; i < poksautettavat.size(); i++){
				Kupla tutkittava = poksautettavat.get(i);
				ArrayList<Kupla> tempNaapurit = tutkittava.annaNaapurit();

				/*
				 * K‰yd‰‰n l‰pi ‰sken listatut tutkittavan kuplan naapurit yksi 
				 * kerrallaan, ja jokaisen kohdalla tarkistetaan, onko sit‰ 
				 * tutkittu aiemmin ja jos ei, se lis‰t‰‰n tutkittujen listalle
				 * ja tarkistetaan, onko se samanv‰rinen kuin ammuttu kupla.
				 */
				for (Kupla n : tempNaapurit){

					/*
					 * Kupla tarkastetaan vain, jos sit‰ ei ole viel‰
					 * tarkistettu.
					 */
					if (!tarkastetut.contains(n)){
						tarkastetut.add(n);

						if (this.onSamanVarinen(n)){
							/*
							 * Lis‰t‰‰n poksautettaviin n, jolle tehd‰‰n 
							 * uudestaan koko tarkastelu silmukan alusta
							 * l‰htien.
							 */
							poksautettavat.add(n);
						}
					}
				}
			}
		}

		/*
		 * Silmukan j‰lkeen poksautettavat-lista sis‰lt‰‰ kaikki ne samanv‰riset
		 * kuplat, joihin kupla ja sen naapurit ja naapurin naapurin jne.
		 * koskevat. Sitten vain poksautetaan ne!
		 */

		if (!(this instanceof Superkupla)){

			/*
			 * Kuplat poksautetaan vain, jos poksautettavia on 3 tai enemm‰n.
			 */
			if (poksautettavat.size() >= 3){
				for (int i = 0; i < poksautettavat.size(); i++){
					poksautettavat.get(i).poksahda();
				}
			}

			/*
			 * Superkuplan tapauksessa poksautetaan kuplia aina.
			 */
		} else {
			for (int i = 0; i < poksautettavat.size(); i++){
				poksautettavat.get(i).poksahda();
			}
		}
	}
}