package enderecows;

import com.google.gson.Gson;
import enderecows.model.Endereco;
import enderecows.services.IEnderecoService;
import enderecows.services.impl.EnderecoService;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

/**
 * REST Web Service
 *
 * @author Wellington Gonçalves Pires
 */
@Path("endereco")
public class EnderecoWS {

    private IEnderecoService enderecoService = null;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EnderecoWS
     */
    public EnderecoWS() {
    }

    @GET
    @Produces(("application/json; charset=UTF-8"))
    @Path("listarEnderecos/get")
    public String listarEnderecos() {
        try {
            enderecoService = new EnderecoService();
            List<Endereco> lstEnderecos = enderecoService.listarEnderecos();
            return new Gson().toJson(lstEnderecos);
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    @POST
    @Consumes("application/json")
    @Path("deletarRegistro/post")
    public String deletarEndereco(String content) {
        try {
            enderecoService = new EnderecoService();
            Endereco e = new Gson().fromJson(content, Endereco.class);
            enderecoService.excluir(e);
        } catch (Exception ex) {
            return ex.getMessage();
        }
        return "";
    }

    @POST
    @Produces("application/json; charset=UTF-8")
    @Path("buscarEndereco/post")
    public String buscarEndereco(String conteudo) {
        try {
            enderecoService = new EnderecoService();
            Endereco e = new Gson().fromJson(conteudo, Endereco.class);
            if (e.getCep() == null || e.getCep() == 0) {
                e = enderecoService.buscarEndereco(e);
            } else if (e.getCep() != null || e.getCep() > 0) {
                e = enderecoService.buscarEnderecoPorCep(e);
            }

            return new Gson().toJson(e);
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    @POST
    @Consumes("application/json")
    @Path("inserirRegistro/post")
    public String inserirEndereco(String content) {
        try {
            enderecoService = new EnderecoService();
            Endereco e = new Gson().fromJson(content, Endereco.class);
            enderecoService.inserir(e);
        } catch (Exception ex) {
            return ex.getMessage();
        }
        return "";
    }

    @PUT
    @Consumes("application/json")
    @Path("alterarRegistro/put")
    public String alterarEndereco(String content) {
        try {
            enderecoService = new EnderecoService();
            Endereco e = new Gson().fromJson(content, Endereco.class);
            enderecoService.alterar(e);
        } catch (Exception ex) {
            return ex.getMessage();
        }
        return "";
    }
}
