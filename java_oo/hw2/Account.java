/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw2;

/**
 *
 * @author boyan
 */
public class Account {
    private double balance=0;//餘額    
    
    
    public double saveMoney(double a){//存錢
        balance+=a;
        return balance;
    }
    public double takeMoney(double a){//零錢
        balance-=a;
        return balance;
    }
    public double printOut(){//查餘額
        return balance;
    }
}
