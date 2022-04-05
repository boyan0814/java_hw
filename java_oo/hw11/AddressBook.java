/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw11;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 *
 * @author boyan
 */
public class AddressBook {
    //private List<Contact> contacts = new ArrayList<Contact>();
    private Map<String,Contact> contacts = new TreeMap<String,Contact>();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        Scanner scanner =new Scanner(System.in);
        String input="";        
        do{
            showMenu();
            input=scanner.next();
            System.out.println();
            
            switch(input){
                case "1":
                    addressBook.addContact();
                    break;
                case "2":
                    addressBook.showContacts();
                    break;
                case "3":
                    addressBook.editContacts();
                    break;
                case "4":
                    addressBook.delConstacts();
                    break;
                case "5":
                    addressBook.readFile("data.txt");
                    break;
                case "6":
                    addressBook.writeFile("contacts.txt");
                    break;
                case "7":
                    addressBook.clearList();
                    break;
            }
        }while(!input.equals("0"));
        // TODO code application logic here
    }
    
    public static void showMenu(){
        System.out.println("***** Address Book *****");
        System.out.println("1) Add a New Contact");
        System.out.println("2) Show Contact List");
        System.out.println("3) Edit the Contact");
        System.out.println("4) Delete the Contact");
        System.out.println("5) Import Contacts");
        System.out.println("6) Export Contacts");
        System.out.println("7) Clear Contact List");
        System.out.println("0) Exit");
        System.out.println("************************");
        System.out.print("Please enter a number in [1,2,3,4,5,6,7,0]: ");
    }
    
    public void addContact(){
        Scanner scanner=new Scanner(System.in);
        
        System.out.print("Name: ");
        String name = scanner.next();
        System.out.print("Phone: ");
        String phone = scanner.next();
        System.out.print("E-mail: ");
        String email = scanner.next();
        System.out.print("Birthday(yyyy/MM/dd): ");
        String birthdayString = scanner.next();
        
        if(isValidFormat(name, birthdayString, phone, email)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date birthday = null;
            try{
                birthday = sdf.parse(birthdayString);
                contacts.put(name,new Contact(name, birthday, phone, email));
                System.out.println("The contact has been added.\n");
                //Collections.sort(contacts.);
            }catch(ParseException e){
                e.printStackTrace();
            }        
        }
    }
    
    public void editContacts(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of contact that you want to edit.");
        System.out.print("Name: ");
        String editName = scanner.next();
        boolean exist = false;
        for(Contact contact : contacts.values()){
            if(contact.getName().equals(editName)){
                exist = true;
                
                System.out.print("Phone: ");
                String phone = scanner.next();
                System.out.print("E-mail: ");
                String email = scanner.next();
                System.out.print("Birthday(yyyy/MM/dd): ");
                String birthdayString = scanner.next();
                
                if(isValidFormat(editName, birthdayString, phone, email)){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    Date birthday = null;
                    try{
                        birthday = sdf.parse(birthdayString);
                        contacts.remove(editName);
                        contacts.put(editName,new Contact(editName, birthday, phone, email));
                        System.out.println("The contact has been change.");
                        //Collections.sort(contacts);
                    }catch(ParseException e){
                        e.printStackTrace();
                    }        
                }
                break;  
            }            
        }
        if(exist == false){
            System.out.println("Not found!");
        }
        
    }
    
    public void delConstacts(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of contact that you want to delete.");
        System.out.print("Name: ");
        String editName = scanner.next();
        
        boolean exist = false;
        for(Contact contact : contacts.values()){
            if(contact.getName().equals(editName)){
                exist = true;
                contacts.remove(contact.getName());
                //Collections.sort(contacts);
                System.out.println("The contact has been delete.");
               break;
            }
        }
        if(exist == false){
            System.out.println("Not found!");
        }
    }
    
    public void readFile(String fileName){
        try{
            File file=new File(fileName);
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            String line=null;
            StringBuilder data =new StringBuilder();
            while((line=br.readLine())!=null){
                data.append(line+"#");
            }
            br.close();
            fr.close();
            String dataStr = data.toString();
            String lineStr[]=dataStr.split("#");
            
            for(String temp:lineStr){
                String tempList[]=temp.split("\t");
                //System.out.println(tempList[0]+tempList[1]+tempList[2]+tempList[3]);
                addDirect(tempList[0],tempList[1],tempList[2],tempList[3]);               
            }          
        }catch(Exception e){    
            e.printStackTrace();           
        }
        System.out.println("All of the contacts have been imported.\n");
    }
    
    public void writeFile(String fileName){
        try{
             File file=new File(fileName);
             FileWriter fw=new FileWriter(file);
             BufferedWriter bw=new BufferedWriter(fw);
              SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
              
              bw.write("Name\tBirthday\tPhone\t\tE-mail\n");
              bw.write("----------------------------------------------\n");
             for(Contact contact:contacts.values()){
                    bw.write(contact.getName()+"\t"+sdf.format(contact.getBirthday())+"\t"+contact.getPhone()+"\t"+contact.getEmail());
                    bw.newLine();
                }                
             bw.close();
             fw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        System.out.println("The contact list has been exported.\n");
    }
    
    public void addDirect(String name,String birthdayString, String phone,String email){
        if(isValidFormat(name, birthdayString, phone, email)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date birthday = null;
            try{
                birthday = sdf.parse(birthdayString);
                contacts.put(name,new Contact(name, birthday, phone, email));              
                //Collections.sort(contacts);
            }catch(ParseException e){
                e.printStackTrace();
            }        
        }
    }
    
    public void showContacts(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("Name\tBirthday\tPhone\tE-mail");
        System.out.println("----------------------------------------------");
        for(Contact contact : contacts.values()){
            System.out.printf("%s\t%s\t%s\t%s\n",contact.getName(),sdf.format(contact.getBirthday()),contact.getPhone(),contact.getEmail());
        }
        System.out.println("");
    }
    
    public void clearList(){
        contacts.clear();
        System.out.println("The contact list is empty.");
    }
    
    public static boolean isValidFormat(String name, String birthday, String phone, String email){
        boolean isValidFormat = true;
        
        if(!name.matches("^[a-zA-Z]+$")){
            System.out.println("Invalid name format!");
            isValidFormat = false;
        }
        
        if(!phone.matches("^09[0-9]{2}-[0-9]{3}-[0-9]{3}$")){
            System.out.println("Invalid phone format!");
            isValidFormat = false;
        }
        
        if(!email.matches("^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$")){
            System.out.println("Invalid email format!");
            isValidFormat = false;
        }
        
        if(!birthday.matches("^((19|20)?[0-9]{2}/(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01]))$")){
            System.out.println("Invalid date format!");
            isValidFormat = false;
        }
        return isValidFormat;
    }
}
