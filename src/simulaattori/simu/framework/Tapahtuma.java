package simulaattori.simu.framework;

import simulaattori.simu.model.TapahtumanTyyppi;
import simulaattori.simu.model.util.IPalvelupiste;

public class Tapahtuma implements Comparable<Tapahtuma> {

	private TapahtumanTyyppi tyyppi;
    private double aika;
    private IPalvelupiste palvelupiste;

    public Tapahtuma(TapahtumanTyyppi tyyppi, double aika, IPalvelupiste palvelupiste) {
        this.tyyppi = tyyppi;
        this.aika = aika;
        this.palvelupiste = palvelupiste;
    }

    public Tapahtuma(TapahtumanTyyppi tyyppi, double aika) {
        this.tyyppi = tyyppi;
        this.aika = aika;
    }

    public IPalvelupiste getPalvelupiste() {
        return palvelupiste;
    }

    public void setPalvelupiste(IPalvelupiste palvelupiste) {
        this.palvelupiste = palvelupiste;
    }

    public TapahtumanTyyppi getTyyppi() {
        return tyyppi;
    }

    public void setTyyppi(TapahtumanTyyppi tyyppi) {
        this.tyyppi = tyyppi;
    }

	public void setAika(double aika) {
		this.aika = aika;
	}

	public double getAika() {
		return aika;
	}

	@Override
	public int compareTo(Tapahtuma arg) {
		if (this.aika < arg.aika)
			return -1;
		else if (this.aika > arg.aika)
			return 1;
		return 0;
	}
}
