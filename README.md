# XYZ Bank Loan Management System

## Overview

The **XYZ Bank Loan Management System** is a Java-based console application developed to manage customer information and their existing loan records.

The system allows bank staff to register customers, record different types of loans, validate customer and loan information, and determine whether a customer is eligible to arrange additional loans based on their annual income and outstanding loan balance.

The project was developed to demonstrate key **Object-Oriented Programming (OOP)** principles in Java, including inheritance, abstraction, encapsulation, polymorphism and interfaces.

---

## Features

### Customer Management

The application allows users to:

- Register new customers.
- Assign each customer a unique customer ID.
- Record the customer's annual income.
- Manage multiple customers within the bank.
- Display customer information and eligibility status.

Customer IDs must follow the required format of **3 letters followed by 3 digits**.

Example:

```text
ABC123
```

### Loan Management

Customers can have multiple loan records. The system supports five different loan types:

- Mortgage Loan
- Builder Loan
- Auto Loan
- Personal Loan
- Other Loan

Each loan record stores information including:

- Record ID
- Loan type
- Interest rate
- Amount left to pay
- Remaining loan term

Mortgage and Builder loans can also include an overpayment value.

### Loan Record Validation

The application validates loan record IDs to ensure they contain six characters/digits and prevents duplicate record IDs from being entered.

The system also performs input validation for numerical values such as:

- Interest rates
- Outstanding loan amounts
- Remaining loan terms
- Customer income

Invalid input is rejected and the user is prompted to enter the information again.

### Eligibility Checking

The system determines whether a customer is eligible to arrange additional loans by comparing their total outstanding loan balance with their annual income.

The eligibility rule implemented is:

```text
Total Outstanding Loans <= 4 × Annual Income
```

For example, if a customer has an annual income of £30,000, their total outstanding loans must not exceed £120,000 to satisfy the implemented eligibility condition.

## How to Run

### Requirements

To run the project, you will need:

- **Java Development Kit (JDK)**
- A Java-compatible IDE such as IntelliJ IDEA, Eclipse or VS Code

### Running the Application

1. Clone or download the project.
2. Open the project in your preferred Java IDE.
3. Ensure all files within the `src` directory are included in the project.
4. Run:

```text
XYZBank.java
```

5. Follow the instructions displayed in the console.

---

## Example Usage

When the application starts, the user is prompted to enter a customer ID and income:

```text
Enter customer ID (3 letters 3 digits):
ABC123

Enter customer income:
30000
```

The user can then select a loan type:

```text
Select the loan type (enter the number):
1. Mortgage Loan
2. Builder Loan
3. Auto Loan
4. Personal Loan
5. Other Loan
```

After selecting a loan, the user provides the relevant loan information.

Multiple loans can be added to the same customer, and multiple customers can be registered.

At the end of the program, customer information and eligibility status are displayed.

---

## Technologies Used

- **Java**
- **Object-Oriented Programming**
- Java Collections Framework
- `ArrayList`
- `HashSet`
- Java `Scanner` for console input

---

## Key Learning Outcomes

This project provided practical experience with:

- Designing classes and objects in Java
- Applying inheritance and polymorphism
- Creating and implementing interfaces
- Using abstract classes and methods
- Encapsulating data using access modifiers
- Managing objects using Java collections
- Implementing input validation
- Handling user interaction through the command line
- Designing relationships between customers and loan records
- Implementing business rules for loan eligibility


## Author

**Anetta Chibangula**

This project was developed as part of a Java/Object-Oriented Programming project.
