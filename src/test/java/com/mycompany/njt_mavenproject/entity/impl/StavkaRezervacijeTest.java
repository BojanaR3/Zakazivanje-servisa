package com.mycompany.njt_mavenproject.entity.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StavkaRezervacijeTest {

    StavkaRezervacije s;

    @BeforeEach
    void setUp() throws Exception {
        s = new StavkaRezervacije();
    }

    @AfterEach
    void tearDown() throws Exception {
        s = null;
    }

    @Test
    void testStavkaRezervacije() {
        assertNotNull(s);
    }

    @Test
    void testStavkaRezervacijeLongDoubleRezervacijaUsluga() {
        Rezervacija r = new Rezervacija(1L);
        Usluga u = new Usluga(1L);

        s = new StavkaRezervacije(1L, 500.0, r, u);

        assertNotNull(s);
        assertEquals(1L, s.getId());
        assertEquals(500.0, s.getUnitPrice());
        assertEquals(r, s.getRezervacija());
        assertEquals(u, s.getUsluga());
    }

    @Test
    void testStavkaRezervacijeKonstruktorUnitPriceNull() {
        Rezervacija r = new Rezervacija(1L);
        Usluga u = new Usluga(1L);

        assertThrows(IllegalArgumentException.class,
                () -> new StavkaRezervacije(1L, null, r, u));
    }

    @Test
    void testStavkaRezervacijeKonstruktorUnitPriceNegativna() {
        Rezervacija r = new Rezervacija(1L);
        Usluga u = new Usluga(1L);

        assertThrows(IllegalArgumentException.class,
                () -> new StavkaRezervacije(1L, -1.0, r, u));
    }

    @Test
    void testStavkaRezervacijeKonstruktorRezervacijaNull() {
        Usluga u = new Usluga(1L);

        assertThrows(IllegalArgumentException.class,
                () -> new StavkaRezervacije(1L, 500.0, null, u));
    }

    @Test
    void testStavkaRezervacijeKonstruktorUslugaNull() {
        Rezervacija r = new Rezervacija(1L);

        assertThrows(IllegalArgumentException.class,
                () -> new StavkaRezervacije(1L, 500.0, r, null));
    }

    @Test
    void testDefaultKolicina() {
        assertEquals(1, s.getKolicina());
    }

    @Test
    void testGetUkupno() {
        s.setUnitPrice(500.0);
        s.setKolicina(2);

        assertEquals(1000.0, s.getUkupno());
    }

    @Test
    void testSetId() {
        s.setId(1L);

        assertEquals(1L, s.getId());
    }

    @Test
    void testSetKolicina() {
        s.setKolicina(3);

        assertEquals(3, s.getKolicina());
    }

    @Test
    void testSetKolicinaNull() {
        assertThrows(IllegalArgumentException.class, () -> s.setKolicina(null));
    }

    @Test
    void testSetKolicinaManjaOdJedan() {
        assertThrows(IllegalArgumentException.class, () -> s.setKolicina(0));
    }

    @Test
    void testSetKolicinaNegativna() {
        assertThrows(IllegalArgumentException.class, () -> s.setKolicina(-1));
    }

    @Test
    void testSetKolicinaJedan() {
        s.setKolicina(1);

        assertEquals(1, s.getKolicina());
    }

    @Test
    void testSetUnitPrice() {
        s.setUnitPrice(500.0);

        assertEquals(500.0, s.getUnitPrice());
    }

    @Test
    void testSetUnitPriceNull() {
        assertThrows(IllegalArgumentException.class, () -> s.setUnitPrice(null));
    }

    @Test
    void testSetUnitPriceNegativna() {
        assertThrows(IllegalArgumentException.class, () -> s.setUnitPrice(-1.0));
    }

    @Test
    void testSetUnitPriceNula() {
        s.setUnitPrice(0.0);

        assertEquals(0.0, s.getUnitPrice());
    }

    @Test
    void testSetRezervacija() {
        Rezervacija r = new Rezervacija(1L);

        s.setRezervacija(r);

        assertEquals(r, s.getRezervacija());
    }

    @Test
    void testSetRezervacijaNull() {
        assertThrows(IllegalArgumentException.class, () -> s.setRezervacija(null));
    }

    @Test
    void testSetUsluga() {
        Usluga u = new Usluga(1L);

        s.setUsluga(u);

        assertEquals(u, s.getUsluga());
    }

    @Test
    void testSetUslugaNull() {
        assertThrows(IllegalArgumentException.class, () -> s.setUsluga(null));
    }
}