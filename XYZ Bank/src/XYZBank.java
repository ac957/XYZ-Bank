import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

// Class definition for XYZBank
public class XYZBank {
    private List<Customer> customers; //List to store object
    private Set<String> recordIDs;// Set to store unique record ID for each record
    private Set<String> customerIDs; // set to maintain unique customer IDs for each record

    // Constructor to initialize lists and sets
    public XYZBank() {
        this.customers = new ArrayList<>(); //Initializes list for customers
        this.recordIDs = new HashSet<>(); //Initializes set of record IDs
        this.customerIDs = new HashSet<>(); //Initializes set of customer IDs
    }

    // Method to register a new customer
    public void registerCustomer(Customer customer) {
        if (!customerIDs.contains(customer.getCustomerID())) { //Check if customer ID is unique for each record
            customers.add(customer); //Add customer to the list
            customerIDs.add(customer.getCustomerID()); // Add the customer ID to the set
            System.out.println("Customer successfully registered."); // print message
        }
    }

    // Method to validate uniqueness of record ID(make sure every customer loan has different record id)
    public boolean isUniqueRecordID(String recordID) {
        return !recordIDs.contains(recordID); // Checks if recordID is not already in the set
    }

    // Method to add a record ID to the set
    public void addRecordID(String recordID) {
        recordIDs.add(recordID); // Adds record ID to the set
    }
    // Method to validate customer ID format
    public static boolean isValidCustomerID(String customerID) {

        // Check if the customerID matches the format 'AAAXXX', where A is a capital letter and X is a digit
        return customerID.matches("[A-Z]{3}\\d{3}");
    }

    // Method to validate record ID format
    protected boolean isValidRecordID(String recordID) {
        // Check if the length is 6 characters
        if (recordID.length() != 6) {
            return false;
        }
        // If all checks pass, return true
        return recordID.matches("\\d+"); // Check if recordID contains only digits
    }


    // Method to update information about an existing customer with new loan records
    public void updateCustomer(String customerID, double newIncome, boolean newStatus, Loan newLoanRecord, Loan oldLoanRecord) {
        Customer customer = findCustomer(customerID); // finds the customer by its ID
        if (customer != null) { //Checks if the customer exists
            customer.setIncome(newIncome); // Sets a new income fot the customer
            customer.setEligibilityStatus(newStatus);// New eligibility status set for the customer
            if (newLoanRecord != null) { //Checks if there is a new loan record
                double amountLeftToPay = newLoanRecord.getAmountLeftToPay();
                double annualIncome = customer.getIncome();
                if (customer.checkEligibility(amountLeftToPay, annualIncome)) { //Checks eligibility
                    customer.addLoanRecord(newLoanRecord); // adds the new loan record
                    System.out.println("New loan record successfully added.");
                } else {
                    System.out.println("Customer is not eligible for the new loan as the income entered is too small.");
                }
            }
            if (oldLoanRecord != null) { //Checks if there is an old loan Record
                customer.removeLoanRecord(oldLoanRecord);
                System.out.println("Old loan record removed successfully.");
            }
        } else {
            System.out.println("Customer not found."); //Prints ar error message
        }
    }

    //Method to print customer info
    public void printCustomersInfo() {
        if (!customers.isEmpty()) {//Checks if customer list isn't empty
            System.out.println("========================================");
            for (Customer customer : customers) {//Goes through each customer input
                System.out.println("CustomerID  " + customer.getCustomerID() + " Eligible to arrange new loans - " + (customer.isEligible() ? "YES" : "NO")); // Print customer ID and eligibility status

                // Print loan details for this customer
                List<Loan> loanRecords = customer.getLoanRecords();//Fetches the loan record for the customer
                if (!loanRecords.isEmpty()) {
                    System.out.println("RecordID\tLoanType\tIntRate\tAmountLeft\tTimeLeft");//print header for output
                    for (Loan loan : loanRecords) { //Checks each loan record
                        System.out.printf("%s\t%s\t%.2f\t%.2f\t%d\n", loan.getRecordID(), loan.getLoanType(), loan.getInterestRate(), loan.getAmountLeftToPay(), loan.getLoanTermLeft());//prints the loan records details in format
                    }
                }else {
                    System.out.println("No loan records for this customer.");
                }

                System.out.println("========================================");
            }
        } else {
            System.out.println("No customers have been registered yet.");
        }
    }



