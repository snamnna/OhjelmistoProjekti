/**
 * Tämä luokka tarjoaa staattisen metodin, joka luo uuden palvelupisteen annetun tapahtuman tyypin perusteella.
 */
package simulaattori.model.util;

import eduni.distributions.Normal;
import simulaattori.framework.Tapahtumalista;
import simulaattori.model.Laakari;
import simulaattori.model.Labra;
import simulaattori.model.Sairaanhoitaja;
import simulaattori.model.TapahtumanTyyppi;


public class FPalvelupiste {
	/**
	 * Luo uuden palvelupisteen annetun tapahtuman tyypin ja tapahtumalistauksen perusteella.
	 *
	 * @param tyyppi         palvelupisteen tapahtuman tyyppi
	 * @param tapahtumalista tapahtumalista, johon uusi palvelupiste lisätään
	 * @return uusi palvelupiste annetun tapahtuman tyypin ja tapahtumalistauksen perusteella
	 * @throws IllegalArgumentException jos annettu tapahtuman tyyppi ei vastaa mitään määriteltyä palvelupistettä
	 */
	public static IPalvelupiste createPalvelupiste(TapahtumanTyyppi tyyppi, Tapahtumalista tapahtumalista) {
		IPalvelupiste uusiPalvelupiste;
		switch (tyyppi) {
			case ARR -> uusiPalvelupiste = new Sairaanhoitaja(new Normal(10, 6), tapahtumalista, TapahtumanTyyppi.ARR);
			case YLARR -> uusiPalvelupiste = new Laakari(new Normal(10, 10), tapahtumalista, TapahtumanTyyppi.YLARR);
			case ELARR -> uusiPalvelupiste = new Laakari(new Normal(5, 3), tapahtumalista, TapahtumanTyyppi.ELARR);
			case LABRA_ARRIVAL ->
					uusiPalvelupiste = new Labra(new Normal(5, 3), tapahtumalista, TapahtumanTyyppi.LABRA_ARRIVAL);
			default -> throw new IllegalArgumentException("Unexpected value: " + tyyppi);
		}
		;
		return uusiPalvelupiste;
	}
}
