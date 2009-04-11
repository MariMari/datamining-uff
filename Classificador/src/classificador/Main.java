/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classificador;
import java.io.*;
/**
 *
 * @author MariMari
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
        
       BaseDados dados = new BaseDados("C:/Users/MariMari/Desktop/desafio1/base.txt");
        
       String classe = getParametro("C:/Users/MariMari/Desktop/desafio1/classe.txt");
       String cliente = getParametro("C:/Users/MariMari/Desktop/desafio1/reg.txt");
       Perfil client = new Perfil (cliente+",-"); /* gambiarra! A entrada nao possui classe,
                                                    logo causa erro na criaçao do objeto Perfil!
                                                   */      
       client.toString();
       System.out.println(dados.toString());
        
    }

}
