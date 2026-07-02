package com.mycompany.njt_mavenproject.entity.impl;

import com.mycompany.njt_mavenproject.entity.MyEntity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja vozilo u sistemu.
 * Klasa sadrži podatke o vozilu, njegovom vlasniku i rezervacijama
 * koje su vezane za to vozilo.
 *
 * @author Bojana
 */
@Entity
@Table(name = "vozilo")
public class Vozilo implements MyEntity {

    /** Jedinstveni identifikator vozila. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Marka vozila. Ne sme biti null ili prazna, između 2 i 50 karaktera. */
    private String marka;

    /** Model vozila. Ne sme biti null ili prazan, između 1 i 50 karaktera. */
    private String model;

    /** Registarska oznaka vozila. Ne sme biti null ili prazna, između 2 i 20 karaktera. */
    private String registracija;

    /** Kilometraža vozila. Može biti null, ali ako je uneta ne sme biti negativna. */
    private Double kilometraza;

    /** Jedinica kilometraže (km ili mi). Ne sme biti null ili prazna. */
    private String jedinicaKilometraze;

    /** Godina proizvodnje vozila. Ne sme biti null i mora biti između 1900 i 2100. */
    private Integer godProizvodnje;

    /** Tip goriva koje vozilo koristi. Ne sme biti null ili prazan. */
    private String tipGoriva;

    /** Vlasnik vozila. Može biti null. */
    @ManyToOne(optional = true)
    @JoinColumn(name = "vlasnik_id", nullable = true)
    private Vlasnik vlasnik;

    /** Lista rezervacija povezanih sa vozilom. Ne sme biti null. */
    @OneToMany(mappedBy = "vozilo")
    private List<Rezervacija> rezervacije = new ArrayList<>();

    /**
     * Podrazumevani konstruktor.
     */
    public Vozilo() {}

    /**
     * Kreira objekat vozila sa svim atributima.
     *
     * @param id                  identifikator vozila
     * @param marka               marka vozila
     * @param model               model vozila
     * @param registracija        registarska oznaka vozila
     * @param kilometraza         kilometraža vozila
     * @param jedinicaKilometraze jedinica kilometraže
     * @param godProizvodnje      godina proizvodnje vozila
     * @param tipGoriva           tip goriva
     * @param vlasnik             vlasnik vozila
     * @throws IllegalArgumentException ako su marka, model, registracija, kilometraža,
     *                                  jedinica kilometraže, godina proizvodnje ili tip goriva neispravni
     */
    public Vozilo(Long id, String marka, String model, String registracija, Double kilometraza,
                  String jedinicaKilometraze, Integer godProizvodnje, String tipGoriva, Vlasnik vlasnik) {
        this.id = id;
        setMarka(marka);
        setModel(model);
        setRegistracija(registracija);
        setKilometraza(kilometraza);
        setJedinicaKilometraze(jedinicaKilometraze);
        setGodProizvodnje(godProizvodnje);
        setTipGoriva(tipGoriva);
        setVlasnik(vlasnik);
    }

    /**
     * Kreira objekat vozila sa zadatim identifikatorom.
     *
     * @param id identifikator vozila
     */
    public Vozilo(Long id) {
        this.id = id;
    }

    /**
     * Vraća identifikator vozila.
     *
     * @return identifikator vozila
     */
    public Long getId() {
        return id;
    }

    /**
     * Postavlja identifikator vozila.
     *
     * @param id identifikator vozila
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Vraća marku vozila.
     *
     * @return marka vozila
     */
    public String getMarka() {
        return marka;
    }

    /**
     * Postavlja marku vozila.
     * Marka ne sme biti null ili prazna i mora biti između 2 i 50 karaktera.
     *
     * @param marka marka vozila
     * @throws IllegalArgumentException ako je marka null, prazna ili nije između 2 i 50 karaktera
     */
    public void setMarka(String marka) {
        if (marka == null || marka.isBlank()) {
            throw new IllegalArgumentException("Marka vozila ne sme biti null ili prazna.");
        }
        if (marka.length() < 2 || marka.length() > 50) {
            throw new IllegalArgumentException("Marka vozila mora biti između 2 i 50 karaktera.");
        }
        this.marka = marka;
    }

    /**
     * Vraća model vozila.
     *
     * @return model vozila
     */
    public String getModel() {
        return model;
    }

