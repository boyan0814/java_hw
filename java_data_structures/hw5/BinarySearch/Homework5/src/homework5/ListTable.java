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
        int stuId =Integer.valueOf(entry.getKey());
        int topSite = 500;
        int lowSite = 0;
        int tempSize;
        try{
            while(true){ 
                tempSize = (lowSite + topSite) / 2;
                if(data[tempSize] == null){
                    return null;
                }else if(entry.equals(data[tempSize])){
                    return data[tempSize];
                }else if(stuId > Integer.valueOf(data[tempSize].getKey())){
                    lowSite = tempSize;
                }else if(stuId < Integer.valueOf(data[tempSize].getKey())){
                    topSite = tempSize;
                }else{
                    return null;
                }
            }
            
        }catch(Exception e){
            return null;
        }    
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
