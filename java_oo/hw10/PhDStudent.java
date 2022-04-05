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
public class PhDStudent {
    private GraduateStudent student;
    private String qualify="";
    private String dissertation="";
        
    public PhDStudent(String name, String studentID, String major, String labor, String advisor, String qualify, String dissertation){
        student=new GraduateStudent(name, studentID, major, labor, advisor);
        this.qualify=qualify;
        this.dissertation=dissertation;
    }
    
    @Override
    public String toString(){
        return String.format("*****PhDStudent*****\nName:%s\nStudent ID:%s\nMajor:%s\nLab:%s\nAdvisor:%s\nQualify:%s\nDissertation:%s\n\n", student.getname(), student.getsStudentID(), student.getmajor(), student.getlabor() ,student.getadvisor(), qualify, dissertation);
    }
}
