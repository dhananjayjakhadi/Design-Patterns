/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genericCheckpointing.util;

/**
 *
 * @author User
 */
public class MyAllTypesFirst extends SerializableObject{
    private int myInt;
    private int myOtherInt;
    private long myLong;
    private long myOtherLong;
    private String myString;
    private boolean myBool;

    public MyAllTypesFirst(){
        myInt=0;
        myOtherInt=0;
        myLong=0;
        myOtherLong=0;
        myString="";
        myBool=false;
    }
    
    public int getMyInt() {
        return myInt;
    }

    public void setMyInt(int myInt) {
        this.myInt = myInt;
    }

    public int getMyOtherInt() {
        return myOtherInt;
    }

    public void setMyOtherInt(int myOtherInt) {
        this.myOtherInt = myOtherInt;
    }

    public long getMyLong() {
        return myLong;
    }

    public void setMyLong(long myLong) {
        this.myLong = myLong;
    }

    public long getMyOtherLong() {
        return myOtherLong;
    }

    public void setMyOtherLong(long myOtherLong) {
        this.myOtherLong = myOtherLong;
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }

    public boolean isMyBool() {
        return myBool;
    }

    public void setMyBool(boolean myBool) {
        this.myBool = myBool;
    }

    @Override
    public String toString() {
        String output="MyAllTypesFirst\n";
        output+="myInt:"+myInt+"\n"
                + "myOtherInt:"+myOtherInt+"\n"
                + "myLong:"+myLong+"\n"
                + "myOtherLong:"+myOtherLong+"\n"
                + "myString:"+myString+"\n"
                + "myBool:"+myBool+"\n";   
        return output;
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
}
