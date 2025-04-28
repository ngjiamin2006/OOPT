package Assignment;

import java.io.*;
import java.util.*;

public class RoomAvailability {
    ArrayList<String[]> rooms = new ArrayList<>();
    ArrayList<String[]> bookings = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    public void readRoomFile() {
        rooms.clear(); // Clear existing data before reading
        try (BufferedReader reader = new BufferedReader(new FileReader("Room.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] roomData = line.split("\\|");
                if (roomData.length == 6) {
                    rooms.add(roomData);
                } else {
                    System.out.println("Invalid room data line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeRoomFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Room.txt"))) {
            for (String[] room : rooms) {
                String newLine = String.join("|", room);
                writer.write(newLine);
                writer.newLine();
            }
            System.out.println("Room status updated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readBookingFile() {
        bookings.clear(); // Clear existing data before reading
        try (BufferedReader reader = new BufferedReader(new FileReader("bookings.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] bookingData = line.split("\\|");
                if (bookingData.length == 9) {
                    bookings.add(bookingData);
                } else {
                    System.out.println("Invalid booking data line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeBookingFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bookings.txt"))) {
            for (String[] booking : bookings) {
                String newLine = String.join("|", booking);
                writer.write(newLine);
                writer.newLine();
            }
            System.out.println("Booking information updated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayAvailabilityStaff() {
        readRoomFile();
        boolean foundStandard = false;
        boolean foundDeluxe = false;
        boolean foundFamily = false;
        boolean foundSuite = false;
        boolean foundExecutive = false;

        System.out.println("-----------------------------");
        System.out.println("|      AVAILABLE ROOM       |");
        System.out.println("-----------------------------");
        for (String[] room : rooms) {
            try {
                String roomType = room[2];
                String status = room[5];

                if ("Available".equals(status)) {
                    if ("Standard Room".equals(roomType) && !foundStandard) {
                        System.out.println("===Standard Room===");
                        foundStandard = true;
                    }
                    if ("Deluxe Room".equals(roomType) && !foundDeluxe) {
                        System.out.println("\n===Deluxe Room===");
                        foundDeluxe = true;
                    }
                    if ("Family Room".equals(roomType) && !foundFamily) {
                        System.out.println("\n===Family Room===");
                        foundFamily = true;
                    }
                    if ("Suite".equals(roomType) && !foundSuite) {
                        System.out.println("\n===Suite===");
                        foundSuite = true;
                    }
                    if ("Executive Room".equals(roomType) && !foundExecutive) {
                        System.out.println("\n===Executive Room===");
                        foundExecutive = true;
                    }
                    System.out.println(room[0]);
                }
            } catch (Exception e) {
                System.out.println("Error processing room data: " + String.join("|", room));
                e.printStackTrace();
            }
        }
    }

    public void modifyAvailability() {
        readRoomFile();
        readBookingFile();

        System.out.println("-----------------------------");
        System.out.println("|     MODIFY ROOM STATUS     |");
        System.out.println("-----------------------------");

        boolean continueLooping = true;

        while (continueLooping) {
            System.out.print("Enter customer ID: ");
            String inputCustomerId = sc.nextLine().trim();

            System.out.print("Enter Current Room Number: ");
            String inputCurrentRoomNumber = sc.nextLine().trim();

            System.out.print("Enter Room Number that want to Change: ");
            String inputChangeRoomNumber = sc.nextLine().trim();

            boolean foundRoom = false;
            for (String[] room : rooms) {
                if (room[0].equals(inputChangeRoomNumber) && room[5].equalsIgnoreCase("Available")) {
                    foundRoom = true;
                    break;
                }
            }

            if (!foundRoom) {
                System.out.println("ERROR: The new room is not available.");
                continue;
            }

            System.out.print("Change status? (1 - Yes | 0 - No): ");
            int answer = sc.nextInt();
            sc.nextLine(); // consume leftover newline
            if (answer == 1) {
                // Update room statuses
                for (String[] room : rooms) {
                    if (room[0].equals(inputCurrentRoomNumber)) {
                        room[5] = "Available"; // old room becomes available
                    }
                    if (room[0].equals(inputChangeRoomNumber)) {
                        room[5] = "Occupied"; // new room becomes occupied
                    }
                }

                // Update booking
                boolean foundBooking = false;
                for (String[] booking : bookings) {
                    if (booking[1].equalsIgnoreCase(inputCustomerId) && booking[8].equals(inputCurrentRoomNumber)) {
                        booking[8] = inputChangeRoomNumber; // update to new room number
                        foundBooking = true;
                        break; // No need to keep looping
                    }
                }

                if (foundBooking) {
                    System.out.println("Successfully updated booking and room status!");
                    writeRoomFile();
                    writeBookingFile();
                } else {
                    System.out.println("ERROR: Booking not found for given Customer ID and Current Room Number.");
                }
            } else {
                System.out.println("No changes made.");
            }

            System.out.println("\n\nPress 0 to EXIT, any other number to CONTINUE");
            int continueAnswer = sc.nextInt();
            sc.nextLine(); // consume leftover newline
            if (continueAnswer == 0) {
                continueLooping = false;
            }
        }
    }
}
