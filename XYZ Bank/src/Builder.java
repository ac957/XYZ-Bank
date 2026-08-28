//class called Builder that extends the Loan class
public class Builder extends Loan {
    // Constructors
    // Default Constructors that sets default values
    public Builder() {
        super("DefaultRecordID", "Builder", 0.0, 0.0, 0);
    }

    // Constructor with parameters to initialize Builder object with provided values
    public Builder(String recordID, double interestRate, double amountLeftToPay, int loanTermLeft, double overpayment) {
        super(recordID, "Builder", interestRate, amountLeftToPay, loanTermLeft);
        // Set the overpayment for BuilderLoan
        setOverpayment(overpayment);
    }//Calls the superlass constructor with default values

    // Method to display BuilderLoan details
    @Override
    public void displayLoanDetails() {

    }

    // Validate the record ID for BuilderLoan
    @Override
    protected boolean isValidRecordID(String recordID) {
        // Implement the validation logic for BuilderLoan record IDs specifically

        return  recordID.length() == 6;// Makes sure the record ID consists of 6 digits
    }

    // Method to set the record ID for BuilderLoan
    @Override
    public void setRecordID(String recordID) {
        // Implement the logic to set the record ID for BuilderLoan
        // For example:
        this.recordID = recordID;//sets the record ID to the given value
    }

    // Method to set the overpayment for BuilderLoan
    public void setOverpayment(double overpayment) {
        // Implement the logic to set the overpayment

    }
}

