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
import java.util.Scanner;
public class TimeToolTest {

	public static void main(String[] args) {
		String input;
		Scanner scanner = new Scanner(System.in);             
                
		do {
			System.out.println("***Main Menu***");
			System.out.println("1) Show The Current Time");
			System.out.println("2) Leap Year");
                        System.out.println("3) Show the Calendar");
			System.out.println("0) Exit");
			System.out.println();
			System.out.print("Please enter a number in [1,2,3,0]: ");

			input = scanner.next(); // 獲取用戶輸入(String)
			int num = Integer.parseInt(input); // 型別轉換(Integer)

			switch (num) {
			case 1:
				TimeTool.showCurrentTime(); // 顯示目前時間
				break;
			case 2:
				System.out.print("Please enter the number of year: ");
				int year = scanner.nextInt(); // 獲取用戶輸入(Integer)
				if (TimeTool.isLeapYear(year)) // 判斷指定年份是否為閏年
					System.out.println(year + " is a leap year.");
				else
					System.out.println(year + " is not a leap year.");
				break;  
                        case 3:
                                                  
                                System.out.print("Year:");
                                int year2 = scanner.nextInt();//存數入的year
                                System.out.print("Month:");
                                int month= scanner.nextInt();//存數入的month
                                
                                TimeTool.calander(year2, month);
                                
			}
		} while (!input.equals("0")); // 若 input 不為 0 則繼續執行迴圈
	}
}
