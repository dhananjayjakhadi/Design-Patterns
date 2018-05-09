package myArrayList.util;

import java.util.ArrayList;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
    private FileProcessor fileProcessor;
    public ArrayList<String> results;
    
    public Results() {
        results=new ArrayList<>();
    }

    public void setFileProcessor(FileProcessor fileProcessor) {
        this.fileProcessor = fileProcessor;
    }
    
    public void storeNewResult(String result){
        results.add(result);
    }

    /**
     * Prints the output on screen
     *
     * @param message message to be printed
     * @param levelIn debug level
     */
    @Override
    public void writeToScreen(String message) {
        System.out.println(message);
    }

    /**
     * Prints the output to file
     *
     * @param s message to be printed
     */
    @Override
    public void writeToFile(String s) {
        fileProcessor.write(s);
    }

    public String getResultOutput() {
        return fileProcessor.readLine();
    }

    public FileProcessor getFileProcessor() {
        return fileProcessor;
    }

    public void close() {
        fileProcessor.close();
    }

    @Override
    public String toString() {
        return "Results []";
    }
}
