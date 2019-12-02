package at.htl;

import at.htl.model.Graveyard;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.json.JsonArray;
import javax.json.JsonValue;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/graveyard")
@RegisterRestClient
public interface GraveyardService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    JsonArray getAll(@QueryParam("offset")@DefaultValue("0")int offset,@QueryParam("limit")@DefaultValue("10")int limit);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    JsonValue getById(@PathParam("id")Long id);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    JsonArray updateGraveyard(Graveyard g);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    JsonArray createGraveyard(Graveyard g);

    @DELETE
    @Path("{id}")
    @Transactional
    JsonArray deleteGraveyard(@PathParam("id")Long id);
}
