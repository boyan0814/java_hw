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
public class Caesar {
    //private
    private int key=0;
    private String plain="";
    private String cypher="";   
    
    public Caesar(int key,String plain){
        //put into private
        this.key=key;
        this.plain=plain;
    }       


public String getCypher(){
        String qwq="";
        char temp;//put into the word you take
        String dic="qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm";//dictionary
        int count=0;//count the place of word
        
        while(true){//let it  "Infinite loop"
        try{
        char getA=(char)plain.codePointAt(count);//change the ascii code num to char
        int place=dic.indexOf(getA)+key;//count the word it will be
        while(place>26){
            place-=26;
        }
        temp=dic.charAt(place);
        qwq+=temp;//put char into string"qwq"
        count++;
        }catch(Exception e){  break;  }}//except process
        
        return qwq;
}



}
