// Abstract class for Loan
public abstract class Loan {
    // Attributes for class
    public String recordID; // Record ID of the loan
    private String loanType; // Type of loan
    private double interestRate; // Interest rate of the loan
    private double amountLeftToPay; // Amount left to pay for the loan
    private int loanTermLeft; // Loan term left for the loan

    // Constructor
    public Loan(String recordID, String loanType, double interestRate, double amountLeftToPay, int loanTermLeft) {
        this.recordID = recordID; // Initializes the record ID
        this.loanType = loanType; // Initializes the loan type
        this.interestRate = interestRate; // Initialize the interest rate
        this.amountLeftToPay = amountLeftToPay; // Initialize the amount left to pay
        this.loanTermLeft = loanTermLeft; // Initialize the loan term left
    }

    // Validation method for Record IDs format
    protected boolean isValidRecordID(String recordID) {
        // Check if the length is 6 characters
        if (recordID.length() != 6) {
            return false; // Returns false if length is not 6
        }

        // Check if each character is a digit
        for (char c : recordID.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false; // Return false if any character is not a digit
            }
        }

        // If all checks pass, return true
        return true;
    }

    // Getters
    public String getRecordID() {
        return recordID; // Return record ID
    }

    public String getLoanType() {
        return loanType; // Return loan type
    }

    public double getInterestRate() {
        return interestRate; // Return interest rate
    }

    public int getLoanTermLeft() {
        return loanTermLeft; // Return loan term left
    }

    public double getAmountLeftToPay() {
        return amountLeftToPay; // Return amount left to pay
    }

    // Abstract method to display loan details
    public abstract void displayLoanDetails();

    // Abstract method to set Record ID
    public abstract void setRecordID(String recordID);

    // Setters (if needed)
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate; // Set interest rate
    }

    public void setLoanTermLeft(int loanTermLeft) {
        this.loanTermLeft = loanTermLeft; // Set loan term left
    }
}
