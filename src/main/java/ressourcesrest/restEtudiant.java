package ressourcesrest;

import entities.Etudiant;
import entities.EtudiantList;
import metiers.EtudiantBusiness;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import entities.Option;
import metiers.OptionBusiness;
@Path("etudiants")
public class restEtudiant {

    public EtudiantBusiness etuB = new EtudiantBusiness();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEtudiant(Etudiant e){
        if(etuB.addEtudiant(e)){
            return Response.status(200).build();
        }
        return Response.status(404).build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEtudiants(){
        List<Etudiant> l = etuB.getAllEtudiants();

        return Response.status(200).entity(l).build();
    }
    @GET
    @Path("/{identifiant}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEtudiant(@PathParam("identifiant") String identifiant){
        Etudiant e = etuB.getEtudiantByIdentifiant(identifiant);

        if(e != null){
            return Response.status(200).entity(e).build();
        }

        return Response.status(404).build();
    }
    @DELETE
    @Path("/{identifiant}")
    public Response deleteEtudiant(@PathParam("identifiant") String identifiant){
        if(etuB.deleteEtudiant(identifiant)){
            return Response.status(204).build();
        }

        return Response.status(404).build();
    }
    @PUT
    @Path("/{identifiant}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEtudiant(@PathParam("identifiant") String identifiant, Etudiant e){
        if(etuB.updateEtudiant(identifiant, e)){
            return Response.status(200).build();
        }

        return Response.status(404).build();
    }
    @GET
    @Path("/option")
    @Produces(MediaType.APPLICATION_XML)
    public Response getEtudiantsByOption(@QueryParam("codeOption") int codeOption) {

        OptionBusiness optB = new OptionBusiness();

        Option option = optB.getOptionByCode(codeOption);

        if (option != null) {

            List<Etudiant> l = etuB.getEtudiantsByOption(option);

            EtudiantList etudiantList = new EtudiantList(l);

            return Response.status(200)
                    .entity(etudiantList)
                    .build();
        }

        return Response.status(404).build();
    }
}
