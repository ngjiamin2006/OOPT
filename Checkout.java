package Assignment;
import java.util.*;

public class Checkout {
    Scanner sc = new Scanner(System.in);
    BreakfastBooking bb = new BreakfastBooking();

    private String customerId;
    private String roomNumber;

    public Checkout(String customerId, String roomNumber){
        this.customerId=customerId;
        this.roomNumber=roomNumber;
    }

    public String getCustomerId(){
        return customerId;
    }

    public void setCustomerId(String customerId){
        this.customerId = customerId;
    }

    public String getRoomNumber(){
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber){
        this.roomNumber=roomNumber;
    }

    public void promptCheckOutDetails(){
                Charges charges = new Charges(customerId,roomNumber, bb.calculateBreakfastTotal());
                charges.selectPaymentMethod();
                System.out.println("Processing to Refund..."); 
                Refund refund = new Refund(customerId,roomNumber,1); //1 是暂时的
                refund.toString();
        }

    public String toString(){
        return "Room Number: "+roomNumber;
    }
}
