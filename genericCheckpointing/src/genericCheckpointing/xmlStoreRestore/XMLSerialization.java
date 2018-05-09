/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.server.SerStrategy;
import genericCheckpointing.util.SerializableObject;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class XMLSerialization implements SerStrategy{
    @Override
    public String processInput(SerializableObject sObject) {
        try {
            Class<?> cls = sObject.getClass();
            Field fields[]=cls.getDeclaredFields();
            String output="<DPSerialization>\n"
                    + "<complexType xsi:type=\""+cls.getName()+"\">\n";
            for(int i=0;i<fields.length;i++){
                Field fld=fields[i];
                fld.setAccessible(true);
                String value=""+fld.get(sObject);
                String field_type=fld.getType().getName();
                if((field_type.equals("int") && Integer.parseInt(value)<10) || (field_type.equals("double") && Double.parseDouble(value)<10) || (field_type.equals("long") && Long.parseLong(value)<10)){
                    continue;
                }
                if(field_type.equals("java.lang.String")){
                    field_type="string";
                }
                String line="<"+fld.getName()+" xsi:type=\"xsd:"+field_type+"\">"+value+"</"+fld.getName()+">\n";
                output+=line;
            }
            return output+="</complexType>\n"
                    + "</DPSerialization>\n";
        } catch (IllegalAccessException ex) {
            Logger.getLogger(XMLSerialization.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(XMLSerialization.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(XMLSerialization.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
}