    /**
     * Postavlja model vozila.
     * Model ne sme biti null ili prazan i mora biti između 1 i 50 karaktera.
     *
     * @param model model vozila
     * @throws IllegalArgumentException ako je model null, prazan ili nije između 1 i 50 karaktera
     */
    public void setModel(String model) {
        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("Model vozila ne sme biti null ili prazan.");
        }
        if (model.length() < 1 || model.length() > 50) {
            throw new IllegalArgumentException("Model vozila mora biti između 1 i 50 karaktera.");
        }
        this.model = model;
    }

    /**
     * Vraća registarsku oznaku vozila.
     *
     * @return registarska oznaka vozila
     */
    public String getRegistracija() {
        return registracija;
    }

    /**
     * Postavlja registarsku oznaku vozila.
     * Registracija ne sme biti null ili prazna i mora biti između 2 i 20 karaktera.
     *
     * @param registracija registarska oznaka vozila
     * @throws IllegalArgumentException ako je registracija null, prazna ili nije između 2 i 20 karaktera
     */
    public void setRegistracija(String registracija) {
        if (registracija == null || registracija.isBlank()) {
            throw new IllegalArgumentException("Registracija ne sme biti null ili prazna.");
        }
        if (registracija.length() < 2 || registracija.length() > 20) {
            throw new IllegalArgumentException("Registracija mora biti između 2 i 20 karaktera.");
        }
        this.registracija = registracija;
    }

    /**
     * Vraća kilometražu vozila.
     *
     * @return kilometraža vozila
     */
    public Double getKilometraza() {
        return kilometraza;
    }

    /**
     * Postavlja kilometražu vozila.
     * Kilometraža može biti null, ali ako je uneta ne sme biti negativna.
     *
     * @param kilometraza kilometraža vozila
     * @throws IllegalArgumentException ako je kilometraža negativna
     */
    public void setKilometraza(Double kilometraza) {
        if (kilometraza != null && kilometraza < 0) {
            throw new IllegalArgumentException("Kilometraža ne sme biti negativna.");
        }
        this.kilometraza = kilometraza;
    }

    /**
     * Vraća jedinicu kilometraže.
     *
     * @return jedinica kilometraže
     */
    public String getJedinicaKilometraze() {
        return jedinicaKilometraze;
    }

    /**
     * Postavlja jedinicu kilometraže.
     * Jedinica kilometraže ne sme biti null ili prazna i mora biti "km" ili "mi".
     *
     * @param jedinicaKilometraze jedinica kilometraže (km ili mi)
     * @throws IllegalArgumentException ako je vrednost null, prazna ili nije "km" ili "mi"
     */
    public void setJedinicaKilometraze(String jedinicaKilometraze) {
        if (jedinicaKilometraze == null || jedinicaKilometraze.isBlank()) {
            throw new IllegalArgumentException("Jedinica kilometraže ne sme biti null ili prazna.");
        }
        if (!jedinicaKilometraze.equals("km") && !jedinicaKilometraze.equals("mi")) {
            throw new IllegalArgumentException("Jedinica kilometraže mora biti 'km' ili 'mi'.");
        }
        this.jedinicaKilometraze = jedinicaKilometraze;
    }

    /**
     * Vraća godinu proizvodnje vozila.
     *
     * @return godina proizvodnje vozila
     */
    public Integer getGodProizvodnje() {
        return godProizvodnje;
    }

    /**
     * Postavlja godinu proizvodnje vozila.
     * Godina ne sme biti null i mora biti između 1900 i 2100.
     *
     * @param godProizvodnje godina proizvodnje vozila
     * @throws IllegalArgumentException ako je godina null ili van opsega 1900-2100
     */
    public void setGodProizvodnje(Integer godProizvodnje) {
        if (godProizvodnje == null || godProizvodnje < 1900 || godProizvodnje > 2100) {
            throw new IllegalArgumentException("Godina proizvodnje mora biti između 1900 i 2100.");
        }
        this.godProizvodnje = godProizvodnje;
    }

    /**
     * Vraća tip goriva.
     *
     * @return tip goriva
     */
    public String getTipGoriva() {
        return tipGoriva;
    }

    /**
     * Postavlja tip goriva.
     * Tip goriva ne sme biti null ili prazan.
     *
     * @param tipGoriva tip goriva vozila
     * @throws IllegalArgumentException ako je tip goriva null ili prazan
     */
    public void setTipGoriva(String tipGoriva) {
        if (tipGoriva == null || tipGoriva.isBlank()) {
            throw new IllegalArgumentException("Tip goriva ne sme biti null ili prazan.");
        }
        this.tipGoriva = tipGoriva;
    }

    /**
     * Vraća vlasnika vozila.
     *
     * @return vlasnik vozila
     */
    public Vlasnik getVlasnik() {
        return vlasnik;
    }

    /**
     * Postavlja vlasnika vozila.
     * Vlasnik može biti null.
     *
     * @param vlasnik vlasnik vozila
     */
    public void setVlasnik(Vlasnik vlasnik) {
        this.vlasnik = vlasnik;
    }

    /**
     * Vraća listu rezervacija vozila.
     *
     * @return lista rezervacija
     */
    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }

    /**
     * Postavlja listu rezervacija vozila.
     * Lista rezervacija ne sme biti null.
     *
     * @param rezervacije lista rezervacija
     * @throws IllegalArgumentException ako je lista rezervacija null
     */
    public void setRezervacije(List<Rezervacija> rezervacije) {
        if (rezervacije == null) {
            throw new IllegalArgumentException("Lista rezervacija ne sme biti null.");
        }
        this.rezervacije = rezervacije;
    }
}