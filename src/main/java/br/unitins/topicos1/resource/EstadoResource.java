package br.unitins.topicos1.resource;

import br.unitins.topicos1.model.Estado;
import br.unitins.topicos1.repository.EstadoRepository;
import org.hibernate.TransactionException;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/estados")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EstadoResource {
    @Inject
    EstadoRepository estadoRepository;

    @GET
    public List<Estado> getAll() {

        // seleciona todas as pessoas do banco de dados
        return estadoRepository.findAll().list();

    }

    @POST
    @Transactional
    public Response insert(Estado estado) {
        estadoRepository.persist(estado);
        return Response.ok(estado).build();

    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Estado update(@PathParam("id") Long id, Estado estado) {
        Estado e = estadoRepository.findById(id);
        e.setSigla(estado.getSigla());
        e.setNome(estado.getNome());
        return estado;
    }

    @GET
    @Path("/{id}")
    @Transactional
    public Response findById(@PathParam("id") Long id) throws EntityNotFoundException, TransactionException {
        Estado estado = estadoRepository.findById(id);
        try {
            if (estado != null) {
                return Response.ok(estado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Objeto não encontrado").build();
            }
        } catch (TransactionException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) throws EntityNotFoundException, TransactionException {
        Estado estado = estadoRepository.findById(id);
        try {
            if (estado != null) {
                estadoRepository.delete(estado);
                return Response.status(Response.Status.NO_CONTENT).build();
            }
            return Response.status(Response.Status.NOT_FOUND).entity("Objeto não encontrado").build();

        } catch (TransactionException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();

        }
    }

    @GET
    @Path("/search/{nome}")
    public Estado search(@PathParam("nome") String nome) {
        return estadoRepository.findByName(nome);

    }

    @GET
    @Path("/count")
    public Long search() {
        return estadoRepository.count();

    }
}
