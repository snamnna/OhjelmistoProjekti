package simu.framework;

import simu.model.util.IPalvelupiste;

public abstract class Moottori {
	
	private double simulointiaika = 0;
	
	private Kello kello;
	
	protected Tapahtumalista tapahtumalista;
	protected IPalvelupiste[] palvelupisteet;
	

	public Moottori(){

		kello = Kello.getInstance(); // Otetaan kello muuttujaan yksinkertaistamaan koodia
		
		tapahtumalista = new Tapahtumalista();
		
		// Palvelupisteet luodaan simu.model-pakkauksessa Moottorin aliluokassa 
		
		
	}

	public void setSimulointiaika(double aika) {
		simulointiaika = aika;
	}

	public void aja(){
		alustukset(); // luodaan mm. ensimmäinen tapahtuma
		while (simuloidaan()){
			
			Trace.out(Trace.Level.INFO, "\nA-vaihe: kello on " + nykyaika());
			kello.setAika(nykyaika());
			
			Trace.out(Trace.Level.INFO, "\nB-vaihe:" );
			suoritaBTapahtumat();
			
			Trace.out(Trace.Level.INFO, "\nC-vaihe:" );
			yritaCTapahtumat();

		}
		tulokset();
		
	}
	
	private void suoritaBTapahtumat(){
		while (tapahtumalista.getSeuraavanAika() == kello.getAika()){
			suoritaTapahtuma(tapahtumalista.poista());
		}
	}

	private void yritaCTapahtumat(){
		for (IPalvelupiste p: palvelupisteet){
			if (!p.onVarattu() && p.onJonossa()){
				p.aloitaPalvelu();
			}
		}
	}

	
	private double nykyaika(){
		return tapahtumalista.getSeuraavanAika();
	}
	
	private boolean simuloidaan(){
		return kello.getAika() < simulointiaika;
	}
	
	// Määritellään simu.model-pakkauksessa Moottorin aliluokassa	
	protected abstract void setLabJakauma();
	protected abstract void setElaakarienLkm(); 
	protected abstract void setYlaakarienLkm();
	protected abstract void alustukset(); 
	protected abstract void suoritaTapahtuma(Tapahtuma t);  
	protected abstract void tulokset(); 
}