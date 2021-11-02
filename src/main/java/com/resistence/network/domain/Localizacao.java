package com.resistence.network.domain;

import javax.persistence.*;

@Entity
@Table(name = "localizacao", schema = "db")
public class Localizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oid_localizacao")
    private Long oidLocalizacao;

    @Column(name = "latitude", nullable = false)
    private int latitude;

    @Column(name = "longitude", nullable = false)
    private int longitude;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oid_rebelde")
    private Rebelde oidRebelde;

    public Long getOidLocalizacao() { return oidLocalizacao; }

    public void setOidLocalizacao(Long oidLocalizacao) { this.oidLocalizacao = oidLocalizacao; }

    public int getLatitude() { return latitude; }

    public void setLatitude(int latitude) { this.latitude = latitude; }

    public int getLongitude() { return longitude; }

    public void setLongitude(int longitude) { this.longitude = longitude; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public Rebelde getOidRebelde() { return oidRebelde; }

    public void setOidRebelde(Rebelde oidRebelde) { this.oidRebelde = oidRebelde; }

}