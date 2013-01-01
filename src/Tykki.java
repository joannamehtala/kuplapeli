import java.awt.Image;
import java.awt.Toolkit;


public class Tykki {
	private AktiivinenKupla nykyinenKupla;
	private AktiivinenKupla seuraavaKupla;
	private Pelimaailma pelimaailma;
	private double x;
	private double y;
	
	public Tykki(Pelimaailma pelimaailma){
		this.pelimaailma = pelimaailma;
		this.x = 228;
		this.y = 402;
		this.nykyinenKupla = new AktiivinenKupla(250, 404, pelimaailma);
		this.seuraavaKupla = new AktiivinenKupla(290, 455, pelimaailma);
	}
	
	public Kupla annaNykyinen(){
		return this.nykyinenKupla;
	}
	
	public Kupla annaSeuraava(){
		return this.seuraavaKupla;
	}
	
	public void ammu(){
		this.nykyinenKupla.liiku(10 /*miten tähän se muutos?*/);
		this.nykyinenKupla = this.seuraavaKupla;
		this.seuraavaKupla = new AktiivinenKupla(290, 455, pelimaailma);
	}
}
