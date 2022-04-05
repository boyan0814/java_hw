/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3;
import java.util.Scanner;
/**
 *
 * @author boyan
 */
public class Guess {
    private int min=0;//存最小值
    private int max=0;//最大值
    private int time=0;//能猜幾次
    private int num=0;//答案
    private int dic[];//存你猜的答案
    private String temp="";//暫存
    public Guess(int min,int max,int time){
        this.min=min;//將值放入
        this.max=max;
        this.time=time;
        
        num=(int) (Math.random() * (max - min + 1) + min);//RANDOM 出答案
    }
    
    public boolean GameStart(){
        Scanner input=new Scanner(System.in);
        System.out.println("*********** 遊戲開始 ***********");
        int []dic=new int[time];//能猜幾次能存幾個答案
        for(int i=0;i<time;i++){//跑 time 次
            System.out.print("數字範圍: "+min+"~"+max);
            System.out.print("您猜的數字: ");           
            
            int temp=0;
            
            if(Checking(input.nextLine())==false)continue;//輸入例外處理
            temp=Integer.valueOf(takeNum());
            
            dic[i]=temp;
            
            if(temp>num && temp<max)max=temp;//判斷要print出的數字
            if(temp<num && temp>min)min=temp;
            
            if(temp==num){
                System.out.println("恭喜您猜對了!");
                break;
            }                             
            else if(temp>num)
                System.out.println("猜錯了! 您猜的數字比答案大!");
            else if(temp<num)
                System.out.println("猜錯了! 您猜的數字比答案小!");
            else
                System.out.println("猜錯了! 您猜的數字超出答案範圍!");
        } 
        
        System.out.println("*********** 遊戲結束 ***********");
        int i;
        for(i=0;i<dic.length;i++){
            if(dic[i]==0)break;
            System.out.println("第"+(i+1)+"次: "+dic[i]);
        }
        System.out.println("---");
        System.out.println("猜數字限制 "+time+" 次");
        System.out.println("您總共猜了 "+i+" 次");
        System.out.println("正確數字: "+num);
        
        System.out.println("******** 再玩一次(Y/N)? ********");
        //input.nextLine();
        String again=input.nextLine();
        if(again.equals("Y"))
            return true;
        else
            return false;
    }   
    
    public boolean Checking(String x){
        int tempNum=0;       
        try{
            tempNum=Integer.valueOf(x);
            temp=x; 
            return true;
        }catch(Exception e){
            System.out.println("請輸入數字!!");
            return false;
        }                   
    }
    
    public String takeNum(){
        return temp;
    }
}
