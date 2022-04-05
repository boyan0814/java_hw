/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import java.util.Scanner;
/**
 *
 * @author pclab
 */
public class Launcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        Player player=new Player();
        Monster monster=new Monster();
    
        String mainChoose="";
        boolean start=false;
        System.out.println("*****LegendsGame*****");
        System.out.println("1. 設定玩家資料");
        System.out.println("2. 顯示玩家資料");
        System.out.println("3. 開始攻擊");
        System.out.println("4. 商店");
        System.out.println("0. 離開");
        
        while(!mainChoose.equals("0")){
            
            System.out.print("請輸入選項[1,2,3,4,0]:");
            mainChoose=input.nextLine();

            if(mainChoose.equals("1")){
                System.out.print("請輸入玩家名稱:");
                String name=input.nextLine();
                player.setName(name);
                start=true;
            }else if(start==true){
                if(mainChoose.equals("2")){
                    player.getComlete();
                }

                if(mainChoose.equals("3")){
                    if(player.Fight()==1){
                        mainChoose="0";
                    }
                }

                if(mainChoose.equals("4")){            
                    player.setHp();
                }
            }else if(start==false){
                System.out.println("尚未設定玩家名稱。:");
            }
    
        }
        
        // TODO code application logic here
    }
    
}
