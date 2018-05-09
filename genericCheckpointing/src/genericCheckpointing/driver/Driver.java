/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genericCheckpointing.driver;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author User
 */
public class Driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {	
	if(args.length!=3){
            System.err.println("Arguments are missing, Enter Mode, Number of Objects and File Name.");
            System.exit(1);
        }
        
        String mode=args[0];
        if(!mode.equals("serdeser") && !mode.equals("deser")){
            System.err.println("Unknown mode");
            System.exit(1);
        }
        
        int NUM_OF_OBJECTS=Integer.parseInt(args[1]);
        String fileName=args[2];
        
        //System.out.println(fileName);
        // FIXME: read the value of checkpointFile from the command line
	
	ProxyCreator pc = new ProxyCreator();
	
	// create an instance of StoreRestoreHandler (which implements
	// the InvocationHandler
	
        StoreRestoreHandler handler=new StoreRestoreHandler();
        handler.setCheckpointFile(fileName);
	
        // create a proxy
	StoreRestoreI cpointRef = 
                (StoreRestoreI) pc.createProxy(new Class[]{StoreI.class, RestoreI.class}, handler);
		
	// FIXME: invoke a method on the handler instance to set the flie name for checkpointFile and open the file
	MyAllTypesFirst myFirst = null;
	MyAllTypesSecond  mySecond = null;

	// Use an if/switch to proceed according to the command line argument
	// For deser, just deserliaze the input file into the data structure and then print the objects
	// The code below is for "serdeser" mode
	// For "serdeser" mode, both the serialize and deserialize functionality should be called.

	// create a data structure to store the objects being serialized
        // NUM_OF_OBJECTS refers to the count for each of MyAllTypesFirst and MyAllTypesSecond
        
       
        FileProcessor fp=new FileProcessor();
        fp.setInputFile(fileName);
        String line;
        Vector<SerializableObject> vector=new Vector<>();
        SerializableObject sr=null;
        while((line=fp.readLine())!=null){
            if(line.contains("xsi:type=\"")){
                int pos=line.indexOf("xsi:type=\"")+"xsi:type=\"".length();
                String type=line.substring(pos,line.lastIndexOf("\""));
                if(type.equals("genericCheckpointing.util.MyAllTypesFirst")){
                    sr=new MyAllTypesFirst();
                }else if(type.equals("genericCheckpointing.util.MyAllTypesSecond")){
                    sr=new MyAllTypesSecond();
                }
                Class c=sr.getClass();
                String name=c.getName();
                String tag=line.substring(line.indexOf("<")+1,line.indexOf(" x"));
                if(tag.equals("complexType")){
                    myFirst = new MyAllTypesFirst();                    
                    mySecond = new MyAllTypesSecond();
                    continue;
                }
                if(name.equals("genericCheckpointing.util.MyAllTypesFirst")){
                    String tag_val=line.substring(line.indexOf(">")+1,line.indexOf("</"));
                    if(tag.equals("myInt")){
                        myFirst.setMyInt(Integer.parseInt(tag_val));
                    }else if(tag.equals("myOtherInt")){
                        myFirst.setMyOtherInt(Integer.parseInt(tag_val));
                    }else if(tag.equals("myLong")){
                        myFirst.setMyLong(Long.parseLong(tag_val));
                    }else if(tag.equals("myOtherLong")){
                        myFirst.setMyLong(Long.parseLong(tag_val));
                    }else if(tag.equals("myString")){
                        myFirst.setMyString(tag_val);
                    }
                }else if(name.equals("genericCheckpointing.util.MyAllTypesSecond")){
                    String tag_val=line.substring(line.indexOf(">")+1,line.indexOf("</"));
                    if(tag.equals("myDoubleT")){
                        mySecond.setMyDoubleT(Double.parseDouble(tag_val));
                    }else if(tag.equals("myOtherDoubleT")){
                        mySecond.setMyOtherDoubleT(Double.parseDouble(tag_val));
                    }else if(tag.equals("myFloatT")){
                        mySecond.setMyFloatT(Float.parseFloat(tag_val));
                    }else if(tag.equals("myShortT")){
                        mySecond.setMyShortT(Short.parseShort(tag_val));
                    }else if(tag.equals("myOtherShortT")){
                        mySecond.setMyOtherShortT(Short.parseShort(tag_val));
                    }else if(tag.equals("myCharT")){
                        mySecond.setMyCharT(tag_val.charAt(0));
                    }
                }                
            }else if(line.contains("</complexType>")){
                Class c=sr.getClass();
                String name=c.getName();
                if(name.equals("genericCheckpointing.util.MyAllTypesFirst")){
                    vector.add(myFirst);
                }else if(name.equals("genericCheckpointing.util.MyAllTypesSecond")){
                    vector.add(mySecond);
                }
            }
        }
        
