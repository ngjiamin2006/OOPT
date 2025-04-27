package Assignment;

public class Transaction {

    //customer details
    private String customerId;
    private int roomNumber;

    //room prices
    public static final double STANDARD_ROOM = 150.0;
    public static final double DELUXE_ROOM = 250.0;
    public static final double FAMILY_ROOM = 350.0;
    public static final double SUITE_ROOM = 600.0;
    public static final double EXECUTIVE_ROOM = 450.0;
    private double roomPrice;

    //tax rate
    public static final double SST_RATE = 0.06;
    
    //deposit
    public static final double DEPOSIT = 200.0;

    //day count 
    private int dayCount;

    /* 
    //additional charges
    static final String[] ITEMS={"TOWEL","PILLOW","BED","CHAIR","TABLE","DESK","MIRROR",
    "TELEVISION","AIRCOND","HAIRDRYER","FRIDGE","SINK","TOILET","SHOWER","DOOR","WINDOW","WALL","CEILING",
    "COOKWARE","DISH","GLASS","KEY"};

    static final double[] ITEM_PRICES={20.0,40.0,500.0,50.0,150.0,200.0,150.0,2000.0,3000.0,200.0,
    1000.0,200.0,300.0,200.0,200.0,200.0,200.0,200.0,100.0,20.0,10.0,50.0};
    
    private int[] itemCounts=new int[ITEMS.length];
    */

    //constructor
    public Transaction(String customerId, int roomNumber, int dayCount) {
        this.customerId=customerId;
        this.roomNumber=roomNumber;
        this.dayCount=dayCount;
        roomPrice=0.0;
    }

    // Getters and Setters
    public String getCustomerId(){
        return customerId;
    }
    public int getRoomNumber(){
        return roomNumber;
    }
    public int getDayCount() {
        return dayCount;
    }

    public void setCustomerId(String customerId){
        this.customerId=customerId;
    }
    public void setRoomNumber(int roomNumber){
        this.roomNumber=roomNumber;
    }
    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public double calculateRoomPrice(int roomType){ 
        //decide room type 
        switch(roomType){
            case 1:
                roomPrice+=STANDARD_ROOM;
                break;
            case 2:
                roomPrice+=DELUXE_ROOM;
                break;
            case 3:
                roomPrice+=FAMILY_ROOM;
                break;
            case 4:
                roomPrice+=SUITE_ROOM;
                break;
            case 5:
                roomPrice+=EXECUTIVE_ROOM;
                break;
            default:
                System.out.print("NO ROOM SELECTED! ");
        }
        return roomPrice;
    }

    public double calculateTotal(){
        return (roomPrice+(roomPrice*SST_RATE)+DEPOSIT);
    }

    public void displayTransaction(){ 
        //display transaction details
        double totalRoomPrice=roomPrice*dayCount;
        System.out.println("Customer ID: "+customerId);
        System.out.println("Room Number: "+roomNumber);
        System.out.println("Room price "+"for "+dayCount+"days : "+totalRoomPrice);
        System.out.println("Deposit - "+DEPOSIT);
        System.out.println("Total price - "+calculateTotal());
    }
}


