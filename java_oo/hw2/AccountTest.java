/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw2;
import java.util.Scanner;
/**
 *
 * @author boyan
 */
public class AccountTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {    
        Scanner input = new Scanner(System.in);

        Boolean Account=false;
        Account account=new Account();
        int num=5;
        do {
            System.out.println("\n***Main Menu***");
            System.out.println("1) 開戶");
            System.out.println("2) 存款");
            System.out.println("3) 提款");
            System.out.println("4) 目前餘額");
            System.out.println("0) Exit");
            System.out.println();
            System.out.print("Please enter a number in [1,2,3,4,0]: ");
	
            num = input.nextInt();                        
                switch (num) {
			case 1://開戶
				if(Account==false){                                    
                                    double temp=0;
                                    System.out.print("請輸入開戶金額：");
                                    try{//輸入錯誤例外處理
                                        temp=input.nextDouble();
                                    }catch(Exception e){
                                        System.out.println("開戶失敗!請輸入金額!");
                                        input.nextLine();//清除Scanner
                                        break;//跳出
                                    }                                                                     
                                    
                                    if(temp<1000){//測是否小於1000
                                        if(temp<0){//測是否為負
                                            System.out.println("開戶失敗!請輸入非負值!");
                                        }
                                    System.out.println("開戶金額不足!");
                                    break;
                                    }        
                                    
                                    Account=true;
                                    System.out.printf("開戶成功! 存入 $ %.2f 元!",temp);
                                    account.saveMoney(temp);
                                    
                                }else{
                                    System.out.println("已開戶!");
                                }
                                break;
			case 2://存款
				if(Account==true){//看是否開戶
                                    System.out.print("請輸入存款金額：");
                                    double temp=0;
                                    try{//輸入錯誤例外處理
                                        temp=input.nextInt();
                                    }catch(Exception e){
                                        System.out.println("存款失敗!請輸入金額!");
                                        input.nextLine();
                                        break;
                                    }                                       
                                        
                                        if(temp<0){//測是否為負
                                            System.out.printf("存款失敗! 請輸入非負數值!");
                                            break;
                                        }
                                        account.saveMoney(temp);
                                        System.out.printf("成功存入：$%.2f元!",temp);
                                    
                                }else{
                                    System.out.println("未開戶!");
                                }
				break;
                        case 3://提款
                            if(Account==true){//看是否開戶
                                System.out.print("請輸入提款金額：");
                                    double temp=0;
                                    
                                    try{//輸入錯誤例外處理
                                        temp=input.nextInt();
                                    }catch(Exception e){
                                        System.out.println("提款失敗!請輸入金額!");
                                        input.nextLine();
                                        break;
                                    }
                                    
                                    if(temp>account.printOut()){//提款不得高於餘額
                                        System.out.printf("提款失敗! 提款金額大於目前餘額!");
                                        break;
                                    }
                                    
                                    account.takeMoney(temp);
                                    System.out.printf("成功領出：$%.2f元!",temp);
                            }else{
                                    System.out.println("未開戶!");
                                }
				break;
                        case 4://查餘額
                            if(Account==true){
                                System.out.println("目前餘額："+account.printOut()+"0元");                                   
                            }else{
                                    System.out.println("未開戶!");
                                }
				break;                         
			}
		} while (num!=0);
    }
    
}
