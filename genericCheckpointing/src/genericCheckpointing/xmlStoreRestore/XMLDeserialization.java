/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.server.DeserStrategy;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.SerializableObject;

/**
 *
 * @author User
 */
public class XMLDeserialization implements DeserStrategy{

    @Override
    public SerializableObject processInput(FileProcessor fp) {
        MyAllTypesFirst myFirst = null;
        MyAllTypesSecond mySecond = null;
        SerializableObject sr=null;
        String line;
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
                    return myFirst;
                }else if(name.equals("genericCheckpointing.util.MyAllTypesSecond")){
                    return mySecond;
                }
            }
        }
        return null;
    }
    
}
