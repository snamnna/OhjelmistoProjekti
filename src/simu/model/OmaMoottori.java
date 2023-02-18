package simu.model;

import eduni.distributions.Negexp;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;
import simu.model.util.FPalvelupiste;
import simu.model.util.IPalvelupiste;

import java.util.Map;
import controller.IKontrolleriVtoM;

public class OmaMoottori extends Moottori {

	private final Saapumisprosessi saapumisprosessi;

	public OmaMoottori(IKontrolleriVtoM kontrolleri) {
		super(kontrolleri);
		saapumisprosessi = new Saapumisprosessi(new Negexp(15, 5), tapahtumalista, TapahtumanTyyppi.ARR);
	}

	@Override
	protected void alustukset() {
		Map<String, Integer> pPisteet = kontrolleri.haePalvelupisteet();

		for (String s : pPisteet.keySet()) {
			// palvelupisteiden alustus
			for (int i = 0; i < pPisteet.get(s); i++) {
				IPalvelupiste palvelupiste = FPalvelupiste.createPalvelupiste(getTapahtumanTyyppi(s), tapahtumalista);
				palvelupisteet.putIfAbsent(palvelupiste.getID(), palvelupiste);
			}
		}

		saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
	}

	protected TapahtumanTyyppi getTapahtumanTyyppi(String tyyppi, Boolean arrival) {
		TapahtumanTyyppi tTyyppi = TapahtumanTyyppi.ARR;
		switch (tyyppi) {
		case "Sairaanhoitaja" -> tTyyppi = TapahtumanTyyppi.ARR;
		case "YLaakari" -> tTyyppi = arrival ? TapahtumanTyyppi.YLARR : TapahtumanTyyppi.YLDEP;
		case "ELaakari" -> tTyyppi = arrival ? TapahtumanTyyppi.ELARR : TapahtumanTyyppi.ELDEP;
		case "Labra" -> tTyyppi = arrival ? TapahtumanTyyppi.LABRA_ARRIVAL : TapahtumanTyyppi.LABRA_DEPARTURE;
		}
		return tTyyppi;
	}

	protected TapahtumanTyyppi getTapahtumanTyyppi(String tyyppi) {
		return getTapahtumanTyyppi(tyyppi, true);
	}

	@Override
	protected void suoritaTapahtuma(Tapahtuma t) { // B-vaiheen tapahtumat
		TapahtumanTyyppi tyyppi = t.getTyyppi();
		palvelupisteet.get(t.getPalvelupisteID()).siirraAsiakas(t, palvelupisteet);
		if (tyyppi == TapahtumanTyyppi.ARR)
			saapumisprosessi.generoiSeuraava();
	}

	@Override
	protected void tulokset() {
		// luodaanko tulokset tulos-luokassa ja sieltä sitten jotenkin tänne ja
		// eteenpäin uille?
		System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());
		System.out.println("Tulokset ... puuttuvat vielä");
	}

	public void setLabJakauma() {
		// TODO Auto-generated method stub

	}

	public void setElaakarienLkm() {
		// TODO Auto-generated method stub

	}

	public void setYlaakarienLkm() {
		// TODO Auto-generated method stub

	}
}
