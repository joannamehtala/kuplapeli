import java.awt.Image;
import java.awt.Toolkit;


public class Superkupla extends AktiivinenKupla{
	private static final Image superkupla = 
			Toolkit.getDefaultToolkit().createImage("media/superkupla.png");

	public Superkupla(double x, double y, Maailma maailma) {
		super(x, y, maailma);
		
	}
	
	@Override
	public Image annaKuva(){
		return superkupla;
	}
	
	
	
}
