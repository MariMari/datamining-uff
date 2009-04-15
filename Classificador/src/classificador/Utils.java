/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classificador;

/**
 *
 * @author igor
 */
public class Utils {
    
    public static String getOption(String flag, String[] options) throws Exception {
        int i = 0;
        String readFlag = options[i];
        
        while ((i < options.length) && !readFlag.equals(flag)) {
             i = i + 2;
             readFlag = options[i];
        }
        
        if (!readFlag.equals(flag))
            throw new Exception("A opcao " + flag + " nao pode ser encontrada!");
        
        return options[i + 1];
    }

}
