/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3;
import java.util.Scanner;
/**
 *
 * @author boyan
 */
public class GameLauncher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        boolean again=true;
        Guess temp=new Guess(0,0,0);
        while(again){
            int min=0;
            int max=0;
            int time=0;
            Scanner input=new Scanner(System.in);
            System.out.println("********** 猜數字遊戲 **********");
            System.out.print("猜數字範圍最小值: ");          
            
            if(temp.Checking(input.nextLine())==false)continue;
            min=Integer.valueOf(temp.takeNum());
            
            System.out.print("猜數字範圍最大值: ");
            
            if(temp.Checking(input.nextLine())==false)continue;
            max=Integer.valueOf(temp.takeNum());
            
            System.out.print("猜數字次數限制: ");
            
            try{
                time=input.nextInt();
            }catch(Exception e){
                System.out.println("請輸入數字!!");
                continue;
            }
            
            if(max<=min){
                System.out.println("請先輸入'小'再輸入'大'");
                continue;
            }
            
            Guess game=new Guess(min,max,time);
            again=game.GameStart();
            
        }
        
    }
    
}
