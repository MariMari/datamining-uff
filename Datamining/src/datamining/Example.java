/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datamining;

import java.util.ArrayList;

/**
 *
 * @author Marianna Portela
 * @author igorg
 */
public class Example {
    
    private DataBase dataBase;
    private ArrayList<Double> attrValues;
        
    public Example(DataBase base, String line) throws Exception {
        dataBase = base;
        String [] strAttrs = line.split(",");
        if (strAttrs.length != dataBase.numAttributes()){
            throw new Exception("Numero de atributos errado");
        }
        attrValues = new ArrayList<Double>(dataBase.numAttributes());
        for (int i = 0; i < dataBase.numAttributes(); i++) {
            Double value = dataBase.attribute(i).doubleForDomainValue(strAttrs[i]);
            attrValues.add(value);
        }
    }
    
    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }
    
    public DataBase getDataBase() {
        return dataBase;
    }
            
    public Double getAttrValue(int index) throws Exception {
        if (index < 0 || index >= attrValues.size())
            throw new Exception("Valor inexistente");
        return attrValues.get(index);
    }
    
    public Double getAttrValue(String name) throws Exception {
        Attribute attr = dataBase.attribute(name);
        return attrValues.get(attr.getIndex());
    }
    
    public Double getClassValue() {
        return attrValues.get(dataBase.getClassIndex());
    }
        
    public Double[] getAttrValues() {
        Double[] values = null;
        this.attrValues.toArray(values);
        return values;
    }
    
    public void setAttrValue(int index, Double value) {
        if (index < 0 || index >= attrValues.size())
            attrValues.set(index, value);
    }
    
    public void setClassValue(Double value) {
        setAttrValue(dataBase.getClassIndex(), value);
    }
    
    public int numAttributes() {
        return attrValues.size();
    }
    
    /**
     * Retorna a representacao do exemplo em String. O formato e o mesmo de um
     * registro em uma base de dados.
     * 
     * @return a representacao do exemplo em formato de registro 
     */
    public String toRegisterString() throws Exception {
        String example = dataBase.attribute(0).getDomainValue(attrValues.get(0));
        for (int i = 0; i < attrValues.size(); i++) {
            String strAttr = dataBase.attribute(i).getDomainValue(
                                                   attrValues.get(i));
            example += ", " + strAttr;
        }
        return example;
    }

    @Override
    public String toString () {
        String example = "";
        for (int i = 0; i < attrValues.size(); i++) {
            try {
                Attribute attr = dataBase.attribute(i);
                example += attr.getName() + ": "
                           + attr.getDomainValue(attrValues.get(i)) + "\n";
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return example;
    }
}