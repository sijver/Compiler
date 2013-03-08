package com.example.compilTest.core.parser.tree;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 */
public class TreeNode {

    private Object nodeValue;

    private LinkedList<TreeNode> childrenNodes;

    private TreeNode parentNode = null;

    private int rule = -1;

    public TreeNode(Object nodeValue, int rule){
        this.nodeValue = nodeValue;
        this.rule = rule;
        childrenNodes = new LinkedList<TreeNode>();
    }

    public boolean hasChildren(){
        return !childrenNodes.isEmpty();
    }

    public boolean isCompressable(){
        for(TreeNode child : childrenNodes){
            if(child.hasChildren()){
                return false;
            }
        }
        return true;
    }

    public void removeChildrenNodes(){
        for(TreeNode child : childrenNodes){
            child.removeParentEdge();
        }
        childrenNodes.clear();
    }

    public void removeParentEdge(){
        parentNode = null;
    }

    public void addChildNode(TreeNode childNode){
        childNode.parentNode = this;
        childrenNodes.addFirst(childNode);
    }

    public void setParentNode(TreeNode parentNode){
        parentNode.addChildNode(this);
    }

    public TreeNode getParentNode(){
        return parentNode;
    }

    public Object getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(Object nodeValue) {
        this.nodeValue = nodeValue;
    }

    public LinkedList<TreeNode> getChildrenNodes() {
        return childrenNodes;
    }

    public void setChildrenNodes(LinkedList<TreeNode> childrenNodes) {
        this.childrenNodes = childrenNodes;
    }

    public int getRule() {
        return rule;
    }

    public int getTreeNodes(){
        int sum = 0;
        for(TreeNode child : childrenNodes){
            sum += child.getTreeNodes();
        }
        sum++;
        return sum;
    }

    public String getTreeText(){
        String a = "";
        a = a.concat(nodeValue.toString() + " " + rule + "\n");
        for(TreeNode child : childrenNodes){
            a = a.concat("-" + child.getBranchText("|"));
        }
        return a;
    }

    private String getBranchText(String s){
        String a = "";
        a = a.concat(nodeValue.toString() + " " + rule +  "\n");
        for(TreeNode child : childrenNodes){
            a = a.concat(s + "-" + child.getBranchText("|" + s));
        }
        return a;
    }

}
