package com.mycompany.njt_mavenproject.entity.impl;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class RezervacijaTest {

    Rezervacija r;

    @BeforeEach
    void setUp() throws Exception {
        r = new Rezervacija();
    }

    @AfterEach
    void tearDown() throws Exception {
        r = null;
    }

    @Test
    void testRezervacija() {
        assertNotNull(r);
    }

    @Test
    void testRezervacijaLong() {
        r = new Rezervacija(1L);
        assertNotNull(r);
        assertEquals(1L, r.getId());
    }

    @Test
    void testRezervacijaLongLocalDateTimeDoubleStatusRezervacijeVlasnikVoziloServis() {
        LocalDateTime datum = LocalDateTime.of(2025, 6, 1, 10, 0);
        Vlasnik vlasnik = new Vlasnik(1L);
        Vozilo vozilo = new Vozilo(1L);
        Servis servis = new Servis(1L);
        r = new Rezervacija(1L, datum, 1500.0, StatusRezervacije.CREATED, vlasnik, vozilo, servis);
        assertNotNull(r);
        assertEquals(1L, r.getId());
        assertEquals(datum, r.getDatum());
        assertEquals(1500.0, r.getUkupanIznos());
        assertEquals(StatusRezervacije.CREATED, r.getStatus());
        assertEquals(vlasnik, r.getVlasnik());
        assertEquals(vozilo, r.getVozilo());
        assertEquals(servis, r.getServis());
    }

    @Test
    void testPrePersistDatumNull() {
        r.prePersist();
        assertNotNull(r.getDatum());
    }

    @Test
    void testPrePersistDatumNijeNull() {
        LocalDateTime datum = LocalDateTime.of(2025, 6, 1, 10, 0);
        r.setDatum(datum);
        r.prePersist();
        assertEquals(datum, r.getDatum());
    }

    @Test
    void testAddItem() {
        StavkaRezervacije stavka = new StavkaRezervacije();
        stavka.setUnitPrice(500.0);
        stavka.setKolicina(1);
        r.addItem(stavka);
        assertEquals(1, r.getStavke().size());
        assertEquals(r, stavka.getRezervacija());
        assertEquals(500.0, r.getUkupanIznos());
    }

    @Test
    void testAddItemNull() {
        assertThrows(IllegalArgumentException.class, () -> r.addItem(null));
    }

    @Test
    void testAddItemVise() {
        StavkaRezervacije s1 = new StavkaRezervacije();
        s1.setUnitPrice(500.0);
        s1.setKolicina(1);
        StavkaRezervacije s2 = new StavkaRezervacije();
        s2.setUnitPrice(300.0);
        s2.setKolicina(2);
        r.addItem(s1);
        r.addItem(s2);
        assertEquals(2, r.getStavke().size());
        assertEquals(1100.0, r.getUkupanIznos());
    }

    @Test
    void testRemoveItem() {
        StavkaRezervacije stavka = new StavkaRezervacije();
        stavka.setUnitPrice(500.0);
        stavka.setKolicina(1);
        r.addItem(stavka);
        r.removeItem(stavka);
        assertEquals(0, r.getStavke().size());
        assertEquals(0.0, r.getUkupanIznos());
    }
    
    @Test
    void testRemoveItemNull() {
        assertThrows(IllegalArgumentException.class, () -> r.removeItem(null));
    }

    @Test
    void testRecalcTotalPrazneStavke() {
        r.recalcTotal();
        assertEquals(0.0, r.getUkupanIznos());
    }

    @Test
    void testRecalcTotalSaStavkama() {
        StavkaRezervacije s1 = new StavkaRezervacije();
        s1.setUnitPrice(1000.0);
        s1.setKolicina(1);
        StavkaRezervacije s2 = new StavkaRezervacije();
        s2.setUnitPrice(500.0);
        s2.setKolicina(2);
        r.addItem(s1);
        r.addItem(s2);
        r.recalcTotal();
        assertEquals(2000.0, r.getUkupanIznos());
    }

    @Test
    void testSetId() {
        r.setId(1L);
        assertEquals(1L, r.getId());
    }

    @Test
    void testSetDatum() {
        LocalDateTime datum = LocalDateTime.of(2025, 6, 1, 10, 0);
        r.setDatum(datum);
        assertEquals(datum, r.getDatum());
    }

    @Test
    void testSetDatumNull() {
        assertThrows(IllegalArgumentException.class, () -> r.setDatum(null));
    }

    @Test
    void testSetUkupanIznos() {
        r.setUkupanIznos(1500.0);
        assertEquals(1500.0, r.getUkupanIznos());
    }

    @Test
    void testSetUkupanIznosNull() {
        assertThrows(IllegalArgumentException.class, () -> r.setUkupanIznos(null));
    }

    @Test
    void testSetUkupanIznosNegativan() {
        assertThrows(IllegalArgumentException.class, () -> r.setUkupanIznos(-1.0));
    }

    @Test
    void testSetUkupanIznosNula() {
        r.setUkupanIznos(0.0);
        assertEquals(0.0, r.getUkupanIznos());
    }

    @Test
    void testSetStatus() {
        r.setStatus(StatusRezervacije.CONFIRMED);
        assertEquals(StatusRezervacije.CONFIRMED, r.getStatus());
    }

    @Test
    void testSetStatusNull() {
        assertThrows(IllegalArgumentException.class, () -> r.setStatus(null));
    }

    @Test
    void testSetVlasnik() {
        Vlasnik v = new Vlasnik(1L);
        r.setVlasnik(v);
        assertEquals(v, r.getVlasnik());
    }

    @Test
    void testSetVlasnikNull() {
        assertThrows(IllegalArgumentException.class, () -> r.setVlasnik(null));
    }

    @Test
    void testSetVozilo() {
        Vozilo v = new Vozilo(1L);
        r.setVozilo(v);
        assertEquals(v, r.getVozilo());
    }

    @Test
    void testSetVoziloNull() {
        assertThrows(IllegalArgumentException.class, () -> r.setVozilo(null));
    }

    @Test
    void testSetServis() {
        Servis s = new Servis(1L);
        r.setServis(s);
        assertEquals(s, r.getServis());
    }

    @Test
    void testSetServisNull() {
        assertThrows(IllegalArgumentException.class, () -> r.setServis(null));
    }

    @Test
    void testSetStavke() {
        List<StavkaRezervacije> stavke = new ArrayList<>();
        r.setStavke(stavke);
        assertEquals(stavke, r.getStavke());
    }

    @Test
    void testSetStavkeNull() {
        assertThrows(IllegalArgumentException.class, () -> r.setStavke(null));
    }

    @Test
    void testSetTrajanjeMin() {
        r.setTrajanjeMin(60);
        assertEquals(60, r.getTrajanjeMin());
    }

    @Test
    void testSetTrajanjeMinNull() {
        assertThrows(IllegalArgumentException.class, () -> r.setTrajanjeMin(null));
    }

    @Test
    void testSetTrajanjeMinNegativan() {
        assertThrows(IllegalArgumentException.class, () -> r.setTrajanjeMin(-1));
    }

    @Test
    void testDefaultVrednosti() {
        assertEquals(StatusRezervacije.CREATED, r.getStatus());
        assertEquals(0.0, r.getUkupanIznos());
        assertEquals(0, r.getTrajanjeMin());
        assertNotNull(r.getStavke());
        assertTrue(r.getStavke().isEmpty());
    }
}