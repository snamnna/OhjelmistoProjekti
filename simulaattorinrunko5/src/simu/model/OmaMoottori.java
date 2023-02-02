package simu.model;

import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;

public class OmaMoottori extends Moottori{
	
	private Saapumisprosessi saapumisprosessi;
	
	private final static int SAIRAANHOITAJA = 0;
	private final static int YLEISLAAKARI = 1;
	private final static int ERIKOISLAAKARI = 2;
	private final static int LABRA = 3;

	public OmaMoottori(){
			

		
		palvelupisteet = new Palvelupiste[4];
		
	
		palvelupisteet[SAIRAANHOITAJA]=new Palvelupiste(new Normal(10,6), tapahtumalista, TapahtumanTyyppi.ARR);	
		palvelupisteet[YLEISLAAKARI]=new Palvelupiste(new Normal(10,10), tapahtumalista, TapahtumanTyyppi.YLARR);
		palvelupisteet[ERIKOISLAAKARI]=new Palvelupiste(new Normal(5,3), tapahtumalista, TapahtumanTyyppi.ELARR);
		palvelupisteet[LABRA]=new Palvelupiste(new Normal(5,3), tapahtumalista);

		
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
			
			case ARR: palvelupisteet[SAIRAANHOITAJA].lisaaJonoon(new Asiakas());	
				       saapumisprosessi.generoiSeuraava();	
				break;
			case YLARR: a = palvelupisteet[SAIRAANHOITAJA].otaJonosta();
				   	   palvelupisteet[YLEISLAAKARI].lisaaJonoon(a);
				break;
			case ELARR: a = palvelupisteet[SAIRAANHOITAJA].otaJonosta();
				   	   palvelupisteet[ERIKOISLAAKARI].lisaaJonoon(a); 
				break;  
			case YLLABARR: a = palvelupisteet[YLEISLAAKARI].otaJonosta();
	   					palvelupisteet[LABRA].lisaaJonoon(a);
						
				break;	
			case ELLABARR: a = palvelupisteet[ERIKOISLAAKARI].otaJonosta();
		   	   			palvelupisteet[LABRA].lisaaJonoon(a); 
		   	   	break;						
			case ELDEP:
				       a = palvelupisteet[ERIKOISLAAKARI].otaJonosta();
					   a.setPoistumisaika(Kello.getInstance().getAika());
			           a.raportti(); 
			    break;
			case YLDEP:
			       a = palvelupisteet[YLEISLAAKARI].otaJonosta();
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
