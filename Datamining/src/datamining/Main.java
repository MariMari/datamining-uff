/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datamining;
import java.io.*;
import static datamining.Utils.*;
/**
 *
 * @author MariMari
 * @author igorg
 * 
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    public static String getParameter (String fileName) {
         //metodo que le do fileName qual parametro utilizar para a classificação
        try {
            String classe = null;
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            while (in.ready()) {
                classe = in.readLine();
            }
            in.close();
            return classe;
        } catch (IOException e) {
            System.out.println("Erro ao ler parametro!");
            return null;
        }
    }
    
    public static void main(String[] args) {
        try {
            // Avaliando as opcoes passadas ao classificador
            String trainingFileName = getOption("t", args);
//            String instanceFileName = getOption("i", args);
//            String classFileName = getOption("c", args);

            DataBase dataBase = new DataBase(trainingFileName);

//            String register = getParameter(classFileName);
//            String classe = getParameter(instanceFileName);
//            
//            Example client = new Example (register + ",-"); /* gambiarra! A entrada nao possui classe,
//                                                    logo causa erro na criaçao do objeto Example!
//                                                   */      
//            client.toString();
            System.out.println(dataBase.toString());

        } catch (Exception e) {
            System.out.println("O seguinte erro ocorreu: " + e.getMessage());
        }        
    }

}
