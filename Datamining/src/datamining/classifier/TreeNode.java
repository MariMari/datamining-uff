/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datamining.classifier;

import java.util.ArrayList;

/**
 *
 * @author igor
 */
public class TreeNode {

    private int attribute;
    private ArrayList<TreeNode> children;
    
    public TreeNode(int attribute) {
        this.attribute = attribute;
    }
    
    public TreeNode getChild(int index) {
        return children.get(index);
    }
    
    public void insertChild(TreeNode child) {
        if (children == null) 
            children = new ArrayList<TreeNode>();
        
        children.add(child);
    }
    
}
