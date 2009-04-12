/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classificador;
import java.io.*;
import static classificador.Utils.*;
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
    
    public static String getParametro (String arquivo) {
         //metodo que le do arquivo qual parametro utilizar para a classificação
        try {
            String classe = null;
            BufferedReader in = new BufferedReader(new FileReader(arquivo));
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
            String trainingFileName = getOption("t", args);
            String instanceFileName = getOption("i", args);
            String classFileName = getOption("c", args);

            BaseDados dados = new BaseDados(trainingFileName);

            String cliente = getParametro(classFileName);
            String classe = getParametro(instanceFileName);
            
            Perfil client = new Perfil (cliente+",-"); /* gambiarra! A entrada nao possui classe,
                                                    logo causa erro na criaçao do objeto Perfil!
                                                   */      
            client.toString();
            System.out.println(dados.toString());

        } catch (Exception e) {
            System.out.println("O seguinte erro ocorreu: " + e.getMessage());
        }        
    }

}
