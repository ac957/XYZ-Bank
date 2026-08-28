//class called Auto that extends the Loan class
public class Auto extends Loan {
    // Constructors
    // Default Constructors that sets default values
    public Auto() {
        super("DefaultRecordID", "Auto", 0.0, 0.0, 0);
    }

    // Constructor with parameters to initialize Builder object with provided values
    public Auto(String recordID, double interestRate, double amountLeftToPay, int loanTermLeft) {
        super(recordID, "Auto", interestRate, amountLeftToPay, loanTermLeft);
    }//Calls the superlass constructor with default values

    // Method to display AutoLoan details
    @Override
    public void displayLoanDetails() {

    }

    // Validate the record ID for AutoLoan
    @Override
    protected boolean isValidRecordID(String recordID) {
        // Implement the validation logic for AutoLoan record ID specifically

        return  recordID.length() == 6; // Ensure the record ID consists of 6 digits
    }

    // Method to set the record ID for AutoLoan
    @Override
    public void setRecordID(String recordID) {
        // Implement the logic to set the record ID for AutoLoan

        this.recordID = recordID;//sets the record ID to the given value
    }
}

