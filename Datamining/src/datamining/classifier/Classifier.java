/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * Obs.: Nessa classe, atencao com as conversoes entre inteiros e doubles.
 */

package datamining.classifier;

import java.util.LinkedList;

import datamining.*;
import static datamining.Utils.*;

/**
 /**
 * @author Fábio Gomes
 * @author Gabriel Baims
 * @author Marianna Portela
 * @author Igor Giusti
 */
public class Classifier {
    
    private TreeNode root;
    double minimum;
    
    /** Construtor vazio */
    public Classifier() {}
    
    /**
     * Retorna a contagem de classes para uma divisao de uma base de dados
     * em funcao do dominio de um atributo.
     * 
     * @param attr atributo que dividira a base
     * @param db   base a ser dividida
     * 
     * @return     a contagem de classes para a divisao de db
     */
    private double[][] countClass(Attribute attr, DataBase db) {
        double[][] classCount =
            new double[attr.cardinality()][db.numClasses()];
        
        for (int i = 0; i < db.numExamples(); i++) {
            Example example = db.example(i);
            int attrValue = (int) example.getAttrValue(attr.getIndex());
            int classValue = (int) example.getClassValue();
            classCount[attrValue][classValue]++;
        }
        
        return classCount;
    }
    
    private double[][] countClass(DataBase db) {
        double[][] classCount =
            new double[1][db.numClasses()];
        
        for (int i = 0; i < db.numExamples(); i++) {
            Example example = db.example(i);
            int classValue = (int) example.getClassValue();
            classCount[0][classValue]++;
        }
        
        return classCount;
    }
    
    
    
    /**
     * Retorna um array de DataBases que sao as divisoes do DataBase db em
     * relacao aos valores do dominio do atributo attr.
     * 
     * @param attr atributo sobre o qual a base deve ser dividida
     * @param db   a base a ser dividida
     * 
     * @return     divisao da base em um array de novas bases
     */
    private static DataBase[] splitDataBase(Attribute attr, DataBase db) {
        DataBase[] splits = new DataBase[attr.cardinality()];
        
        for (int i = 0; i < splits.length; i++) {
            splits[i] = new DataBase();
        }
        
        for (int i = 0; i < db.numExamples(); i++) {
            Example example = db.example(i);
            int attrValue = (int) example.getAttrValue(attr.getIndex());
            splits[attrValue].addExample(example);
        }
        
        return splits;
    }
    
    /**
     * Retorna a quantidade de informacao de uma contagem de classes
     * baseada em uma distribuicao proporcionada pela divisao de uma base
     * pelo dominio de um atributo.
     * 
     * @param classCount    a contagem das classes
     * @param totalExamples o total de exemplos na base original
     * 
     * @return              a quantidade de informacao em classCount
     */
    private static double infoAmount(double[][] classCount, double totalExamples) {
        double attrInfo = 0.0;
        
        for (int i = 0; i < classCount.length; i++) {
            double[] probs = new double[classCount[i].length];
            double numExamples = 0.0;
            
            for (int j = 0; j < classCount[i].length; j++) {
                probs[j] = classCount[i][j];
                numExamples += probs[j];
            }
            
            for (int j = 0; j < probs.length; j++) {
                if (numExamples == 0) {
                    probs[j] = 0.0;
                } else {
                    probs[j] = probs[j] / numExamples;
                }
            }
            
            double vEntropy = entropy(probs);
            attrInfo += (numExamples / totalExamples) * vEntropy;
        }
        
        return attrInfo;
    }
    
    private double commandingClass(DataBase db) {
        double[] classes = new double[db.numClasses()];
        
        for (int i = 0; i < db.numExamples(); i++) {
            classes[(int) db.example(i).getClassValue()]++;
        }
        
        double greater = 0.0;
        for (int i = 1; i < classes.length; i++) {
            if (classes[(int) greater] < classes[i]) {
                greater = i;
            }
        }
        
        return greater;
    }
    
    /**
     * Retorna uma arvore de decisao construida de forma recursiva
     */
    private TreeNode buildTree(LinkedList<Attribute> attrs, DataBase split,
                               int minimo) {
        
        TreeNode node = null;
        
        if ((split.numExamples() > minimo) && (attrs.size() > 0)) {
            //Calculando info para o split
            double[][] setCount = countClass(split);
            double info = infoAmount(setCount, split.numExamples());
            
            Attribute chosen = attrs.poll();
            double[][] count = countClass(chosen, split);
            double chosenInfo = infoAmount(count, split.numExamples());
            double gain = info - chosenInfo;
            
            // TODO: Verificar se o atributo e discreto, tratar diferenciadamente.
            for (int i = 0; i < attrs.size(); i++) {
                Attribute candidate = attrs.poll();
                double[][] candidateCount = countClass(candidate, split);
                double candidateInfo = infoAmount(candidateCount, split.numExamples());
                double candidateGain = info - candidateInfo;
                // Se o candidato ganhar do atual escolhido...
                if (gain < candidateGain) {
                    attrs.add(chosen);
                    chosen = candidate;
                    chosenInfo = candidateInfo;
                    gain = candidateGain;
                } else {
                    attrs.add(candidate);
                }
            }

            if (gain > 0) {
                DataBase[] splits = splitDataBase(chosen, split);

                node = new TreeNode(chosen.getIndex());
                node.setClassValue(commandingClass(split));

                //Criar os filhos a partir do split 
                for (int i = 0; i < splits.length; i++) {
					node.insertChild(buildTree((LinkedList<Attribute>) attrs.clone(),
                                                splits[i], minimo));
                }
            }
        }
        
        return node;
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
        int minExamples = (int) (trainingSet.numExamples() * minimum);
        
        /* Iniciando uma lista de atributos a serem comparados. Essa lista nao 
         possui o attributo classe. */
        LinkedList<Attribute> attrs = new LinkedList<Attribute>();
        for (int i = 0; i < (trainingSet.numAttributes() - 1); i++) {
            // TODO: remover esse if...
            if (trainingSet.attribute(i).isDiscrete())
                attrs.add(trainingSet.attribute(i));
        }
        
        /* Construindo a arvore recursivamente */
        this.root = buildTree(attrs, trainingSet, minExamples);
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
      
        TreeNode nextNode = this.root;
	TreeNode currentNode = null;
        int aux = 0;
        
	while(nextNode != null)	{
		currentNode = nextNode;
                aux = (int) example.getAttrValue(currentNode.getAttribute());
		nextNode = currentNode.getChild(aux);
        }
        
        return currentNode.getClassValue();
    }

}
