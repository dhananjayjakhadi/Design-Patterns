package studentCoursesBackup.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import studentCoursesBackup.myTree.Node;
import studentCoursesBackup.myTree.ObserverI;

public class FileProcessor{

	private FileWriter fileWriter;
	private BufferedWriter writer;
	private TreeBuilder tb=new TreeBuilder();
	private String outputFileName;
	private String stringOutputAll="";
	public FileProcessor() {
		// TODO Auto-generated constructor stub
	}

	public String getStringOutputAll() {
		return stringOutputAll;
	}

	public void setStringOutputAll(String stringOutputAll) {
		this.stringOutputAll = stringOutputAll;
	}

	public FileProcessor(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	public TreeBuilder getTb() {
		return tb;
	}

	public void setTb(TreeBuilder tb) {
		this.tb = tb;
	}

	public String readLine(String input) {
		Scanner scr = null;
		String allInputs = "";

		try {
	        scr = new Scanner(new File(input));
	        while (scr.hasNext()) {
	            allInputs+=scr.next()+" ";
	        }
	    }
	    catch(Exception e) {
	        System.out.println("error"+e.getMessage());
	    }

		return allInputs;
	}

	public void readingNodesFromFile(String inputFileName) {
		String inputString = readLine(inputFileName);
		String[] stringNodes = inputString.split(" ");
		for(int i=0;i<stringNodes.length;i++) {
			String eachNode = stringNodes[i];
			String[] separatedNode = eachNode.split(":");
			if(separatedNode[1].charAt(0) < 'L') {
				Node node = new Node(Integer.parseInt(separatedNode[0]));
				List list = new ArrayList();
				list.add(separatedNode[1]);
				node.setCourses(list);
				tb.insert(node);
			}
		}
	}

	public void deletingNodesFromFile(String deleteFileName) {

		String deleteString = readLine(deleteFileName);
		System.out.println(deleteString);

		if(!"".equals(deleteString) && deleteString != null) {
			String[] stringDeleteNodes = deleteString.split(" ");

			for(int i=0;i<stringDeleteNodes.length;i++) {
				String eachNode = stringDeleteNodes[i];
				String[] separatedNode1 = eachNode.split(":");
				System.out.println(separatedNode1[1]);
				tb.delete(Integer.parseInt(separatedNode1[0]),separatedNode1[1]);
			}
		}
	}

	public void write(String outputText) throws IOException {
		stringOutputAll+=outputText;
		System.out.println(outputText);
		try {
			fileWriter = new FileWriter(outputFileName);
			writer = new BufferedWriter(fileWriter);
			writer.write(stringOutputAll);
			writer.flush();
		} catch (IOException e) {
		}
	}

}
