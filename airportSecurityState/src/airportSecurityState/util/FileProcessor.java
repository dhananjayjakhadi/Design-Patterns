package airportSecurityState.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileProcessor {

    private FileReader fileReader;
    private FileWriter fileWriter;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String inputFileName, outputFileName;
    private String strOutput = "";

    public FileProcessor() {
        // TODO Auto-generated constructor stub
    }

    public String getStringOutputAll() {
        return strOutput;
    }

    public void setStrOutput(String strOutput) {
        this.strOutput = strOutput;
    }

    public FileProcessor(String inputFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
    }

    public String readLine() {
        try {
            fileReader = new FileReader(inputFileName);
            reader = new BufferedReader(fileReader);
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = reader.readLine();
            }
            return sb.toString();
        } catch (Exception ex1) {
            MyLogger.setDebugValue(1);
            MyLogger.DebugLevel debugLevel = MyLogger.DebugLevel.IN_RUN;
            MyLogger.writeMessage("File Not found, Please enter valid file", debugLevel);
            System.exit(1);
        } finally {
        }
        return null;
    }

    public void write(String outputText) throws IOException {
        strOutput += outputText;
        //System.out.println(outputText);
        try {
            fileWriter = new FileWriter(outputFileName);
            writer = new BufferedWriter(fileWriter);
            writer.write(strOutput);
            writer.flush();
        } catch (IOException e) {
        }
    }

    public void append(String outputText) throws IOException {
        strOutput += outputText;
        //MyLogger.writeMessage(outputText, MyLogger.DebugLevel.IN_RESULTS);
        //System.out.print(outputText);
        try {
            fileWriter = new FileWriter(outputFileName);
            writer = new BufferedWriter(fileWriter);
            writer.append(strOutput);
            writer.flush();
        } catch (IOException e) {
        }
    }
}