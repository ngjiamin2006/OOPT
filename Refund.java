package Assignment;

public class Refund extends Checkout{
    private double totalRefund;
    private int refundType;
    private String refundNotes;

    //constructor
    public Refund(int roomNumber, int refundType){
        super(roomNumber);
        totalRefund=0.0;
        this.refundType=refundType;
        refundNotes="";
    }

    //getter
    public double getTotalRefund(){
        return totalRefund;
    }
    public int getRefundType(){
        return refundType;
    }

    public String getRefundNotes(){
        return refundNotes;
    }

    //setter
    public void setRefundType(int refundType){
        this.refundType=refundType;
    }

    public void setRefundNotes(String refundNotes){
        this.refundNotes=refundNotes;
    }

    //method
    public double refundOfDeposit(){
        switch(refundType){
            case 1: //no issue
                totalRefund=Transaction.DEPOSIT; // 100% refund
                break;
            case 2: //minor damage
                totalRefund=Transaction.DEPOSIT*0.8;
                break;
            case 3: //moderate damage
                totalRefund=Transaction.DEPOSIT*0.5;
                break;
            case 4: //serious damage
                totalRefund=0.0;
                break;
            default:
                System.out.println("Damage record not found!");
        }
        return totalRefund;
    }

    public String displayRefundNotes(){
        switch(refundType){
            case 1: //no issue
                refundNotes="Fully return, no additional charges.";
                break;
            case 2: //minor damage
            refundNotes="80% return, minor damage found.";
                break;
            case 3: //moderate damage
            refundNotes="Half return, moderate damage found.";
                break;
            case 4: //serious damage
            refundNotes="No return, serious damage found.";
                break;
            default:
                System.out.println("Damage record not found!");
        }
        return refundNotes;

}

    public String toString(){
        return super.toString()+
                "\nTotal refund: "+totalRefund+
                "\nNotes: "+refundNotes;
    }

}
