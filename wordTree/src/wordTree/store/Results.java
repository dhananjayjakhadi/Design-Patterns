package wordTree.store;

import wordTree.util.FileDisplayInterface;
import wordTree.util.FileProcessor;
import wordTree.util.MyLogger;
import wordTree.util.StdoutDisplayInterface;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	private FileProcessor fileProcessor;

	public Results() {}

        public void setFileProcessor(FileProcessor fileProcessor){
            this.fileProcessor=fileProcessor;
        }
	
        /**
            * Prints the output on screen
            * @param  message  message to be printed
            * @param  levelIn debug level
        */
        @Override
        public void writeToScreen(String  message, MyLogger.DebugLevel levelIn) {
		MyLogger.writeMessage(message, levelIn);
	}

        /**
            * Prints the output to file
            * @param  s  message to be printed
        */
	@Override
	public void writeSchedulesToFile(String s) {
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
