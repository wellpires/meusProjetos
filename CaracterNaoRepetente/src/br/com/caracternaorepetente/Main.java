package br.com.caracternaorepetente;

import br.com.caracternaorepetente.istream.IStream;
import br.com.caracternaorepetente.istream.StreamImpl;
import javax.swing.JOptionPane;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class Main {

    public static void main(String[] args) {

        String input;
        do {
            input = JOptionPane.showInputDialog(null, "Forneça uma cadeia de caracteres", "Caracter Repetido", JOptionPane.QUESTION_MESSAGE);
            if(input == null){
                System.exit(0);
            }
        } while (input.trim().length() == 0);

        IStream stream = new StreamImpl(input);
        IStream streamComparacao;

        int contador = 0;
        String caracterRepetido = "";

        while (stream.hasNext()) {
            char caracter = stream.getNext();
            streamComparacao = new StreamImpl(input);
            while (streamComparacao.hasNext()) {
                if (caracter == streamComparacao.getNext()) {
                    contador++;
                }
            }
            if (contador == 1) {
                caracterRepetido = String.valueOf(caracter);
                break;
            }
            contador = 0;
        }

        String msg = "Todos os caracteres se repetem";
        if (caracterRepetido.length() > 0) {
            msg = caracterRepetido;
        }
        JOptionPane.showMessageDialog(null, msg, "Caracter Repetido", JOptionPane.INFORMATION_MESSAGE);

    }
}
