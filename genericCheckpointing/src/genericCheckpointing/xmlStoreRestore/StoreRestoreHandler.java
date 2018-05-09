/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.server.DeserStrategy;
import genericCheckpointing.server.SerStrategy;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class StoreRestoreHandler implements InvocationHandler{
    FileProcessor fp;
    String fileName;
    Vector<SerializableObject> vector=new Vector<>();
    public StoreRestoreHandler(){}
    
    public void setCheckpointFile(String fileName){
        this.fileName=fileName;
        fp=new FileProcessor(fileName);
        fp.setInputFile(fileName);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        if(method.getName().equals("writeObj")){
            String output=serializeData((SerializableObject) args[0], new XMLSerialization());
            try {
                fp.append(output);
            } catch (IOException ex) {
                Logger.getLogger(StoreRestoreHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(method.getName().equals("readObj")){
                return deserializeData(fp, new XMLDeserialization());
        }
        return null;
    }
    public String serializeData(SerializableObject sObject, SerStrategy sStrategy) {
        return sStrategy.processInput(sObject);
    }
    
    public SerializableObject deserializeData(FileProcessor fp,DeserStrategy dStrategy){
        return dStrategy.processInput(fp);
    }
    
    public void close(){
        fp.close();
    }
}
