/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wordTree.threadMgmt;

import wordTree.store.BinarySearchTree;
import wordTree.store.Results;
import wordTree.util.FileProcessor;
import wordTree.util.MyLogger;

/**
 *
 * @author User
 */
public class DeleteThread implements Runnable{
    FileProcessor fp;
    Results results;
    Thread t;
    BinarySearchTree bst;
    String delete_word;
    public DeleteThread(FileProcessor fp, Results results, String thread_name, BinarySearchTree bst, String delete_word) {
        results.writeToScreen("Delete Thread Constructor", MyLogger.DebugLevel.CONSTRUCTOR);
        this.fp=fp;
        this.results=results;
        this.bst=bst;
        this.delete_word=delete_word;
        
        t=new Thread(this,thread_name);
        t.start();
    }

    @Override
    public void run() {
        results.writeToScreen("Delete Thread Running", MyLogger.DebugLevel.THREAD_RUN);
            if(!bst.isEmpty())
            bst.delete(delete_word);
    }
    
    public void join() throws InterruptedException{
        t.join();
    }
}