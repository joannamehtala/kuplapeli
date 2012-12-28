import java.util.List;


public class Maailma {
	private int leveys;
	private int korkeus;
	private int x;
	private int y;
	private int kuplienLkm;
	private List<Kupla> kuplat;

	public Maailma(){
		this.leveys = 450;
		this.korkeus = 450;
		this.kuplienLkm = 0;
		
		if (this.x > this.leveys){
			this.x = this.leveys;
		}
		if (this.y > this.korkeus){
			this.y = this.korkeus;
		}
		
		/*for (this.x = 0; this.x < this.leveys; x++){
			for (this.y = 0; this.y < this.korkeus; y++){
				Kupla kupla = new Kupla(x, y);
				kuplienLkm++;
			}
		}*/
	}
	
	public int annaLeveys(){
		return this.leveys;
	}
	
	public int annaKorkeus(){
		return this.korkeus;
	}
}
