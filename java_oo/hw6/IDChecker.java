/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw6;
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
        
            ID temp=new ID();
            temp.readFile("C:\\Users\\boyan\\Desktop\\IDChecker\\src\\hw4\\input.txt");
            temp.writeCorrect("C:\\Users\\boyan\\Desktop\\IDChecker\\src\\hw4\\correct.txt");
            temp.writeWrong("C:\\Users\\boyan\\Desktop\\IDChecker\\src\\hw4\\error.txt");
        }              
        // TODO code application logic here
    }
    

