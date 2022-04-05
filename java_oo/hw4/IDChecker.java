/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw4;
import java.util.Scanner;
/**
 *
 * @author boyan
 */
public class IDChecker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        while(true){
            Scanner input=new Scanner(System.in);
            Scanner trySca=new Scanner(System.in,"big5");

            System.out.println("***********************");
            System.out.println("1.    驗證身份證字號");
            System.out.println("2.    產生身份證字號");
            System.out.println("0.    離開");
            System.out.println("***********************");
            System.out.print("請選擇:");
            String inside=input.nextLine();
            ID temp=new ID();
            if(inside.equals("1")){               
                System.out.println();
                System.out.print("請輸入身分證字號:");
                temp.setID(input.nextLine());
                boolean owo=temp.Check();
            }
            
            if(inside.equals("2")){                
                System.out.println();
                System.out.print("請輸入縣市: ");
                String city=trySca.nextLine();
                
                
                System.out.print("請輸入性別: ");
                String sex=trySca.nextLine();
                temp.setSexCity(city, sex);
                temp.Random();
                
            }
            if(inside.equals("0")){break;}          
        }              
        // TODO code application logic here
    }
    
}
