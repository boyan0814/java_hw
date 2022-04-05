/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw7;
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
        
        TicTacToe temp=new TicTacToe();
        Scanner input = new Scanner(System.in);
        while(true){
            temp.GameStart();
            temp.Clear();
            String tempStr=input.nextLine();
            tempStr=tempStr.toUpperCase();
            if(tempStr.equals("N")){
                break;
            }
        }
        
        
    }
    
}
