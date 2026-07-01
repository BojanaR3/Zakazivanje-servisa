package com.mycompany.njt_mavenproject.entity.impl;

import com.mycompany.njt_mavenproject.entity.MyEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entitet koji predstavlja vlasnika vozila i korisnika sistema.
 * Email i username moraju biti jedinstveni.
 *
 * @author Bojana
 */
@Entity
@Table(
    name = "vlasnik",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_vlasnik_email", columnNames = "email"),
        @UniqueConstraint(name = "uk_vlasnik_username", columnNames = "username")
    }
)
public class Vlasnik implements MyEntity {

    /** Jedinstveni identifikator vlasnika. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Ime vlasnika. Ne sme biti null ili prazno, maksimalno 50 karaktera. */
    @Column(nullable = false, length = 50)
    private String ime;

    /** Prezime vlasnika. Ne sme biti null ili prazno, maksimalno 50 karaktera. */
    @Column(nullable = false, length = 50)
    private String prezime;

    /** Email adresa vlasnika, mora biti jedinstvena. Ne sme biti null ili prazna. */
    @Column(nullable = false, length = 120)
    private String email;

    /** Korisničko ime vlasnika, mora biti jedinstveno. Ne sme biti null ili prazno. */
    @Column(nullable = false, length = 50)
    private String username;

    /** Hešovana lozinka vlasnika. Ne sme biti null ili prazna. */
    @Column(nullable = false)
    private String lozinka;

    /** Uloga korisnika u sistemu, podrazumevano VLASNIK. Ne sme biti null. */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Uloga uloga = Uloga.VLASNIK;

    /** Oznaka da li je nalog aktiviran putem email verifikacije. */
    @Column(nullable = false)
    private boolean enabled = false;

    /** Lista vozila u vlasništvu korisnika. */
    @OneToMany(mappedBy = "vlasnik", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vozilo> vozila = new ArrayList<>();

    /** Lista rezervacija koje je vlasnik kreirao. */
    @OneToMany(mappedBy = "vlasnik")
    private List<Rezervacija> rezervacije = new ArrayList<>();

    /**
     * Podrazumevani konstruktor.
     */
    public Vlasnik() {}

    /**
     * Konstruktor sa svim parametrima.
     *
     * @param id       jedinstveni identifikator vlasnika
     * @param ime      ime vlasnika
     * @param prezime  prezime vlasnika
     * @param email    email adresa vlasnika
     * @param username korisničko ime vlasnika
     * @param lozinka  lozinka vlasnika
     */
    public Vlasnik(Long id, String ime, String prezime, String email, String username, String lozinka) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.username = username;
        this.lozinka = lozinka;
    }

    /**
     * Konstruktor koji kreira referencu na vlasnika sa zadatim ID-em.
     *
     * @param id jedinstveni identifikator vlasnika
     */
    public Vlasnik(Long id) { this.id = id; }

    /**
     * Vraća ID vlasnika.
     *
     * @return jedinstveni identifikator
     */
    public Long getId() { return id; }

    /**
     * Postavlja ID vlasnika.
     *
     * @param id jedinstveni identifikator
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Vraća ime vlasnika.
     *
     * @return ime
     */
    public String getIme() { return ime; }

    /**
     * Postavlja ime vlasnika.
     * Ime ne sme biti null ili prazno i ne sme biti duže od 50 karaktera.
     *
     * @param ime ime vlasnika
     * @throws IllegalArgumentException ako je ime null, prazno ili duže od 50 karaktera
     */
    public void setIme(String ime) {
        if (ime == null || ime.isBlank())
            throw new IllegalArgumentException("Ime ne sme biti null ili prazno.");
        if (ime.length() > 50)
            throw new IllegalArgumentException("Ime ne sme biti duže od 50 karaktera.");
        this.ime = ime;
    }

    /**
     * Vraća prezime vlasnika.
     *
     * @return prezime
     */
    public String getPrezime() { return prezime; }

