/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1;
import java.util.Scanner;
/**
 *
 * @author boyan
 */
public class CaesarTest {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        
        int key=0;
         String plain="";
        
        System.out.print("Enter key=");
        key=input.nextInt();
        
        plain=input.nextLine();//let /n disappear
        
        System.out.print("Enter plain=");
        plain=input.nextLine();
        plain=plain.toLowerCase();//let it turn into lower case
        
       Caesar owo=new Caesar(key,plain);
       
       System.out.print(owo.getCypher());//print out
       
    }
    
}
