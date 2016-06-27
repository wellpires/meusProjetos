package br.projetojpa.models;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public enum TipoCombustivel {
    

    ALCOOL("Álcool"),
    GASOLINA("Gasolina"),
    DIESEL("Diesel"),
    BICOMBUSTIVEL("Bicombustível");

    private final String tipoCombustivel;
    
    private TipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }
    
    public String getTipoCombustivel(){
        return tipoCombustivel;
    }
    
}
