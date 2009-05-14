/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datamining.classifier;

import java.util.ArrayList;

/**
 /**
 * @author Fábio Gomes
 * @author Gabriel Baims
 * @author Marianna Portela
 * @author Igor Giusti
 */
public class TreeNode {

    private int attribute;
    private ArrayList<TreeNode> children;
    private double classValue;
    
    public TreeNode () {}
    
    public TreeNode(int attribute) {
        this.attribute = attribute;
    }
    
    public TreeNode getChild(int index) {
        return children.get(index);
    }
    
    public int getAttribute () {
        return attribute;
    }
    
    public void insertChild(TreeNode child) {
        if (children == null) 
            children = new ArrayList<TreeNode>();
        
        children.add(child);
    }
    
    public Double getClassValue() {
        return classValue;
    }
    
    public void setClassValue(double classValue) {
        this.classValue = classValue;
    }
    
}
