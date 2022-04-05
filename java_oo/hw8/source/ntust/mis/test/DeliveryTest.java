/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ntust.mis.test;
import ntust.mis.oo.*;
import java.util.Scanner;
/**
 *
 * @author boyan
 */
public class DeliveryTest {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        
        String doAgain="y";
        
        while(doAgain.equals("y")){
            Delivery delivery=new Delivery();
            System.gc();
            
            if(delivery.conAgain()==0){
                System.out.print("\n\nProcess finished with exit code 0");
                break;
            }
            
            System.out.println();
            System.out.println("Do you want to continue?(y/n)");
            doAgain=input.nextLine();
        } 
    }
    
}
