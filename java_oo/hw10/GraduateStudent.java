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
public class GraduateStudent {
    private Student student;
    
    private String labor="";
    private String advisor="";
    
    public GraduateStudent(String name, String studentID, String major, String labor, String advisor){
        student=new Student(name, studentID, major);
        this.labor=labor;
        this.advisor=advisor;
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
    
    public String getlabor(){
        return labor;
    }
    
    public String getadvisor(){
        return advisor;
    }
}
