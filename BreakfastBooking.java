package Assignment;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class BreakfastBooking {
    private String customerId;
    private int numberOfPaxPerRoom;
    public static final double BREAKFAST_PRICE = 35.0;
    private double overallTotalPrice;
    private double totalPricePerDay;
    private int roomNumber;
    private LocalDate bookingDate;

    //No arg constructor
    public BreakfastBooking(){
        customerId="";
        numberOfPaxPerRoom = 0;
        overallTotalPrice = 0.0;
        totalPricePerDay = 0.0;
        roomNumber = 0;
        bookingDate = null;
    }

    //Getter
    public String getCustomerId(){
        return customerId;
    }
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
    public void setCustomerId(String customerId){
        this.customerId=customerId;
    }
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

    public void readFromFile() {
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
                    roomNumber = Integer.parseInt(parts[1]);
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


    public void writeIntoFile() {
        List<String> lines = new ArrayList<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader("BreakfastBooking.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 3 && parts[0].equals(customerId)) {
                    // If match customerId, update the line
                    line = customerId + "|" + roomNumber + "|" + overallTotalPrice;
                }
                lines.add(line); // Add (updated or original) line to list
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    
        // Write all lines back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("BreakfastBooking.txt"))) {
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
    

    public void promptBooking(){
        System.out.println("\033c");

        readFromFile();
        
        System.out.println("====================================");
        System.out.println("|                                  |");
        System.out.println("|        BREAKFAST BOOKING         |");
        System.out.println("|                                  |");
        System.out.println("====================================");

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter customer ID: ");
        customerId=sc.nextLine();     

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
        System.out.println("Customer ID: "+customerId);
        System.out.println("Room Number: "+roomNumber);
        System.out.println("Booking Date: "+bookingDate);        
        System.out.println("Breakfast Price for today - "+calculatePricePerDay());
        System.out.println("\nTotal Price: "+calculateBreakfastTotal());

        writeIntoFile();
    }

    public double calculatePricePerDay(){
        totalPricePerDay = numberOfPaxPerRoom * BREAKFAST_PRICE;
        return totalPricePerDay;
    }

    public double calculateBreakfastTotal(){ //should include in payment
            overallTotalPrice += totalPricePerDay;

            return overallTotalPrice;
        }

    }



