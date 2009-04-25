/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datamining.classifier;
import datamining.*;

/**
 *
 * @author MariMari
 * A classe calcula a porcentagem das ocorrencias dos atributos
 * na basa de dados ou em um subconjunto da base de dados.
 */
public class Calculator {
    
   
    public static DataBase filtrateDataBase (DataBase data, int dominio, int valor) {
        
        DataBase aux =null;
        int index = 0;
        while (index<=data.getSize()) {
            aux.addExample(data.example(index));
            index++;            
        }    
        return aux;
    }

    
    

}