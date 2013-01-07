import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

/**
 * Maailma-luokka, joka sis�lt�� pelilogiikkaa maailman osalta; pidet��n
 * silm�ll� pelin kuplien lukum��r��, niiden v�rej� ja asetetaan kuplat oikeille
 * paikoilleen.
 * @author 345480
 *
 */

public class Maailma {

	/**
	 * Attribuutteina leveys, korkeus, koordinaatit x ja y, pelimaailma,
	 * maailman nykyinen ja seuraava aktiivinen kupla, boolean pelin loppumisen
	 * tarkastelulle sek� kuplien lukum��r�� kuvaava int.
	 */
	private int leveys;
	private int korkeus;
	public static Random rand = new Random();
	private boolean peliLoppunut;
	private Stack<Kupla> kuplat;
	private Pelimaailma pelimaailma;
	private Ohjaaja ohjaaja;
	private double alkupiste_x;
	private double alkupiste_y;


	/**
	 * Maailman konstruktori. Alustetaan attribuutit ja luodaan jonkin verran
	 * kuplia ruudun yl�reunaan.
	 * @param 
	 */
	public Maailma(Pelimaailma pelimaailma){

		/*
		 * Alustetaan leveys ja korkeus pelikent�n halutun koon mukaisesti.
		 */
		this.leveys = 450;
		this.korkeus = 500;
		this.pelimaailma = pelimaailma;

		this.kuplat = new Stack<Kupla>();

		this.alkupiste_x = 50;
		this.alkupiste_y = 50;

		for (int i = 0; i < 4; i++){
			if (i % 2 == 0){
				//parittomille riveille luodaan 10 kuplaa alkaen x=50
				for (int a = 0; a < 9; a++){
					Kupla kupla2 = new Kupla(this.alkupiste_x+(a*45), 
							this.alkupiste_y+(i*45));
					this.kuplat.push(kupla2);
				}
			} else {
				//parillisille riveille luodaan 9 kuplaa alkaen x=72.5
				for (int a = 0; a < 8; a++){
						Kupla kupla = new Kupla(this.alkupiste_x+22.5+(a*45),
								this.alkupiste_y+(i*45));
						this.kuplat.push(kupla);
				}
			}
		}

		AktiivinenKupla kupla_nykyinen = 
				new AktiivinenKupla(Pelimaailma.LAHTO_X - 22.5,
						Pelimaailma.LAHTO_Y - 22.5, this.pelimaailma);
		this.kuplat.push(kupla_nykyinen);
		this.ohjaaja = new Ohjaaja(this, this.pelimaailma, this.annaNykyinen());
	}

	/**
	 * Metodi palauttaa maailman leveyden.
	 * @return int leveys
	 */
	public int annaLeveys(){
		return this.leveys;
	}

	/**
	 * Metodi palauttaa maailman korkeuden.
	 * @return int korkeus
	 */
	public int annaKorkeus(){
		return this.korkeus;
	}

	/**
	 * Palauttaa nykyisen aktiivisen kuplan.
	 * @return this.nykyinen
	 */
	public AktiivinenKupla annaNykyinen(){
		return (AktiivinenKupla) this.kuplat.peek();
	}

	public Iterator<Kupla> kuplaiteraattori(){
		return this.kuplat.iterator();
	}

	public void ammuNykyinen(double kulma){
		this.annaNykyinen().ammu(kulma);
	}

	public void arvoUusi(){
		AktiivinenKupla arvottu = new AktiivinenKupla(Pelimaailma.LAHTO_X - 22.5,
				Pelimaailma.LAHTO_Y - 22.5, 
				this.pelimaailma);
		this.kuplat.push(arvottu);
	}

	public void liikutaNykyista(long muutos){
		this.annaNykyinen().liiku(muutos);
		if (this.annaNykyinen().onPysahtynyt()){
			this.arvoUusi();
		}
	}

	/**
	 * Metodi palauttaa tiedon siit�, onko peli ohi vai ei.
	 * @return true, jos peli on loppunut, ja false, jos se on viel� k�ynniss�.
	 */
	public boolean peliOhi(){
		if (this.peliLoppunut){
			return true;
		} return false;
	}
}
