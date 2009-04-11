/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classificador;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author MariMari
 */
public class BaseDados {
    
    private ArrayList<Perfil> dados;
   
    
    BaseDados(String arquivo) {
        dados = new ArrayList();
        this.montarBaseDados(arquivo);
    }
    
       
    public void setEntrada (Perfil entrada) {
        dados.add(entrada);
    }
    
    public Perfil getEntrada (int index) {
        return (Perfil) dados.get(index);
    }
    
     public void montarBaseDados (String arquivo) {
        //copia e trata as entradas da base de dados
        try {
            BufferedReader in = new BufferedReader(new FileReader(arquivo));
            String linha = null;
            while (in.ready()) {
                linha = in.readLine();
                Perfil aux = new Perfil(linha);
                this.setEntrada(aux);                
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Erro ao copiar base de dados!");
        }    
    }
    
   public String toString () {
       String saida = null;
       for (int i=0; i<dados.size(); i++) {
           saida = saida + this.getEntrada(i).toString() + "\n";
       }
       return saida;
   }
    

}
