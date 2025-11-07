package br.com.juadsa.regional.application.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Regional {

    private String id;
    private String nome;
    private String sigla;
    private String descricao;
    private Integer ordem;

    public static Regional create(String nome, String sigla, String descricao, Integer ordem) {
        if (nome == null || sigla == null || descricao == null || ordem == null) {
            throw new IllegalArgumentException("Faltando parametros de dados");
        }

        Regional regional = new Regional();

        regional.setNome(nome);
        regional.setSigla(sigla);
        regional.setDescricao(descricao);
        regional.setOrdem(ordem);

        return regional;
    }

    public static Regional create(String id, String nome, String sigla, String descricao, Integer ordem) {
        if (id == null || nome == null || sigla == null || descricao == null || ordem == null) {
            throw new IllegalArgumentException("Faltando parametros de dados");
        }

        Regional regional = new Regional();

        regional.setId(id);
        regional.setNome(nome);
        regional.setSigla(sigla);
        regional.setDescricao(descricao);
        regional.setOrdem(ordem);

        return regional;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }
}
