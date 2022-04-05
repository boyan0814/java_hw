/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework5;

/**
 *
 * @author boyan
 */
public interface Table<T extends Listable> {
    
        boolean insertEntry(T entry);

	T retrieveEntry(T entry);
}
