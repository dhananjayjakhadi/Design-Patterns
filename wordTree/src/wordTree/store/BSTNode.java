package wordTree.store;

import wordTree.util.MyLogger;

class BSTNode {
    BSTNode left, right;
    String data;

    /* Constructor */
    public BSTNode() {
        MyLogger.writeMessage("BSTNode Constructor", MyLogger.DebugLevel.CONSTRUCTOR);
        left = null;
        right = null;
        data = "";
    }

    /* Constructor */
    public BSTNode(String str) {
        MyLogger.writeMessage("BSTNode Constructor", MyLogger.DebugLevel.CONSTRUCTOR);
        left = null;
        right = null;
        data = str;
    }

        /**
            * Function to set left node
            * @param n BST node
            */
    public void setLeft(BSTNode n) {
        left = n;
    }

    /**
            * Function to set right node
            * @param n BST node
            */
    public void setRight(BSTNode n) {
        right = n;
    }

    /**
            * Function to get left node
            * @return BSTNode
            */
    public BSTNode getLeft() {
        return left;
    }

    /**
            * Function to get right node
            * @return BSTNode
            */
    public BSTNode getRight() {
        return right;
    }
    
    /**
            * Function to set data to node
            * @param str BST node
            */
    public void setData(String str) {
        data = str;
    }

    /**
            * Function to get data from node
            * @return String data
            */
    public String getData() {
        return data;
    }
}