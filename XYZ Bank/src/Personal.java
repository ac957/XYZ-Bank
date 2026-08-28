//class called Personal that extends the Loan class
public class Personal extends Loan {
    // Constructors
    // Default Constructors that sets default values
    public Personal() {
        super("DefaultRecordID", "Personal", 0.0, 0.0, 0);
    }

    // Constructor with parameters to initialize Builder object with provided values
    public Personal(String recordID, double interestRate, double amountLeftToPay, int loanTermLeft) {
        super(recordID, "Personal", interestRate, amountLeftToPay, loanTermLeft);
    }//Calls the superlass constructor with default values

    // Method to display PersonalLoan details
    @Override
    public void displayLoanDetails() {

    }

    // Validate the record ID for PersonalLoan
    @Override
    protected boolean isValidRecordID(String recordID) {
        // Implement the validation logic specific to PersonalLoan record IDs

        return recordID.length() == 6;// Makes sure the record ID consists of 6 digits
    }

    // Method to set the record ID for PersonalLoan
    @Override
    public void setRecordID(String recordID) {
        // Implement the logic to set the record ID for PersonalLoan

        this.recordID = recordID;//sets the record ID to the given value
    }
}

