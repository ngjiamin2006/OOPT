package Assignment;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FeedbackSupport {
    Scanner sc = new Scanner(System.in);

    // instance variable
    private int breakfastPax;
    public static final double BREAKFASTPRICE = 30.0;


    // No-arg Constructor
    public FeedbackSupport() {
        breakfastPax = 0;
    }

    // Parameterized Constructor
    public FeedbackSupport(int breakfastPax) {
        this.breakfastPax = breakfastPax;
    }

    // Getter
    public int getBreakfastPax() {
        return breakfastPax;
    }

    // Setter
    public void setBreakfastPax(int breakfastPax) {
        this.breakfastPax = breakfastPax;
    }

    // Methods
    public void promptComplaint() { //call this when run
        System.out.println("Select complaint type: " + "\n1. Room Issue\n2. Facilities\n3. House keeping\n4. Billing\n5. Food\n6. Service\n\n0. Exit");
        int complaintType = sc.nextInt();
        sc.nextLine(); // Consume newline left-over

        if (complaintType != 0) {
            inputComplaint(complaintType);
        }
    }

    public void inputComplaint(int complaintType) {
        try {
            System.out.println("Enter your Complaint: ");
            String complaintText = sc.nextLine();
            writeComplaintToFile(complaintType, complaintText);
            System.out.println("Customer message saved to file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public void writeComplaintToFile(int complaintType, String complaintText) throws IOException {
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
            writer.write(complaintText + "\n---------------------------------------------------------");
        }
    }
}






