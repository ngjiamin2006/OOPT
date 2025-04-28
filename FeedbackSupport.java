package Assignment;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FeedbackSupport {
    Scanner sc = new Scanner(System.in);

    // instance variable
    private int complaintType;
    private String complaintText;

    // No-arg Constructor
    public FeedbackSupport() {
        complaintType = 0;
        complaintText = "";
    }

    // Getter
    public int getComPlaintType() {
        return complaintType;
    }

    // no need setter because prompt from user

    // Methods
    public void promptComplaint() { //call this when run
        System.out.println();
        System.out.println("====================================");
        System.out.println("|                                  |");
        System.out.println("|         FEEDBACK REPORT          |");
        System.out.println("|                                  |");
        System.out.println("====================================");

        do{
            System.out.println("Select complaint type: " + "\n1. Room Issue\n2. Facilities\n3. House keeping\n4. Billing\n5. Food\n6. Service\n\n0. Exit");
            System.out.print("Enter your choice: ");
            complaintType = sc.nextInt();
            sc.nextLine(); // Consume newline left-over

            if (complaintType != 0) {
                inputComplaint();
            }
        }while(complaintType!=0);
    }

    public void inputComplaint() {

        try {
            System.out.println("Please enter your Complaint: ");
            complaintText = sc.nextLine();
            writeComplaintToFile();
            System.out.println("Customer message saved to file.");
            System.out.println("");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public void writeComplaintToFile() throws IOException {
        String fileName;
        switch (complaintType) {
            case 1:
                fileName = "RoomIssueComplaint.txt";
                break;
            case 2:
                fileName = "FacilitiesComplaint.txt";
                break;
            case 3:
                fileName = "HousekeepingComplaint.txt";
                break;
            case 4:
                fileName = "BillingComplaint.txt";
                break;
            case 5:
                fileName = "FoodComplaint.txt";
                break;
            case 6:
                fileName = "ServiceComplaint.txt";
                break;
            default:
                System.out.println("Invalid complaint type. Please try again.");
                return;
        }

        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(complaintText + "\n---------------------------------------------------------+\n");
        }
    }
}






