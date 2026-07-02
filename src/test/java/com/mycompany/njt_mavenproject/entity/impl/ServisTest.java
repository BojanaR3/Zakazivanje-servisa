package com.mycompany.njt_mavenproject.entity.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServisTest {

    Servis s;

    @BeforeEach
    void setUp() throws Exception {
        s = new Servis();
    }

    @AfterEach
    void tearDown() throws Exception {
        s = null;
    }

    @Test
    void testServis() {
        assertNotNull(s);
    }

    @Test
    void testServisLong() {
        s = new Servis(1L);
        assertNotNull(s);
        assertEquals(1L, s.getId());
    }

    @Test
    void testServisLongStringStringString() {
        s = new Servis(1L, "Auto servis", "Bulevar 1", "0111234567");

        assertNotNull(s);
        assertEquals(1L, s.getId());
        assertEquals("Auto servis", s.getNaziv());
        assertEquals("Bulevar 1", s.getAdresa());
        assertEquals("0111234567", s.getTelefon());
    }

    @Test
    void testServisKonstruktorNazivNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Servis(1L, null, "Bulevar 1", "0111234567"));
    }

    @Test
    void testServisKonstruktorNazivPrazan() {
        assertThrows(IllegalArgumentException.class,
                () -> new Servis(1L, "", "Bulevar 1", "0111234567"));
    }

    @Test
    void testServisKonstruktorNazivBlanko() {
        assertThrows(IllegalArgumentException.class,
                () -> new Servis(1L, "   ", "Bulevar 1", "0111234567"));
    }

    @Test
    void testServisKonstruktorAdresaNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Servis(1L, "Auto servis", null, "0111234567"));
    }

    @Test
    void testServisKonstruktorAdresaPrazna() {
        assertThrows(IllegalArgumentException.class,
                () -> new Servis(1L, "Auto servis", "", "0111234567"));
    }

    @Test
    void testServisKonstruktorAdresaBlanko() {
        assertThrows(IllegalArgumentException.class,
                () -> new Servis(1L, "Auto servis", "   ", "0111234567"));
    }

    @Test
    void testServisKonstruktorAdresaPreduga() {
        String preduga = "A".repeat(201);

        assertThrows(IllegalArgumentException.class,
                () -> new Servis(1L, "Auto servis", preduga, "0111234567"));
    }

    @Test
    void testSetId() {
        s.setId(1L);

        assertEquals(1L, s.getId());
    }

    @Test
    void testSetNaziv() {
        s.setNaziv("Auto servis");

        assertEquals("Auto servis", s.getNaziv());
    }

    @Test
    void testSetNazivNull() {
        assertThrows(IllegalArgumentException.class, () -> s.setNaziv(null));
    }

    @Test
    void testSetNazivPrazno() {
        assertThrows(IllegalArgumentException.class, () -> s.setNaziv(""));
    }

    @Test
    void testSetNazivBlanko() {
        assertThrows(IllegalArgumentException.class, () -> s.setNaziv("   "));
    }

    @Test
    void testSetAdresa() {
        s.setAdresa("Bulevar 1");

        assertEquals("Bulevar 1", s.getAdresa());
    }

    @Test
    void testSetAdresaNull() {
        assertThrows(IllegalArgumentException.class, () -> s.setAdresa(null));
    }

    @Test
    void testSetAdresaPrazno() {
        assertThrows(IllegalArgumentException.class, () -> s.setAdresa(""));
    }

    @Test
    void testSetAdresaBlanko() {
        assertThrows(IllegalArgumentException.class, () -> s.setAdresa("   "));
    }

    @Test
    void testSetAdresaPreduga() {
        String preduga = "A".repeat(201);

        assertThrows(IllegalArgumentException.class, () -> s.setAdresa(preduga));
    }

    @Test
    void testSetAdresaTacno200Karaktera() {
        String adresa = "A".repeat(200);

        s.setAdresa(adresa);

        assertEquals(adresa, s.getAdresa());
    }

    @Test
    void testSetTelefon() {
        s.setTelefon("0111234567");

        assertEquals("0111234567", s.getTelefon());
    }
}