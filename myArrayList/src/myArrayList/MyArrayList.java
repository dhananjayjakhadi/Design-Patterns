/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myArrayList;

import java.util.Arrays;

/**
 *
 * @author User
 */
public class MyArrayList {
    private int[] array;
    private int[] temp;
    private int size;
    private int count;
    public MyArrayList(){
        size=50;
        count=0;
        array=new int[size];
        
        for(int i=0;i<array.length;i++){
            array[i]=-1;
        }
    }
    
    public void resize(){
        temp=new int[size];
        for(int i=0;i<count;i++){
            temp[i]=array[i];
        }
        
        size+=size/2;
        array=new int[size];
        
        for(int i=0;i<count;i++){
            array[i]=temp[i];
        }
    }
    
    public int insertSorted(int newValue){
        if(newValue<0 || newValue>10000){
            System.err.println("Value is not in 0-10000 range");
            return -1;
        }
        if(size==count){
            resize();
        }
        array[count]=newValue;
        count++;
        Arrays.sort(array,0,count);
        
        return 1;
    }
    
    public int removeValue(int value){
        if(count==0){
            System.err.println("Array is empty");
            return -1;
        }
        int index=indexOf(value);
        if(index==-1){
            System.err.println("Value not found");
            return -1;
        }else{
            for(int i=0;i<size;i++){
                if(array[i]==value){
                    int j=i;
                    for(;j<size-1;j++){
                        array[j]=array[j+1];
                    }
                    array[j]=-1;
                    count--;
                    i--;
                }
            }
            Arrays.sort(array,0,count);
            return 1;
        }
    }
    
    public int indexOf(int value){
        for(int i=0;i<array.length;i++){
            if(array[i]==value){
                return i;
            }
        }
        return -1;
    }
    
    public int size(){
        int total=0;
        for(int i=0;i<array.length;i++){
            if(array[i]!=-1){
                total++;
            }
        }
        return total;
    }
    
    public int sum(){
        int sum=0;
        for(int i=0;i<array.length;i++){
            if(array[i]!=-1){
                sum+=array[i];
            }
        }
        return sum;
    }
    
    public String toString(){
        String str="";
        for(int i=0;i<size;i++){
            if(array[i]!=-1){
                str+=array[i]+"\n";
            }
        }
        return str;
    }
    
    public int getMaxLimit(){
        return array.length;
    }
}