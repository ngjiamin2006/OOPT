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
    private String roomNumber;
    private LocalDate bookingDate;
    private String paymentStatus;

    //No arg constructor
    public BreakfastBooking(){
        customerId="";
        numberOfPaxPerRoom = 0;
        overallTotalPrice = 0.0;
        totalPricePerDay = 0.0;
        roomNumber = "";
        bookingDate = null;
        paymentStatus="PENDING";
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
    public String getRoomNumber(){
        return roomNumber;
    }
    public LocalDate getBookingDate(){
        return bookingDate;
    }

    public String getPaymentStatus(){
        return paymentStatus;
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
    public void setRoomNumber(String roomNumber){
        this.roomNumber = roomNumber;
    }

    public void setPaymentStatus(String paymentStatus){
        this.paymentStatus=paymentStatus;
    }

    public void readFromFile(String targetCustomerId, String targetRoomNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader("BreakfastBooking.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    String fileCustomerId = parts[0];
                    String fileRoomNumber = parts[1];

                    if (fileCustomerId.equals(targetCustomerId) && fileRoomNumber.equals(targetRoomNumber)) {
                        customerId = fileCustomerId;
                        roomNumber = fileRoomNumber;
                        overallTotalPrice = Double.parseDouble(parts[2]);
                        paymentStatus = parts[3];
                        break; // Found match, stop
                    }
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
        boolean updated = false;
    
        try (BufferedReader reader = new BufferedReader(new FileReader("BreakfastBooking.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
    
                if (parts.length >= 4 && parts[0].equals(customerId) && parts[1].equals(roomNumber)) {
                    // Found the line to update
                    line = customerId + "|" + roomNumber + "|" + overallTotalPrice + "|" + paymentStatus;
                    updated = true;
                }
    
                lines.add(line); // Add either the original or updated line
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    
        if (!updated) {
            // If not found in existing file, append new line
            lines.add(customerId + "|" + roomNumber + "|" + overallTotalPrice + "|" + paymentStatus);
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
        
        System.out.println("====================================");
        System.out.println("|        BREAKFAST BOOKING         |");
        System.out.println("====================================");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter customer ID: ");
        customerId=sc.nextLine();     

        if(customerId.contains("CUST")){
            System.out.print("Enter Room Number: ");
            roomNumber = sc.nextLine();
            
            readFromFile(customerId, roomNumber);

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
        else{
            System.out.println("ERROR: Invalid Customer ID, must contain CUST.");
        }
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



