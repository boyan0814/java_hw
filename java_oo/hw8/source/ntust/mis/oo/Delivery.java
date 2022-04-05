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
public class Delivery {
    private Person sender;
    private Person receiver;
    private Goods goods;
    private static final double MAX_WEIGHT=100;
    private static double currentWeight=0;
    String[] citylist={"TAIPEI","TAICHUNG","KAOHSIUNG","HSINCHU","HUALIEN"};
    public Delivery(){
        Scanner input=new Scanner(System.in);               
        
        System.out.print("Please enter the sender's name:");
        String senderStr=input.nextLine(); 
        this.sender=new Person(senderStr);       
        
        System.out.println("");
        System.out.print("Please enter the receiver's name:");
        String receiverStr=input.nextLine(); 
        this.receiver=new Person(receiverStr);
        
        System.out.println();
        System.out.print("Please enter good's name:");
        String Gname=input.nextLine();
        System.out.print("Please enter good's weight:");
        double weight=0;
        
        while(weight==0){
            try{
                weight=input.nextDouble();
            }catch(Exception e){
                System.out.println("Please enter number");
                input.nextLine();
            }
        }            

        System.out.println("Please enter good's type");
        System.out.print("(1)BUSSINESS (2)PERSONAL:");
        int typeNum=input.nextInt();
        
        String typeStr="";
        if(typeNum==1){
            typeStr="BUSSINESS";
        }else{
            typeStr="PERSONAL";
        }
        
        Type type=Type.valueOf(typeStr);

        this.goods=new Goods(Gname,weight,type); 
        
        weight=send(weight);
        System.out.println("Enter valid number");
        System.out.println("Previous remaining weight ="+(getAvaliableWeight()+weight));  
        System.out.println("Remain weight left="+getAvaliableWeight());
        
        System.out.println();
        System.out.println("Sender:"+sender.toString());
        System.out.println("Receiver:"+receiver.toString());
        System.out.println("Goods:"+goods.toString());       
        
        //回收
        Person sender=null;
        Person receiver=null;
        Goods goods=null;
    }
    
    public Delivery(Person sender,Person receiver,Goods goods){
        this.sender=sender;
        this.receiver=receiver;
        this.goods=goods;
    }
    
    public double  getAvaliableWeight(){
        return (MAX_WEIGHT-currentWeight);
    }
    
    public double send(double temp){
        if(temp>50){            
            System.out.println("Goods too heavy,will not add product");
            goods.clearWeight();
            return 0;
        }else if((currentWeight+temp)<=MAX_WEIGHT){
            currentWeight=currentWeight+temp;
            return temp;
        }
        return temp;
    }
    
    public int conAgain(){
        if(currentWeight==100){
            System.out.println("Out of available weight,thanks for your using");
            return 0;
        }        
        return 1;
    }
}
