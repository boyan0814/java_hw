/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ntust.mis.oo;

/**
 *
 * @author boyan
 */
public class Goods {
    private String name="";
    private double weight=0;
    private Type type;
    
    public Goods(){
        
    }
    
    public Goods(String name,double weight,Type type){
        this.name=name;
        this.weight=setWeight(weight);
        this.type=type;
    }
    
    private double setWeight(double weight){
        if(weight<0){
            return 0;
        }
        return weight;
    }
    
    public void clearWeight(){
        weight=0;
    } 
    
    public double getWeight(){
        return weight;
    }
    
    @Override
    public String toString(){
        return(name+"-"+weight+"-"+type);
    }
}
