/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw13;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.util.Calendar;
/**
 *
 * @author boyan
 */
public class Time {
    public Time(){
        Date date = new Date();
        SimpleDateFormat hr = new SimpleDateFormat ("HH");
        SimpleDateFormat min = new SimpleDateFormat ("mm");
        
        Scanner input = new Scanner(System.in);        

        
        
        String temp = "";
        boolean setAlarm = true;
        while(setAlarm){
            try{
                System.out.println(date.toString());
                System.out.print("請設定鬧鐘時間(HH:mm:ss):");
                temp = input.next();
                if(!temp.matches("(\\d*):(\\d*):(\\d*)")){
                    throw new TimeFormatException("格式錯誤");
                }
                
                 if(!temp.matches("^(([2]{1}[0-4]{1})|([0-1]{1}[0-9]{1})):(\\d*):(\\d*)")){
                     throw new TimeFormatException("Hour格式錯誤");
                 }else if(!temp.matches("[0-2]{1}[0-9]{1}:[0-5]{1}[0-9]{1}:(\\d*)")){
                     throw new TimeFormatException("Minute格式錯誤");
                 }else if(!temp.matches("[0-2]{1}[0-9]{1}:[0-5]{1}[0-9]{1}:[0-5]{1}[0-9]{1}")){
                     throw new TimeFormatException("Second格式錯誤");
                 }
                 setAlarm = false;
            }catch(TimeFormatException e){
                System.out.println(e+"\n");
            }    
        }
        
        String []strAlarm = temp.split(":");
        
        
        
        System.out.println(strAlarm[0]);
        
        String strHr = hr.format(date);
        int intHr = Integer.parseInt(hr.format(date));
        int intMin = Integer.parseInt(min.format(date));
        
        int countHr,countMin;
        
        if(Integer.parseInt(strAlarm[0])<intHr){
            countHr = Integer.parseInt(strAlarm[0]) - intHr + 24;
        }else{
            countHr = Integer.parseInt(strAlarm[0]) - intHr;
        }

        if(Integer.parseInt(strAlarm[1]) < intMin){
            countMin = Integer.parseInt(strAlarm[1]) - intMin + 60;
            countHr--;
        }else{
            countMin = Integer.parseInt(strAlarm[1]) - intMin ;
        }
        
        System.out.println("已將鬧鐘設定在 " + countHr + "小時又 " + countMin +"後啟動");
            
    }  
}
