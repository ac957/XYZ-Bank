//interface for checkprinter
public interface CheckerPrinter {
    //method that checks eligibility based on the total amount left to pay and the annual income input
    boolean checkEligibility(double totalAmountLeftToPay, double annualIncome);
    //prints the customer details
    void printCustomerDetails();
}

