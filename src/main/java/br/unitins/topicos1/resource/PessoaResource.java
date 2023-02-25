package br.unitins.topicos1.resource;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.unitins.topicos1.model.Pessoa;
import org.hibernate.TransactionException;

@Path("/pessoas")
public class PessoaResource {

    @GET
    public List<Pessoa> getAll() {

        // seleciona todas as pessoas do banco de dados
        return Pessoa.findAll().list();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Pessoa insert(Pessoa pessoa) {

        // adiciona a pessoa no banco de dados
        pessoa.persist();

        return pessoa;
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Pessoa update(@PathParam("id") Long id, Pessoa pessoa) {
        Pessoa p = Pessoa.findById(id);
        p.setCpf(pessoa.getCpf());
        p.setNome(pessoa.getNome());
        return pessoa;
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response findById(@PathParam("id") Long id) throws EntityNotFoundException, TransactionException {
        Pessoa pessoa = Pessoa.findById(id);
        try {
            if (pessoa != null) {
                return Response.ok(pessoa).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Objeto não encontrado").build();
            }
        } catch (TransactionException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response delete(@PathParam("id") Long id) throws EntityNotFoundException, TransactionException {
        Pessoa pessoa = Pessoa.findById(id);
        try {
            if (pessoa != null) {
                pessoa.delete();
                return Response.status(Response.Status.NO_CONTENT).build();
            }
            return Response.status(Response.Status.NOT_FOUND).entity("Objeto não encontrado").build();

        } catch (TransactionException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();

        }
    }

}
