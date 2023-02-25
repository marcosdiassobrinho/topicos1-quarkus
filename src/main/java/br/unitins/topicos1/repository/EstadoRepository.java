package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Estado;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EstadoRepository implements PanacheRepository<Estado> {

    public Estado findByName(String nome) {
        return  find("nome", nome).firstResult();
    }
}
