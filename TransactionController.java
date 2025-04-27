package Assignment;

import java.util.ArrayList;
import java.util.Scanner;

public class TransactionController {

    private ArrayList<Transaction> transactions;

    public TransactionController(){
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    public Transaction searchByCustomerId(String customerId){ //returns a transaction object
        for (Transaction t : transactions){
            if (t.getCustomerId().equalsIgnoreCase(customerId)){
                return t;
            }
        }
        return null;
    }

    public Transaction searchByRoomNumber(int roomNumber){
        for (Transaction t : transactions){
            if (t.getRoomNumber()==roomNumber){
                return t;
            }
        }
        return null;
    }

    public void displayTransaction(){
        Scanner sc = new Scanner(System.in);

        System.out.println("---------------------\n|TRANSACTION HISTORY|\n---------------------");
        while(true){
            System.out.print("---Select filter option--- \n1. Room Number \n2. Customer ID\n\n0.Exit\n\nEnter number to select: ");
            int transactionFilterSelection=sc.nextInt();
            sc.nextLine();//leftover newline

            if(transactionFilterSelection==1){
                System.out.print("---------------------------------\nEnter Room Number: ");
                int promptRoomNumber=sc.nextInt();
                sc.nextLine();

                Transaction result = searchByRoomNumber(promptRoomNumber);
                if (result!=null){
                    System.out.println("\nHere is Transaction for "+promptRoomNumber+"\n");
                    result.displayTransaction();                    
                }
                else{
                    System.out.println("Transaction not found.");
                }

                System.out.println("\n\n");
                
                break;

            }
            else if (transactionFilterSelection==2){
                System.out.print("---------------------------------\nEnter Customer ID: ");
                String promptCustomerId=sc.nextLine();

                Transaction result = searchByCustomerId(promptCustomerId);
                if (result!=null){
                System.out.println("\nHere is Transaction for "+promptCustomerId+"\n");
                result.displayTransaction();
                }
                else{
                    System.out.println("Transaction not found.");
                }
                break;
            }
            else if (transactionFilterSelection==0){
                System.out.println("Exiting program ........");
                break;
            }
            else{
                System.out.println("INVALID SELECTION. Please enter 1 or 2.");
                continue;
            }
        }
    } 
}
