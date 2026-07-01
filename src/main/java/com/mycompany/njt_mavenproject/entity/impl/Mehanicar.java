package com.mycompany.njt_mavenproject.entity.impl;
import com.mycompany.njt_mavenproject.entity.MyEntity;
import jakarta.persistence.*;
/**
 * Entitet koji predstavlja mehaničara zaposlenog u servisu za vozila.
 *
 * @author Bojana
 */
@Entity
@Table(name = "mehanicar")
public class Mehanicar implements MyEntity {
	
    /** Jedinstveni identifikator mehaničara. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    /** Ime mehaničara. Ne sme biti null ili prazno. */
    @Column(nullable = false)
    private String ime;
    
    /** Prezime mehaničara. Ne sme biti null ili prazno. */
    @Column(nullable = false)
    private String prezime;
    
    /** Specijalnost mehaničara (npr. Mehanika, Elektrika). */
    private String specijalnost;
    
    /** Kontakt telefon mehaničara. */
    private String telefon;
    
    /** Servis u kome je mehaničar zaposlen. Ne sme biti null. */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "servis_id", nullable = false)
    private Servis servis;
    
    /**
     * Podrazumevani konstruktor.
     */
    public Mehanicar() {}
    
    /**
     * Konstruktor koji kreira referencu na mehaničara sa zadatim ID-em.
     *
     * @param id jedinstveni identifikator mehaničara
     */
    public Mehanicar(Long id) { this.id = id; }
    
    /**
     * Vraća ID mehaničara.
     *
     * @return jedinstveni identifikator
     */
    public Long getId() { return id; }
    
    /**
     * Postavlja ID mehaničara.
     *
     * @param id jedinstveni identifikator
     */
    public void setId(Long id) { this.id = id; }
    
    /**
     * Vraća ime mehaničara.
     *
     * @return ime
     */
    public String getIme() { return ime; }
    
    /**
     * Postavlja ime mehaničara.
     * Ime ne sme biti null ili prazno.
     *
     * @param ime ime mehaničara
     * @throws IllegalArgumentException ako je ime null ili prazno
     */
    public void setIme(String ime) {
        if (ime == null || ime.isBlank())
            throw new IllegalArgumentException("Ime ne sme biti null ili prazno.");
        this.ime = ime;
    }
    
    /**
     * Vraća prezime mehaničara.
     *
     * @return prezime
     */
    public String getPrezime() { return prezime; }
    
    /**
     * Postavlja prezime mehaničara.
     * Prezime ne sme biti null ili prazno.
     *
     * @param prezime prezime mehaničara
     * @throws IllegalArgumentException ako je prezime null ili prazno
     */
    public void setPrezime(String prezime) {
        if (prezime == null || prezime.isBlank())
            throw new IllegalArgumentException("Prezime ne sme biti null ili prazno.");
        this.prezime = prezime;
    }
    
    /**
     * Vraća specijalnost mehaničara.
     *
     * @return specijalnost
     */
    public String getSpecijalnost() { return specijalnost; }
    
    /**
     * Postavlja specijalnost mehaničara.
     *
     * @param specijalnost specijalnost mehaničara
     */
    public void setSpecijalnost(String specijalnost) { this.specijalnost = specijalnost; }
    
    /**
     * Vraća kontakt telefon mehaničara.
     *
     * @return telefon
     */
    public String getTelefon() { return telefon; }
    
    /**
     * Postavlja kontakt telefon mehaničara.
     *
     * @param telefon kontakt telefon
     */
    public void setTelefon(String telefon) { this.telefon = telefon; }
    
    /**
     * Vraća servis u kome mehaničar radi.
     *
     * @return servis
     */
    public Servis getServis() { return servis; }
    
    /**
     * Postavlja servis u kome mehaničar radi.
     * Servis ne sme biti null.
     *
     * @param servis servis mehaničara
     * @throws IllegalArgumentException ako je servis null
     */
    public void setServis(Servis servis) {
        if (servis == null)
            throw new IllegalArgumentException("Servis ne sme biti null.");
        this.servis = servis;
    }
}