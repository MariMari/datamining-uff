/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datamining;

import java.util.ArrayList;

/**
 *
 * @author igor
 */
public class Attribute {
    
    public static final String MISSING_VALUE = "?";
    
    private String name;
    private boolean discrete;
    private ArrayList<String> domain;
    private int index;
    
    public Attribute() {       
        domain = new ArrayList<String>();
        domain.add(MISSING_VALUE);
    }

    
    public Attribute(String name, boolean discrete, int index) {
        this();
        
        this.name = name;
        this.discrete = discrete;
        this.index = index;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isDiscrete() {
        return discrete;
    }
    
    public int getIndex() {
        return index;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setDiscrete(boolean discrete) {
        this.discrete = discrete;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    /**
     * Retorna o valor do dominio a partir da representacao numerica de um
     * atributo nominal. No caso da representacao nao ter mapeamento, retorna-
     * se null.
     * 
     * @param  index a representacao numerica do valor do atributo.
     * @return       o valor mapeado no dominio por index.
     */
    public String getDomainValue(int index) {
        String domainValue = null;
        if ((index > -1) && (index < domain.size()))          
            domainValue = domain.get(index);
        return domainValue;
    }
    
    /**
     * Retorna a representacao numerica para um valor de atributo nominal
     * baseada no dominio. No caso do valor nao existir no dominio, retorna -1
     * 
     * @param   domainValue  o valor do atributo
     * @return               a representacao numerica de domainValue.
     */
    public int integerForDomainValue(String domainValue) {
        return domain.indexOf(domainValue);
    }
    
    /**
     * Inclui o valor value se esse nao existe no dominio, isso garante que
     * o dominio seja um conjunto.
     * 
     * @param value valor do dominio a ser excluido 
     */
    public void addDomainValue(String value) {
        if (!domain.contains(value))
            domain.add(value);
    }
    
    /**
     * Retorna verdadeiro se o dominio nao contem nenhum valor.
     * 
     * @return verdadeiro para dominio vazio
     */
    public boolean hasEmptyDomain() {
        return domain.size() > 1;
    }
    
}
