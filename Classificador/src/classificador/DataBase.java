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
 * @author igorg
 */
public class DataBase {
    
    private ArrayList<Example> data;
   
    
    DataBase(String fileName) {
        data = new ArrayList();
        this.createDataBase(fileName);
    }
       
    public void setIn (Example in) {
        data.add(in);
    }
    
    public Example getIn (int index) {
        return (Example) data.get(index);
    }
    
     public void createDataBase (String fileName) {
        //copia e trata as entradas da base de data
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String lineRead = null;
            while (in.ready()) {
                lineRead = in.readLine();
                Example aux = new Example(lineRead);
                this.setIn(aux);                
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler base de dados!");
        }
    }
    
   public String toString () {
       String out = null;
       for (int i = 0; i < data.size(); i++) {
           out = out + this.getIn(i).toString() + "\n";
       }
       return out;
   }
    

}
