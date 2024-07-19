import java.util.Scanner;

class FinancialRecord {
    private double funds;

    public FinancialRecord(double funds) {
        this.funds = funds;
    }

    public boolean addFunds(double amount) {
        if (amount > 0) {
            this.funds += amount;
            return true;
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive value.");
            return false;
        }
    }

    public boolean removeFunds(double amount) {
        if (amount > 0 && amount <= this.funds) {
            this.funds -= amount;
            return true;
        } else {
            if (amount <= 0) {
                System.out.println("Invalid withdrawal amount. Please enter a positive value.");
            } else {
                System.out.println("Insufficient balance for withdrawal.");
            }
            return false;
        }
    }

    public double checkFunds() {
        return this.funds;
    }
}

class CashMachine {
    private FinancialRecord userRecord;

    public CashMachine(FinancialRecord userRecord) {
        this.userRecord = userRecord;
    }

    public void displayOptions() {
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void runCashMachine() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayOptions();
            System.out.print("Enter your choice (1-4): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount;
                    try {
                        withdrawalAmount = Double.parseDouble(scanner.nextLine());
                        if (userRecord.removeFunds(withdrawalAmount)) {
                            System.out.println("Withdrawal successful. Remaining balance: $" + userRecord.checkFunds());
                        } else {
                            System.out.println("Withdrawal failed.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                    break;

                case "2":
                    System.out.print("Enter deposit amount: ");
                    double depositAmount;
                    try {
                        depositAmount = Double.parseDouble(scanner.nextLine());
                        if (userRecord.addFunds(depositAmount)) {
                            System.out.println("Deposit successful. Updated balance: $" + userRecord.checkFunds());
                        } else {
                            System.out.println("Deposit failed.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                    break;

                case "3":
                    System.out.println("Current balance: $" + userRecord.checkFunds());
                    break;

                case "4":
                    System.out.println("Exiting Cash Machine. Thank you!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }
}

public class CashMachineInterface {
    public static void main(String[] args) {
        FinancialRecord userRecord = new FinancialRecord(1000);  // Initial balance $1000
        CashMachine cashMachine = new CashMachine(userRecord);
        cashMachine.runCashMachine();
    }
}
