package myArrayList.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
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
        try {
            fileReader = new FileReader(inputFileName);
            reader = new BufferedReader(fileReader);
        } catch (FileNotFoundException ex) {
            System.err.println("File Not found, Please enter valid filename");
            System.exit(1);
        }   
    }

    public FileProcessor(String outputFileName) {
        this.outputFileName = outputFileName;
    }
    
    public void setInputFile(String inputFileName) {
        this.inputFileName = inputFileName;
        try {
            fileReader = new FileReader(inputFileName);
            reader = new BufferedReader(fileReader);
        } catch (Exception ex) {
            System.err.println("File Not found, Please enter valid filename");
            System.exit(1);
        }
    }
    
    public void setOutputFile(String outputFileName) {
        this.outputFileName = outputFileName;   
    }

    public String readLine(){
        try {
            return reader.readLine();
        } catch (IOException ex) {
            System.err.println(ex.toString());
            System.exit(1);
        }
        return null;
    }
    
    public String readFile() {
        try {
            FileReader fileReader1 = new FileReader(inputFileName);
            BufferedReader reader1 = new BufferedReader(fileReader1);
            StringBuilder sb = new StringBuilder();
            String line = reader1.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = reader1.readLine();
            }
            return sb.toString();
        } catch (Exception ex1) {
            System.err.println(ex1.toString());
            System.exit(1);
        }
        return null;
    }

    public void write(String outputText) {
        strOutput += outputText;
        try {
            fileWriter = new FileWriter(outputFileName);
            writer = new BufferedWriter(fileWriter);
            writer.write(strOutput);
            writer.flush();
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    public void append(String outputText) throws IOException {
        strOutput += outputText;
        try {
            fileWriter = new FileWriter(outputFileName);
            writer = new BufferedWriter(fileWriter);
            writer.append(strOutput);
            writer.flush();
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    public void close() {
        if (fileReader != null) {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.err.println(e.toString());
            }
        }
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e1) {
                System.err.println(e1.toString());
            }
        }
        if (fileWriter != null) {
            try {
                fileWriter.close();
            } catch (IOException e) {
                System.err.println(e.toString());
            }
        }
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                System.err.println(e.toString());
            }
        }
    }
}