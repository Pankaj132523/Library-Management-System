package paymentsService;

public class CashPayment extends Payment {
    public CashPayment(double amount, String patronId) {
        super(amount, patronId, "Cash");
    }

    @Override
    public boolean processPayment() {
        // Simulate cash payment always succeeds
        return true;
    }
}