    // Method to handle loan type selection
    public Loan handleLoanTypeSelection(int loanType, Scanner scanner) {
        switch (loanType) {// Switch case depends on the selected loan type
            case 1:
                return handleMortgageLoanInput(scanner);// call method to handle mortgage loan that is input
            case 2:
                return handleBuilderLoanInput(scanner);//call meth to handle builder loan that is input
            case 3:
                return handleAutoLoanInput(scanner);
            case 4:
                return handlePersonalLoanInput(scanner);
            case 5:
                return handleOtherLoanInput(scanner);
            default:
                System.out.println("Invalid loan type.");
                return null;
        }
    }

    // Helper method to handle mortgage loan input
    private Mortgage handleMortgageLoanInput(Scanner scanner) {
        String mortgageRecordID;
        //loop to ensure a valid input for the mortgage record ID is entered
        do {
            System.out.println("Enter record ID for Mortgage Loan (6 digits):");
            mortgageRecordID = scanner.next();//read input from user
            if (!isValidRecordID(mortgageRecordID)) { //checks if input is valid
                System.out.println("Invalid record ID format. Please use the format 'XXXXXX', where X is a digit 0-9.");
            } else if (!isUniqueRecordID(mortgageRecordID)) {//checks if the record ID entered is unique
                System.out.println("This Record ID has already been entered. Please enter a unique record ID.");
            }
        } while (!isValidRecordID(mortgageRecordID) || !isUniqueRecordID(mortgageRecordID));

        System.out.println("Enter interest rate for Mortgage Loan:");
        double mortgageInterestRate;// Declare variable to store mortgage interest rate
        while (!scanner.hasNextDouble()) {// Validates input for mortgage interest rate
            System.out.println("Invalid input. Please enter a valid interest rate:");
            scanner.next(); // Clear the invalid input
        }
        mortgageInterestRate= scanner.nextDouble();

        System.out.println("Enter amount left to pay for Mortgage Loan:");
        double mortgageAmountLeftToPay; // Declare variable to store amount left to pay for Mortgage Loan
        while (!scanner.hasNextDouble()) { // Declare variable to store amount left to pay for Mortgage Loan
            System.out.println("Invalid input. Please enter a valid amount left to pay:");
            scanner.next(); // Clear the invalid input
        }
        mortgageAmountLeftToPay = scanner.nextDouble();

        System.out.println("Enter loan term left for Mortgage Loan:");
        int mortgageLoanTermLeft;// Declare variable to store loan term left for Mortgage Loan
        while (!scanner.hasNextInt()) {  // Validate input for loan term left
            System.out.println("Invalid input. Please enter a valid loan term left:");
            scanner.next(); // Clear the invalid input
        }
        mortgageLoanTermLeft = scanner.nextInt();

        double mortgageOverpayment;
        boolean validOverpayment = false;
        // loop to ensure a valid input for mortgage overpayment is entered
        do {
            System.out.println("Enter overpayment for Mortgage Loan (in percentage between 0 and 2):");
            while (!scanner.hasNextDouble()) {// Validate input for overpayment
                System.out.println("Invalid input. Please enter a valid overpayment:");
                scanner.next(); // Clear the invalid input
            }
            mortgageOverpayment = scanner.nextDouble();

            //Check if overpayment percentage entered is within the valid range
            if (mortgageOverpayment >= 0 && mortgageOverpayment <= 2) {
                validOverpayment = true;// Set to true if overpayment is within range
            } else {
                System.out.println("Overpayment percentage must be between 0 and 2.");
            }
        } while (!validOverpayment);


        // Create and return an instance of MortgageLoan with input
        return new Mortgage(mortgageRecordID, mortgageInterestRate, mortgageAmountLeftToPay, mortgageLoanTermLeft, mortgageOverpayment);
    }

