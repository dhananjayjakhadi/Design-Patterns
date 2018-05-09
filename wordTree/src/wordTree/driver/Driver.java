package wordTree.driver;

import wordTree.store.Results;
import wordTree.threadMgmt.CreateWorkers;
import wordTree.util.MyLogger;
import wordTree.util.FileProcessor;

public class Driver {
    public static void main(String args[]) {
        Results results = new Results();
        if (args.length < 5) {
            MyLogger.setDebugValue(MyLogger.DebugLevel.ERROR);
            results.writeToScreen("Arguments are missing, Please Enter Input File, Output File, Threads Count, Delete words & Debug Value", MyLogger.DebugLevel.ERROR);
            System.exit(1);
        }

        String inputfile=args[0];
        String outputfile=args[1];
			
        int numThreads = 0;
		try{
			numThreads=Integer.parseInt(args[2]);
		}catch(Exception ex){
			MyLogger.setDebugValue(MyLogger.DebugLevel.ERROR);
            results.writeToScreen("Number not found, Please enter valid Thread Count", MyLogger.DebugLevel.ERROR);
            System.exit(1);
		}
		
        if (numThreads < 1 || numThreads > 3) {
            MyLogger.setDebugValue(MyLogger.DebugLevel.ERROR);
            results.writeToScreen("Number of Threads should be between 1 and 3", MyLogger.DebugLevel.ERROR);
            System.exit(1);
        }
        
        String words=args[3];
        String delete_words[]=words.split(" ");
        
        if(delete_words.length!=numThreads){
            MyLogger.setDebugValue(MyLogger.DebugLevel.ERROR);
            results.writeToScreen("Number of Delete words should be same as Number of Threads", MyLogger.DebugLevel.ERROR);
            System.exit(1);
        }
        
        int debuglvl = 0;
		try{
			debuglvl=Integer.parseInt(args[4]);
		}catch(Exception ex){
			MyLogger.setDebugValue(MyLogger.DebugLevel.ERROR);
            results.writeToScreen("Number not found, Please enter valid Debug Value", MyLogger.DebugLevel.ERROR);
            System.exit(1);
		}
		
        if (debuglvl < 0 || debuglvl > 4) {
            MyLogger.setDebugValue(MyLogger.DebugLevel.ERROR);
            results.writeToScreen("Last Argument should be in 0-4 range", MyLogger.DebugLevel.ERROR);
            System.exit(1);
        }
        
        MyLogger.setDebugValue(MyLogger.getDebugValue(debuglvl));
        results.writeToScreen("Number of Threads: "+numThreads, MyLogger.DebugLevel.DEBUG_VALUE);
        results.writeToScreen("Delete Words: "+words, MyLogger.DebugLevel.DEBUG_VALUE);        
        results.writeToScreen("Debug Level: "+debuglvl, MyLogger.DebugLevel.DEBUG_VALUE);
        
        FileProcessor fp=new FileProcessor(inputfile, outputfile);
        results.setFileProcessor(fp);
        
        CreateWorkers createWorkers=new CreateWorkers(fp, results);
        createWorkers.startPopulateWorkers(numThreads);
        createWorkers.startDeleteWorkers(numThreads,delete_words);
        String output="The total number of words: "+createWorkers.getTotal_word_count();
               output+="\nThe total number of characters: "+createWorkers.getTotal_char_count();
               output+="\nThe total number of distinct words: "+createWorkers.getTotal_distinct_count();
        results.writeSchedulesToFile(output);
    }
}