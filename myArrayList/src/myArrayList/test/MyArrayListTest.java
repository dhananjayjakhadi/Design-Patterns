/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myArrayList.test;

import myArrayList.MyArrayList;
import myArrayList.util.Results;

/**
 *
 * @author User
 */
public class MyArrayListTest {
    public void testMe(MyArrayList myArrayList, Results results){
        results.storeNewResult(insertValidInt(myArrayList));
        results.storeNewResult(insertInvalidInt(myArrayList));
        results.storeNewResult(removeAvailableInt(myArrayList));
        results.storeNewResult(removeUnavailableInt(myArrayList));
        results.storeNewResult(checkResizableLimit(myArrayList));
        results.storeNewResult(removeFromEmptyList(myArrayList));
        results.storeNewResult(checkSum(myArrayList));
        results.storeNewResult(getTotalCount(myArrayList));
        results.storeNewResult(getTotalCountAfterRemoval(myArrayList));
        results.storeNewResult(getTotalSumAfterRemoval(myArrayList));
    }
    
    public String insertValidInt(MyArrayList myArrayList){
        if(myArrayList.insertSorted(100)==1){
            return "test1 insertValidInt passed";
        }else{
            return "test1 insertValidInt failed";
        }
    }
    
    public String insertInvalidInt(MyArrayList myArrayList){
        if(myArrayList.insertSorted(100000)==-1){
            return "test2 insertInvalidInt passed";
        }else{
            return "test2 insertInvalidInt failed";
        }
    }
    
    public String removeAvailableInt(MyArrayList myArrayList){
        myArrayList.insertSorted(100);
        myArrayList.insertSorted(1500);
        myArrayList.insertSorted(50);
        if(myArrayList.removeValue(100)==1){
            return "test3 removeAvailableInt passed";
        }else{
            return "test3 removeAvailableInt failed";
        }
    }
    
    public String removeUnavailableInt(MyArrayList myArrayList){
        myArrayList.insertSorted(100);
        myArrayList.insertSorted(1500);
        myArrayList.insertSorted(50);
        if(myArrayList.removeValue(15)==-1){
            return "test4 removeAvailableInt passed";
        }else{
            return "test4 removeAvailableInt failed";
        }
    }
    
    public String checkResizableLimit(MyArrayList myArrayList){
        int size=myArrayList.getMaxLimit();
        for(int i=0;i<50;i++){
            myArrayList.insertSorted(i);
        }
		int new_size=myArrayList.getMaxLimit();
        if(new_size>size){
            return "test5 checkResizableLimit passed";
        }else{
            return "test5 checkResizableLimit failed";
        }
    }
    
    public String removeFromEmptyList(MyArrayList myArrayList){
        myArrayList=new MyArrayList();
        if(myArrayList.removeValue(100)==-1){
            return "test6 removeFromEmptyList passed";
        }else{
            return "test6 removeFromEmptyList failed";
        }
    }
    
    public String checkSum(MyArrayList myArrayList){
        myArrayList=new MyArrayList();
        myArrayList.insertSorted(150);
        myArrayList.insertSorted(75);
        myArrayList.insertSorted(500);
        if(myArrayList.sum()==725){
            return "test7 checkSum passed";
        }else{
            return "test7 checkSum failed";
        }
    }
    
    public String getTotalCount(MyArrayList myArrayList){
        myArrayList=new MyArrayList();
        myArrayList.insertSorted(150);
        myArrayList.insertSorted(75);
        myArrayList.insertSorted(500);
        myArrayList.insertSorted(10);
        myArrayList.insertSorted(500);
        if(myArrayList.size()==5){
            return "test8 getTotalCount passed";
        }else{
            return "test8 getTotalCount failed";
        }
    }
    
    public String getTotalCountAfterRemoval(MyArrayList myArrayList){
        myArrayList=new MyArrayList();
        myArrayList.insertSorted(150);
        myArrayList.insertSorted(75);
        myArrayList.insertSorted(500);
        myArrayList.insertSorted(10);
        myArrayList.insertSorted(500);
        
        myArrayList.removeValue(500);
        if(myArrayList.size()==3){
            return "test9 getTotalCountAfterRemoval passed";
        }else{
            return "test9 getTotalCountAfterRemoval failed";
        }
    }
    
    public String getTotalSumAfterRemoval(MyArrayList myArrayList){
        myArrayList=new MyArrayList();
        myArrayList.insertSorted(150);
        myArrayList.insertSorted(75);
        myArrayList.insertSorted(500);
        myArrayList.insertSorted(10);
        myArrayList.insertSorted(500);
        
        myArrayList.removeValue(500);
        if(myArrayList.sum()==235){
            return "test10 getTotalSumAfterRemoval passed";
        }else{
            return "test10 getTotalSumAfterRemoval failed";
        }
    }
}