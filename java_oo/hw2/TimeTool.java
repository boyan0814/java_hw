/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw2;

/**
 *
 * @author boyan
 */
import java.util.Date;
import java.io.*;
public class TimeTool {
	
	/**
	 * showCurrentTime：顯示目前時間
	 */
	public static void showCurrentTime() {
		Date now = new Date(); // 建立 Date 物件會記錄目前的時間(初始化)
		System.out.println(now); // 可直接印出 Date 物件所記錄的時間資訊
	}

	/**
	 * isLeapYear：判斷指定年份是否為閏年
	 * 
	 * @param  int year
	 * @return boolean
	 */
	public static boolean isLeapYear(int year) {
		boolean isLeapYear = false;
		/*
		 * 判斷是否為閏年的規則如下，滿足其中一個即為閏年：
		 * 1. 西元年是 4 的倍數，但不是 100 的倍數，例如：1996 年。
		 * 2. 西元年是 400 的倍數，例如：2000 年。
		 */	
		if((year % 4) == 0 && (year % 100) != 0 || (year % 400) == 0)
			isLeapYear = true;
		
		return isLeapYear;
	}
        public static void calander(int y,int m){
            
            int totalday=0;//總共日
            int day=0;
            for(int i=0;i<y;i++) {//從1990開加
                boolean leap = isLeapYear(i);//是否為閏年
                if(leap==true){
                    totalday+=366;//加total日
                }else {
                    totalday+=365;
                }
            }
            
            boolean leap = isLeapYear(y);
                for(int j=1;j<=m;j++){
                     
                    int [] dic={0,31,28,31,30,31,30,31,31,30,31,30,31};//每月的總日數(平年
                    if(leap==true)dic[2]=29;  //閏年二月便29日
                    day=dic[j];//取出日數                  
                    
                    if(j!=m){
                    totalday+=day;
                    }
                }
            System.out.println("Sun\tMon\tTue\tWed\tThu\tFri\tSat");   
            int spaceday=0;
            spaceday=-1+totalday%7;//設預定值
            if(spaceday==7)spaceday=0;
            
            for(int i=0;i<spaceday;i++){
                System.out.print("\t");//print空格
            }
            
            for(int i=1;i<=day;i++){
                System.out.print(i+"\t");
                if((i+spaceday)%7==0)System.out.println();//print日期
            }
            System.out.println();
        }
    }

