package at.htl.rest;

import at.htl.model.Contract;
import at.htl.model.Grave;
import at.htl.model.Graveyard;
import at.htl.model.GraveyardKeeper;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/contract")
@ApplicationScoped
public class ContractEndpoint {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("offset")@DefaultValue("0")int offset, @QueryParam("limit")@DefaultValue("10")int limit){
        PanacheQuery<Contract> contracts = Contract.findAll();
        contracts.page(Page.of(offset,limit));
        return Response.ok().entity(contracts.list()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getGraveyardsForGraveyardkeeper(@PathParam("id")long graveyardkeeperId){
        GraveyardKeeper keeper = GraveyardKeeper.findById(graveyardkeeperId);
        List<Contract> contracts = Contract.list("graveyardKeeper",keeper);
        List<Grave> graves = new ArrayList<>();
        for (Contract contract:contracts) {
            List<Grave> result = Grave.list("graveyard",contract.graveyard);
            graves.addAll(result);
        }
        return Response.ok().entity(graves).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/salary")
    public Response getSalaryToGive(){
        int sum = 0;
        List<Contract> contracts =  Contract.listAll();
        for (Contract con: contracts) {
            sum += con.salary;
        }
        return Response.ok().entity(sum).build();
    }

}
