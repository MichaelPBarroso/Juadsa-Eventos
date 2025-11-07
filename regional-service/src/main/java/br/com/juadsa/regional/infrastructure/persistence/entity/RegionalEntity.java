package br.com.juadsa.regional.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="endereco")
//@Document(collation = "regional")
public class RegionalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nome;

    private String sigla;

    private String descricao;

    private Integer ordem;

}
