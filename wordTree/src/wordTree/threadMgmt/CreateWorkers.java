package wordTree.threadMgmt;

import wordTree.store.BinarySearchTree;
import wordTree.store.Results;
import wordTree.util.FileProcessor;
import wordTree.util.MyLogger;

public class CreateWorkers {

    Results results;
    FileProcessor fp;
    BinarySearchTree bst;

    public CreateWorkers(FileProcessor fp, Results results) {
        results.writeToScreen("Create Workers Constructor", MyLogger.DebugLevel.CONSTRUCTOR);
        this.fp = fp;
        this.results = results;

        String file_data = fp.readFile();
        if (file_data.trim().length() == 0) {
            MyLogger.setDebugValue(MyLogger.DebugLevel.ERROR);
            results.writeToScreen("Inpur File is empty", MyLogger.DebugLevel.ERROR);
            System.exit(1);
        }
        bst = new BinarySearchTree();
    }

    /**
     * Starts number of threads to populate Binary Search Tree
     *
     * @param numThreads maximum number of threads
     */
    public void startPopulateWorkers(int numThreads) {
        for (int i = 0; i < numThreads; i++) {
            PopulateThread pt = new PopulateThread(fp, results, "Populate Worker " + (i + 1), bst);
            try {
                pt.join();
            } catch (InterruptedException ex) {
                results.writeToScreen(ex.toString(), MyLogger.DebugLevel.ERROR);
            }
        }
    }

    /**
     * Starts number of threads to delete nodes from Binary Search Tree
     *
     * @param numThreads maximum number of threads
     * @param delete_words[] words to delete
     */
    public void startDeleteWorkers(int numThreads, String[] delete_words) {
        for (int i = 0; i < numThreads; i++) {
            DeleteThread dt = new DeleteThread(fp, results, "Delete Worker " + (i + 1), bst, delete_words[i]);
            try {
                dt.join();
            } catch (InterruptedException ex) {
                results.writeToScreen(ex.toString(), MyLogger.DebugLevel.ERROR);
            }
        }
    }

    /**
     * Returns total word counts
     *
     * @return int total number of words
     */
    public int getTotal_word_count() {
        return bst.getTotal_word_count();
    }

    /**
     * Returns total number of characters
     *
     * @return int number of characters
     */
    public int getTotal_char_count() {
        return bst.getTotal_char_count();
    }

    /**
     * Returns total distinct words
     *
     * @return int Total number of distinct words
     */
    public int getTotal_distinct_count() {
        return bst.countNodes();
    }
}