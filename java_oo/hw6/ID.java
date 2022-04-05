/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw6;
import java.io.*;
/**
 *
 * @author boyan
 */
public class ID {
    private String id="";
    private String city="";
    private int checkNum=0;
    private String sex="";
    private String wrongStr="";
    private String []Allid;
    private final String []dic={"A","台北市", "1","B","臺中市","10","C","基隆市","19","D","台南市","28","E","高雄市","37","F","新北市","46","G","宜蘭縣","55","H","桃園縣","64",
                      "I","嘉義市","39","J","新竹縣","73","K","苗栗縣","82","L","台中縣", "2","M","南投縣","11","N","彰化縣","20","O","新竹市","48","P","雲林縣","29",
                      "Q","嘉義縣","38","R","台南縣","47","S","高雄縣","56","T","屏東縣","65","U","花蓮縣","74","V","台東縣","83","W","金門縣","21","X","澎湖縣", "3",
                      "Y","陽明山管理局","12","Z","連江縣","30"};       
    
    public boolean Check(){     
        if(id.matches("[A-Z]{1}[1-2]{1}[0-9]{8}")==false){ 
            wrongStr="格式不符";
            return false;
        }
        char []charArr=id.toCharArray();
        String tempstr=new String(charArr,0,1);   
        for(int i=0;i<78;i=i+3){
            if(tempstr.equals(dic[i])){
                checkNum+=Integer.parseInt(dic[i+2]);               
                city=dic[i+1];
            }
        }
        int j=1;
        for(int i=8;i>=0;i--){               
                String temp=new String(charArr,j,1);              
                checkNum+=Integer.parseInt(temp)*i;
                j++;
            }  
        String temp=new String(charArr,9,1);     
        checkNum+=Integer.parseInt(temp);    
        String sexstr=new String(charArr,1,1);
        if(sexstr.equals("1")){
            sex="男性";
        }else{
            sex="女性";
        }        
        if(checkNum % 10==0){
            wrongStr="身份證字號正確";
            return true;
        }else{
            wrongStr="身份證字號錯誤";
        }
        checkNum=0;
        return false;
    }
    
    private void setAll(String str){
        String line[]=str.split("@");
        Allid=new String[line.length];
        for(int i=0;i<line.length;i++){ 
            
            Allid[i]=line[i];  
            System.out.println(Allid[i]);
            
        }
    }
    
    public void readFile(String fileName){
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
    
    public void writeCorrect(String fileName){
        try{
             File file=new File(fileName);
             FileWriter fw=new FileWriter(file);
             BufferedWriter bw=new BufferedWriter(fw);
             for(int i=0;i<Allid.length;i++){
             id=Allid[i];
                if(Check()==true){
                    bw.write(id+", ");
                    bw.write(city+" ");
                    bw.write(sex);
                    bw.newLine();
                }   
             }
             
             bw.close();
             fw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void writeWrong(String fileName){
        try{
             File file=new File(fileName);
             FileWriter fw=new FileWriter(file);
             BufferedWriter bw=new BufferedWriter(fw);
             for(int i=0;i<Allid.length;i++){
             id=Allid[i];
                if(Check()==false){
                    bw.write(id+", ");
                    bw.write(wrongStr);
                    bw.newLine();
                }   
             }
             bw.close();
             fw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}


