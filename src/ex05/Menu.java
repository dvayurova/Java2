package src.ex05;

import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private TransactionsService transactionsService;
    private Scanner scanner;
    private boolean devMode;

    public Menu() {
        transactionsService = new TransactionsService();
        scanner = new Scanner(System.in);
        devMode = false;
    }

    public void setDevMode() {
        devMode = true;
    }

    public void run() {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    viewUserBalances();
                    break;
                case 3:
                    performTransfer();
                    break;
                case 4:
                    viewUserTransactions();
                    break;
                case 5:
                    removeTransfer();
                    break;
                case 6:
                    checkTransferValidity();
                    break;
                case 7:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
            System.out.println("---------------------------------------------------------");
        }
    }

    private void printMenu() {
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        System.out.println("5. DEV – remove a transfer by ID");
        System.out.println("6. DEV – check transfer validity");
        System.out.println("7. Finish execution");
    }

    private void addUser() {
        System.out.println("Enter a user name and a balance");
        String name = scanner.next();
        int balance = scanner.nextInt();
        User user = new User(name, balance);
        transactionsService.addUser(user);
        System.out.println("User with id = " + user.getIdentifier() + " is added");
    }

    private void viewUserBalances() {
        System.out.println("Enter a user ID");
        int id = scanner.nextInt();
        try {
            System.out.println(transactionsService.getUser(id).getName() + " - " + transactionsService.getUsersBalance(id));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void performTransfer() {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        int senderId = scanner.nextInt();
        int recipientId = scanner.nextInt();
        int transferAmount = scanner.nextInt();
        try {
            transactionsService.executeTransaction(recipientId, senderId, transferAmount);
            System.out.println("The transfer is completed");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewUserTransactions() {
        System.out.println("Enter a user ID");
        int id = scanner.nextInt();
        try {
            User user = transactionsService.getUser(id);
            user.getTransactionsList().printTransactionList();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void removeTransfer() {
        if (accessDenied()) return;
        System.out.println("Enter a user ID and a transfer ID");
        int userId = scanner.nextInt();
        String transferIdString = scanner.next();
        UUID transferId;
        try {
            transferId = UUID.fromString(transferIdString);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return;
        }
        try {
            User user = transactionsService.getUser(userId);
            Transaction transaction = user.getTransactionsList().getTransaction(transferId);
            transactionsService.removeTransaction(transferId, userId);
            if (transaction.getTransferCategory() == Transaction.TransferCategory.DEBIT) {
                System.out.print("Transfer To " + transaction.getRecipient().getName() + "(id = " + transaction.getRecipient().getIdentifier() + ") ");
            } else {
                System.out.print("Transfer From " + transaction.getSender().getName() + "(id = " + transaction.getSender().getIdentifier() + ") ");
            }
            System.out.println(transaction.getTransferAmount() + " removed");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void checkTransferValidity() {
        if (accessDenied()) return;
        System.out.println("Check results:");
        Transaction[] unpairedTransactions = transactionsService.checkTransactionsValidity();
        if (unpairedTransactions == null) {
            System.out.println("Unacknowledged transfers not found");
            return;
        }
        for (Transaction t : unpairedTransactions) {
            System.out.println(transactionInfo(t));
        }
    }

    private String transactionInfo(Transaction transaction) {
        boolean isSender = true;
        User user = transaction.getSender();
        try {
            user.getTransactionsList().getTransaction(transaction.getIdentifier());
        } catch (RuntimeException e) {
            isSender = false;
            user = transaction.getRecipient();
        }
        String info = user.getName() + "(id = " + user.getIdentifier() + ") has an unacknowledged transfer id = " + transaction.getIdentifier();
        if (isSender) {
            info = info + " to " + transaction.getRecipient().getName() + "(id = " + transaction.getRecipient().getIdentifier();
        } else {
            info = info + " from " + transaction.getSender().getName() + "(id = " + transaction.getSender().getIdentifier();
        }
        info = info + ") for " + transaction.getTransferAmount();
        return info;
    }

    private boolean accessDenied() {
        if (!devMode) {
            System.out.println("Access denied");
            return true;
        }
        return false;
    }
}
