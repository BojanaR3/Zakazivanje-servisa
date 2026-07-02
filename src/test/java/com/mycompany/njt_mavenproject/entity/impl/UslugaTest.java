package com.mycompany.njt_mavenproject.entity.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UslugaTest {

    Usluga u;

    @BeforeEach
    void setUp() throws Exception {
        u = new Usluga();
    }

    @AfterEach
    void tearDown() throws Exception {
        u = null;
    }

    @Test
    void testUsluga() {
        assertNotNull(u);
    }

    @Test
    void testUslugaLong() {
        u = new Usluga(1L);

        assertNotNull(u);
        assertEquals(1L, u.getId());
    }

    @Test
    void testUslugaLongStringIntegerString() {
        u = new Usluga(1L, "Zamena ulja", 30, "min");

        assertNotNull(u);
        assertEquals(1L, u.getId());
        assertEquals("Zamena ulja", u.getNaziv());
        assertEquals(30, u.getTrajanje());
        assertEquals("min", u.getJedinicaMere());
    }

    @Test
    void testUslugaKonstruktorNazivNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Usluga(1L, null, 30, "min"));
    }

    @Test
    void testUslugaKonstruktorNazivPrazan() {
        assertThrows(IllegalArgumentException.class,
                () -> new Usluga(1L, "", 30, "min"));
    }

    @Test
    void testUslugaKonstruktorNazivBlanko() {
        assertThrows(IllegalArgumentException.class,
                () -> new Usluga(1L, "   ", 30, "min"));
    }

    @Test
    void testUslugaKonstruktorNazivPrekratak() {
        assertThrows(IllegalArgumentException.class,
                () -> new Usluga(1L, "A", 30, "min"));
    }

    @Test
    void testUslugaKonstruktorNazivPredugacak() {
        String predugacak = "A".repeat(101);

        assertThrows(IllegalArgumentException.class,
                () -> new Usluga(1L, predugacak, 30, "min"));
    }

    @Test
    void testUslugaKonstruktorTrajanjeNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Usluga(1L, "Zamena ulja", null, "min"));
    }

    @Test
    void testUslugaKonstruktorTrajanjeManjeOdJedan() {
        assertThrows(IllegalArgumentException.class,
                () -> new Usluga(1L, "Zamena ulja", 0, "min"));
    }

    @Test
    void testUslugaKonstruktorJedinicaMereNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Usluga(1L, "Zamena ulja", 30, null));
    }

    @Test
    void testUslugaKonstruktorJedinicaMerePrazno() {
        assertThrows(IllegalArgumentException.class,
                () -> new Usluga(1L, "Zamena ulja", 30, ""));
    }

    @Test
    void testUslugaKonstruktorJedinicaMereBlanko() {
        assertThrows(IllegalArgumentException.class,
                () -> new Usluga(1L, "Zamena ulja", 30, "   "));
    }

    @Test
    void testSetId() {
        u.setId(1L);

        assertEquals(1L, u.getId());
    }

    @Test
    void testSetNaziv() {
        u.setNaziv("Zamena ulja");

        assertEquals("Zamena ulja", u.getNaziv());
    }

    @Test
    void testSetNazivNull() {
        assertThrows(IllegalArgumentException.class, () -> u.setNaziv(null));
    }

    @Test
    void testSetNazivPrazno() {
        assertThrows(IllegalArgumentException.class, () -> u.setNaziv(""));
    }

    @Test
    void testSetNazivBlanko() {
        assertThrows(IllegalArgumentException.class, () -> u.setNaziv("   "));
    }

    @Test
    void testSetNazivPrekratak() {
        assertThrows(IllegalArgumentException.class, () -> u.setNaziv("A"));
    }

    @Test
    void testSetNazivPredugacak() {
        String predugacak = "A".repeat(101);

        assertThrows(IllegalArgumentException.class, () -> u.setNaziv(predugacak));
    }

    @Test
    void testSetNazivTacno2Karaktera() {
        u.setNaziv("AB");

        assertEquals("AB", u.getNaziv());
    }

    @Test
    void testSetNazivTacno100Karaktera() {
        String naziv = "A".repeat(100);

        u.setNaziv(naziv);

        assertEquals(naziv, u.getNaziv());
    }

    @Test
    void testSetTrajanje() {
        u.setTrajanje(30);

        assertEquals(30, u.getTrajanje());
    }

    @Test
    void testSetTrajanjeNull() {
        assertThrows(IllegalArgumentException.class, () -> u.setTrajanje(null));
    }

    @Test
    void testSetTrajanjeManjeOdJedan() {
        assertThrows(IllegalArgumentException.class, () -> u.setTrajanje(0));
    }

    @Test
    void testSetTrajanjeNegativno() {
        assertThrows(IllegalArgumentException.class, () -> u.setTrajanje(-1));
    }

    @Test
    void testSetTrajanjeJedan() {
        u.setTrajanje(1);

        assertEquals(1, u.getTrajanje());
    }

    @Test
    void testSetJedinicaMere() {
        u.setJedinicaMere("min");

        assertEquals("min", u.getJedinicaMere());
    }

    @Test
    void testSetJedinicaMereNull() {
        assertThrows(IllegalArgumentException.class, () -> u.setJedinicaMere(null));
    }

    @Test
    void testSetJedinicaMerePrazno() {
        assertThrows(IllegalArgumentException.class, () -> u.setJedinicaMere(""));
    }

    @Test
    void testSetJedinicaMereBlanko() {
        assertThrows(IllegalArgumentException.class, () -> u.setJedinicaMere("   "));
    }

    @Test
    void testSetCena() {
        u.setCena(1500.0);

        assertEquals(1500.0, u.getCena());
    }

    @Test
    void testSetCenaNula() {
        u.setCena(0.0);

        assertEquals(0.0, u.getCena());
    }

    @Test
    void testSetCenaNull() {
        u.setCena(null);

        assertNull(u.getCena());
    }

    @Test
    void testSetCenaNegativna() {
        assertThrows(IllegalArgumentException.class, () -> u.setCena(-1.0));
    }
}