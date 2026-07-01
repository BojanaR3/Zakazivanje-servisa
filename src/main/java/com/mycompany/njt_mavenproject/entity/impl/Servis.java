package com.mycompany.njt_mavenproject.entity.impl;
import com.mycompany.njt_mavenproject.entity.MyEntity;
import jakarta.persistence.*;
/**
 * Entitet koji predstavlja servis za vozila.
 * Sadrži osnovne informacije o servisu kao što su naziv, adresa i telefon.
 *
 * @author Bojana
 */
@Entity
@Table(name = "servis")
public class Servis implements MyEntity {
	
    /** Jedinstveni identifikator servisa. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /** Naziv servisa. Ne sme biti null ili prazan. */
    private String naziv;
    
    /** Adresa servisa. Ne sme biti null ili prazna, maksimalno 200 karaktera. */
    private String adresa;
    
    /** Kontakt telefon servisa. */
    private String telefon;
    
    /**
     * Podrazumevani konstruktor.
     */
    public Servis() {}
    
    /**
     * Konstruktor koji kreira referencu na servis sa zadatim ID-em.
     *
     * @param id jedinstveni identifikator servisa
     */
    public Servis(Long id) { this.id = id; }
    
    /**
     * Konstruktor sa svim parametrima.
     *
     * @param id      jedinstveni identifikator servisa
     * @param naziv   naziv servisa
     * @param adresa  adresa servisa
     * @param telefon kontakt telefon servisa
     */
    public Servis(Long id, String naziv, String adresa, String telefon) {
        this.id = id;
        this.naziv = naziv;
        this.adresa = adresa;
        this.telefon = telefon;
    }
    
    /**
     * Vraća ID servisa.
     *
     * @return jedinstveni identifikator
     */
    public Long getId() { return id; }
    
    /**
     * Postavlja ID servisa.
     *
     * @param id jedinstveni identifikator
     */
    public void setId(Long id) { this.id = id; }
    
    /**
     * Vraća naziv servisa.
     *
     * @return naziv servisa
     */
    public String getNaziv() { return naziv; }
    
    /**
     * Postavlja naziv servisa.
     * Naziv ne sme biti null ili prazan.
     *
     * @param naziv naziv servisa
     * @throws IllegalArgumentException ako je naziv null ili prazan
     */
    public void setNaziv(String naziv) {
        if (naziv == null || naziv.isBlank())
            throw new IllegalArgumentException("Naziv servisa ne sme biti null ili prazan.");
        this.naziv = naziv;
    }
    
    /**
     * Vraća adresu servisa.
     *
     * @return adresa servisa
     */
    public String getAdresa() { return adresa; }
    
    /**
     * Postavlja adresu servisa.
     * Adresa ne sme biti null ili prazna i ne sme biti duža od 200 karaktera.
     *
     * @param adresa adresa servisa
     * @throws IllegalArgumentException ako je adresa null, prazna ili duža od 200 karaktera
     */
    public void setAdresa(String adresa) {
        if (adresa == null || adresa.isBlank())
            throw new IllegalArgumentException("Adresa servisa ne sme biti null ili prazna.");
        if (adresa.length() > 200)
            throw new IllegalArgumentException("Adresa servisa ne sme biti duža od 200 karaktera.");
        this.adresa = adresa;
    }
    
    /**
     * Vraća kontakt telefon servisa.
     *
     * @return telefon
     */
    public String getTelefon() { return telefon; }
    
    /**
     * Postavlja kontakt telefon servisa.
     *
     * @param telefon kontakt telefon
     */
    public void setTelefon(String telefon) { this.telefon = telefon; }
}