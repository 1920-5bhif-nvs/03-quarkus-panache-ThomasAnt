package at.htl.rest;

import at.htl.model.Graveyard;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

@Path("/graveyard")
@ApplicationScoped
public class GraveyardEndpoint {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("offset")@DefaultValue("0")int offset,@QueryParam("limit")@DefaultValue("10")int limit){
        PanacheQuery<Graveyard> graveyards = Graveyard.findAll();
        graveyards.page(Page.of(offset,limit));
        return Response.ok().entity(graveyards.list()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getById(@PathParam("id")Long id){

        return Response.ok().entity(Graveyard.findById(id)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateGraveyard(Graveyard g){
        if(g != null){
            Graveyard graveyard = Graveyard.findById(g.id);
            graveyard.location = g.location;
            graveyard.name = g.name;
            graveyard.numberOfGraves = g.numberOfGraves;
            return Response.ok().build();
        }
        return Response.status(404).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createGraveyard(Graveyard g){
        Graveyard.persist(g);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteGraveyard(@PathParam("id")Long id){
        //Graveyard.delete("id",id);
        Graveyard.findById(id).delete();
        return Response.ok().build();
    }

}
