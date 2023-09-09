package src.ex03;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User paul = new User("Paul", 1000);
        User jessica = new User("Jessica", 500);

        Transaction first = new Transaction(paul, jessica, 100);
        paul.addTransaction(first);
        System.out.println("------First transaction added------");
        paul.getTransactionsList().printTransactionList();

        Transaction second = new Transaction(paul, jessica, 200);
        paul.addTransaction(second);
        System.out.println("------Second transaction added------");
        paul.getTransactionsList().printTransactionList();

        paul.getTransactionsList().removeTransaction(first.getIdentifier());
        System.out.println("------First transaction removed------");
        paul.getTransactionsList().printTransactionList();

        paul.getTransactionsList().removeTransaction(second.getIdentifier());
        System.out.println("------Second transaction removed------");
        paul.getTransactionsList().printTransactionList();

        System.out.println("------Trying to remove transaction that doesn't exist anymore------");
        try {
            paul.getTransactionsList().removeTransaction(second.getIdentifier());
            paul.getTransactionsList().printTransactionList();
        } catch (TransactionNotFoundException e) {
            System.out.println("Error = " + e.getLocalizedMessage());
        }
    }
}