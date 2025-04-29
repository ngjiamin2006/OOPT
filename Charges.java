package Assignment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Charges extends Checkout {
    BreakfastBooking bb = new BreakfastBooking();      
    Scanner sc = new Scanner(System.in);  

    //cash
    private double cash;
    private double balance;
    private double totalAmount;

    public Charges(String customerId, String roomNumber, double totalAmount){
        super(customerId, roomNumber);
        this.totalAmount=bb.getOverallTotalPrice();
        cash=0;
        balance=0;
    }
    
    public void readFromFile(String customerId, String roomNumber, double overallTotalPrice) {
        try (BufferedReader reader = new BufferedReader(new FileReader("BreakfastBooking.txt"))) {
            String lastLine = null;
            String line;
            while ((line = reader.readLine()) != null) {
                lastLine = line; // Keep the last line (most recent booking)
            }

            if (lastLine != null) {
                String[] parts = lastLine.split("\\|");
                if (parts.length >= 3) {
                    customerId = parts[0];
                    roomNumber = parts[1];
                    overallTotalPrice = Double.parseDouble(parts[2]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: No previous booking found.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }  

    public void cashPayment(){
        System.out.println("TOTAL: RM"+totalAmount);
        System.out.println();

        do{
        System.out.println("Enter cash amount: ");
        cash=sc.nextDouble();

        if (cash >=totalAmount){
            System.out.println("\nPayment Successful!\n");
            balance=cash-totalAmount;
            System.out.println("Balance: RM"+balance);
            break;
        }
        else{
            System.out.println("ERROR: cash amount lesser than required. Please try again. \n");
        }
        }while(true);
    }

    public void cardPayment(){
        System.out.println("Please insert card or tap to pay wave.");
        System.out.println("Transaction processing..............");
        System.out.println("Payment Successful!");
    }

    public void ewalletPayment(){
        System.out.println("Please scan QR to pay.");
        System.out.println("Transaction processing..............");
        System.out.println("Payment Successful!");      
    }

    public void selectPaymentMethod(){
        readFromFile(super.getCustomerId(), super.getRoomNumber(), totalAmount);
        int promptMethod;
        System.out.println(">>>>PAYMENT<<<<");
        System.out.println("\nTOTAL: RM"+totalAmount);
        System.out.println();

        do{
        System.out.println("Select a payment method:\n1.Cash\n2.Visa card\n3.Ewallet\n4.Exit");
        promptMethod=sc.nextInt();
        sc.nextLine();
        switch(promptMethod){
            case 1:
            cashPayment();
            break;
            case 2:
            cardPayment();
            break;
            case 3:
            ewalletPayment();
            break;
            case 4:
            System.out.println("Exiting.............\n");
            break;
            default:
            System.out.println("ERROR: please input within the range only. Please try again.\n");
        }
       }while(promptMethod!=4);
    }

    public String toString(){ //after class this, call select PaymentMethod
        return super.toString()+"\nTotal Amount:" + totalAmount;
    }
}
