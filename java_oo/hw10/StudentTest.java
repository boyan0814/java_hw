/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw10;

/**
 *
 * @author boyan
 */
public class StudentTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MasterStudent master = new MasterStudent("David Tsai","M10009001","Information Management","Integrated Digital Services Lab","Professor Lo","EMBA","Unfinished");
        System.out.print(master.toString());
        
        PhDStudent phd = new PhDStudent("Peter Yang","D10015001","Computer Science","Software Engineering Lab","Professor Lee","Passed","Unfinished");
        System.out.print(phd.toString());
        
        FullTimeStudent full = new FullTimeStudent("Jack Chen","B10005001","Contruction Engineering","Sophomore","Class A","Baseball Club","Leader");
        System.out.print(full.toString());
        
        PartTimeStudent part = new PartTimeStudent("Michelle Lin","B10008001","Business Administration","Freshman","Class B","7-ELEVEN Club","Clerk");
        System.out.print(part.toString());
    }
    
}
