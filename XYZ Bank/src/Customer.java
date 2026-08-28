import java.util.ArrayList;
import java.util.List;

//A class for a customer who can have many loan records
public class Customer implements CheckerPrinter {
    //attributes of the class
    private String customerID;//customer ID
    private double income;// customers annual income
    private boolean eligibilityStatus;//eligibility status of the customer
    private List<Loan> loanRecords;//list of the loan record for the customer

    // Constructor
    public Customer(String customerID, double income) {
        this.customerID = customerID; // initializes the customers ID
        this.income = income; // initializes the customers income
        this.eligibilityStatus = false; // initializes the eligibility status as false
        this.loanRecords = new ArrayList<>(); //initializes the loan records list
    }

    // Getters and setters
    public String getCustomerID() {
        return customerID;
    } //returns the customers ID

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income; //sets the icome for the customer
    }

    public boolean isEligible() {
        return eligibilityStatus;
    } //returns the eligibility

    public void setEligibilityStatus(boolean eligibilityStatus) {
        this.eligibilityStatus = eligibilityStatus;
    } //sets the eligibility

    // Methods for managing loan records
    public void addLoanRecord(Loan loan) {
        loanRecords.add(loan);
    } //add the loan record to the list

    public void removeLoanRecord(Loan loan) {
        loanRecords.remove(loan);
    } // removes the loan record from the list

    public List<Loan> getLoanRecords() {
        return loanRecords;
    } //returns the list of loan records

    // Implementation of CheckerPrinter interface methods
    @Override
    public boolean checkEligibility(double amountLeftToPay, double annualIncome) {
        // Calculate total amount left to pay based on loan records
        double totalAmountLeftToPay = 0;
        for (Loan loan : loanRecords) {
            totalAmountLeftToPay += loan.getAmountLeftToPay();
        }

        // Check eligibility based on the criteria
        return totalAmountLeftToPay <= 4 * income; //returns true or false based of eligibility
    }

    @Override
    public void printCustomerDetails() {
        System.out.println("Customer ID: " + customerID); //prints cutomers ID
        System.out.println("Income: " + income); //prints the income
        System.out.println("Eligibility Status: " + (isEligible() ? "Eligible" : "Not Eligible")); //prints eligibility status
    }
}

