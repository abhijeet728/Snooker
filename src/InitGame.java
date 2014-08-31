/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
import java.util.Scanner;
public class InitGame {
    
    public static void main(String[] args) {
        // TODO code application logic here
        int option; 
        Scanner in = new Scanner(System.in);
        
        do{
            System.out.println("Please choose one of the following option");
            System.out.println("1: Play Snooker");
            System.out.println("2: Exit");
            option = in.nextInt();
            
            switch(option){
                case 1:
                    
                    Snooker game = new Snooker();
                    
                    option = 2;     //to exit
                    break;
                case 2:
                    System.out.println("Exiting!!!!!");
                    break;
                default:
                    System.out.println("ERROR: Invalid input");
                    break;
            }
       }while(option != 2);
    }   
}
