package Assignment;
import java.util.Scanner;

public class TransactionHistory {
    TransactionController tc = new TransactionController();
    Scanner sc = new Scanner(System.in);
    String CustomerId;

    public TransactionHistory(){
        CustomerId="";
    }


    public void displayTransactionHistory(){
        System.out.println("\033c");
        System.out.println("========================================");
        System.out.println("||         TRANSACTION HISTORY        ||");
        System.out.println("========================================");

        boolean continueLooping=true;
        while(continueLooping){
        System.out.print("Enter Customer ID: ");
        CustomerId=sc.nextLine();

        tc.displayTransactionsByCustomerId(CustomerId);
        System.out.print("Do you still want to continue? (1 - Yes | 0 - Exit):  ");
        int loopingAnswer = sc.nextInt();
        sc.nextLine();
        
        if(loopingAnswer == 1){
          System.out.println("\n\n");  
        }
        else if (loopingAnswer == 0){
            System.out.println("Exiting.....");
            continueLooping=false;
        }
        else{
            System.out.println("ERROR: Please try again.");
        }
        }
    }
}

