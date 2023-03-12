package simulaattori.controller;

/**
 * Rajapinta määrittelee metodit, joita käyttöliittymä käyttää moottorin kanssa kommunikoimiseen.
 */
public interface IKontrolleriVtoM {
    /**
     * Hidastaa simulaattoria.
     */
    void hidasta();

    /**
     * Nopeuttaa simulaattoria.
     */
    void nopeuta();

    /**
     * Käynnistää simulaattorin jos simulointi ei ole jo käynnissä.
     */
    void kaynnistaSimulointi();
}
