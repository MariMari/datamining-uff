/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datamining;

/**
 *
 * @author igor
 */
public class Utils {

    /**
     * Retorna a representacao numerica de um valor de atributo nominal baseada
     * no dominio desse atributo.
     * 
     * @param   domain o dominio do atributo
     * @param   value  o valor do atributo
     * @return         a representacao numerica de value.
     */
    public static int attrStringToNumerical(String[] domain, String value) throws Exception {
        int intValue = 0;

        //Testa se o valor do atributo e desconhecido
        if (value.equals("?")) {
            intValue = -1;
        } else {
            while (intValue < domain.length && !domain[intValue].equals(value))
                intValue++;
            if (intValue >= domain.length)
                throw new Exception("Valor " + value + " nao pertence ao dominio." );
        }

        return intValue;
    }

    /**
     * Retorna o valor do dominio a partir da representacao numerica de um
     * atributo nominal.
     * 
     * @param   domain o dominio do atributo
     * @param   value  a representacao numerica do valor do atributo
     * @return         o valor mapeado no dominio por value.
     */
    public static String attrNumericalToString(String[] domain, int value) {
        String strValue = "?";
        if (value >= 0 && value < domain.length)
            strValue = domain[value];
        
        return strValue;
    }

    public static String getOption(String flag, String[] options) throws Exception {
        int i = 0;
        String readFlag = options[i];

        while ((i < options.length) && !readFlag.equals("-" + flag)) {
            readFlag = options[i];
            i = i + 2;
        }

        if (!readFlag.equals("-" + flag)) {
            throw new Exception("A opcao " + flag + " nao pode ser encontrada!");
        }
        return options[i + 1];
    }

    public static double entropy(Attribute attribute) throws Exception {
        int i,size;
        double probability,sum;

        sum = 0;
        size = attribute.getDomainSize();

        for(i = 0; i < size; i++)
        {
            probability = probability(attribute.getDomainValue(Double.valueOf(i)));
            sum = sum + (probability * log(probability,2));
        }

        return ((-1) * sum);

    }

    private static double probability(String domainValue){
        return 0;
    }

    private static double log(double number, int base){
        return(Math.log10(number)/Math.log10(base));
    }
}
