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
        
        // Suponho a entropia inicial a maior possivel e escolho o primeiro
        // atributo como separador.
        double iEntropy = 1;
        Attribute chosen = trainingSet.attribute(0);
        
        // Calculo a entropia para o no escolhido
        DataBase[] splits = new DataBase[chosen.domainCardinality()];
        for (int i = 0; i < splits.length; i++) {
            splits[i] = new DataBase();
        }
        
        double[][] classCount =
            new double[chosen.domainCardinality()][numClasses];
        
        for (int i = 0; i < numExamples; i++) {
            Example example = trainingSet.example(i);
            int attrValue = example.getAttrValue(chosen.getIndex()).intValue();
            int classValue = example.getClassValue().intValue();
            splits[attrValue].addExample(example);
            classCount[attrValue][classValue]++;
        }
        
        LinkedList<Double> probs = new LinkedList<Double>();
        for (int i = 0; i < classCount.length; i++) {
            for (int j = 0; j < numClasses; j++) {
                double prob = classCount[i][j] / splits[i].numExamples();
                probs.add(new Double(prob));
            }
        }
        
        double attrEntropy = entropy(probs);
        
        System.out.println(attrEntropy);
        
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
