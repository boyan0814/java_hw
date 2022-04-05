/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
/**
 *
 * @author boyan
 */
public class ListTableTest {
    public static void main(String[] args){
        Table<Student> table = new ListTable(500);
        
        String path = "querylist.txt";
        String temp;
        
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            while((temp = br.readLine()) != null){
                String[] arrTemp = temp.split(" ");
                table.insertEntry(new Student(arrTemp[0], Integer.parseInt(arrTemp[1])));
            }
        }catch(Exception e){
            e.printStackTrace();
        } 
        
        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.print("請輸入您的完整准考證號碼(輸入0退出) : ");
            String stuId = scanner.nextLine();
            if(stuId.equals("0"))break;
            searchTest(table, stuId);
            
        }    
    }
    
    public static void searchTest(Table<Student> table, String key) {
        Student found = table.retrieveEntry(new Student(key, 0));
        if (found == null) {
            System.out.println(key + " 找不到");
        } else if(found.school == -1){
            System.out.println(found.key + " 未錄取任何學校");
        }else{
            System.out.println(found.key + " 恭喜錄取" + found.school + "號學校");
        }
       
    }	
}
