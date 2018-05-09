/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wordTree.threadMgmt;

import java.util.logging.Level;
import java.util.logging.Logger;
import wordTree.store.BinarySearchTree;
import wordTree.store.Results;
import wordTree.util.FileProcessor;
import wordTree.util.MyLogger;

/**
 *
 * @author User
 */
public class PopulateThread implements Runnable{
    FileProcessor fp;
    Results results;
    Thread t;
    BinarySearchTree bst;
    public PopulateThread(FileProcessor fp, Results results, String thread_name, BinarySearchTree bst) {
        results.writeToScreen("Populate Thread Constructor", MyLogger.DebugLevel.CONSTRUCTOR);
        this.fp=fp;
        this.results=results;
        this.bst=bst;
        t=new Thread(this,thread_name);
        t.start();
    }

    @Override
    public void run() {
        results.writeToScreen("Populate Thread Running", MyLogger.DebugLevel.THREAD_RUN);
        String file_data=null;
        while((file_data=fp.readLine())!=null){
            String[] str=file_data.split("\\s");
            for(int i=0;i<str.length;i++){
                if(str[i].trim().length()==0)
                    continue;
                bst.setTotal_word_count(bst.getTotal_word_count()+1);
                bst.setTotal_char_count(bst.getTotal_char_count()+str[i].length());
                if(!bst.search(str[i]))
                bst.insert(str[i]);
            }        
        }
        
    }
    
    public void join() throws InterruptedException{
        t.join();
    }
}
