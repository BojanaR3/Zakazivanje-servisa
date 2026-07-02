package com.mycompany.njt_mavenproject.entity.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class VlasnikTest {

    Vlasnik v;

    @BeforeEach
    void setUp() throws Exception {
        v = new Vlasnik();
    }

    @AfterEach
    void tearDown() throws Exception {
        v = null;
    }

    @Test
    void testVlasnik() {
        assertNotNull(v);
    }

    @Test
    void testVlasnikLong() {
        v = new Vlasnik(1L);

        assertNotNull(v);
        assertEquals(1L, v.getId());
    }

    @Test
    void testVlasnikLongStringStringStringStringString() {
        v = new Vlasnik(1L, "Marko", "Markovic", "marko@gmail.com", "marko123", "lozinka");

        assertNotNull(v);
        assertEquals(1L, v.getId());
        assertEquals("Marko", v.getIme());
        assertEquals("Markovic", v.getPrezime());
        assertEquals("marko@gmail.com", v.getEmail());
        assertEquals("marko123", v.getUsername());
        assertEquals("lozinka", v.getLozinka());
    }

    @Test
    void testVlasnikKonstruktorImeNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Vlasnik(1L, null, "Markovic", "marko@gmail.com", "marko123", "lozinka"));
    }

    @Test
    void testVlasnikKonstruktorPrezimePrazno() {
        assertThrows(IllegalArgumentException.class,
                () -> new Vlasnik(1L, "Marko", "", "marko@gmail.com", "marko123", "lozinka"));
    }

    @Test
    void testVlasnikKonstruktorEmailBlanko() {
        assertThrows(IllegalArgumentException.class,
                () -> new Vlasnik(1L, "Marko", "Markovic", "   ", "marko123", "lozinka"));
    }

    @Test
    void testVlasnikKonstruktorUsernameNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Vlasnik(1L, "Marko", "Markovic", "marko@gmail.com", null, "lozinka"));
    }

    @Test
    void testVlasnikKonstruktorLozinkaPrazna() {
        assertThrows(IllegalArgumentException.class,
                () -> new Vlasnik(1L, "Marko", "Markovic", "marko@gmail.com", "marko123", ""));
    }

    @Test
    void testDefaultVrednosti() {
        assertEquals(Uloga.VLASNIK, v.getUloga());
        assertFalse(v.isEnabled());
        assertNotNull(v.getVozila());
        assertTrue(v.getVozila().isEmpty());
        assertNotNull(v.getRezervacije());
        assertTrue(v.getRezervacije().isEmpty());
    }

    @Test
    void testSetId() {
        v.setId(1L);

        assertEquals(1L, v.getId());
    }

    @Test
    void testSetIme() {
        v.setIme("Marko");

        assertEquals("Marko", v.getIme());
    }

    @Test
    void testSetImeNull() {
        assertThrows(IllegalArgumentException.class, () -> v.setIme(null));
    }

    @Test
    void testSetImePrazno() {
        assertThrows(IllegalArgumentException.class, () -> v.setIme(""));
    }

    @Test
    void testSetImeBlanko() {
        assertThrows(IllegalArgumentException.class, () -> v.setIme("   "));
    }

    @Test
    void testSetImePredugacko() {
        String predugacko = "A".repeat(51);

        assertThrows(IllegalArgumentException.class, () -> v.setIme(predugacko));
    }

    @Test
    void testSetImeTacno50Karaktera() {
        String ime = "A".repeat(50);

        v.setIme(ime);

        assertEquals(ime, v.getIme());
    }

    @Test
    void testSetPrezime() {
        v.setPrezime("Markovic");

        assertEquals("Markovic", v.getPrezime());
    }

    @Test
    void testSetPrezimeNull() {
        assertThrows(IllegalArgumentException.class, () -> v.setPrezime(null));
    }

    @Test
    void testSetPrezimePrazno() {
        assertThrows(IllegalArgumentException.class, () -> v.setPrezime(""));
    }

    @Test
    void testSetPrezimeBlanko() {
        assertThrows(IllegalArgumentException.class, () -> v.setPrezime("   "));
    }

    @Test
    void testSetPrezimePredugacko() {
        String predugacko = "A".repeat(51);

        assertThrows(IllegalArgumentException.class, () -> v.setPrezime(predugacko));
    }

    @Test
    void testSetPrezimeTacno50Karaktera() {
        String prezime = "A".repeat(50);

        v.setPrezime(prezime);

        assertEquals(prezime, v.getPrezime());
    }

    @Test
    void testSetEmail() {
        v.setEmail("marko@gmail.com");

        assertEquals("marko@gmail.com", v.getEmail());
    }

    @Test
    void testSetEmailNull() {
        assertThrows(IllegalArgumentException.class, () -> v.setEmail(null));
    }

    @Test
    void testSetEmailPrazno() {
        assertThrows(IllegalArgumentException.class, () -> v.setEmail(""));
    }

    @Test
    void testSetEmailBlanko() {
        assertThrows(IllegalArgumentException.class, () -> v.setEmail("   "));
    }

    @Test
    void testSetEmailPredugacak() {
        String email = "A".repeat(121);

        assertThrows(IllegalArgumentException.class, () -> v.setEmail(email));
    }

    @Test
    void testSetEmailTacno120Karaktera() {
        String email = "A".repeat(120);

        v.setEmail(email);

        assertEquals(email, v.getEmail());
    }

    @Test
    void testSetUsername() {
        v.setUsername("marko123");

        assertEquals("marko123", v.getUsername());
    }

    @Test
    void testSetUsernameNull() {
        assertThrows(IllegalArgumentException.class, () -> v.setUsername(null));
    }

    @Test
    void testSetUsernamePrazno() {
        assertThrows(IllegalArgumentException.class, () -> v.setUsername(""));
    }

    @Test
    void testSetUsernameBlanko() {
        assertThrows(IllegalArgumentException.class, () -> v.setUsername("   "));
    }

    @Test
    void testSetUsernamePredugacak() {
        String username = "A".repeat(51);

        assertThrows(IllegalArgumentException.class, () -> v.setUsername(username));
    }

    @Test
    void testSetUsernameTacno50Karaktera() {
        String username = "A".repeat(50);

        v.setUsername(username);

        assertEquals(username, v.getUsername());
    }

    @Test
    void testSetLozinka() {
        v.setLozinka("lozinka123");

        assertEquals("lozinka123", v.getLozinka());
    }

    @Test
    void testSetLozinkaNull() {
        assertThrows(IllegalArgumentException.class, () -> v.setLozinka(null));
    }

    @Test
    void testSetLozinkaPrazno() {
        assertThrows(IllegalArgumentException.class, () -> v.setLozinka(""));
    }

    @Test
    void testSetLozinkaBlanko() {
        assertThrows(IllegalArgumentException.class, () -> v.setLozinka("   "));
    }

    @Test
    void testSetUloga() {
        v.setUloga(Uloga.ADMIN);

        assertEquals(Uloga.ADMIN, v.getUloga());
    }

    @Test
    void testSetUlogaNull() {
        assertThrows(IllegalArgumentException.class, () -> v.setUloga(null));
    }

    @Test
    void testIsEnabledPodrazumevano() {
        assertFalse(v.isEnabled());
    }

    @Test
    void testSetEnabled() {
        v.setEnabled(true);

        assertTrue(v.isEnabled());
    }

    @Test
    void testSetEnabledFalse() {
        v.setEnabled(true);
        v.setEnabled(false);

        assertFalse(v.isEnabled());
    }

    @Test
    void testSetVozila() {
        List<Vozilo> vozila = new ArrayList<>();
        vozila.add(new Vozilo(1L));

        v.setVozila(vozila);

        assertEquals(vozila, v.getVozila());
    }

    @Test
    void testSetVozilaNull() {
        assertThrows(IllegalArgumentException.class, () -> v.setVozila(null));
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