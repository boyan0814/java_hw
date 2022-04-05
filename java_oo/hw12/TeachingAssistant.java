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
public class TeachingAssistant extends Student implements Work{
    //major, degree, department, salary, course
    private String  department, course;
    private double salary;

    @Override
    public String getDepartment() {
        return department;
    }

    @Override
    public void setDepartment(String department) {
        this.department=department;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public void setSalary(double salary) {
       this.salary=salary;
    }
    
    @Override
    public String toString(){
        return String.format("who studies in %s, \nwhose degree is %s, \nwho works in %s, \nwhos salary is %s.",major, degree,department,(int)salary);
    }
}
