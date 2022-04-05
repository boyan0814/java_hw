/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw2;

import java.util.Scanner;

/**
 *
 * @author boyan
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Input :n =");
        int in_num = input.nextInt();
        System.out.print("Output:" + fib(in_num));     
    }
    public static int fib(int num){
        if(num <= 0){
            return 0;
        }	
	else if(num == 1){
            return 1;
        }	
        else{
            return fib(num-1)+fib(num-2); 
        }	
    }
    
    
}
