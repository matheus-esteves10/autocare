package org.example.controller;

import org.example.dto.PessoaDto;
import org.example.dto.VeiculoDto;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.model.informacoesPessoais.Pessoa;
import org.example.model.informacoesPessoais.Veiculo;
import org.example.service.Service;
import org.example.service.veiculo.VeiculoService;
import org.example.service.veiculo.VeiculoServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/veiculo")
public class VeiculoController {

    private final VeiculoService veiculoService = VeiculoServiceFactory.create();

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(VeiculoDto input) throws UnsupportedServiceOperationException {
        if (input.id() == null) {
            try {
                Veiculo veiculo = (Veiculo) this.veiculoService.create(new Veiculo(null, input.marca(), input.modelo(), input.ano(), input.documentoVeiculo(), input.placaVeiculo(), input.idPessoa()));
                return Response
                        .status(Response.Status.CREATED)
                        .entity(veiculo)
                        .build();
            } catch (SQLException | NotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "erro inesperado ao tentar inserir veiculo: " + e)).build();
            }

        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(
                            Map.of(
                                    "mensagem",
                                    "esse método só permite a criação de novos veiculos"))
                    .build();
        }
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(Response.Status.OK)
                .entity(this.veiculoService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        try {
            return Response.status(Response.Status.OK)
                    .entity(this.veiculoService.findById(id)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long idPessoa, VeiculoDto input){
        try {
            Veiculo updated = (Veiculo) this.veiculoService.update(new Veiculo(input.id(), input.marca(), input.modelo(), input.ano(), input.documentoVeiculo(), input.placaVeiculo(), idPessoa));
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem","erro inesperado ao tentar atualizar veiculo" + s)).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id")Long id){
        try {
            this.veiculoService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem","erro inesperado ao tentar deletar veiculo")).build();
        }
    }
}
