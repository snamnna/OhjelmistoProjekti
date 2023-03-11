package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import simulaattori.framework.Kello;
import simulaattori.framework.Tapahtuma;
import simulaattori.model.TapahtumanTyyppi;

class TapahtumaTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Tapahtumaluokka testi");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Asetetaan palvelupisteelle ID")
	void testSetPalvelupisteId() {
		Tapahtuma tapahtuma = new Tapahtuma(TapahtumanTyyppi.ARR, 2);
		tapahtuma.setPalvelupisteID(1);
		assertEquals(1, tapahtuma.getPalvelupisteID(), "Palvelupisteen ID:tä ei asetettu oikein");
	}

	//Vaihdetaan luodun tapahtuman tyyppiä ja todetaan että se muuttuu
	@Test
	void testSetTyyppi() {
		Tapahtuma tapahtuma = new Tapahtuma(TapahtumanTyyppi.ARR, 2);
		tapahtuma.setTyyppi(TapahtumanTyyppi.ELDEP);
		assertEquals(TapahtumanTyyppi.ELDEP, tapahtuma.getTyyppi(), "Tapahtuman tyyppiä ei asetettu oikein");
	}

	//Vaihdetaan luodun tapahtuman aika ja todetaan sen muuttuvan
	@Test
	void testSetAika() {
		Tapahtuma tapahtuma = new Tapahtuma(TapahtumanTyyppi.ARR, 2);
		tapahtuma.setAika(3);
		assertEquals(3, tapahtuma.getAika(), 0.001, "Tapahtuman aikaa ei asetettu oikein");
	}

	//Vertaillaan tapatumien aikoja keskenään
	@Test
	void testCompareTo() {
		Tapahtuma tapahtuma1 = new Tapahtuma(TapahtumanTyyppi.ARR, 2);
		Tapahtuma tapahtuma2 = new Tapahtuma(TapahtumanTyyppi.ELDEP, 3);
		assertTrue(tapahtuma1.compareTo(tapahtuma2) < 0, "compareTo() palauttaa väärän arvon");
		assertTrue(tapahtuma2.compareTo(tapahtuma1) > 0, "compareTo() palauttaa väärän arvon");
		assertTrue(tapahtuma1.compareTo(tapahtuma1) == 0, "compareTo() palauttaa väärän arvon");
	}
}
