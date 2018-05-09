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
public interface SerStrategy {
    String processInput(SerializableObject sObject);
}
