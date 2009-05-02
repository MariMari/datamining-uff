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
    
    private double infoAmount(double[][] classCount, double totalExamples) {
        double attrInfo = 0;
        
        for (int i = 0; i < classCount.length; i++) {
            Double[] probs = new Double[classCount[i].length];
            double numExamples = 0;
            
            for (int j = 0; j < classCount[i].length; j++) {
                double prob = (classCount[i][j]);
                probs[j] = new Double(prob);
                numExamples += prob;
            }
            
            for (int j = 0; j < probs.length; j++) {
                probs[j] = new Double(probs[j].doubleValue() / numExamples);
            }
            
            double vEntropy = entropy(probs);
            attrInfo += (numExamples / totalExamples) * vEntropy;
        }
        
        return attrInfo;
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
        int numAttr = trainingSet.numAttributes() - 1;
        int numClasses = trainingSet.numClasses();       
        // Suponho o nivel de informacao inicial o maior possivel e escolho o
        // primeiro atributo como separador.
        double currentInfo = 1;
        Attribute chosen = trainingSet.attribute(numAttr);
        double[][] classCount = countClass(chosen, trainingSet);
        DataBase[] splits = createSplits(chosen, trainingSet);
        double attrInfo = infoAmount(classCount, numExamples);
        double higher = 0;
        double indexHigher = 0;
        // O atributo que obtiver o maior ganho de informacao deve ser o escolhido.
        // Esse teste deve ser feito para todos os atributos apesar de aqui eu
        // so mostrar para o primeiro.
        double infoGain = currentInfo - attrInfo;
        double interrupt = trainingSet.numExamples() * (0.3);
        TreeNode root = new TreeNode();
        TreeNode leaf = root;
       /* while((trainingSet.getSize()>1)){
            higher = 0;
            while ((trainingSet.numExamples()> interrupt) && (numAttr>=1)) {
                classCount = countClass(chosen, trainingSet);
                attrInfo = infoAmount(classCount, numExamples);
                infoGain = currentInfo - attrInfo;
                if (infoGain > higher) {
                   currentInfo = attrInfo;
                   indexHigher = numAttr;
                }
                numAttr--;
                chosen = trainingSet.attribute(numAttr);
            }
            
        }
        //organizar ideias. criar o root e ir expandindo os filhos, os filhos dos filhos...
        // atentar para o corte minimo
        
        */
        // Ainda falta a comparacao com o valor de corte minimo!!!
    }
    
    /**
     * Retorna a classificacao de um exemplo de acordo com a arvore de decisao
     * construida.
     * 
     * @param  example o exemplo a ser classificado
     * 
     * @return a classe do exemplo representada por um Double
     */
   public Double classifyExample(Example example) throws Exception {
      
        TreeNode nextNode = this.root;
	TreeNode currentNode = null;
        int aux = 0;
        
	while(nextNode != null)	{
		currentNode = nextNode;
                aux = example.getAttrValue(currentNode.getAttribute()).intValue();
		nextNode = currentNode.getChild(aux);
        }
        
        return currentNode.getClassValue();
    }

}
