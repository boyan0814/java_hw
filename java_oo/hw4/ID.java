/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw4;

/**
 *
 * @author boyan
 */
public class ID {
    private String id="";
    private String city="";
    private int checkNum=0;
    private String sex="";
    private final String []dic={"A","台北市", "1","B","臺中市","10","C","基隆市","19","D","台南市","28","E","高雄市","37","F","新北市","46","G","宜蘭縣","55","H","桃園縣","64",
                      "I","嘉義市","39","J","新竹縣","73","K","苗栗縣","82","L","台中縣", "2","M","南投縣","11","N","彰化縣","20","O","新竹市","48","P","雲林縣","29",
                      "Q","嘉義縣","38","R","台南縣","47","S","高雄縣","56","T","屏東縣","65","U","花蓮縣","74","V","台東縣","83","W","金門縣","21","X","澎湖縣", "3",
                      "Y","陽明山管理局","12","Z","連江縣","30"};
    public void setID(String input){
        id=input;
    }
    
    public void setSexCity(String City,String Sex){
        city=City;
        sex=Sex;    
    }
    
    public boolean Random(){       
        while(true){
            String randomStr="";
            
            for(int i=1;i<78;i=i+3){
            if(city.equals(dic[i])){
                randomStr+=dic[i-1];
              }
            }    
          
          if(randomStr.equals("")){
              System.out.println("縣市錯誤");
              break;
          }   
          
          if((sex.equals("男") || sex.equals("女"))==false){
              System.out.println("性別錯誤");
              break;
          }
          
          if(sex.equals("男"))randomStr+="1";
          if(sex.equals("女"))randomStr+="2";
          
          for(int i=0;i<8;i++){
              int num=(int) (Math.random() * 8 + 1);
              randomStr+=num;
          }         
          id=randomStr;
          if(CheckRandom()==true)break;
        }    
          return false;  
    }
    
    public boolean CheckRandom(){
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
        
        if(checkNum % 10==0){
            System.out.println(id);
            System.out.println("身份證字號正確");
            System.out.printf("是位出生在%s的%s朋友呢\n\n",city,sex);
            return true;
        }else{       
        return false;
        }
    }
    
    
    public boolean Check(){
        if(id.matches("[A-Z]{1}[1-2]{1}[0-9]{8}")==false){ 
            System.out.println("格式不符");
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
            System.out.println("身份證字號正確");
            System.out.printf("是位出生在%s的%s朋友呢\n\n",city,sex);
            return true;
        }else{
        System.out.println("身份證字號錯誤");
        return false;
        }     
    }
}


