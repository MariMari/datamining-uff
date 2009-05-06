/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datamining;

import java.util.ArrayList;

/**
 /**
 * @author Fábio Gomes
 * @author Gabriel Baims
 * @author Marianna Portela
 * @author Igor Giusti
 */
public class Attribute {
    
    public static final String MISSING_VALUE = "?";
    
    private String name;
    private boolean discrete;
    private ArrayList<String> domain;
    private int index;
    
    public Attribute() {       
        domain = new ArrayList<String>();
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

    public int cardinality(){
        return domain.size();
    }
    
    /**
     * Retorna o valor do dominio a partir da representacao numerica de um
     * atributo nominal. No caso da representacao nao ter mapeamento, retorna-
     * se null.
     * 
     * @param  index a representacao numerica do valor do atributo.
     * @return       o valor mapeado no dominio por index.
     */
    public String getDomainValue(double index) {
        String domainValue = MISSING_VALUE;
        if (index != Double.NaN) {
            if (!discrete) {
                domainValue = Double.toString(index);
            } else {
                if ((index > -1) && (index < domain.size())) {
                    domainValue = domain.get((int) index);
                } else {
                    throw new RuntimeException("O valor nao pode ser encontrado" +
                                          " no dominio do atributo: " + name );
                }
            }
        }
        return domainValue;
    }
    
    /**
     * Retorna a representacao numerica para um valor de atributo nominal
     * baseada no dominio. No caso do valor nao existir no dominio, retorna -1
     * 
     * @param   domainValue  o valor do atributo
     * @return               a representacao numerica de domainValue.
     */
    public double doubleForDomainValue(String domainValue) {
        double value = Double.NaN;
        if (!domainValue.equals(MISSING_VALUE)) {
            if (!discrete) {
                value = Double.valueOf(domainValue);
            } else {
                int intValue = domain.indexOf(domainValue);
                if (intValue == -1) {
                    throw new RuntimeException("O valor " + domainValue + " nao tem"
                                        + " correspondente no dominio do"
                                        + " atributo: " + name);
                }
                value  = Double.valueOf(intValue);
            }
        }
        return value;
    }
    
    /**
     * Inclui o valor value se esse nao existe no dominio, isso garante que
     * o dominio seja um conjunto.
     * 
     * @param value valor do dominio a ser incluido
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
    
    @Override
    public boolean equals(Object obj) {
        boolean equal = true;
        try {
            Attribute attr = (Attribute) obj;
            if (this.name.equals(attr.getName()))
                equal = false;
        } catch (ClassCastException e) {
            return false;
        }
        return equal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }
    
}
