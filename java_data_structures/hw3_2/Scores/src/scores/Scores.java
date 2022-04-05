package scores;
import java.io.*;
import scores.Sorter;
import scores.BubbleSort;
/**
 *
 * @author boyan
 */
public class Scores {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String[][] arrScore = new String[500][5];
        String[][] unMoveName = new String[500][5];
        
        String[][] arrWish = new String[500][12];
        String[][] arrSchool = new String[10][2];
        
        String[] arrFinalChoose = new String[500];
                
        String path = "scores.txt";
        String wishpath = "wish.txt";
        String schoolpath = "school.txt";
        
        arrScore = writeFile(path);
        unMoveName = writeCompleteFile(path);
        arrWish = writeWish(wishpath);
        arrSchool = writeSchool(schoolpath);
        
        arrScore = sumScore(arrScore);
        unMoveName = sumScore(unMoveName);
        
        /*for(int i = 0; i < 500; i++){
            int sumScore = 0;
            for(int j = 1; j < 4; j++){
                sumScore += Integer.parseInt(arrScore[i][j]);
            }
           arrScore[i][4] = String.valueOf(sumScore);
        }
        
        for(int i = 0; i < 500; i++){
            int sumScore = 0;
            for(int j = 1; j < 4; j++){
                sumScore += Integer.parseInt(unMoveName[i][j]);
            }
           unMoveName[i][4] = String.valueOf(sumScore);
        }*/
        
        
        BubbleSort sorter = new BubbleSort();     
        sorter.sort(arrScore);
        arrFinalChoose = chooseSchool(arrScore,arrWish,arrSchool);
        
        //display(arrScore); 
        System.out.println("-----------------------------");
        //display(arrWish); 
        //displaySchool(arrSchool);
        displayFinal(arrFinalChoose,unMoveName); 
        
    }
    
    public static void display(String x[][]){
        for(int i = 0; i < 500; i++){
            for(String temp:x[i]){
            System.out.print(temp+",");
            }
            System.out.println();
        }
    }
    
    public static void displaySchool(String x[][]){
        for(int i = 0; i < 10; i++){
            for(String temp:x[i]){
            System.out.print(temp+",");
            }
            System.out.println();
        }
    }
    
    public static void displayFinal(String x[], String unMoveName[][]){
        for(int i = 1;i < 11; i++){
            System.out.println("第"+i+"所學校錄取考生編號");
            for(int j = 0; j < 500; j++){
                if(x[j].equals(Integer.toString(i))){
                    System.out.println(unMoveName[j][0]);
                }   
            }
        }
        System.out.println("-----落榜-----");
        for(int j = 0; j < 500; j++){ 
            if(x[j].equals(Integer.toString(0))){
                System.out.println(unMoveName[j][0]);
            }   
        }
        System.out.println("-------------------------");
        System.out.println("編號\t總成績\t錄取學校");
        for(int i = 0;i < 500;i++){
            System.out.println(unMoveName[i][0] +"\t" + unMoveName[i][4] + "\t" + x[i]);
        }
        
    }
    
    public static String[][] writeFile(String x){
        String temp;
        String[][] tempArr = new String[500][5];
        try{
            String path = x;
            BufferedReader br = new BufferedReader(new FileReader(path));
            int stuNum = 1;
            
            while((temp = br.readLine()) != null){
                int count = 0;
                String[] arrTemp = temp.split(",");
                for(String wrScore:arrTemp){
                    if(count == 0){
                        //移除 "A"
                        wrScore = wrScore.replace("A", "");
                        int intScore = Integer.parseInt(wrScore);
                        wrScore = Integer.toString(intScore);
                    }
                    
                    tempArr[stuNum-1][count] = wrScore;
                    count++;
                    if(count >= 4){
                        count -= 4;
                        stuNum++;
                    }
                }                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return tempArr;
    }
    
    public static String[][] sumScore(String x[][]){
        for(int i = 0; i < 500; i++){
            int sumScore = 0;
            for(int j = 1; j < 4; j++){
                sumScore += Integer.parseInt(x[i][j]);
            }
           x[i][4] = String.valueOf(sumScore);
        }
        return x;
    }
    
    public static String[][] writeWish(String x){
        String[][] tempArr = new String[500][12];
        String temp;
        int num = 0;
        try{
            String path = x;
            BufferedReader br = new BufferedReader(new FileReader(path));
            while((temp = br.readLine()) != null){
                String[] arrTemp = temp.split(" ");
                int count = 0;
                for(String strWish:arrTemp){
                    tempArr[num][count] = strWish;
                    count++;
                    if(strWish.equals("0"))num++;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return tempArr;
    }
    
    public static String[][] writeSchool(String x){
        String[][] tempArr = new String[10][2];
        String temp;
        int num = 0;
        try{
            String path = x;
            BufferedReader br = new BufferedReader(new FileReader(path));
            while((temp = br.readLine()) != null){
                String[] arrTemp = temp.split(" ");
                int count = 0;
                for(String strSchool:arrTemp){
                    tempArr[num][count] = strSchool;
                    count++;
                    if(count == 2){
                        num++;
                        count = 0;
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return tempArr;
    }
    
    public static String[] chooseSchool(String arrScore[][],String arrWish[][], String arrSchool[][]){
        String[] arrFinalChoose = new String[500];
        for(int i = 0;i < 500;i++){
            int stuNum = Integer.parseInt(arrScore[i][0]);
            String[] tempWish = arrWish[stuNum-1];
            for(int j = 1;j < 12;j++){
                int wishSchool = Integer.parseInt(tempWish[j]);
                
                if(wishSchool == 0){
                    arrFinalChoose[stuNum-1] = Integer.toString(wishSchool);
                    break;
                }
                
                if(!arrSchool[wishSchool-1][1].equals("0")){
                    arrSchool[wishSchool-1][1] = Integer.toString(Integer.parseInt(arrSchool[wishSchool-1][1])-1);
                    arrFinalChoose[stuNum-1] = Integer.toString(wishSchool);
                    break;
                }
            }
        }
        return arrFinalChoose;
    }
    
    public static String[][] writeCompleteFile(String x){
        String temp;
        String[][] tempArr = new String[500][5];
        try{
            String path = x;
            BufferedReader br = new BufferedReader(new FileReader(path));
            int stuNum = 1;
            
            while((temp = br.readLine()) != null){
                int count = 0;
                String[] arrTemp = temp.split(",");
                for(String wrScore:arrTemp){
                    tempArr[stuNum-1][count] = wrScore;
                    count++;
                    if(count >= 4){
                        count -= 4;
                        stuNum++;
                    }
                }                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return tempArr;
    }
}
