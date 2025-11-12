package br.com.juadsa.louvor.application.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Louvor {

    private String id;
    private String nome;
    private String cantor;
    private String idRegional;
    private String letra;

    public static Louvor create(String nome, String cantor, String idRegional, String letra) {
        if(nome == null || cantor == null || idRegional == null || letra == null) {
            throw new IllegalArgumentException("Faltando parametros de dados");
        }

        Louvor louvor = new Louvor();

        louvor.setNome(nome);
        louvor.setCantor(cantor);
        louvor.setIdRegional(idRegional);
        louvor.setLetra(letra);

        return louvor;
    }

    public static Louvor create(String id, String nome, String cantor, String idRegional, String letra) {
        if(id == null || nome == null || cantor == null || idRegional == null || letra == null) {
            throw new IllegalArgumentException("Faltando parametros de dados");
        }

        Louvor louvor = new Louvor();

        louvor.setId(id);
        louvor.setNome(nome);
        louvor.setCantor(cantor);
        louvor.setIdRegional(idRegional);
        louvor.setLetra(letra);

        return louvor;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        if(nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não informado");
        }

        this.nome = nome;
    }

    public void setCantor(String cantor) {
        this.cantor = cantor;
    }

    public void setIdRegional(String idRegional) {
        this.idRegional = idRegional;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }
}
