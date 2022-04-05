/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw12;

/**
 *
 * @author boyan
 */
public class PersonTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Student bob=new Student();
        Staff alice=new Staff();
        TeachingAssistant alex=new TeachingAssistant();
        
        alice.name("Alice");
        alice.setDepartment("BA");
        alice.setSalary(22000);
        System.out.println("Staff name = "+alice.getname()+",\n"+alice.toString()+"\n");
        
        
        bob.name("Bob");
        bob.setMajor("CS");
        bob.setDegree("Master");
        System.out.println("Student name = "+bob.getname()+",\n"+bob.toString()+"\n");
        
        alex.name("Alex");
        alex.setMajor("MI");
        alex.setDegree("PhD");
        alex.setDepartment("MI");
        alex.setSalary(3000);
        System.out.println("TA name = "+alex.getname()+",\n"+alex.toString()+"\n");
    }
    
}
