package simulaattori.view;

/**
 * Rajapinta määrittelee metodit, joita kontrolleri käyttää käyttöliittymän kanssa kommunikointiin.
 */
public interface ISimulaattorinUI {
    /**
     * Palauttaa käyttäjän syöttämän yleislääkärien lukumäärän.
     */
    int getYlaakarienLkm();

    /**
     * Palauttaa käyttäjän syöttämän erikoislääkärien lukumäärän.
     */
    int getElaakarienLkm();

    /**
     * Palauttaa käyttäjän syöttämän laboratorioiden lukumäärän.
     */
    int getLabraLkm();

    /**
     * Palauttaa käyttäjän syöttämän sairaanhoitajien lukumäärän.
     */
    int getSairaanhoitajaLkm();

    /**
     * Palauttaa käyttäjän syöttämän simulointiajan.
     */
    double getSimulointiAika();

    /**
     * Palauttaa SimulaattoriControllerin, joka vastaa visualisoinnista.
     */
    SimulaattoriController getVisualisointi();

    /**
     * Palauttaa käyttäjän syöttämän viiveen.
     */
    long getViive();

    /**
     * Asettaa, simuloidaanko vai ei.
     *
     * @param value true, jos simulaatio on käynnissä, false muuten.
     */
    void setSimuloidaan(boolean value);

    /**
     * Hakee tulokset simulaattorilta
     */
    void getTulos();

    /**
     * tekee visualisointiin tarvittavat alustukset.
     */
    void alustaVisualisointi();
}
