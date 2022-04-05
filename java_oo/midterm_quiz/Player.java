/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import java.io.*;
import java.util.Scanner;
/**
 *
 * @author pclab
 */
public class Player {
    private String name="";
    private int hp=500;
    private int coin=100;

    private boolean MonsterAlive=false;
    private String Mname="";
    private int Mhealth=0;
    
    Monster monster=new Monster();
    
    public void setName(String temp){
        monster.loadMonster("monster.txt");
        name=temp;       
        System.out.println("");
        System.out.println("設定完成 !");
    }
    
    public void getComlete(){
        System.out.println("***目前玩家資訊***");
        System.out.println("名稱:"+name);
        System.out.println("生命值:"+hp);
        System.out.println("金幣:"+coin);
        System.out.println("*****************");
    }
    
    
    
     public int Fight(){
         if(MonsterAlive==false){
            int monsterId=(int)(Math.random()*17+1);
            String id=monster.setMonster();
            String[] tempMon=id.split(",");

            Mname=tempMon[0];
            Mhealth=Integer.parseInt(tempMon[1]);
            MonsterAlive=true;
         }

         
         System.out.println("***目前怪物資訊***");
         System.out.println("名稱:"+Mname);
         System.out.println("生命值:"+Mhealth);
         System.out.println("*****************");
         
         int playAtack=(int)(Math.random()*10+10);
         int monAtak=(int)(Math.random()*10+40);
         monster.setHp(playAtack);
         Mhealth=monster.getHp();
         System.out.println("怪物受到 -"+playAtack+"攻擊 (生命值剩餘:"+Mhealth+")");
         if(Mhealth<=0){
             System.out.println("Victory !");
             coin+=100;
             MonsterAlive=false;
             return 0;
         }
         hp-=monAtak;
         System.out.println("您受到 -"+monAtak+"攻擊 (生命值剩餘:"+hp+")");
         if(hp<=0){
             System.out.println("Defeat !");
             return 1;
         }
         
         return 0;
     }
     
     public int setHp(){
         Scanner input=new Scanner(System.in);
         System.out.print("請輸入購買紅水晶(補充10生命值)數量:");
         int num=0;
         try{
             num=Integer.parseInt(input.nextLine()) ;
         }catch(NumberFormatException e){
             System.out.println("已取消購買");
             return 0;
         }
         if(coin<num*10){
             System.out.println("金幣不足，請透過打怪賺取更多金幣。");
             return 0;
         }    
         System.out.print("確認以"+(10*num)+"金幣購買"+num+"份紅水晶(Y/N)");  
         String temp=input.nextLine();
         
         System.out.println();
         if(!temp.equals("Y")){
             System.out.println("已取消購買");
             return 0;
         }
         
         
         coin-=num*10;
         hp+=num*10;
         System.out.println("購買成功");       
         return 0;
     }
    
}
