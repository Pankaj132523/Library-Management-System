package paymentsService;

public class UpiPayment extends Payment {
    private String upiId;

    public UpiPayment(double amount, String patronId, String upiId) {
        super(amount, patronId , "UPI");
        this.upiId = upiId;
    }

    public String getUpiId() {
        return upiId;
    }

    @Override
    public boolean processPayment() {
        // Simulate UPI payment always succeeds
        return true;
    }
}
