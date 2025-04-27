import java.io.*;
import java.util.*;

public class ViewCustProfile {
    Scanner sc = new Scanner(System.in);
    List<Customer> customers;  // List to hold customer data

    // Constructor to initialize customer data
    public ViewCustProfile() {
        customers = readCustomersFromFile("customer.txt");
    }

    // Method to read customer data from file
    public List<Customer> readCustomersFromFile(String filename) {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length == 6) { // Ensure correct data format
                    String customerId = data[0];
                    String username = data[1];
                    String fullName = data[2];
                    String password = data[3];
                    String phoneNo = data[4];
                    String email = data[5];
                    customers.add(new Customer(customerId, username, fullName, password, phoneNo, email));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }

    // Method to display customer profile based on customer ID
    public void displayCustomerProfile(String customerId) {
        Customer customer = findCustomerById(customerId);

        if (customer != null) {
            System.out.println("===============================================");
            System.out.println("                Customer Profile               ");
            System.out.println("===============================================");
            System.out.printf("Customer ID  : %s\n", customer.getCustomerId());
            System.out.printf("Username     : %s\n", customer.getUserName());
            System.out.printf("Full Name    : %s\n", customer.getFullname());
            System.out.printf("Phone Number : %s\n", customer.getPhoneNo());
            System.out.printf("Email        : %s\n", customer.getEmail());
            System.out.println("===============================================");
        } else {
            System.out.println("[Customer not found. Please try again with a valid ID.]");
        }
    }

    // Method to search for a customer by ID
    public Customer findCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;  // Customer not found
    }

    // Method for receptionist to enter customer ID and view profile
    public void viewProfileForReceptionist() {
        System.out.print("Enter Customer ID: ");
        String customerId = sc.nextLine().trim();

        displayCustomerProfile(customerId);
    }

    // Main method to run the program
    public static void main(String[] args) {
        ViewCustProfile viewCustProfile = new ViewCustProfile();
        viewCustProfile.viewProfileForReceptionist();  // Let the receptionist enter a customer ID and view the profile
    }

    // Customer class to hold customer details
    public static class Customer {
        String customerId;
        String username;
        String fullName;
        String password;
        String phoneNo;
        String email;

        // Constructor
        public Customer(String customerId, String username, String fullName, String password, String phoneNo, String email) {
            this.customerId = customerId;
            this.username = username;
            this.fullName = fullName;
            this.password = password;
            this.phoneNo = phoneNo;
            this.email = email;
        }

        // Getter methods
        public String getCustomerId() {
            return customerId;
        }

        public String getUserName() {
            return username;
        }

        public String getFullname() {
            return fullName;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public String getEmail() {
            return email;
        }
    }
}
