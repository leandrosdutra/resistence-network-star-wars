package com.resistence.network.application.dto;

public class LocalizacaoDto {

    private Long oid;
    private int latitude;
    private int longitude;
    private String nome;

    public Long getOid() { return oid; }

    public void setOid(Long oid) { this.oid = oid; }

    public int getLatitude() { return latitude; }

    public void setLatitude(int latitude) { this.latitude = latitude; }

    public int getLongitude() { return longitude; }

    public void setLongitude(int longitude) { this.longitude = longitude; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    @Override
    public String toString() {
        return "LocalizacaoDto{" +
                "oid=" + oid +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", nome='" + nome + '\'' +
                '}';
    }
}