package br.projetojpa.services;

import br.projetojpa.models.Marca;
import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public interface IMarcaService {
    
    public void gravar(Marca marca) throws Exception;
    public void excluir(Marca marca) throws Exception;
    public void alterar(Marca marca) throws Exception;
    
    public List<Marca> listarMarcas (Class marca) throws Exception;
}
