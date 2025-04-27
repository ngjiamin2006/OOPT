package Assignment;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class RoomService {
    private int roomNumber;
    private String requestType;
    private LocalDateTime requestTime;
    private String requestStatus;

    // Constructor
    public RoomService() {
        roomNumber = 0;
        requestType = "";
        requestStatus = "";
    }

    public RoomService(int roomNumber, String requestType, String requestStatus) {
        this.roomNumber = roomNumber;
        this.requestType = requestType;
        this.requestStatus = requestStatus;
    }

    // Getters
    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRequestType() {
        return requestType;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    // Setters
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    // Methods
    public void promptRequest() {
        try (Scanner sc = new Scanner(System.in)) { // Use try-with-resources for Scanner
            System.out.print("Enter customer room number: ");
            roomNumber = sc.nextInt();
            sc.nextLine(); // Consume newline left-over
            System.out.print("Enter request: ");
            requestType = sc.nextLine();
            requestTime = LocalDateTime.now();
            System.out.print("File is saved, on "+requestTime + "\n");
        }
    }

    public void writeRequestToFile() {
        try (FileWriter writer = new FileWriter("RoomServiceRequests.txt", true)) { // Use try-with-resources for FileWriter
            requestStatus="Pending";
            writer.write("---------------------------------------------------------\n");
            writer.write("Room Number: " + roomNumber + "\n"
                        + "Request: " + requestType + "\n"
                        + "Request Time: " + requestTime + "\n"
                        + "Request Status: " + requestStatus + "\n"
                        + "---------------------------------------------------------\n");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public void displayRoomService() { //call this when run 
        System.out.println("====================================");
        System.out.println("|                                  |");
        System.out.println("|          ROOM SERVICE            |");
        System.out.println("|                                  |");
        System.out.println("====================================");
        promptRequest(); // Updates instance variables
        writeRequestToFile(); // Uses updated instance variables
    }
}
