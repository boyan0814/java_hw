/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1;

/**
 *
 * @author boyan
 */
public class Book {
    
    // set private  
    private String  bookname="";
    private String  bookCode="";
    private double bookPrice=0;

    // constructor
    public Book(double a,String b,String c) {

        //put into private 
            this.bookPrice = a;
            this.bookname = c;         
            this.bookCode = b;
            
    }
    public double getPrice(){
        return  bookPrice;//return back  the thing you want
    }
    
    public String  getName(){
        return  bookname;
    }
    
    public String  getCode(){
        return  bookCode;
    }
}
