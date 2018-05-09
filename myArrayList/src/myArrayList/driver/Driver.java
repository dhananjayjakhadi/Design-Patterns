/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myArrayList.driver;

import java.util.ArrayList;
import myArrayList.MyArrayList;
import myArrayList.test.MyArrayListTest;
import myArrayList.util.FileProcessor;
import myArrayList.util.Results;

/**
 *
 * @author User
 */
public class Driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Results results = new Results();
        if (args.length !=2) {
            System.err.println("Arguments are missing, Please Enter Input File & Output File");
            System.exit(1);
        }

        String inputfile=args[0];
        String outputfile=args[1];
	
        FileProcessor fp=new FileProcessor(outputfile);
        fp.setInputFile(inputfile);
        
        String file_data=fp.readFile();
        if(file_data==null || file_data.trim().length()==0){
            System.err.println("Input File is empty");
            System.exit(1);
        }
        results.setFileProcessor(fp);
        MyArrayList myArrayList=new MyArrayList();
        
        String line=null;
        while((line=fp.readLine())!=null){
            try{
                myArrayList.insertSorted(Integer.parseInt(line));
            }catch(Exception ex){
                System.out.println(ex.toString());
                continue;
            }
        }
        
        String sum="The sum of all the values in the array list is: "+myArrayList.sum();
		System.out.println(sum);
        results.writeToFile(sum);
        
		System.out.println("\nTesting Started\n");
        MyArrayListTest myArrayListTest=new MyArrayListTest();
        myArrayListTest.testMe(myArrayList, results);
        
        ArrayList<String> res_array=results.results;
        String res="";
        for(int i=0;i<res_array.size();i++){
            res+="\n"+res_array.get(i);
        }
		System.out.println(res);
        results.writeToFile(res);
    }
}
