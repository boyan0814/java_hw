/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw12;

/**
 *
 * @author boyan
 */
public abstract class Person {
    private String name="";
    
    public void name(String name){
        this.name=name;
    }
    
    public String getname(){
        return name;
    }
}