    // Helper method to handle builder loan input
    private Builder handleBuilderLoanInput(Scanner scanner) {
        String builderRecordID;
        do { //loop ensures valid input for builder is entered
            System.out.println("Enter record ID for Builder Loan (6 digits): ");
            builderRecordID = scanner.next();//reads the users input
            if (!isValidRecordID(builderRecordID)) { //checks if the input is valid
                System.out.println("Invalid record ID format. Please use the format 'XXXXXX', where X is a digit 0-9.");
            } else if (!isUniqueRecordID(builderRecordID)) {//checks if the record iD is unique
                System.out.println("This Record ID has already been entered. Please enter a unique record ID.");
            }
        } while (!isValidRecordID(builderRecordID) || !isUniqueRecordID(builderRecordID));

        System.out.println("Enter interest rate for Builder Loan:");
        double builderInterestRate;
        while (!scanner.hasNextDouble()) {//validates the input for the builder interest rate
            System.out.println("Invalid input. Please enter a valid interest rate:");
            scanner.next(); // Clear the invalid input
        }
        builderInterestRate = scanner.nextDouble();

        System.out.println("Enter amount left to pay for Builder Loan:");
        double builderAmountLeftToPay;
        while (!scanner.hasNextDouble()) {//Validates the input for amount left to pay
            System.out.println("Invalid input. Please enter a valid amount left to pay:");
            scanner.next(); // Clear the invalid input
        }
        builderAmountLeftToPay = scanner.nextDouble();
        System.out.println("Enter loan term left for Builder Loan:");
        int builderLoanTermLeft;
        while (!scanner.hasNextInt()) {// Validates input for loan term left
            System.out.println("Invalid input. Please enter a valid loan term left:");
            scanner.next(); // Clear the invalid input
        }
        builderLoanTermLeft = scanner.nextInt();

        double builderOverpayment;
        boolean validOverpayment = false; // checks if overpayment input is valid
        do { // loop to ensure the input for builder overpayment is valid
            System.out.println("Enter overpayment for Builder Loan (in percentage between 0 and 2):");
            while (!scanner.hasNextDouble()) {// Validates input for overpayment
                System.out.println("Invalid input. Please enter a valid overpayment:");
                scanner.next(); // Clear the invalid input
            }
            builderOverpayment = scanner.nextDouble();

            // Check if overpayment percentage is within the range
            if (builderOverpayment >= 0 && builderOverpayment <= 2) {
                validOverpayment = true;// Set to true if overpayment is valid
            } else {
                System.out.println("Overpayment percentage must be between 0 and 2.");
            }
        } while (!validOverpayment);

        // Creates and returns an instance of BuilderLoan
        return new Builder(builderRecordID, builderInterestRate, builderAmountLeftToPay, builderLoanTermLeft, builderOverpayment);
    }

    // method to handle auto loan input
    private Auto handleAutoLoanInput(Scanner scanner) {
        String autoRecordID;
        boolean validRecordID = false;//checks if record ID is valid
        do { // loop to ensure valid input for auto record ID
            System.out.println("Enter record ID for Auto Loan (6 digits):");
            autoRecordID = scanner.next(); //reads the users input
            if (!isValidRecordID(autoRecordID)) {
                System.out.println("Invalid record ID format. Please use the format 'XXXXXX', where X is a digit 0-9.");
            } else {
                validRecordID = true; //Checks and sets to true if the record ID input is true
            }
        } while (!validRecordID);

        System.out.println("Enter interest rate for Auto Loan:");
        double autoInterestRate;
        while (!scanner.hasNextDouble()) {  // Validates input for auto interest rate
            System.out.println("Invalid input. Please enter a valid interest rate:");
            scanner.next(); // Clear the invalid input
        }
        autoInterestRate = scanner.nextDouble();
        System.out.println("Enter amount left to pay for Auto Loan (in years):");
        double autoAmountLeftToPay;
        while (!scanner.hasNextDouble()) {  // Validates the input for amount left to pay
            System.out.println("Invalid input. Please enter a valid amount left to pay:");
            scanner.next(); // Clear the invalid input
        }
        autoAmountLeftToPay = scanner.nextDouble();

        System.out.println("Enter loan term left for Auto Loan (in years):");
        int autoLoanTermLeft;
        while (!scanner.hasNextInt()) { // Validates input for loan term left
            System.out.println("Invalid input. Please enter a valid loan term left:");
            scanner.next(); // Clear the invalid input
        }
        autoLoanTermLeft = scanner.nextInt();

        // Create and return an instance of AutoLoan
        return new Auto(autoRecordID, autoInterestRate, autoAmountLeftToPay, autoLoanTermLeft);
    }


