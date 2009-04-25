/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datamining;
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
    
    public Example example (int index) {
        return data.get(index);
    }
    
    public int getSize () {
        return data.size();
    }
    
    public void createDataBase (File file) throws Exception {
    //copia e trata as entradas da base de data
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String lineRead = null;
            data = new ArrayList<Example>();
            
            while ((lineRead = in.readLine()) != null) {
                Example example = new Example(lineRead);
                addExample(example);
            }
            
            in.close();
        } catch (FileNotFoundException e) {
            throw new Exception("O arquivo nao pode ser encontrado");
        } catch (IOException e) {
            throw new Exception("A leitura do arquivo nao pode ser realizada");
        }
    }
   
    /**
     * Retorna a representacao da base de dados em String. O formato e o mesmo
     * de uma base de dados formatada para ser lida.
     * 
     * @return a representacao da base de dados em formato de leitura
     */
    public String toDataBaseString() {
        String dataBase = null;
        for (int i = 0; i < data.size(); i++) {
            dataBase = dataBase + example(i).toRegisterString() + "\n";
        }
        return dataBase;
    }
    
    @Override
    public String toString () {
        String dataBase = null;
        for (int i = 0; i < data.size(); i++) {
            dataBase = dataBase + this.example(i).toString() + "\n";
        }
        return dataBase;
    }

}
