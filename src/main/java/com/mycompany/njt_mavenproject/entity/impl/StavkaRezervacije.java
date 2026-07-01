package com.mycompany.njt_mavenproject.entity.impl;
import com.mycompany.njt_mavenproject.entity.MyEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

/**
 * Entitet koji predstavlja jednu stavku u okviru rezervacije.
 * Sadrži informacije o usluzi, količini i zaključanoj ceni u trenutku kreiranja rezervacije.
 *
 * @author Bojana
 */
@Entity
@Table(name = "stavkarezervacije")
public class StavkaRezervacije implements MyEntity {
	
    /** Jedinstveni identifikator stavke rezervacije. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /** Količina usluge u stavci, podrazumevano 1. Mora biti veća ili jednaka 1. */
    @Column(nullable = false)
    private Integer kolicina = 1;
    
    /** Zaključana jedinična cena usluge u trenutku kreiranja rezervacije. Ne sme biti null ili negativna. */
    @Column(nullable = false)
    private Double unitPrice;
    
    /** Rezervacija kojoj ova stavka pripada. Ne sme biti null. */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rezervacija_id", nullable = false)
    private Rezervacija rezervacija;
    
    /** Usluga koja se nalazi u ovoj stavci. Ne sme biti null. */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usluga_id", nullable = false)
    private Usluga usluga;
    
    /**
     * Izračunava ukupan iznos stavke kao proizvod jedinične cene i količine.
     *
     * @return ukupan iznos stavke, ili 0.0 ako cena ili količina nisu postavljeni
     */
    public Double getUkupno() {
        if (unitPrice == null || kolicina == null) return 0.0;
        return unitPrice * kolicina;
    }
    
    /**
     * Podrazumevani konstruktor.
     */
    public StavkaRezervacije() {}
    
    /**
     * Konstruktor sa osnovnim parametrima.
     *
     * @param id          jedinstveni identifikator stavke
     * @param unitPrice   jedinična cena usluge
     * @param rezervacija rezervacija kojoj stavka pripada
     * @param usluga      usluga u stavci
     */
    public StavkaRezervacije(Long id, Double unitPrice, Rezervacija rezervacija, Usluga usluga) {
        this.id = id;
        this.unitPrice = unitPrice;
        this.rezervacija = rezervacija;
        this.usluga = usluga;
    }
    
    /**
     * Vraća ID stavke rezervacije.
     *
     * @return jedinstveni identifikator
     */
    public Long getId() { return id; }
    
    /**
     * Postavlja ID stavke rezervacije.
     *
     * @param id jedinstveni identifikator
     */
    public void setId(Long id) { this.id = id; }
    
    /**
     * Vraća količinu usluge u stavci.
     *
     * @return količina
     */
    public Integer getKolicina() { return kolicina; }
    
    /**
     * Postavlja količinu usluge u stavci.
     * Količina mora biti veća ili jednaka 1.
     *
     * @param kolicina količina usluge
     * @throws IllegalArgumentException ako je količina null ili manja od 1
     */
    public void setKolicina(Integer kolicina) {
        if (kolicina == null || kolicina < 1)
            throw new IllegalArgumentException("Količina mora biti veća ili jednaka 1.");
        this.kolicina = kolicina;
    }
    
    /**
     * Vraća zaključanu jediničnu cenu usluge.
     *
     * @return jedinična cena
     */
    public Double getUnitPrice() { return unitPrice; }
    
    /**
     * Postavlja zaključanu jediničnu cenu usluge.
     * Cena ne sme biti null ili negativna.
     *
     * @param unitPrice jedinična cena usluge
     * @throws IllegalArgumentException ako je cena null ili negativna
     */
    public void setUnitPrice(Double unitPrice) {
        if (unitPrice == null || unitPrice < 0)
            throw new IllegalArgumentException("Jedinična cena ne sme biti null ili negativna.");
        this.unitPrice = unitPrice;
    }
    
    /**
     * Vraća rezervaciju kojoj ova stavka pripada.
     *
     * @return rezervacija
     */
    public Rezervacija getRezervacija() { return rezervacija; }
    
    /**
     * Postavlja rezervaciju kojoj ova stavka pripada.
     * Rezervacija ne sme biti null.
     *
     * @param rezervacija rezervacija
     * @throws IllegalArgumentException ako je rezervacija null
     */
    public void setRezervacija(Rezervacija rezervacija) {
        if (rezervacija == null)
            throw new IllegalArgumentException("Rezervacija ne sme biti null.");
        this.rezervacija = rezervacija;
    }
    
    /**
     * Vraća uslugu koja se nalazi u ovoj stavci.
     *
     * @return usluga
     */
    public Usluga getUsluga() { return usluga; }
    
    /**
     * Postavlja uslugu u stavci.
     * Usluga ne sme biti null.
     *
     * @param usluga usluga rezervacije
     * @throws IllegalArgumentException ako je usluga null
     */
    public void setUsluga(Usluga usluga) {
        if (usluga == null)
            throw new IllegalArgumentException("Usluga ne sme biti null.");
        this.usluga = usluga;
    }
}