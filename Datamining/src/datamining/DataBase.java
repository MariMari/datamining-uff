/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datamining;
import java.io.*;
import java.util.ArrayList;

/**
 * @author Fábio Gomes
 * @author Gabriel Baims
 * @author Marianna Portela
 * @author Igor Giusti
 */
public class DataBase {
    
    private int classIndex;
    private ArrayList<Attribute> attributes;
    private ArrayList<Example> data;
    
    //Constantes referentes aos valores dos dominios dos atributos discretos.
    private static final String[] dominioSaldo = 
            {"<0", "0<=X<200", ">=200", "'no checking'"};
    
    private static final String[] dominioHistCredito = 
            {"'no credits/all paid'", "'all paid'", "'existing paid'",
             "'delayed previously'", "'critical/other existing credit'"};
    
    private static final String[] dominioEconomias =
            {"<100", "100<=X<500", "500<=X<1000", ">=1000", "'no known savings'"};
    
    private static final String[] dominioEstPessoal =
            {"'male div/sep'", "'female div/dep/mar'", "'male single'",
             "'male mar/wid'", "'female single'"};
    
    private static final String[] dominioClasse =
            {"good", "bad"};

    public DataBase() {
        initDataBase();
        data = new ArrayList<Example>();
    }
    
    public DataBase(String fileName) throws Exception {
        initDataBase();
        File file = new File(fileName);
        this.createDataBase(file);
    }

    public DataBase(File file) throws Exception {
        initDataBase();
        this.createDataBase(file);
    }
       
    public void addExample (Example example) {
        data.add(example);
    }
    
    public Example example (int index) {
        if ((index < 0) || (index >= data.size())) {
            throw new RuntimeException("Tentativa de acesso a exemplo nao " +
                                       "existente na base!");
        }
        
        return data.get(index);
    }
    
    public int numExamples() {
        return data.size();
    }
    
    /**
     * Inclui o atributo attribute se esse nao existe no dominio, isso garante
     * que attributes seja um conjunto.
     * 
     * @param attribute atributo a ser incluido
     */
    public void addAttribute(Attribute attribute) {
        if (!attributes.contains(attribute)) {
            attributes.add(attribute);
        }
    }
    
    /**
     * Metodo para recuperar um atributo pelo indice
     * 
     * @param index indice do atributo
     * 
     * @return      o atributo relativo ao indice index
     */
    public Attribute attribute(int index) {
        if (index < 0 || index >= attributes.size()) {
            throw new RuntimeException("Tentativa de acesso a atributo nao " +
                                       "existente na base!");
        }
        
        return attributes.get(index);
    }
    
    /**
     * Metodo para recuperar um atributo pelo nome
     * 
     * @param name nome do atributo
     * 
     * @return     o atributo com nome name
     */
    public Attribute attribute(String name) {
        int index = attributes.indexOf(new Attribute(name, true, 0));
        if (index == -1) {
            throw new RuntimeException("Tentativa de acesso a atributo nao " +
                                       "existente na base!");
        }
        
        return attribute(index);
    }
    
    public Attribute classAttribute() {
        return attributes.get(classIndex);
    }
    
    public int numAttributes() {
        return attributes.size();
    }
    
    public int getClassIndex() {
        return classIndex;
    }
    
    /**
     * Metodo para atribuir um valor indice que representa o atributo classe
     * 
     * @param classIndex indice do valor classe
     */
    public void setClassIndex(int classIndex) {
        if (classIndex < 0 || classIndex >= attributes.size()) {
            throw new RuntimeException("O indice referente a classe nao " +
                                       "corresponde a um atributo da base!");
        }
        
        this.classIndex = classIndex;
    }
    
    public int numClasses() {
        return attributes.get(classIndex).cardinality();
    }
    
    private void createDataBase (File file) throws Exception {
        //copia e trata as entradas da base de data
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String lineRead = null;
            data = new ArrayList<Example>();
            
            while ((lineRead = in.readLine()) != null) {
                Example example = new Example(this, lineRead);
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
        String dataBase = "";
        for (int i = 0; i < data.size(); i++) {
            dataBase = dataBase + example(i).toRegisterString() + "\n";
        }
        
        return dataBase;
    }
    
    @Override
    public String toString () {
        String dataBase = "";
        for (int i = 0; i < data.size(); i++) {
            dataBase = dataBase + this.example(i).toString() + "\n";
        }
        
        return dataBase;
    }
    
    /**
     * Metodo para atribuicao de valores fixos para o primeiro trabalho de 
     * implementacao de datamining
     */
    private void initDataBase() {
        attributes = new ArrayList<Attribute>();
        
        //Criando todos os atributos para o problema de classificacao.
        Attribute attr0 = new Attribute("saldo", true, 0);
        for (int i = 0; i < dominioSaldo.length; i++) {
            attr0.addDomainValue(dominioSaldo[i]);
        }
            
        Attribute attr1 = new Attribute("duracao", false, 1);
        
        Attribute attr2 = new Attribute("historico", true, 2);
        for (int i = 0; i < dominioHistCredito.length; i++) {
            attr2.addDomainValue(dominioHistCredito[i]);
        }
        
        Attribute attr3 = new Attribute("valor", false, 3);
        
        Attribute attr4 = new Attribute("economias", true, 4);
        for (int i = 0; i < dominioEconomias.length; i++) {
            attr4.addDomainValue(dominioEconomias[i]);
        }
        
        Attribute attr5 = new Attribute("estado pessoal", true, 5);
        for (int i = 0; i < dominioEstPessoal.length; i++) {
            attr5.addDomainValue(dominioEstPessoal[i]);
        }
        
        Attribute attr6 = new Attribute("classe", true, 6);
        for (int i = 0; i < dominioClasse.length; i++) {
            attr6.addDomainValue(dominioClasse[i]);
        }
        
        attributes.add(attr0);
        attributes.add(attr1);
        attributes.add(attr2);
        attributes.add(attr3);
        attributes.add(attr4);
        attributes.add(attr5);
        attributes.add(attr6);
        
        classIndex = 6;
    }

}
