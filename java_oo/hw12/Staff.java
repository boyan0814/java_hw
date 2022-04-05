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
public class Staff extends Person implements Work{
    //department, salary

    private String department="";
    private double salary=0;

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
        return String.format("who works in %s, \nwhose salary is %s.",department, (int)salary);
    }
}
