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
public class Student {
    private String name="";
    private String studentID="";
    private String major="";
   
    private String system="";
    private String thesis="";
    public Student(String name,String studentID,String major){
        this.name=name;
        this.studentID=studentID;
        this.major=major;
    }
    
    public String getName(){
        return name;
    }
    
    public String getStudentID(){
        return studentID;
    }
    
    public String getmajor(){
        return major;
    }    
}
