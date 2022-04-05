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
public class Student extends Person implements Study{
    
    public String major="";
    public String degree="";

    @Override
    public String getMajor() {
        return major;
    }

    @Override
    public void setMajor(String major) {
        this.major=major;
    }

    @Override
    public String getDegree() {
        return degree;
    }

    @Override
    public void setDegree(String degree) {
        this.degree=degree;
    }
    
    @Override
    public String toString(){
        return String.format("who studies in %s, \nwhose degree is %s.",major, degree);
    }
}
