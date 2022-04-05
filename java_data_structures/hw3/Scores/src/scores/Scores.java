/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scores;
import java.io.*;
import scores.Sorter;
import scores.BubbleSort;
/**
 *
 * @author boyan
 */
public class Scores {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String[][] arrScore = new String[500][5];
        String path = "C:\\Users\\boyan\\Desktop\\資料結構_hw3\\B10809029_HW3\\scores.txt";
        arrScore = writeFile(path);      
        
        for(int i = 0; i < 500; i++){
            int sumScore = 0;
            for(int j = 1; j < 4; j++){
                sumScore += Integer.parseInt(arrScore[i][j]);
            }
           arrScore[i][4] = String.valueOf(sumScore);
        }
        
        BubbleSort sorter = new BubbleSort();     
        sorter.sort(arrScore);
        display(arrScore);    
    }
    
    public static void display(String x[][]){
        for(int i = 0; i < 500; i++){
            for(String temp:x[i]){
            System.out.print(temp+",");
            }
            System.out.println();
        }
    }
    
    public static String[][] writeFile(String x){
        String temp;
        String[][] tempArr = new String[500][5];
        try{
            String path = x;
            BufferedReader br = new BufferedReader(new FileReader(path));
            int stuNum = 1;
            
            while((temp = br.readLine()) != null){
                int count = 0;
                String[] arrTemp = temp.split(",");
                for(String wrScore:arrTemp){
                    tempArr[stuNum-1][count] = wrScore;
                    count++;
                    if(count >= 4){
                        count -= 4;
                        stuNum++;
                    }
                }                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return tempArr;
    }
}
