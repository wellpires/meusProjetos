package br.projetojpa.util;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public abstract class PaginaConstants {
    
    private static final String FACES_REDIRECT = "";
//    private static final String FACES_REDIRECT = "?faces-redirect=true";
    
    protected static final String DETALHAR_REGISTRO = "detalharRegistro.xhtml" + FACES_REDIRECT;
    protected static final String CADASTRAR_REGISTRO = "index.xhtml" + FACES_REDIRECT;
    protected static final String NOVO_REGISTRO = "novoRegistro.xhtml" + FACES_REDIRECT;
    protected static final String NOVA_MARCA = "novaMarca.xhtml" + FACES_REDIRECT;
    protected static final String NOVO_ACESSORIO = "novoAcessorio.xhtml" + FACES_REDIRECT;
}
