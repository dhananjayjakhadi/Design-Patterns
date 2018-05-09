/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genericCheckpointing.server;

import genericCheckpointing.util.SerializableObject;

/**
 *
 * @author User
 */
public interface RestoreI extends StoreRestoreI{
    SerializableObject readObj(String wireFormat);
}
