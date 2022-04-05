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
public class MasterStudent {
    private GraduateStudent student;
    private String system="";
    private String thesis="";
    
    public MasterStudent(String name, String studentID, String major, String labor, String advisor, String system, String thesis){
        student=new GraduateStudent(name, studentID, major, labor, advisor);
        this.system=system;
        this.thesis=thesis;
    }
    
    @Override
    public String toString(){
        return String.format("*****MasterStudent*****\nName:%s\nStudent ID:%s\nMajor:%s\nLab:%s\nAdvisor:%s\nSystem:%s\nThesis:%s\n\n", student.getname(), student.getsStudentID(), student.getmajor(), student.getlabor() ,student.getadvisor(), system, thesis);
    }
}
