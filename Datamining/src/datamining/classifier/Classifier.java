/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * Obs.: Nessa classe, atencao com as conversoes entre inteiros e doubles.
 */

package datamining.classifier;

import datamining.*;
import java.util.LinkedList;
import static datamining.Utils.*;

/**
 *
 * @author igor
 */
public class Classifier {
    
    private TreeNode root;
    double eParameter;
    
    /** Construtor vazio */
    public Classifier() {}
    
    private double[][] countClass(Attribute attr, DataBase db) throws Exception {
        double[][] classCount =
            new double[attr.cardinality()][db.numClasses()];
        
        for (int i = 0; i < db.numExamples(); i++) {
            Example example = db.example(i);
            int attrValue = example.getAttrValue(attr.getIndex()).intValue();
            int classValue = example.getClassValue().intValue();
            classCount[attrValue][classValue]++;
        }
        
        return classCount;
    }
    
    private DataBase[] createSplits(Attribute attr, DataBase db) throws Exception {
        DataBase[] splits = new DataBase[attr.cardinality()];
        
        for (int i = 0; i < splits.length; i++) {
            splits[i] = new DataBase();
        }
        
        for (int i = 0; i < db.numExamples(); i++) {
            Example example = db.example(i);
            int attrValue = example.getAttrValue(attr.getIndex()).intValue();
            splits[attrValue].addExample(example);
        }
        
        return splits;
    }
    
    private Double attributeInfo(Attribute attr, double[][] classCount) {
        double attrInfo = 0;
        
        for (int i = 0; i < classCount.length; i++) {
            LinkedList<Double> probs = new LinkedList<Double>();
            for (int j = 0; j < classCount[].length; j++) {
                double prob = (classCount[i][j] / splits[i].numExamples());
                probs.add(new Double(prob));
            }
            double vEntropy = entropy(probs);
            attrInfo += (splits[i].numExamples() / numExamples) * vEntropy;
        }
        
        return null;
    }
    
    /**
     * Metodo para construir a arvore de decisao em funcao do conjunto de
     * treinamento passado como parametro.
     * 
     * @param trainingSet um conjunto com exemplos de treinamento 
     */
    public void buildClassifier(DataBase trainingSet) throws Exception {
        
        // Constroi apenas para classes discretas
        if (!trainingSet.classAttribute().isDiscrete()) {
            throw new Exception("Classificador nao pode ser construido para " +
                                "classes continuas!");
        }
        
        // Declaracao de variaveis
        double numExamples = trainingSet.numExamples();
        double numAttr = trainingSet.numAttributes() - 1;
        int numClasses = trainingSet.numClasses();
        
        // Suponho o nivel de informacao inicial o maior possivel e escolho o
        // primeiro atributo como separador.
        double info = 1;
        Attribute chosen = trainingSet.attribute(0);
        
        double[][] classCount = countClass(chosen, trainingSet);
        
        DataBase[] splits = createSplits(chosen, trainingSet);
        
        
        System.out.println(attrInfo);
        
        // Se a separacao for maior que o parametro de corte minimo
            // Compara os attributos e escolhe o que melhor separa as classes
            // Cria um no com esse attributo
        // Passa a analise do proximo no.    
    }
    
    /**
     * Retorna a classificacao de um exemplo de acordo com a arvore de decisao
     * construida.
     * 
     * @param  example o exemplo a ser classificado
     * 
     * @return a classe do exemplo representada por um Double
     */
    public Double classifyExample(Example example) {
        return null;
    }

}
