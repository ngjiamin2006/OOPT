package Assignment;

import java.time.LocalDate;
import java.util.Scanner;

public class BreakfastBooking {
    private int numberOfPaxPerRoom;
    public static final double BREAKFAST_PRICE = 35.0;
    private double overallTotalPrice;
    private double totalPricePerDay;
    private int roomNumber;
    private LocalDate bookingDate;

    //No arg constructor
    public BreakfastBooking(){
        numberOfPaxPerRoom = 0;
        overallTotalPrice = 0.0;
        totalPricePerDay = 0.0;

        roomNumber = 0;
        bookingDate = null;
    }
    //Parameterized constructor
    public BreakfastBooking(int numberOfPaxPerRoom, double overallTotalPrice, double totalPricePerDay, int roomNumber, LocalDate bookingDate) {
        this.numberOfPaxPerRoom = numberOfPaxPerRoom;
        this.overallTotalPrice = overallTotalPrice;
        this.totalPricePerDay = totalPricePerDay;
        this.roomNumber = roomNumber;
        this.bookingDate = bookingDate;
    }
    //Getter
    public int getNumberOfPaxPerRoom(){
        return numberOfPaxPerRoom;
    }
    public double getTotalPricePerDay(){
        return totalPricePerDay;
    }
    public double getOverallTotalPrice(){
        return overallTotalPrice;
    }
    public int getRoomNumber(){
        return roomNumber;
    }
    public LocalDate getBookingDate(){
        return bookingDate;
    }

    //Setter
    public void setNumberOfPaxPerRoom(int numberOfPaxPerRoom){
        this.numberOfPaxPerRoom = numberOfPaxPerRoom;
    }
    public void setTotalPricePerDay(double totalPricePerDay){
        this.totalPricePerDay = totalPricePerDay;
    }
    public void setOverallTotalPrice(double overallTotalPrice){
        this.overallTotalPrice = overallTotalPrice;
    }
    public void setRoomNumber(int roomNumber){
        this.roomNumber = roomNumber;
    }

    public void promptBooking(){
        System.out.println("-------------------"+"|BREAKFAST BOOKING|" + "-------------------");
        Scanner sc = new Scanner(System.in);     

        System.out.print("Enter room number: ");
        roomNumber = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter number of pax for breakfast: ");
        numberOfPaxPerRoom = sc.nextInt();
        sc.nextLine(); // Consume newline left-over

        //set date
        bookingDate = LocalDate.now();

        System.out.println("\n\n");
        System.out.println("==================================================================");
        System.out.println("Room Number: "+roomNumber);
        System.out.println("Booking Date: "+bookingDate);        
        System.out.println("Breakfast Price for today - "+totalPricePerDay);
        System.out.println("\nTotal Price: "+calculateBreakfastTotal());
    }

    public double calculateBreakfastTotal(){ //should include in payment
        totalPricePerDay = numberOfPaxPerRoom * BREAKFAST_PRICE;
        overallTotalPrice += totalPricePerDay;

        return overallTotalPrice;
    }
}   


