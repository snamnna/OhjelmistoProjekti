package simulaattori.view;

import simulaattori.simu.model.TapahtumanTyyppi;
import simulaattori.simu.model.util.IPalvelupiste;

import java.util.List;
import java.util.Map;

public interface ISimulaattorinUI {

    int getYlaakarienLkm();

    int getElaakarienLkm();

    int getLabraLkm();

    int getSairaanhoitajaLkm();

    double getSimulointiAika();

    void setLoppuaika(double aika);

    SimulaattoriController getVisualisointi();

    long getViive();

    void setSimuloidaan(boolean value);

    void getTulos();

    void setPalvelupisteet(Map<TapahtumanTyyppi, List<IPalvelupiste>> palvelupisteet);
}
