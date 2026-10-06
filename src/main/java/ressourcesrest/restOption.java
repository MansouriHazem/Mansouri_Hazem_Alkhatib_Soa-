package ressourcesrest;

import entities.Option;
import metiers.OptionBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("options")
public class restOption {
   public OptionBusiness optB= new OptionBusiness();

   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response getOptions(@QueryParam("domaine") String d){
      List<Option> l = new ArrayList<Option>();

      if(d != null){
         l = optB.getOptionsByDomaine(d);
      }
      else{
         l = optB.getListeOptions();
      }

      return Response.status(200).entity(l).build();
   }

   @GET
   @Path("/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getOption(@PathParam("id") int id){
      Option op = optB.getOptionByCode(id);

      if(op != null){
         return Response.status(200).entity(op).build();
      }

      return Response.status(404).build();
   }

   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   public Response addOption(Option op){
      if(optB.addOption(op)){
         return Response.status(201).build();

      }
      return Response.status(400).build();
   }

   @DELETE
   @Produces(MediaType.TEXT_PLAIN)
   @Path("/{id}")
   public Response deleteOption(@PathParam("id") int id){
      if(optB.deleteOption(id)){
         return Response.status(201).entity("supprimé avec succes").build();
      }
      return Response.status(400).entity("Error").build();
   }
   @PUT
   @Path("/{id}")
   @Consumes(MediaType.APPLICATION_JSON)
   public Response updateOption(@PathParam("id") int id, Option op){
      if(optB.updateOption(id, op)){
         return Response.status(200).build();
      }
      return Response.status(404).build();
   }

}




