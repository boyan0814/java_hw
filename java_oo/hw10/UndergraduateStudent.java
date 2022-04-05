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
public class UndergraduateStudent {
    private Student student;
    
    private String grade="";
    private String tempClass="";
    
    public UndergraduateStudent(String name, String studentID, String major, String grade, String tempClass){
        student=new Student(name, studentID, major);
        this.grade=grade;
        this.tempClass=tempClass;
    }
    
    public String getname(){
        return student.getName();
    }
    
    public String getsStudentID(){
        return student.getStudentID();
    }
    
    public String getmajor(){
        return student.getmajor();
    }
    
    public String getgrade(){
        return grade;
    }
    
    public String getclass(){
        return tempClass;
    }
}
