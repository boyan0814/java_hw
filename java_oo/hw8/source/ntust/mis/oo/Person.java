/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ntust.mis.oo;

import java.util.Scanner;

/**
 *
 * @author boyan
 */
public class Person {
    private String name="";
    private String phoneNum="";
    private City city;
    
    public Person(String name){
        this.name=name;
        
        String[] citylist={"TAIPEI","TAICHUNG","KAOHSIUNG","HSINCHU","HUALIEN"};
        
        Scanner input=new Scanner(System.in);        
        
        System.out.printf("Please enter the %s's phone:",name);
        phoneNum=input.nextLine(); 
        System.out.printf("Please enter the %s's address(Please Eter 1-5)\n",name);
        System.out.print("(1)TAIPEI (2)TAICHUNG (3)KAOHSIUNG (4)HSINCHU (5)HUALIEN:"); 
        
        int cityNum=0;
        String tempCity="";
        
        while(cityNum==0){
            try{
                cityNum=input.nextInt();                
            }catch(Exception e){
                System.out.println("Please enter 1-5");         
                input.nextLine();
            }

            if(cityNum>5 || cityNum<0){
                System.out.println("Please enter 1-5");
                cityNum=0;
            }    
        }           
        tempCity=citylist[cityNum-1];
        city=City.valueOf(tempCity);          
    }
    
    @Override
    public String toString(){
        return(name+"-"+phoneNum+"-"+city);
    }
    
}
