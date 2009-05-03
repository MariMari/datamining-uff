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
                
                // Copiando o exemplo
                Example newExample = new Example(resultBase);
                for (int j = 0; j < testBase.numAttributes(); j++) {
                    newExample.setAttrValue(j, example.getAttrValue(j));
                }
                
                newExample.setClassValue(klass);
                
                resultBase.addExample(example);
            }
            
            System.out.println(testBase.toString());
            System.out.println(resultBase.toString());
        } catch (Exception e) {
            System.out.println("O seguinte erro ocorreu: " + e.getMessage());
        }        
    }

}
