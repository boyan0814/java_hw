/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import java.io.*;
/**
 *
 * @author pclab
 */
public class Monster {
    private String[] monster;
    private String Mname="";
    private int hp=0;
    
    private String id="";
    public Monster(){
    
    }
    
    public void loadMonster(String fileName){
         try{
            File file=new File(fileName);
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            
            String line=null;
            StringBuilder data =new StringBuilder();
            while((line=br.readLine())!=null){
                data.append(line+"@");
            }
            br.close();
            fr.close();
            
            setAll(data.toString());
        }catch(Exception e){
            System.out.print("qwq");
            e.printStackTrace();           
        }
     }
    
    private void setAll(String str){
        String line[]=str.split("@");
        monster=new String[line.length];
        for(int i=0;i<line.length;i++){ 
            
            monster[i]=line[i];  
            //System.out.println(monster[i]);
            
        }
    }

    public  String setMonster(){
        int monsterId=(int)(Math.random()*17+1);
        id=(monster[monsterId-1]);
        String[] tempMon=id.split(",");
        Mname=tempMon[0];        
        hp=Integer.parseInt(tempMon[1]);
        return(id);
    }
    
    public void setHp(int temp){
        hp-=temp;      
    }
    
    public int getHp(){
        return hp;     
    }
}
