package br.com.contatosWS.webService;

import br.com.contatosWS.modelo.Contato;
import br.com.contatosWS.modelo.Operadora;
import br.com.contatosWS.utils.JPADao;
import br.com.contatosWS.utils.JPAUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Wellington Gonçalves Pires
 */
@Path("contatoWS")
public class ContatoWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ContatoWS
     */
    public ContatoWS() {
        JPAUtil.getEntityManaged();
    }

    /**
     * Retrieves representation of an instance of br.com.contatosWS.webService.ContatoWS
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path(value = "buscarContatos")
    public Response buscarContatos() {
        try {
            List<Contato> lstContatos = (List<Contato>) JPADao.buscarVariosRegistros(Contato.class);
            Gson json = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
            return Response.ok(json.toJson(lstContatos)).header("Access-Control-Allow-Origin", "*").build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path(value = "buscarOperadoras")
    public Response buscarOperadoras(){
        try {
            List<Operadora> lstOperadoras = (List<Operadora>) JPADao.buscarVariosRegistros(Operadora.class);
            return Response.ok(new Gson().toJson(lstOperadoras)).header("Access-Control-Allow-Origin", "*").build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }
    
    /**
     * PUT method for updating or creating an instance of ContatoWS
     * @param content representation for the resource
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