    //  method to handle personal loan input
    private Personal handlePersonalLoanInput(Scanner scanner) {
        String personalRecordID;//variable that will store personal record ID
        do { //do-while loop to ensure valid input for personal record ID
            System.out.println("Enter record ID for Personal Loan (6 digits):");
            personalRecordID = scanner.next();
            if (!isValidRecordID(personalRecordID)) { // Check if the entered record ID format is valid
                System.out.println("Invalid record ID format. Please use the format 'XXXXXX', where X is a digit 0-9.");
            } else if (!isUniqueRecordID(personalRecordID)) {
                System.out.println("This Record ID has already been entered. Please enter a unique record ID.");
            }
        } while (!isValidRecordID(personalRecordID) || !isUniqueRecordID(personalRecordID));

        System.out.println("Enter interest rate for Personal Loan:");
        double personalInterestRate;
        while (!scanner.hasNextDouble()) { // Validates the input for personal interest rate
            System.out.println("Invalid input. Please enter a valid interest rate:");
            scanner.next(); // Clears the invalid input
        }
        personalInterestRate = scanner.nextDouble();
        System.out.println("Enter amount left to pay for Personal Loan:");
        double personalAmountLeftToPay;
        while (!scanner.hasNextDouble()) { //validates the input for personal loan amount left to pay
            System.out.println("Invalid input. Please enter a valid amount left to pay:");
            scanner.next(); // Clears the invalid input
        }
        personalAmountLeftToPay = scanner.nextDouble();
        System.out.println("Enter loan term left for Personal Loan:");
        int personalLoanTermLeft;
        while (!scanner.hasNextInt()) { //validates the input for the loan term left
            System.out.println("Invalid input. Please enter a valid loan term left:");
            scanner.next(); // Clears the invalid input
        }
        personalLoanTermLeft = scanner.nextInt();

        // Create and return an instance of PersonalLoan
        Personal personalLoan = new Personal(personalRecordID, personalInterestRate,
                personalAmountLeftToPay, personalLoanTermLeft);
        addRecordID(personalRecordID); //adds the Record ID to the set of record IDs input
        return personalLoan; //returns instance
    }

    //method to handle other loan input
    private Other handleOtherLoanInput(Scanner scanner) {
        String otherRecordID; //variable that will store other record ID
        do { //do-while loop to ensure valid input for other record ID
            System.out.println("Enter record ID for Other Loan (6 digits):");
            otherRecordID = scanner.next();
            if (!isValidRecordID(otherRecordID)) { // Check if the entered record ID format is valid
                System.out.println("Invalid record ID format. Please use the format 'XXXXXX', where X is a digit 0-9.");
            } else if (!isUniqueRecordID(otherRecordID)) {
                System.out.println("This Record ID has already been entered. Please enter a unique record ID.");
            }
        } while (!isValidRecordID(otherRecordID) || !isUniqueRecordID(otherRecordID));

        System.out.println("Enter interest rate for Other Loan:");
        double otherInterestRate;
        while (!scanner.hasNextDouble()) {  // Validates the input for other interest rate
            System.out.println("Invalid input. Please enter a valid interest rate:");
            scanner.next(); // Clears the invalid input
        }
        otherInterestRate = scanner.nextDouble();
        System.out.println("Enter amount left to pay for Other Loan:");
        double otherAmountLeftToPay;
        while (!scanner.hasNextDouble()) {  // Validates the input for amount left to pay
            System.out.println("Invalid input. Please enter a valid amount left to pay:");
            scanner.next(); // Clears the invalid input
        }
        otherAmountLeftToPay = scanner.nextDouble();
        System.out.println("Enter loan term left for Other Loan:");
        int otherLoanTermLeft;
        while (!scanner.hasNextInt()) { // Validates the input for loan term left fot other loan
            System.out.println("Invalid input. Please enter a valid loan term left:");
            scanner.next(); // Clears the invalid input
        }
        otherLoanTermLeft = scanner.nextInt();

        // Create and return an instance of OtherLoan
        Other otherLoan = new Other(otherRecordID, otherInterestRate,
                otherAmountLeftToPay, otherLoanTermLeft);
        addRecordID(otherRecordID);
        return otherLoan; //returns instance
    }



