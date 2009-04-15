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
   
    
    public DataBase(String fileName) throws Exception {
        File file = new File(fileName);
        this.createDataBase(file);
    }

    public DataBase(File file) throws Exception {
        this.createDataBase(file);
    }
       
    public void addExample (Example example) {
        data.add(example);
    }
    
    public Example getExample (int index) {
        return (Example) data.get(index);
    }
    
    public void createDataBase (File file) throws Exception {
    //copia e trata as entradas da base de data
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String lineRead = null;
            
            while (in.ready()) {
                lineRead = in.readLine();
                Example example = new Example(lineRead);
                this.addExample(example);
            }
            
            in.close();
        } catch (FileNotFoundException e) {
            throw new Exception("O arquivo nao pode ser encontrado");
        } catch (IOException e) {
            throw new Exception("A leitura do arquivo nao pode ser realizada");
        }
    }
   
    @Override
    public String toString () {
        String out = null;
        for (int i = 0; i < data.size(); i++) {
            out = out + this.getExample(i).toString() + "\n";
        }
        return out;
    }

}
