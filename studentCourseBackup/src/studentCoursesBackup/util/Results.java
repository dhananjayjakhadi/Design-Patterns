package studentCoursesBackup.util;

import java.io.IOException;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

	private FileProcessor fileProcessor;
	
	public Results(String outputFileName) {
		fileProcessor = new FileProcessor(outputFileName);
	}

	@Override
	public void writeToFile(String s) {

		try {
			fileProcessor.write(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}