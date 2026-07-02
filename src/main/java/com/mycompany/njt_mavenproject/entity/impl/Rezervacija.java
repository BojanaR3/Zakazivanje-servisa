package com.mycompany.njt_mavenproject.entity.impl;

import com.mycompany.njt_mavenproject.entity.MyEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entitet koji predstavlja rezervaciju termina u servisu za vozila.
 * Kombinacija servis_id i datum mora biti jedinstvena.
 *
 * @author Bojana
 */
@Entity
@Table(
    name = "rezervacija",
    uniqueConstraints = @UniqueConstraint(columnNames = {"servis_id", "datum"})
)
public class Rezervacija implements MyEntity {

    /** Jedinstveni identifikator rezervacije. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Datum i vreme rezervisanog termina. Ne sme biti null. */
    @Column(nullable = false)
    private LocalDateTime datum;

    /** Status rezervacije (CREATED, CONFIRMED, CANCELED, COMPLETED). Ne sme biti null. */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusRezervacije status = StatusRezervacije.CREATED;

    /** Ukupan iznos rezervacije izračunat iz stavki. Ne sme biti null i mora biti >= 0. */
    @Column(nullable = false)
    private Double ukupanIznos = 0.0;

    /** Ukupno trajanje rezervacije u minutima. Ne sme biti null i mora biti >= 0. */
    @Column(name = "trajanje_min", nullable = false)
    private Integer trajanjeMin = 0;

    /** Vlasnik koji je kreirao rezervaciju. Ne sme biti null. */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vlasnik_id", nullable = false)
    private Vlasnik vlasnik;

    /** Vozilo koje se servisira u okviru rezervacije. Ne sme biti null. */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vozilo_id", nullable = false)
    private Vozilo vozilo;

