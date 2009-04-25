/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datamining.classifier;

import datamining.*;

/**
 *
 * @author igor
 */
public class Classifier {
    
    private TreeNode root;
    
    /** Construtor vazio */
    public Classifier() {}
    
    /**
     * Metodo para construir a arvore de decisao em funcao do conjunto de
     * treinamento passado como parametro.
     * 
     * @param trainingSet um conjunto com exemplos de treinamento 
     */
    public void buildClassifier(DataBase trainingSet) {
        // Compara os attributos e escolhe o que melhor separa as classes
        // Se a separacao for maior que o parametro de corte minimo
            // Cria um no com esse attributo
        // Passa a analise do proximo no.    
    }
    
    public int classifyExample(Example example) {
        return 0;
    }

}
