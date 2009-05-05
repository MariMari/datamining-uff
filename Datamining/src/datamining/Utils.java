/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datamining;

import java.util.LinkedList;
import static java.lang.Math.*;

/*/**
 * @author Fábio Gomes
 * @author Gabriel Baims
 * @author Marianna Portela
 * @author Igor Giusti
 */
public class Utils {
    
    /**
     * Retorna uma opcao sinalizada por uma flag  no array de opcoes.
     * 
     * @param flag    sinalizador da opcao desejada
     * @param options array com sinalizadores e opcoes
     * 
     * @return o valor da opcao desejada
     */
    public static String getOption(String flag, String[] options) throws Exception {
        if (options.length == 0) {
            throw new Exception("Nenhuma opcao foi definida!");
        }
        int i = 0;
        String readFlag = "";

        while ((i < options.length) && !(readFlag = options[i]).equals("-" + flag)) {
            i = i + 2;
        }

        if (!readFlag.equals("-" + flag)) {
            throw new Exception("A opcao " + flag + " nao pode ser encontrada!");
        }
        return options[i + 1];
    }
    
    /**
     * Efetua o calculo da entropia de Shannon para uma colecao de
     * probabilidades
     * 
     * @param probs colecao de probabilidades
     * 
     * @return o valor da entropia para as probabilidades passadas
     */
    public static Double entropy(Double[] probs) {
        double entropyValue = 0;
        for (int i = 0; i < probs.length; i++) {
            double px = probs[i].doubleValue();
            double log2 = 0;
            if (px != 0) {
                log2 = log(px) / log(2);
            }
            entropyValue += px * log2;
        }
        return Double.valueOf(-entropyValue);
    }

}
