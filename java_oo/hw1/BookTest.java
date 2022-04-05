/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1;

/**
 *
 * @author boyan
 */
import java.util.Scanner;//impor scanner
public class BookTest {

    public static void main(String[] args) {
        
        Book book1 = new Book(100, "RDBMS", "1E2"); // create book1 object
        
        System.out.println("Book{bookPrice=" + book1.getPrice()+", bookName="+book1.getName()+", bookCode="+book1.getCode());// print  book1
        System.out.println("Enter Second Book's Name");

        // create Scanner 
        Scanner input = new Scanner(System.in);
        String name=input.nextLine(); //save the thing you type into "name"
        
        System.out.println("Enter Second Book's Code");
        String code=input.nextLine();
        
        System.out.println("Enter Second Book's Price");
        int price=input.nextInt();
        
        Book book2=new Book(price,code,name);//create book2  input price code name
        
        //print out book2
         System.out.println("Book{bookPrice=" + book2.getPrice()+", bookName="+book2.getName()+", bookCode="+book2.getCode());
    }
}
