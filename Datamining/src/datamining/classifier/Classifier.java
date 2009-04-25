/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datamining.classifier;

import datamining.*;
import java.util.LinkedList;

/**
 *
 * @author igor
 */
public class Classifier {
    
    private TreeNode root;
    double eParameter;
    
    /** Construtor vazio */
    public Classifier() {}
    
        
    public Attribute selectAttribute(LinkedList<Attribute> attrs) {
        return null;
    }
    
    /**
     * Metodo para construir a arvore de decisao em funcao do conjunto de
     * treinamento passado como parametro.
     * 
     * @param trainingSet um conjunto com exemplos de treinamento 
     */
    public void buildClassifier(DataBase trainingSet) throws Exception {
        //contar o numero de exemplos para cada classe no training set
        if (!trainingSet.classAttribute().isDiscrete()) {
            throw new Exception("A arvore so pode ser construida para classes"
                                + " com dominio discreto.");
        }
        
        int[] numExamplePerClass = new int[trainingSet.numClasses()];
        for (int i = 0; i < trainingSet.numExamples(); i++) {
            numExamplePerClass[trainingSet.example(i).getClassAttrValue().intValue()]++;
        }
        
        System.out.println(numExamplePerClass[0]);
        System.out.println(numExamplePerClass[1]);
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
