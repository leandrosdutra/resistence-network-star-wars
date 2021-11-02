package com.resistence.network.application.dto.request;

import com.resistence.network.domain.enums.GeneroEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

public class RebeldeRequest {

    @Size(min=1, max=50, message = "Nome do rebelde deve possuir no máximo 50 caracteres")
    @NotEmpty(message = "Nome do rebelde obrigatório")
    private String nome;
    @Positive(message = "Idade do rebelde deve ser maior que zero")
    private int idade;
    private GeneroEnum genero;
    private LocalizacaoRequest localizacao;
    private List<ItemRequest> inventario;

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public int getIdade() { return idade; }

    public void setIdade(int idade) { this.idade = idade; }

    public GeneroEnum getGenero() { return genero; }

    public void setGenero(GeneroEnum genero) { this.genero = genero; }

    public LocalizacaoRequest getLocalizacao() { return localizacao; }

    public void setLocalizacao(LocalizacaoRequest localizacao) { this.localizacao = localizacao; }

    public List<ItemRequest> getInventario() { return inventario; }

    public void setInventario(List<ItemRequest> inventario) { this.inventario = inventario; }

    @Override
    public String toString() {
        return "RebeldeRequest{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", genero=" + genero +
                ", localizacao=" + localizacao +
                ", inventario=" + inventario +
                '}';
    }
}