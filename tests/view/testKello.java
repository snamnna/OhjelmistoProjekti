package view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import simulaattori.simu.framework.Kello;

class testKello {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Testataan kello-luokkaa");
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
    @DisplayName("Kello voi saada vain yhden instanssin")
	void testSaaVainYhdenInstanssin() {
		Kello kello1 = Kello.getInstance();
		Kello kello2 = Kello.getInstance();
		
		assertEquals(kello1, kello2, "Instansseja voi luoda useamman, muokkaa metodi sellaiseksi että saa aina saman instanssin");
	}
	@Test
	void testSetAika() {
		Kello kello = Kello.getInstance();
		kello.setAika(12.5);
		
		assertEquals(12.5, kello.getAika(), "Kello ei aseta aikaa oikein");
	}

}
