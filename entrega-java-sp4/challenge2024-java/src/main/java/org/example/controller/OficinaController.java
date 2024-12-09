package org.example.controller;

import org.example.dto.OficinaDto;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.model.oficina.Oficina;
import org.example.service.Service;
import org.example.service.oficina.OficinaServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;


@Path("/oficina")
public class OficinaController {

    private final Service<Oficina> oficinaService = OficinaServiceFactory.create();

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(Response.Status.OK)
                .entity(this.oficinaService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        if (id == null || id <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "ID inválido fornecido"))
                    .build();
        }
        try {
            Oficina oficina = (Oficina) this.oficinaService.findById(id);
            return Response.status(Response.Status.OK)
                    .entity(oficina)
                    .build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("mensagem", "Oficina não encontrada para o ID fornecido"))
                    .build();
        }
    }



    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(OficinaDto input) throws UnsupportedServiceOperationException {
        if (input.id() == null) {
            try {
                Oficina oficina = (Oficina) this.oficinaService.create(new Oficina(null, input.nomeOficina(), input.cidadeOficina(),input.enderecoOficina(),input.cnpjOficina()));
                return Response
                        .status(Response.Status.CREATED)
                        .entity(oficina)
                        .build();
            } catch (SQLException | NotSavedException e){
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem","erro inesperado ao tentar inserir oficina: " + e)).build();
            }

        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(
                            Map.of(
                                    "mensagem",
                                    "esse método só permite a criação de novas oficinas"))
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, OficinaDto input){
        try {
            Oficina updated = (Oficina) this.oficinaService.update(new Oficina(id, input.nomeOficina(), input.cidadeOficina(),input.enderecoOficina(),input.cnpjOficina()));
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem","erro inesperado ao tentar atualizar oficina")).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id")Long id){
        try {
            this.oficinaService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem","erro inesperado ao tentar deletar oficina")).build();
        }
    }
}
