/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw5;
/**
 *
 * @author boyan
 */
public class TimeLength {
    
    private int time = 0;
    private String prTime="";
    private int hr=0;
    private int min=0;
    private int sec=0;
    
    public TimeLength(){
        time = 0;
    }
    
    public  void setTime(int tm){
        time = tm;
    }
    
    public void setTime(int hr,int min,int sec){
        time = hr*3600+min*60+sec;
    }
    
    public void lessTimeComple(boolean addless,int hr,int min,int sec){
        if(addless==false){
            time=time-(hr*3600+min*60+sec);
        }else{
            time=time+(hr*3600+min*60+sec);
        }       
    }
    
    public void lessTimeSim(int tm){
            time+=tm;

    }
    public void printAll(){
        System.out.println("The current length of time <seconds> :"+time);
        int hr=0;
        int min=0;
        int sec=0;
        boolean signNum=true;
        if(time>=0){
            sec=time;
           
        }else{
            sec=0-time;
            signNum=false;
        }
         while(sec>=3600){
                hr++;
                sec-=3600;
             }
        
             while(sec>=60){
                min++;
                sec-=60;
            }
        
        String []combo=new String[3];
        combo[0]=Integer.toString(hr);
        combo[1]=Integer.toString(min);
        combo[2]=Integer.toString(sec);
         System.out.print("The current length of time <seconds> :");
         if(signNum==false)System.out.print("-");
         
        for(int i=0;i<3;i++){
            if(combo[i].matches("[0-9]")==true){
                System.out.print("0");
            }
            System.out.print(combo[i]+":");
        }
    }
}