    /** Servis u kome je rezervisan termin. Ne sme biti null. */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "servis_id", nullable = false)
    private Servis servis;

    /** Lista stavki rezervacije. */
    @OneToMany(mappedBy = "rezervacija", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StavkaRezervacije> stavke = new ArrayList<>();

    /**
     * Konstruktor koji kreira referencu na rezervaciju sa zadatim ID-em.
     *
     * @param id jedinstveni identifikator rezervacije
     */
    public Rezervacija(Long id) {
        this.id = id;
    }

    /**
     * Podrazumevani konstruktor.
     */
    public Rezervacija() {}

    /**
     * Konstruktor sa svim parametrima.
     *
     * @param id          jedinstveni identifikator rezervacije
     * @param datum       datum i vreme rezervacije
     * @param ukupanIznos ukupan iznos rezervacije
     * @param status      status rezervacije
     * @param vlasnik     vlasnik koji je kreirao rezervaciju
     * @param vozilo      vozilo koje se servisira
     * @param servis      servis u kome je rezervisan termin
     * @throws IllegalArgumentException ako su datum, iznos, status, vlasnik, vozilo ili servis neispravni
     */
    public Rezervacija(Long id, LocalDateTime datum, Double ukupanIznos, StatusRezervacije status,
            Vlasnik vlasnik, Vozilo vozilo, Servis servis) {
		this.id = id;
		setDatum(datum);
		setUkupanIznos(ukupanIznos);
		setStatus(status);
		setVlasnik(vlasnik);
		setVozilo(vozilo);
		setServis(servis);
    }

    /**
     * Metoda koja se poziva pre čuvanja entiteta u bazu.
     * Postavlja datum na trenutno vreme ako nije zadat i izračunava ukupan iznos.
     */
    @PrePersist
    public void prePersist() {
        if (datum == null) datum = LocalDateTime.now(Clock.systemDefaultZone());
        recalcTotal();
        if (trajanjeMin == null) trajanjeMin = 0;
    }

    /**
     * Dodaje stavku u rezervaciju i ažurira ukupan iznos.
     *
     * @param item stavka rezervacije koja se dodaje
     * @throws IllegalArgumentException ako je stavka null
     */
    public void addItem(StavkaRezervacije item) {
        if (item == null)
            throw new IllegalArgumentException("Stavka rezervacije ne sme biti null.");
        item.setRezervacija(this);
        this.stavke.add(item);
        recalcTotal();
    }

    /**
     * Uklanja stavku iz rezervacije i ažurira ukupan iznos.
     *
     * @param item stavka rezervacije koja se uklanja
     * @throws IllegalArgumentException ako je stavka null
     */
    public void removeItem(StavkaRezervacije item) {
        if (item == null) {
            throw new IllegalArgumentException("Stavka rezervacije ne sme biti null.");
        }
        this.stavke.remove(item);
        recalcTotal();
    }

    /**
     * Izračunava i ažurira ukupan iznos rezervacije na osnovu svih stavki.
     * Treba pozivati nakon svake izmene stavki, količine ili popusta.
     */
    public void recalcTotal() {
        double ukupno = 0.0;
        for (StavkaRezervacije stavka : stavke) {
            ukupno = ukupno + stavka.getUkupno();
        }
        this.ukupanIznos = ukupno;
    }

    /**
     * Vraća ID rezervacije.
     *
     * @return jedinstveni identifikator
     */
    public Long getId() { return id; }

    /**
     * Postavlja ID rezervacije.
     *
     * @param id jedinstveni identifikator
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Vraća datum i vreme rezervacije.
     *
     * @return datum rezervacije
     */
    public LocalDateTime getDatum() { return datum; }

    /**
     * Postavlja datum i vreme rezervacije.
     * Datum ne sme biti null.
     *
     * @param datum datum i vreme rezervacije
     * @throws IllegalArgumentException ako je datum null
     */
    public void setDatum(LocalDateTime datum) {
        if (datum == null)
            throw new IllegalArgumentException("Datum rezervacije ne sme biti null.");
        this.datum = datum;
    }

    /**
     * Vraća ukupan iznos rezervacije.
     *
     * @return ukupan iznos
     */
    public Double getUkupanIznos() { return ukupanIznos; }

    /**
     * Postavlja ukupan iznos rezervacije.
     * Iznos ne sme biti null i mora biti veći ili jednak nuli.
     *
     * @param ukupanIznos ukupan iznos
     * @throws IllegalArgumentException ako je iznos null ili negativan
     */
    public void setUkupanIznos(Double ukupanIznos) {
        if (ukupanIznos == null || ukupanIznos < 0)
            throw new IllegalArgumentException("Ukupan iznos ne sme biti null ili negativan.");
        this.ukupanIznos = ukupanIznos;
    }

    /**
     * Vraća status rezervacije.
     *
     * @return status rezervacije
     */
    public StatusRezervacije getStatus() { return status; }

    /**
     * Postavlja status rezervacije.
     * Status ne sme biti null.
     *
     * @param status status rezervacije
     * @throws IllegalArgumentException ako je status null
     */
    public void setStatus(StatusRezervacije status) {
        if (status == null)
            throw new IllegalArgumentException("Status rezervacije ne sme biti null.");
        this.status = status;
    }

    /**
     * Vraća vlasnika koji je kreirao rezervaciju.
     *
     * @return vlasnik
     */
    public Vlasnik getVlasnik() { return vlasnik; }

    /**
     * Postavlja vlasnika rezervacije.
     * Vlasnik ne sme biti null.
     *
     * @param vlasnik vlasnik rezervacije
     * @throws IllegalArgumentException ako je vlasnik null
     */
    public void setVlasnik(Vlasnik vlasnik) {
        if (vlasnik == null)
            throw new IllegalArgumentException("Vlasnik rezervacije ne sme biti null.");
        this.vlasnik = vlasnik;
    }

    /**
     * Vraća vozilo koje se servisira.
     *
     * @return vozilo
     */
    public Vozilo getVozilo() { return vozilo; }

    /**
     * Postavlja vozilo koje se servisira.
     * Vozilo ne sme biti null.
     *
     * @param vozilo vozilo rezervacije
     * @throws IllegalArgumentException ako je vozilo null
     */
    public void setVozilo(Vozilo vozilo) {
        if (vozilo == null)
            throw new IllegalArgumentException("Vozilo rezervacije ne sme biti null.");
        this.vozilo = vozilo;
    }

    /**
     * Vraća servis u kome je rezervisan termin.
     *
     * @return servis
     */
    public Servis getServis() { return servis; }

    /**
     * Postavlja servis rezervacije.
     * Servis ne sme biti null.
     *
     * @param servis servis u kome je termin rezervisan
     * @throws IllegalArgumentException ako je servis null
     */
    public void setServis(Servis servis) {
        if (servis == null)
            throw new IllegalArgumentException("Servis rezervacije ne sme biti null.");
        this.servis = servis;
    }

    /**
     * Vraća listu stavki rezervacije.
     *
     * @return lista stavki
     */
    public List<StavkaRezervacije> getStavke() { return stavke; }

    /**
     * Postavlja listu stavki rezervacije.
     * Lista stavki ne sme biti null.
     *
     * @param stavke lista stavki rezervacije
     * @throws IllegalArgumentException ako je lista null
     */
    public void setStavke(List<StavkaRezervacije> stavke) {
        if (stavke == null)
            throw new IllegalArgumentException("Lista stavki ne sme biti null.");
        this.stavke = stavke;
        recalcTotal();
    }

    /**
     * Vraća ukupno trajanje rezervacije u minutima.
     *
     * @return trajanje u minutima
     */
    public Integer getTrajanjeMin() { return trajanjeMin; }

    /**
     * Postavlja ukupno trajanje rezervacije u minutima.
     * Trajanje ne sme biti null ili negativno.
     *
     * @param trajanjeMin trajanje u minutima
     * @throws IllegalArgumentException ako je trajanje null ili negativno
     */
    public void setTrajanjeMin(Integer trajanjeMin) {
        if (trajanjeMin == null || trajanjeMin < 0)
            throw new IllegalArgumentException("Trajanje ne sme biti null ili negativno.");
        this.trajanjeMin = trajanjeMin;
    }
}