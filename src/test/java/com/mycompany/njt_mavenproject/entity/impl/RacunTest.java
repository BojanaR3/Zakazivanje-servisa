package com.mycompany.njt_mavenproject.entity.impl;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

class RacunTest {
    Racun r;
    @BeforeEach
    void setUp() throws Exception {
        r = new Racun();
    }
    @AfterEach
    void tearDown() throws Exception {
        r = null;
    }
    @Test
    void testRacun() {
        assertNotNull(r);
    }
    @Test
    void testRacunLong() {
        r = new Racun(1L);
        assertNotNull(r);
        assertEquals(1L, r.getId());
    }
    @Test
    void testSetId() {
        r.setId(1L);
        assertEquals(1L, r.getId());
    }
    @Test
    void testSetBroj() {
        r.setBroj("R-001");
        assertEquals("R-001", r.getBroj());
    }
    @Test
    void testSetBrojNull() {
        assertThrows(IllegalArgumentException.class, () -> r.setBroj(null));
    }
    @Test
    void testSetBrojPrazno() {
        assertThrows(IllegalArgumentException.class, () -> r.setBroj(""));
    }
    @Test
    void testSetBrojBlanko() {
        assertThrows(IllegalArgumentException.class, () -> r.setBroj("   "));
    }
    @Test
    void testSetDatumIzdavanja() {
        LocalDateTime datum = LocalDateTime.of(2025, 6, 1, 10, 0);
        r.setDatumIzdavanja(datum);
        assertEquals(datum, r.getDatumIzdavanja());
    }
    @Test
    void testSetDatumIzdavanjaNull() {
        assertThrows(IllegalArgumentException.class, () -> r.setDatumIzdavanja(null));
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
    void testSetStatusPlacanja() {
        r.setStatusPlacanja("PLACENO");
        assertEquals("PLACENO", r.getStatusPlacanja());
    }
    @Test
    void testSetStatusPlacanjaNull() {
        assertThrows(IllegalArgumentException.class, () -> r.setStatusPlacanja(null));
    }
    @Test
    void testSetStatusPlacanjaPrazno() {
        assertThrows(IllegalArgumentException.class, () -> r.setStatusPlacanja(""));
    }
    @Test
    void testSetStatusPlacanjaBlanko() {
        assertThrows(IllegalArgumentException.class, () -> r.setStatusPlacanja("   "));
    }
    @Test
    void testSetRezervacija() {
        Rezervacija rez = new Rezervacija(1L);
        r.setRezervacija(rez);
        assertEquals(rez, r.getRezervacija());
    }
    @Test
    void testSetRezervacijaNull() {
        assertThrows(IllegalArgumentException.class, () -> r.setRezervacija(null));
    }
}