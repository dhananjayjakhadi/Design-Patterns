package wordTree.store;

import wordTree.util.MyLogger;

public class BinarySearchTree {
    BSTNode root_node;
    int total_word_count=0,total_char_count=0;
    /* Constructor */
    public BinarySearchTree() {
        MyLogger.writeMessage("Bineary Search Tree Constructor", MyLogger.DebugLevel.CONSTRUCTOR);
        root_node = null;
    }

    /**
            * Function to check if tree is empty
            * @return boolean returns true if tree is empty
            */
    public boolean isEmpty() {
        return root_node == null;
    }

    public void setTotal_char_count(int total_char_count) {
        this.total_char_count = total_char_count;
    }

    public void setTotal_word_count(int total_word_count) {
        this.total_word_count = total_word_count;
    }
    
    public int getTotal_char_count() {
        return total_char_count;
    }

    public int getTotal_word_count() {
        return total_word_count;
    }

    /**
            * Function to insert data
            * @param data data to insert
            */
    public void insert(String data) {
        MyLogger.writeMessage(data+" added to node", MyLogger.DebugLevel.DEBUG_VALUE);
        root_node = insert(root_node, data);
    }

    /**
            * Function to insert data recursively
            * @return BSTNode newly inserted node
            */
    private BSTNode insert(BSTNode node, String data) {
        synchronized(this){
            if (node == null) {
                node = new BSTNode(data);
            } else {
                if (data.compareTo(node.getData())<=0) {
                    node.left = insert(node.left, data);
                } else {
                    node.right = insert(node.right, data);
                }
            }
        }
        return node;
    }

    /**
            * Function to delete data
            * @return boolean returns true if tree is empty
            */
    public void delete(String str) {
        if (isEmpty()) {
            MyLogger.writeMessage("Tree is empty", MyLogger.DebugLevel.DEBUG_VALUE);
        } else if (search(str) == false) {
            MyLogger.writeMessage(str+" is not found", MyLogger.DebugLevel.DEBUG_VALUE);
        } else {
            root_node = delete(root_node, str);
            MyLogger.writeMessage(str+" deleted from tree", MyLogger.DebugLevel.DEBUG_VALUE);
        }
    }

    private BSTNode delete(BSTNode root_node, String str) {
        BSTNode p, p2, n;
        synchronized(this){
            if (root_node.getData().equals(str)) {
                BSTNode lt, rt;
                lt = root_node.getLeft();
                rt = root_node.getRight();
                if (lt == null && rt == null) {
                    return null;
                } else if (lt == null) {
                    p = rt;
                    return p;
                } else if (rt == null) {
                    p = lt;
                    return p;
                } else {
                    p2 = rt;
                    p = rt;
                    while (p.getLeft() != null) {
                        p = p.getLeft();
                    }
                    p.setLeft(lt);
                    return p2;
                }
            }

            if (str.compareTo(root_node.getData()) < 0) {
                n = delete(root_node.getLeft(), str);
                root_node.setLeft(n);
            } else {
                n = delete(root_node.getRight(), str);
                root_node.setRight(n);
            }
        }
        return root_node;
    }

    public int countNodes() {
        return countNodes(root_node);
    }

    /**
            * Function to count number of nodes recursively
            * @return int returns number of nodes
            */
    private int countNodes(BSTNode r) {
        if (r == null) {
            return 0;
        } else {
            int l = 1;
            l += countNodes(r.getLeft());
            l += countNodes(r.getRight());
            return l;
        }
    }

    
    public boolean search(String val) {
        return search(root_node, val);
    }

    /**
            * Function to search for an element recursively
            * @param r root node
            * @param val data to search
            * @return boolean returns true if tree is empty
            */
    private boolean search(BSTNode r, String val) {
        boolean found = false;
        synchronized(this){
            while ((r != null) && !found) {
                String rval = r.getData();
                if (val.compareTo(rval)<0) {
                    r = r.getLeft();
                } else if (val.compareTo(rval)>0) {
                    r = r.getRight();
                } else {
                    found = true;
                    break;
                }
                found = search(r, val);
            }
        }
        return found;
    }
}