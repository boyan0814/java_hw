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
import java.util.Scanner;

public class TimeLengthTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        TimeLength useTime = new TimeLength();
        Scanner input = new Scanner(System.in);
        String mainNum = "";
        String time="";        
        String addTime="";
        boolean comple=false;
        do
        {
            System.out.println("***Convert Length of Time***");
            System.out.println("1) Set a length of time.");
            System.out.println("2) Adjust the Length of time.");
            System.out.println("3) Show length of time in different units.");
            System.out.println("0) Exit\n");
            System.out.print("Please enter a number in [1,2,3,0]:");
            mainNum = input.next();
            
            if(mainNum.matches("[0-3]")){
                int num = Integer.parseInt(mainNum);
                switch(num){
                    case 1:
                        System.out.print("Please enter a time <seconds or hh:mm:ss>:");
                        time = input.next();
                        
                        if(time.matches("\\d+[:]\\d+[:]\\d+")){                            
                            if(time.matches("[0-9][0-9][:][0-9][0-9][:][0-9][0-9]")==false){
                                System.out.println("Please enter XX:XX:XX");
                                break;
                            }
                            
                            comple=true;
                            String[] units = time.split("[:]");
                            
                            int hr  = Integer.parseInt(units[0]);
                            int min = Integer.parseInt(units[1]);
                            int sec = Integer.parseInt(units[2]);
                            useTime.setTime(hr,min,sec);
                          
                            System.out.println("Time:"+units[0]+":"+units[1]+":"+units[2]+"\n");          
                            
                        }else{
                            useTime.setTime(Integer.parseInt(time));   
                            System.out.println("Time:"+time+"\n");      
                        }
                        break;
                    case 2:
                        System.out.print("Please enter a time <seconds or hh:mm:ss>:");
                        addTime=input.next();
                        
                        boolean addless=true;  
                        if(comple==true && addTime.matches("[-]?\\d+[:]\\d+[:]\\d+")){
                            if(addTime.charAt(0)=='-')addless=false;
                            
                            addTime=addTime.replace("-", "");
                            String[] units = addTime.split(":");
                            int hr  = Integer.parseInt(units[0]);
                            int min = Integer.parseInt(units[1]);
                            int sec = Integer.parseInt(units[2]);

                            useTime.lessTimeComple(addless,hr,min,sec);
                            
                            System.out.print("Time:");
                            if(addless==false){
                                System.out.print("-");
                            }
                            System.out.print(units[0]+":"+units[1]+":"+units[2]+"\n"); 
                            System.out.println();
                            
                        }else{
                            useTime.lessTimeSim(Integer.parseInt(addTime));
                            
                            System.out.println("Time:"+addTime+"\n"); 
                        }
                        break;
                    case 3:
                        useTime.printAll();
                        System.out.println();
                }
            }
            
        }while(!mainNum.equals("0"));
        
        
        // TODO code application logic here
    }
    
}
