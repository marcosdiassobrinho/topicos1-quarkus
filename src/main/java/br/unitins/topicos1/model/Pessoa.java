package br.unitins.topicos1.model;

import javax.persistence.*;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Data;

@Entity
@Data
public class Pessoa extends PanacheEntity {
    private String cpf;
    @Column(length = 80)
    private String nome;


}
