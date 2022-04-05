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
public class HashTable<T extends Hashable>  implements Table<T> {
    
        boolean quadraticProbing;
        T[] data;
    
        public HashTable(int capacity) {
            data = (T[])new Hashable[capacity];
            quadraticProbing = false;
        }
        
        public HashTable setQuadratic(boolean enable) {
		quadraticProbing = enable;
		return this;
	}
    
    public boolean insertEntry(T entry){
        int increment = 1;
        int probe = entry.hashing() % data.length;
        int halfSize = data.length / 2;
        int Size = data.length;
        for (int index = 0; index <= halfSize; index++){
            if(data[probe] == null){
                data[probe] = entry;
                return true;
            } else if (entry.equals(data[probe])) {
                System.out.printf("ERROR: cannot insert duplicated entry\n");
                return false;
            }
            probe = (probe + increment) % data.length;
            if (quadraticProbing) {
                increment += 2;
            }
        }
        
        return false;
    }
    
    public T retrieveEntry(T entry){
        int increment = 1;
        int probe = entry.hashing() % data.length;
        int Size = data.length;
        try{
            for (int index = 0; index <= data.length / 2; index++) {
                if (data[probe] == null) {
                    // empty position found
                    return null;
                } else if (entry.equals(data[probe])) {
                    return data[probe];
                }
                probe = (probe + increment) % data.length;
                if (quadraticProbing) {
                    increment += 2;
                }
            }
        }catch(Exception e){
            return null;
        }
        return null;
        
    }
    
    void search(T entry){
            T found = retrieveEntry(entry);
            if (found == null) {
                System.out.printf("%s not found\n", entry);
            } else {
                System.out.println(found + " found.");
            }
    }
}