        if(mode.equals("serdeser")){
            for (int i=0; i<NUM_OF_OBJECTS; i++) {
                // FIXME: create these object instances correctly using an explicit value constructor
                // use the index variable of this loop to change the values of the arguments to these constructors
                //myFirst = new MyAllTypesFirst();
                //mySecond = new MyAllTypesSecond();

                SerializableObject a=vector.get(i);
                String class_name=a.getClass().getName();
                // FIXME: store myFirst and mySecond in the data structure
                if(class_name.equals("genericCheckpointing.util.MyAllTypesFirst")){
                    myFirst=(MyAllTypesFirst) a;
                    ((StoreI) cpointRef).writeObj(myFirst, i+1,"XML");
                }else if(class_name.equals("genericCheckpointing.util.MyAllTypesSecond")){
                    mySecond=(MyAllTypesSecond) a;
                    ((StoreI) cpointRef).writeObj(mySecond, i+1,"XML");
                }
            }

            SerializableObject myRecordRet;
            Vector<SerializableObject> vector1=new Vector<>();
            // create a data structure to store the returned ojects
            for (int j=0; j<NUM_OF_OBJECTS; j++) {
                myRecordRet = ((RestoreI) cpointRef).readObj("XML");
                String class_name=myRecordRet.getClass().getName();
                // FIXME: store myFirst and mySecond in the data structure
                if(class_name.equals("genericCheckpointing.util.MyAllTypesFirst")){
                    myFirst=(MyAllTypesFirst) myRecordRet;
                    vector1.add(myFirst);
                }else if(class_name.equals("genericCheckpointing.util.MyAllTypesSecond")){
                    mySecond=(MyAllTypesSecond) myRecordRet;
                    vector1.add(mySecond);
                }
                // FIXME: store myRecordRet in the vector
            }

            // FIXME: invoke a method on the handler to close the file (if it hasn't already been closed)
            handler.close();

            // FIXME: compare and confirm that the serialized and deserialzed objects are equal. 
            // The comparison should use the equals and hashCode methods. Note that hashCode 
            // is used for key-value based data structures
            int mismatched_objects=0;
            for(int i=0;i<NUM_OF_OBJECTS;i++){
                if(!vector.get(i).equals(vector.get(i)) && vector.hashCode()!=vector1.hashCode()){
                    mismatched_objects++;
                }
            }
            System.out.println(mismatched_objects+" mismatched objects");
        }else if(mode.equals("deser")){
            for (int i=0; i<NUM_OF_OBJECTS; i++) {
                // FIXME: create these object instances correctly using an explicit value constructor
                // use the index variable of this loop to change the values of the arguments to these constructors
                
                SerializableObject a=vector.get(i);
                String class_name=a.getClass().getName();
                // FIXME: store myFirst and mySecond in the data structure
                if(class_name.equals("genericCheckpointing.util.MyAllTypesFirst")){
                    myFirst=(MyAllTypesFirst) a;
                    System.out.println(myFirst.toString());
                }else if(class_name.equals("genericCheckpointing.util.MyAllTypesSecond")){
                    mySecond=(MyAllTypesSecond) a;
                    System.out.println(mySecond.toString());
                }
            }
        }
    }
}