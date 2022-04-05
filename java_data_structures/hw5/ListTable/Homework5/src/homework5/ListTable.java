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
public class ListTable<T extends Listable>  implements Table<T> {
    
        T[] data;
    
        public ListTable(int capacity) {
            data = (T[])new Listable[capacity];
        }
    
    public boolean insertEntry(T entry){
        
        int Size = data.length;
        for (int index = 0; index <= Size; index++){
            if(data[index] == null){
                data[index] = entry;
                return true;
            }
        }
        
        return false;
    }
    
    public T retrieveEntry(T entry){
        int Size = data.length;
        try{
            for (int index = 0; index <= Size; index++){
                if(data[index] == null){
                    return null;
                }else if(entry.equals(data[index])){
                    return data[index];
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
