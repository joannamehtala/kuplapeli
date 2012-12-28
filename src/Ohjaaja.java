import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


public class Ohjaaja implements ActionListener{

	private long edellinenhetki;
	private Timer timer;
	private Pelimaailma pelimaailma;
	private AktiivinenKupla aktiivinenkupla;

	public Ohjaaja(Pelimaailma pelimaailma, AktiivinenKupla aktiivinenkupla){
		this.aktiivinenkupla = aktiivinenkupla;
		this.timer = new Timer(10, this);
		this.pelimaailma = pelimaailma;
		this.timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (this.edellinenhetki == 0){
			this.edellinenhetki = System.currentTimeMillis();
			return;
		}
		long muutos = System.currentTimeMillis() - this.edellinenhetki;
		this.edellinenhetki = System.currentTimeMillis();

		this.aktiivinenkupla.liiku(muutos);

		this.pelimaailma.repaint();

	}
}
