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
public class FullTimeStudent {
    private UndergraduateStudent student;
    private String club="";
    private String role="";
    
    public FullTimeStudent(String name, String studentID, String major, String labor, String advisor, String club, String role){
        student=new UndergraduateStudent(name, studentID, major, labor, advisor);
        this.club=club;
        this.role=role;
    }
    
    @Override
    public String toString(){
        return String.format("*****FullTimeStudent*****\nName:%s\nStudent ID:%s\nMajor:%s\nLab:%s\nAdvisor:%s\nClub:%s\nRole:%s\n\n", student.getname(), student.getsStudentID(), student.getmajor(), student.getgrade(),student.getclass(), club, role);
    }
}
