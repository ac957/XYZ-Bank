//class called Mortgage that extends the Loan class
public class Mortgage extends Loan {
    // Constructors

    // Default Constructors that sets default values
    public Mortgage() {
        super("DefaultRecordID", "Mortgage", 0.0, 0.0, 0);
    }//Calls the superlass constructor with default values


    // Constructor with parameters to initialize Mortgage object with provided values
    public Mortgage(String recordID, double interestRate, double amountLeftToPay, int loanTermLeft, double overpayment) {
        super(recordID, "Mortgage", interestRate, amountLeftToPay, loanTermLeft);
        setOverpayment(overpayment);
    }

    // Method to display MortgageLoan details
    @Override
    public void displayLoanDetails() {

    }

    // Validate the record ID for MortgageLoan
    @Override
    protected boolean isValidRecordID(String recordID) {
        // Implement the validation logic specific to record IDs for mortgage
        // For example:
        return  recordID.length() == 6;// Ensure the record ID consists of 6 digits
    }

    // Method to set the record ID for MortgageLoan
    @Override
    public void setRecordID(String recordID) {
        // Implement the logic to set the record ID for MortgageLoan

        this.recordID = recordID;//sets the record ID to the given value
    }

    // Method to set the overpayment for MortgageLoan
    public void setOverpayment(double overpayment) {
        // Implement the logic to set the overpayment

    }
}