    /**
     * Postavlja prezime vlasnika.
     * Prezime ne sme biti null ili prazno i ne sme biti duže od 50 karaktera.
     *
     * @param prezime prezime vlasnika
     * @throws IllegalArgumentException ako je prezime null, prazno ili duže od 50 karaktera
     */
    public void setPrezime(String prezime) {
        if (prezime == null || prezime.isBlank())
            throw new IllegalArgumentException("Prezime ne sme biti null ili prazno.");
        if (prezime.length() > 50)
            throw new IllegalArgumentException("Prezime ne sme biti duže od 50 karaktera.");
        this.prezime = prezime;
    }

    /**
     * Vraća email adresu vlasnika.
     *
     * @return email adresa
     */
    public String getEmail() { return email; }

    /**
     * Postavlja email adresu vlasnika.
     * Email ne sme biti null ili prazan.
     *
     * @param email email adresa vlasnika
     * @throws IllegalArgumentException ako je email null ili prazan
     */
    public void setEmail(String email) {
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email ne sme biti null ili prazan.");
        this.email = email;
    }

    /**
     * Vraća korisničko ime vlasnika.
     *
     * @return korisničko ime
     */
    public String getUsername() { return username; }

    /**
     * Postavlja korisničko ime vlasnika.
     * Korisničko ime ne sme biti null ili prazno.
     *
     * @param username korisničko ime vlasnika
     * @throws IllegalArgumentException ako je korisničko ime null ili prazno
     */
    public void setUsername(String username) {
        if (username == null || username.isBlank())
            throw new IllegalArgumentException("Korisničko ime ne sme biti null ili prazno.");
        this.username = username;
    }

    /**
     * Vraća hešovanu lozinku vlasnika.
     *
     * @return lozinka
     */
    public String getLozinka() { return lozinka; }

    /**
     * Postavlja hešovanu lozinku vlasnika.
     * Lozinka ne sme biti null ili prazna.
     *
     * @param lozinka hešovana lozinka
     * @throws IllegalArgumentException ako je lozinka null ili prazna
     */
    public void setLozinka(String lozinka) {
        if (lozinka == null || lozinka.isBlank())
            throw new IllegalArgumentException("Lozinka ne sme biti null ili prazna.");
        this.lozinka = lozinka;
    }

    /**
     * Vraća ulogu korisnika u sistemu.
     *
     * @return uloga (VLASNIK ili ADMIN)
     */
    public Uloga getUloga() { return uloga; }

    /**
     * Postavlja ulogu korisnika u sistemu.
     * Uloga ne sme biti null.
     *
     * @param uloga uloga korisnika
     * @throws IllegalArgumentException ako je uloga null
     */
    public void setUloga(Uloga uloga) {
        if (uloga == null)
            throw new IllegalArgumentException("Uloga ne sme biti null.");
        this.uloga = uloga;
    }

    /**
     * Proverava da li je nalog aktiviran.
     *
     * @return true ako je nalog aktiviran, false ako nije
     */
    public boolean isEnabled() { return enabled; }

    /**
     * Postavlja status aktivacije naloga.
     *
     * @param enabled true ako je nalog aktiviran
     */
    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    /**
     * Vraća listu vozila vlasnika.
     *
     * @return lista vozila
     */
    public List<Vozilo> getVozila() { return vozila; }

    /**
     * Postavlja listu vozila vlasnika.
     *
     * @param vozila lista vozila
     */
    public void setVozila(List<Vozilo> vozila) { this.vozila = vozila; }

    /**
     * Vraća listu rezervacija vlasnika.
     *
     * @return lista rezervacija
     */
    public List<Rezervacija> getRezervacije() { return rezervacije; }

    /**
     * Postavlja listu rezervacija vlasnika.
     *
     * @param rezervacije lista rezervacija
     */
    public void setRezervacije(List<Rezervacija> rezervacije) { this.rezervacije = rezervacije; }
}