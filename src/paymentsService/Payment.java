package paymentsService;

public abstract class Payment {
    protected double amount;
    protected String patronId;
    protected String paymentType;

    public Payment(double amount, String patronId, String paymentType) {
        this.amount = amount;
        this.patronId = patronId;
        this.paymentType= paymentType;
    }

    public double getAmount() {
        return amount;
    }

    public String getPatronId() {
        return patronId;
    }

    public abstract boolean processPayment();

    public String getPaymentDetails() {
        return "Payment of amount: " + amount + " by Patron ID: " + patronId;
    }

    public String getPaymentType() {
        return paymentType;
    }
}
