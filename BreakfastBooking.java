package Assignment;

import java.time.LocalDateTime;
import java.util.Scanner;

public class BreakfastBooking {
    private int numberOfPaxPerRoom;
    public static int totalOverallPax = 0;
    public static final double BREAKFAST_PRICE = 35.0;
    private double totalPricePerRoom;
    public static double totalOverallPrice=0.0;
    private int roomNumber;
    private LocalDateTime bookingDateTime;

    //No arg constructor
    public BreakfastBooking(){
        numberOfPaxPerRoom = 0;
        totalPricePerRoom = 0.0;
        roomNumber = 0;
        bookingDateTime = null;
    }
    //Parameterized constructor
    public BreakfastBooking(int numberOfPaxPerRoom, double totalPricePerRoom, int roomNumber, LocalDateTime bookingDateTime) {
        this.numberOfPaxPerRoom = numberOfPaxPerRoom;
        this.totalPricePerRoom = totalPricePerRoom;
        this.roomNumber = roomNumber;
        this.bookingDateTime = bookingDateTime;
    }
    //Getter
    public int getNumberOfPaxPerRoom(){
        return numberOfPaxPerRoom;
    }
    public double getTotalPricePerRoom(){
        return totalPricePerRoom;
    }
    public int getRoomNumber(){
        return roomNumber;
    }
    public LocalDateTime getBookingDateTime(){
        return bookingDateTime;
    }
    //Setter
    public void setNumberOfPaxPerRoom(int numberOfPaxPerRoom){
        this.numberOfPaxPerRoom = numberOfPaxPerRoom;
    }
    public void setTotalPricePerRoom(double totalPricePerRoom){
        this.totalPricePerRoom = totalPricePerRoom;
    }
    public void setRoomNumber(int roomNumber){
        this.roomNumber = roomNumber;
    }

    public void promptBooking(){
        System.out.println("-------------------"+"|BREAKFAST BOOKING|" + "-------------------");
        Scanner sc = new Scanner(System.in);     

        System.out.println("Enter room number: ");
        roomNumber = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter number of pax for breakfast: ");
        numberOfPaxPerRoom = sc.nextInt();
        sc.nextLine(); // Consume newline left-over

        totalPricePerRoom = numberOfPaxPerRoom * BREAKFAST_PRICE;
        System.out.println("==================================================");
        System.out.println("Total breakfast price for room " + roomNumber + " is: " + totalPricePerRoom);
        totalOverallPax += numberOfPaxPerRoom;
        totalOverallPrice += totalPricePerRoom;
        bookingDateTime = LocalDateTime.now();
    }
}   
