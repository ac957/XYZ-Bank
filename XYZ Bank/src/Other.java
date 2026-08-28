//class called other that extends the Loan class
public class Other extends Loan {

    // Default Constructors that sets default values
    public Other() {
        super("DefaultRecordID", "Other", 0.0, 0.0, 0);
    } //Calls the superlass constructor with default values

    // Constructor with parameters to initialize OtherLoan object with provided values
    public Other(String recordID, double interestRate, double amountLeftToPay, int loanTermLeft) {
        super(recordID, "Other", interestRate, amountLeftToPay, loanTermLeft);
    }

    // Method to display OtherLoan details
    @Override
    public void displayLoanDetails() {
        // Print Other Loan Details
        System.out.println("Other Loan Details:");
        System.out.println("Record ID: " + getRecordID()); // Displays the record ID
        System.out.println("Interest Rate: " + getInterestRate() + "%"); // Displays the interest rate
        System.out.println("Amount Left to Pay: " + getAmountLeftToPay()); // Displays the amount left to pay
        System.out.println("Loan Term Left: " + getLoanTermLeft()); // Displays the loan term left
    }

    // Validate the record ID for OtherLoan
    @Override
    protected boolean isValidRecordID(String recordID) {
        // Implement the validation logic

        return recordID.length() == 6; // makes sure the record ID consists of 6 digits
    }

    // Method to set the record ID for OtherLoan
    @Override
    public void setRecordID(String recordID) {
        // Implement the logic to set the record ID

        this.recordID = recordID; //sets the record ID to the given value
    }
}

