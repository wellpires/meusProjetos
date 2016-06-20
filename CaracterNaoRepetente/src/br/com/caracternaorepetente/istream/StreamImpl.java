package br.com.caracternaorepetente.istream;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class StreamImpl implements IStream {

    private String input = "";
    private int contador = -1;

    public StreamImpl(String input) {
        this.contador = -1;
        this.input = input;
    }

    public StreamImpl() {
    }
    
    @Override
    public char getNext() {
        return input.charAt(contador);
    }

    @Override
    public boolean hasNext() {
        if (contador == input.length() - 1) {
            return false;
        } else {
            contador++;
            return true;
        }
    }

}
