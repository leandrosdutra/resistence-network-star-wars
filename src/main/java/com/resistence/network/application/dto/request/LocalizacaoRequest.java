package com.resistence.network.application.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class LocalizacaoRequest {

    @Positive(message = "Latitude deve ser maior que zero")
    @NotEmpty(message = "Latitude obrigatória")
    private int latitude;
    @Positive(message = "Longitude deve ser maior que zero")
    @NotEmpty(message = "Longitude obrigatória")
    private int longitude;
    @Size(min=1, max=100, message = "Nome da área deve possuir no máximo 100 caracteres")
    @NotEmpty(message = "Nome da área obrigatório")
    private String nome;

    public int getLatitude() { return latitude; }

    public void setLatitude(int latitude) { this.latitude = latitude; }

    public int getLongitude() { return longitude; }

    public void setLongitude(int longitude) { this.longitude = longitude; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    @Override
    public String toString() {
        return "LocalizacaoRequest{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", nome='" + nome + '\'' +
                '}';
    }
}