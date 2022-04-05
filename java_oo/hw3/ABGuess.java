/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3;

/**
 *
 * @author boyan
 */
public class ABGuess {
    private int num=0;
    private String temp="";
    public ABGuess(int num){
        this.num=num;
    }
    
    public boolean Checking(String x){
        int tempNum=0;       
        try{
            tempNum=Integer.valueOf(x);
            temp=x; 
            return true;
        }catch(Exception e){
            System.out.println("請輸入數字!!");
            return false;
        }                   
    }
    
    public String takeNum(){
        return temp;
    }
}

