package Assignment;

import java.io.*;
import java.util.*;

public class ViewCustProfile {
    Scanner sc = new Scanner(System.in);
    private List<Customer> customers; //List to hold customer data

    public ViewCustProfile(){
        customers = readCustomersFromFile("customer.txt");
    }

    public List<Customer> readCustomersFromFile(String filename){
        List<Customer> customers = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            while((line=br.readLine()) != null){
                customers.add(new Customer(line));
            }
        }catch(IOException e){
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
            System.out.printf("Username     : %s\n", customer.getUsername());
            System.out.printf("Full Name    : %s\n", customer.getFullName());
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
        //call this
        public void viewProfileForReceptionist() {
            int promptSelection;
            System.out.println("\n======================================");
            System.out.println("|       CUSTOMER PROFILE              |");
            System.out.println("======================================");
            
            do{
            System.out.println("\n1. Search by customer ID\n0. EXIT");
            System.out.print("Enter you selection: ");
            promptSelection=sc.nextInt();
            sc.nextLine();
            if(promptSelection==1){
                System.out.print("\nEnter Customer ID: ");
                String customerId = sc.nextLine().trim();
                displayCustomerProfile(customerId);
                continue;
            }
            else if(promptSelection==0){
                System.out.println("Exiting.....");                
            }
            else{
                System.out.println("\nERROR: Please enter number within the range.");
            }
            }while(promptSelection!=0);
    }
}
