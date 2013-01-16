package Model;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * 
 * Superkupla on erikoinen kupla, joka poksauttaa muihin kupliin osuessaan
 * aina kaikki naapurinsa riippumatta niiden väreistä.
 * @author 345480
 *
 */
public class Superkupla extends AktiivinenKupla{
	
	/** Superkuplan kuva. */
	private static final Image superkupla = 
			Toolkit.getDefaultToolkit().createImage("media/superkupla.png");

	/**
	 * Superkuplan konstruktori.
	 * @param x, x-koordinaatti
	 * @param y, y-koordinaatti
	 * @param maailma, jossa superkupla sijaitsee
	 */
	public Superkupla(double x, double y, Maailma maailma) {
		super(x, y, maailma);
	}
	
	/**
	 * Palauttaa superkuplan kuvan.
	 */
	@Override
	public Image annaKuva(){
		return superkupla;
	}
}
