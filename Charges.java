package Assignment;

import java.util.Scanner;

public class Charges extends Checkout {
    BreakfastBooking bb = new BreakfastBooking();      
    Scanner sc = new Scanner(System.in);  

    //cash
    private double cash;
    private double balance;
    private double totalAmount;

    public Charges(int roomNumber, BreakfastBooking bb){
        super(roomNumber);
        this.totalAmount=bb.getOverallTotalPrice();
        cash=0;
        balance=0;
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
        int promptMethod;
        System.out.println("====================================");
        System.out.println("|                                  |");
        System.out.println("|            PAYMENT               |");
        System.out.println("|                                  |");
        System.out.println("====================================");
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