    //method to find a customer by customerID
    private Customer findCustomer(String customerID) {
        for (Customer customer : customers) { //iterates through the list of customers input
            if (customer.getCustomerID().equals(customerID)) { //checks is the customer id that is currents ID matches the given customer
                return customer;
            }
        }
        return null; //returns if the customer is not found
    }

    // Main method for testing and validation

    public static void main(String[] args) {

        XYZBank bank = new XYZBank(); //creates a new instance of XYZBank
        // initialization for scanner for the users input
        Scanner scanner = new Scanner(System.in);

        // Main loop for handling customers
        boolean addNewCustomer = true;
        while (addNewCustomer) {
            // Registering a new customer
            System.out.println("Enter customer ID (3 letters 3 digits):");
            //makes the input letters uppercase
            String customerID = scanner.next().toUpperCase();
            if (!isValidCustomerID(customerID)) { //checks if format is valid
                System.out.println("Invalid customerID format. Format should be 'AAAXXX', where A is a capital letter and X is a digit.");
                continue; // Restart the loop to ask for ID again
            }

            System.out.println("Enter customer income:");
            double income = scanner.nextDouble();

            // Create a new customer with info given (income and ID)
            Customer newCustomer = new Customer(customerID, income);
            bank.registerCustomer(newCustomer); //registers the new customer
            System.out.println("Customer registered successfully.");


            // Loop for registering loan records for the current customer
            boolean registerLoanRecords = true;
            while (registerLoanRecords) {
                int loanType = 0; // Initialize loanType outside the loop
                boolean validInput = false; // Flag to track if the input is valid i

                //loop for the loan type selection
                while (!validInput) {
                // Handle loan type selection
                System.out.println("Select the loan type (enter the number):");
                System.out.println("1. Mortgage Loan");
                System.out.println("2. Builder Loan");
                System.out.println("3. Auto Loan");
                System.out.println("4. Personal Loan");
                System.out.println("5. Other Loan");

                // Check if the next input is an integer
                if (scanner.hasNextInt()) {
                    loanType = scanner.nextInt();
                    validInput = true; // Set valid Input to true to exit the loop
                } else {
                    // Clears the invalid input
                    scanner.next();
                    System.out.println("Invalid input. Please enter an integer.");
                }
            }

                // Calls method to handle loan input based on the selected loan type
                Loan newLoanRecord = bank.handleLoanTypeSelection(loanType, scanner);

                // Update customer information with the new loan record
                bank.updateCustomer(customerID, income, true, newLoanRecord, null);

                // Ask if the user wants to register another loan record for the current customer
                System.out.println("Do you want to register another loan record for this customer? (yes/no)");
                String response = scanner.next().toLowerCase();
                if (!response.equals("yes")) {
                    registerLoanRecords = false; //sets registered loan reords to false to exit the loop
                }
            }

            // Ask if the user wants to add loan records for a new customer
            System.out.println("Do you want to add loan records for a new customer? (yes/no)");
            String response = scanner.next().toLowerCase(); //converts the input to a
            if (!response.equals("yes")) {
                addNewCustomer = false; // Set add New Customer to false to exit loop
            }
        }

        // Print all customers' information
        bank.printCustomersInfo();

        // Close the scanner
        scanner.close();

    }
}

