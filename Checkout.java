package Assignment;
import java.util.Scanner;

public class Checkout {
    Scanner sc = new Scanner(System.in);
    BreakfastBooking bb = new BreakfastBooking();

    private int roomNumber;

    public Checkout(){
        roomNumber=0;
    }

    public Checkout(int roomNumber){
        this.roomNumber=roomNumber;
    }

    public int getRoomNumber(){
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber){
        this.roomNumber=roomNumber;
    }

    public void promptCheckOutDetails(){
        System.out.println("Enter Room Number: ");
        roomNumber=sc.nextInt();
        sc.nextLine();

        System.out.println("Processing to Additional Payment...");
        Charges charges = new Charges(roomNumber, bb);
        charges.selectPaymentMethod();
        System.out.println("Processing to Refund..."); 
        Refund refund = new Refund(roomNumber,1); //1 是暂时的
        refund.toString();
    }

    public String toString(){
        return "Room Number: "+roomNumber;
    }
}
