package com.mycompany.njt_mavenproject.entity.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class VoziloTest {

    Vozilo v;

    @BeforeEach
    void setUp() throws Exception {
        v = new Vozilo();
    }

    @AfterEach
    void tearDown() throws Exception {
        v = null;
    }

    @Test
    void testVozilo() {
        assertNotNull(v);
    }

    @Test
    void testVoziloLong() {
        v = new Vozilo(1L);

        assertNotNull(v);
        assertEquals(1L, v.getId());
    }

    @Test
    void testVoziloLongStringStringStringDoubleStringIntegerStringVlasnik() {
        Vlasnik vlasnik = new Vlasnik(1L);

        v = new Vozilo(1L, "Toyota", "Corolla", "BG123AB", 50000.0, "km", 2020, "Benzin", vlasnik);

        assertNotNull(v);
        assertEquals(1L, v.getId());
        assertEquals("Toyota", v.getMarka());
        assertEquals("Corolla", v.getModel());
        assertEquals("BG123AB", v.getRegistracija());
        assertEquals(50000.0, v.getKilometraza());
        assertEquals("km", v.getJedinicaKilometraze());
        assertEquals(2020, v.getGodProizvodnje());
        assertEquals("Benzin", v.getTipGoriva());
        assertEquals(vlasnik, v.getVlasnik());
    }

    @Test
    void testVoziloKonstruktorMarkaNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Vozilo(1L, null, "Corolla", "BG123AB", 50000.0, "km", 2020, "Benzin", null));
    }

    @Test
    void testVoziloKonstruktorModelPrazan() {
        assertThrows(IllegalArgumentException.class,
                () -> new Vozilo(1L, "Toyota", "", "BG123AB", 50000.0, "km", 2020, "Benzin", null));
    }

    @Test
    void testVoziloKonstruktorRegistracijaBlanko() {
        assertThrows(IllegalArgumentException.class,
                () -> new Vozilo(1L, "Toyota", "Corolla", "   ", 50000.0, "km", 2020, "Benzin", null));
    }

    @Test
    void testVoziloKonstruktorKilometrazaNegativna() {
        assertThrows(IllegalArgumentException.class,
                () -> new Vozilo(1L, "Toyota", "Corolla", "BG123AB", -1.0, "km", 2020, "Benzin", null));
    }

    @Test
    void testVoziloKonstruktorJedinicaKilometrazeNeispravna() {
        assertThrows(IllegalArgumentException.class,
                () -> new Vozilo(1L, "Toyota", "Corolla", "BG123AB", 50000.0, "metar", 2020, "Benzin", null));
    }

    @Test
    void testVoziloKonstruktorGodProizvodnjeNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Vozilo(1L, "Toyota", "Corolla", "BG123AB", 50000.0, "km", null, "Benzin", null));
    }

    @Test
    void testVoziloKonstruktorGodProizvodnjePreMala() {
        assertThrows(IllegalArgumentException.class,
                () -> new Vozilo(1L, "Toyota", "Corolla", "BG123AB", 50000.0, "km", 1899, "Benzin", null));
    }

    @Test
    void testVoziloKonstruktorTipGorivaNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Vozilo(1L, "Toyota", "Corolla", "BG123AB", 50000.0, "km", 2020, null, null));
    }

    @Test
    void testDefaultRezervacije() {
        assertNotNull(v.getRezervacije());
        assertTrue(v.getRezervacije().isEmpty());
    }

    @Test
    void testSetId() {
        v.setId(1L);

        assertEquals(1L, v.getId());
    }

    @Test
    void testSetMarka() {
        v.setMarka("Toyota");

        assertEquals("Toyota", v.getMarka());
    }

    @Test
    void testSetMarkaNull() {
        assertThrows(IllegalArgumentException.class, () -> v.setMarka(null));
    }

    @Test
    void testSetMarkaPrazno() {
        assertThrows(IllegalArgumentException.class, () -> v.setMarka(""));
    }

    @Test
    void testSetMarkaBlanko() {
        assertThrows(IllegalArgumentException.class, () -> v.setMarka("   "));
    }

    @Test
    void testSetMarkaPrekratka() {
        assertThrows(IllegalArgumentException.class, () -> v.setMarka("A"));
    }

    @Test
    void testSetMarkaPredugacka() {
        String predugacka = "A".repeat(51);

        assertThrows(IllegalArgumentException.class, () -> v.setMarka(predugacka));
    }

    @Test
    void testSetMarkaTacno2Karaktera() {
        v.setMarka("AB");

        assertEquals("AB", v.getMarka());
    }

    @Test
    void testSetMarkaTacno50Karaktera() {
        String marka = "A".repeat(50);

        v.setMarka(marka);

        assertEquals(marka, v.getMarka());
    }

    @Test
    void testSetModel() {
        v.setModel("Corolla");

        assertEquals("Corolla", v.getModel());
    }

    @Test
    void testSetModelNull() {
        assertThrows(IllegalArgumentException.class, () -> v.setModel(null));
    }

    @Test
    void testSetModelPrazno() {
        assertThrows(IllegalArgumentException.class, () -> v.setModel(""));
    }

    @Test
    void testSetModelBlanko() {
        assertThrows(IllegalArgumentException.class, () -> v.setModel("   "));
    }

    @Test
    void testSetModelPredugacak() {
        String predugacak = "A".repeat(51);

        assertThrows(IllegalArgumentException.class, () -> v.setModel(predugacak));
    }

    @Test
    void testSetModelTacno50Karaktera() {
        String model = "A".repeat(50);

        v.setModel(model);

        assertEquals(model, v.getModel());
    }

    @Test
    void testSetRegistracija() {
        v.setRegistracija("BG123AB");

        assertEquals("BG123AB", v.getRegistracija());
    }

    @Test
    void testSetRegistracijaNull() {
        assertThrows(IllegalArgumentException.class, () -> v.setRegistracija(null));
    }

    @Test
    void testSetRegistracijaPrazno() {
        assertThrows(IllegalArgumentException.class, () -> v.setRegistracija(""));
    }

    @Test
    void testSetRegistracijaBlanko() {
        assertThrows(IllegalArgumentException.class, () -> v.setRegistracija("   "));
    }

    @Test
    void testSetRegistracijaPrekratka() {
        assertThrows(IllegalArgumentException.class, () -> v.setRegistracija("A"));
    }

    @Test
    void testSetRegistracijaPredugacka() {
        String predugacka = "A".repeat(21);

        assertThrows(IllegalArgumentException.class, () -> v.setRegistracija(predugacka));
    }

    @Test
    void testSetRegistracijaTacno2Karaktera() {
        v.setRegistracija("AB");

        assertEquals("AB", v.getRegistracija());
    }

    @Test
    void testSetRegistracijaTacno20Karaktera() {
        String registracija = "A".repeat(20);

        v.setRegistracija(registracija);

        assertEquals(registracija, v.getRegistracija());
    }

    @Test
    void testSetKilometraza() {
        v.setKilometraza(50000.0);

        assertEquals(50000.0, v.getKilometraza());
    }

    @Test
    void testSetKilometrazaNull() {
        v.setKilometraza(null);

        assertNull(v.getKilometraza());
    }

    @Test
    void testSetKilometrazaNegativna() {
        assertThrows(IllegalArgumentException.class, () -> v.setKilometraza(-1.0));
    }

    @Test
    void testSetKilometrazaNula() {
        v.setKilometraza(0.0);

        assertEquals(0.0, v.getKilometraza());
    }

    @Test
    void testSetJedinicaKilometraze() {
        v.setJedinicaKilometraze("km");

        assertEquals("km", v.getJedinicaKilometraze());
    }

    @Test
    void testSetJedinicaKilometrazeMi() {
        v.setJedinicaKilometraze("mi");

        assertEquals("mi", v.getJedinicaKilometraze());
    }

    @Test
    void testSetJedinicaKilometrazeNull() {
        assertThrows(IllegalArgumentException.class, () -> v.setJedinicaKilometraze(null));
    }

    @Test
    void testSetJedinicaKilometrazePrazno() {
        assertThrows(IllegalArgumentException.class, () -> v.setJedinicaKilometraze(""));
    }

    @Test
    void testSetJedinicaKilometrazeBlanko() {
        assertThrows(IllegalArgumentException.class, () -> v.setJedinicaKilometraze("   "));
    }

    @Test
    void testSetJedinicaKilometrazeNeispravan() {
        assertThrows(IllegalArgumentException.class, () -> v.setJedinicaKilometraze("metar"));
    }

    @Test
    void testSetGodProizvodnje() {
        v.setGodProizvodnje(2020);

        assertEquals(2020, v.getGodProizvodnje());
    }

    @Test
    void testSetGodProizvodnjeNull() {
        assertThrows(IllegalArgumentException.class, () -> v.setGodProizvodnje(null));
    }

    @Test
    void testSetGodProizvodnjePreMala() {
        assertThrows(IllegalArgumentException.class, () -> v.setGodProizvodnje(1899));
    }

    @Test
    void testSetGodProizvodnjePreVelika() {
        assertThrows(IllegalArgumentException.class, () -> v.setGodProizvodnje(2101));
    }

    @Test
    void testSetGodProizvodnjeDonjaGranica() {
        v.setGodProizvodnje(1900);

        assertEquals(1900, v.getGodProizvodnje());
    }

    @Test
    void testSetGodProizvodnjeGornjaGranica() {
        v.setGodProizvodnje(2100);

        assertEquals(2100, v.getGodProizvodnje());
    }

    @Test
    void testSetTipGoriva() {
        v.setTipGoriva("Benzin");

        assertEquals("Benzin", v.getTipGoriva());
    }

    @Test
    void testSetTipGorivaNull() {
        assertThrows(IllegalArgumentException.class, () -> v.setTipGoriva(null));
    }

    @Test
    void testSetTipGorivaPrazno() {
        assertThrows(IllegalArgumentException.class, () -> v.setTipGoriva(""));
    }

    @Test
    void testSetTipGorivaBlanko() {
        assertThrows(IllegalArgumentException.class, () -> v.setTipGoriva("   "));
    }

    @Test
    void testSetVlasnik() {
        Vlasnik vlasnik = new Vlasnik(1L);

        v.setVlasnik(vlasnik);

        assertEquals(vlasnik, v.getVlasnik());
    }

    @Test
    void testSetVlasnikNull() {
        v.setVlasnik(null);

        assertNull(v.getVlasnik());
    }

    @Test
    void testSetRezervacije() {
        List<Rezervacija> rezervacije = new ArrayList<>();
        rezervacije.add(new Rezervacija(1L));

        v.setRezervacije(rezervacije);

        assertEquals(rezervacije, v.getRezervacije());
    }

    @Test
    void testSetRezervacijeNull() {
        assertThrows(IllegalArgumentException.class, () -> v.setRezervacije(null));
    }
}