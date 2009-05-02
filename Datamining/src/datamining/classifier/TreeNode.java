/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datamining.classifier;

import datamining.DataBase;
import java.util.ArrayList;

/**
 *
 * @author igor
 */
public class TreeNode {

    private int attribute;
    private DataBase split;
    private TreeNode parent;
    private ArrayList<TreeNode> children;
    private Double classValue;
    
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
    
    public TreeNode getParent() {
        return parent;
    }
    
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }
    
    public DataBase getSplit() {
        return split;
    }
    
    public void setSplit(DataBase split) {
        this.split = split;
    }
    
    public Double getClassValue() {
        return classValue;
    }
    
    public void setClassValue(Double classValue) {
        this.classValue = classValue;
    }
    
}
