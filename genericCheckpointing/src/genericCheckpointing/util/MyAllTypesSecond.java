/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genericCheckpointing.util;

/**
 *
 * @author User
 */
public class MyAllTypesSecond extends SerializableObject{
    private double myDoubleT;
    private double myOtherDoubleT;
    private float myFloatT;
    private short myShortT;
    private short myOtherShortT;
    private char myCharT;

    public MyAllTypesSecond(){
        myDoubleT=0.0;
        myOtherDoubleT=0.0;
        myFloatT=0.0f;
        myShortT=0;
        myOtherShortT=0;
    }
    
    public double getMyDoubleT() {
        return myDoubleT;
    }

    public void setMyDoubleT(double myDoubleT) {
        this.myDoubleT = myDoubleT;
    }

    public double getMyOtherDoubleT() {
        return myOtherDoubleT;
    }

    public void setMyOtherDoubleT(double myOtherDoubleT) {
        this.myOtherDoubleT = myOtherDoubleT;
    }

    public float getMyFloatT() {
        return myFloatT;
    }

    public void setMyFloatT(float myFloatT) {
        this.myFloatT = myFloatT;
    }

    public short getMyShortT() {
        return myShortT;
    }

    public void setMyShortT(short myShortT) {
        this.myShortT = myShortT;
    }

    public short getMyOtherShortT() {
        return myOtherShortT;
    }

    public void setMyOtherShortT(short myOtherShortT) {
        this.myOtherShortT = myOtherShortT;
    }

    public char getMyCharT() {
        return myCharT;
    }

    public void setMyCharT(char myCharT) {
        this.myCharT = myCharT;
    }
    
    @Override
    public String toString() {
        String output="MyAllTypesSecond\n";
        output+="myDoubleT:"+myDoubleT+"\n"
                + "myOtherDoubleT:"+myOtherDoubleT+"\n"
                + "myFloatT:"+myFloatT+"\n"
                + "myShortT:"+myShortT+"\n"
                + "myOtherShortT:"+myOtherShortT+"\n"
                + "myCharT:"+myCharT+"\n";   
        return output;
    }
	
	@Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
}