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
