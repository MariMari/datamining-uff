/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datamining;
import datamining.classifier.Classifier;
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
    public static void main(String[] args) {
        try {
              
            // Avaliando as opcoes passadas ao classificador
            String trainingFileName = getOption("t", args);
            String testFileName = getOption("T", args);
            String resultFileName = getOption("r", args);

            // Criando a arvore
            DataBase trainingBase = new DataBase(trainingFileName);
            
            Classifier classifier = new Classifier();
            classifier.buildClassifier(trainingBase);
            
            // Classificando
            DataBase testBase = new DataBase(testFileName);
            DataBase resultBase = new DataBase();
            for (int i = 0; i < testBase.numExamples(); i++) {
                Example example = testBase.example(i);
                Double klass = classifier.classifyExample(example);
                resultBase.addExample(example);
            }
            
//            String register = getParameter(classFileName);
//            String classe = getParameter(instanceFileName);
//            
//            Example client = new Example (register + ",-"); /* gambiarra! A entrada nao possui classe,
//                                                    logo causa erro na criaçao do objeto Example!
//                                                   */      
//            client.toString();
            System.out.println(trainingBase.toString());
        } catch (Exception e) {
            System.out.println("O seguinte erro ocorreu: " + e.getMessage());
        }        
    }

}
