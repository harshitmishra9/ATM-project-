import java.util.ArrayList;
import java.util.Scanner;

class ATM {
    private String pin;  // The ATM PIN (used for authentication)
    private double balance;  // The user's account balance
    private ArrayList<String> transactionHistory;  // To store all transactions (deposits and withdrawals)

    // Constructor to initialize the ATM with a PIN and an initial balance
    public ATM(String pin, double initialBalance) {
        this.pin = pin;  // Set the initial PIN
        this.balance = initialBalance;  // Set the initial account balance
        this.transactionHistory = new ArrayList<>();  // Initialize the transaction history list
    }

    /**
     * Authenticate the user by checking if the entered PIN matches the stored PIN.
     * @param enteredPin The PIN entered by the user for authentication.
     * @return true if the PIN matches, false otherwise.
     */
    public boolean authenticate(String enteredPin) {
        return this.pin.equals(enteredPin);  // Check if the entered PIN matches the stored PIN
    }

    /**
     * Check the current balance in the account.
     */
    public void checkBalance() {
        System.out.println("Your current balance is: $" + balance);  // Display the current balance
    }

    /**
     * Deposit an amount into the account.
     * @param amount The amount to deposit.
     */
    public void deposit(double amount) {
        if (amount > 0) {  // Ensure the deposit amount is positive
            balance += amount;  // Add the deposit amount to the balance
            transactionHistory.add("Deposited: $" + amount);  // Record the deposit in transaction history
            System.out.println("Deposited $" + amount + ". New balance: $" + balance);
        } else {
            System.out.println("Deposit amount must be greater than 0.");  // Error for non-positive deposit amounts
        }
    }

    /**
     * Withdraw an amount from the account.
     * @param amount The amount to withdraw.
     */
    public void withdraw(double amount) {
        if (amount > 0) {  // Ensure the withdrawal amount is positive
            if (amount <= balance) {  // Check if there are sufficient funds
                balance -= amount;  // Subtract the withdrawal amount from the balance
                transactionHistory.add("Withdrew: $" + amount);  // Record the withdrawal in transaction history
                System.out.println("Withdrew $" + amount + ". New balance: $" + balance);
            } else {
                System.out.println("Insufficient funds!");  // Error for insufficient funds
            }
        } else {
            System.out.println("Withdrawal amount must be greater than 0.");  // Error for non-positive withdrawal amounts
        }
    }

    /**
     * Change the PIN of the account.
     * @param newPin The new PIN to set.
     */
    public void changePin(String newPin) {
        this.pin = newPin;  // Set the new PIN
        System.out.println("PIN changed successfully.");
    }

    /**
     * Print the transaction history (all deposits and withdrawals).
     */
    public void printTransactionHistory() {
        System.out.println("\nTransaction History:");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions made yet.");  // If no transactions exist
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);  // Print each transaction in the history
            }
        }
    }
}

public class ATMApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Create a scanner object for user input

        // Create ATM object with a sample PIN ("1234") and an initial balance of $1000
        ATM atm = new ATM("1234", 1000.00);

        // Ask the user to enter their PIN
        System.out.print("Enter PIN: ");
        String enteredPin = scanner.nextLine();  // Read the entered PIN

        // Authenticate the user
        if (atm.authenticate(enteredPin)) {
            System.out.println("Authentication successful.");

            // Start a session loop for the ATM menu
            boolean sessionActive = true;

            while (sessionActive) {
                // Display the ATM menu options
                System.out.println("\n--- ATM Menu ---");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit Cash");
                System.out.println("3. Withdraw Cash");
                System.out.println("4. Change PIN");
                System.out.println("5. View Transaction History");
                System.out.println("6. Exit");

                // Get the user's choice
                System.out.print("Select an option: ");
                int option = scanner.nextInt();

                double amount;

                // Switch case for handling the selected option
                switch (option) {
                    case 1:
                        atm.checkBalance();  // Call the checkBalance method
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: $");
                        amount = scanner.nextDouble();
                        atm.deposit(amount);  // Call the deposit method
                        break;
                    case 3:
                        System.out.print("Enter withdrawal amount: $");
                        amount = scanner.nextDouble();
                        atm.withdraw(amount);  // Call the withdraw method
                        break;
                    case 4:
                        scanner.nextLine();  // Consume newline character left by nextInt()
                        System.out.print("Enter new PIN: ");
                        String newPin = scanner.nextLine();
                        atm.changePin(newPin);  // Call the changePin method
                        break;
                    case 5:
                        atm.printTransactionHistory();  // Call the printTransactionHistory method
                        break;
                    case 6:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        sessionActive = false;  // End the session loop
                        break;
                    default:
                        System.out.println("Invalid option. Please select a valid option.");  // Handle invalid menu option
                        break;
                }
            }
        } else {
            System.out.println("Invalid PIN. Access denied.");  // Deny access for incorrect PIN
        }

        scanner.close();  // Close the scanner object
    }
}