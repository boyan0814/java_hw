/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw7;
import java.util.Scanner;
/**
 *
 * @author boyan
 */
public class TicTacToe {
    private boolean o_x=false;
    private String[][] ans={{" "," "," "},{" "," "," "},{" "," "," "}};
    private int EnterTime=0;
    
    public void GameStart(){
        Scanner input=new Scanner(System.in);
        
        System.out.println("********** Game Start **********");
        String whoturn="";
        
        while(EnterTime<9){
            
            if(o_x==true){
                whoturn="O";
            }else{
                whoturn="X";
            }
            
            System.out.printf("Player-%s, enter your move (row[1-3],column[1-3]:",whoturn);
            String locate=input.nextLine();
            String loactelist[]=locate.split(",");
            int num1=Integer.parseInt(loactelist[0]);
            int num2=Integer.parseInt(loactelist[1]);
            
            if(ans[num1-1][num2-1]!=" "){
                System.out.println("The value you entered is invalid! Please try again.");
                continue;
            }

            if(o_x==true){
                ans[num1-1][num2-1]=" O ";
                o_x=false;
            }else{
                ans[num1-1][num2-1]=" X ";
                o_x=true;
            }
            
            printAll();
            Check();
            EnterTime++;
            //System.out.println(num1);     
        }
        if(EnterTime==9){
            System.out.println("Draw");
        }   
        System.out.println("********** Game Over **********");
        System.out.println("Play again(Y/N)?");
        
    }
    
    public void printAll(){
        for(int i=0;i<3;i++){           
            System.out.printf("%3s|%3s|%3s\n",ans[i][0],ans[i][1],ans[i][2]);
             System.out.println("-----------");
        }
    }
    
    public void Check(){
        //判斷直線
        for(int i=0;i<2;i++){
            if(ans[i][0].equals(ans[i][1]) && ans[i][0].equals(ans[i][2])){
                
                if(ans[i][0].equals(" ") || ans[i][1].equals(" ") || ans[i][2].equals(" ")){
                    continue;
                }
                printWin();              
            }
            
            if(ans[0][i].equals(ans[1][i]) && ans[0][i].equals(ans[2][i])){
                
                if(ans[0][i].equals(" ") || ans[1][i].equals(" ") || ans[2][i].equals(" ")){
                    continue;
                }
                printWin();              
            }
        }
        
        //判斷斜線

        if(!ans[1][1].equals(" ")){
            if(ans[0][0].equals(ans[1][1]) && ans[0][0].equals(ans[2][2])){
            printWin();
            }
        }
        
        
        
    }
    public void printWin(){
        String temp="";
        if(o_x==true){
            temp="x";
            }else{
            temp="o";
        }
                
        System.out.println("Player-"+temp+" is the winner!");
        EnterTime+=100;        
                
    }
    
    public void Clear(){
        EnterTime=0;
        for(int i=0;i<3.;i++){
            for(int j=0;j<3;j++){
                ans[i][j]=" ";
            }
        }
        /*ans[0][0]=" ";ans[0][1]=" ";ans[0][2]=" ";
        ans[1][0]=" ";ans[1][1]=" ";ans[1][2]=" ";
        ans[2][0]=" ";ans[2][1]=" ";ans[2][2]=" ";*/
    }
}

