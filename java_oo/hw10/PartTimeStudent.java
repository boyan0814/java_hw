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
public class PartTimeStudent {
    private UndergraduateStudent student;
    private String company="";
    private String position="";
    
    public PartTimeStudent(String name, String studentID, String major, String labor, String advisor, String company, String position){
        student=new UndergraduateStudent(name, studentID, major, labor, advisor);
        this.company=company;
        this.position=position;
    }
    
    @Override
    public String toString(){
        return String.format("*****PartTimeStudent*****\nName:%s\nStudent ID:%s\nMajor:%s\nLab:%s\nAdvisor:%s\nCompany:%s\nPosition:%s\n\n", student.getname(), student.getsStudentID(), student.getmajor(), student.getgrade(),student.getclass(), company, position);
    }
}

