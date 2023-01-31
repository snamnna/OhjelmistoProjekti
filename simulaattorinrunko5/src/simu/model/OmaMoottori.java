package simu.model;

import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;

public class OmaMoottori extends Moottori{
	
	private Saapumisprosessi saapumisprosessi;
	
	
	public OmaMoottori(){
			
		palvelupisteet = new Palvelupiste[3];
	
		palvelupisteet[0]=new Palvelupiste(new Normal(10,6), tapahtumalista, TapahtumanTyyppi.ARR);	
		palvelupisteet[1]=new Palvelupiste(new Normal(10,10), tapahtumalista, TapahtumanTyyppi.YLARR);
		palvelupisteet[2]=new Palvelupiste(new Normal(5,3), tapahtumalista, TapahtumanTyyppi.ELARR);
		
		saapumisprosessi = new Saapumisprosessi(new Negexp(15,5), tapahtumalista, TapahtumanTyyppi.ARR);

	}

	
	@Override
	protected void alustukset() {
		saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
	}
	
	@Override
	protected void suoritaTapahtuma(Tapahtuma t){  // B-vaiheen tapahtumat

		Asiakas a;
		switch (t.getTyyppi()){
			
			case ARR: palvelupisteet[0].lisaaJonoon(new Asiakas());	
				       saapumisprosessi.generoiSeuraava();	
				break;
			case YLARR: a = palvelupisteet[0].otaJonosta();
				   	   palvelupisteet[1].lisaaJonoon(a);
				break;
			case ELARR: a = palvelupisteet[0].otaJonosta();
				   	   palvelupisteet[2].lisaaJonoon(a); 
				break;  
			case LABARR: 
						//miten toteutetaan et saadaan kahest eri pisteest 
				break;	
			case ELDEP:
				       a = palvelupisteet[2].otaJonosta();
					   a.setPoistumisaika(Kello.getInstance().getAika());
			           a.raportti(); 
			    break;
			case YLDEP:
			       a = palvelupisteet[1].otaJonosta();
				   a.setPoistumisaika(Kello.getInstance().getAika());
		           a.raportti(); 
		        break;
		}	
	}

	
	@Override
	protected void tulokset() {	
		System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());
		System.out.println("Tulokset ... puuttuvat vielä");
	}

	
}
