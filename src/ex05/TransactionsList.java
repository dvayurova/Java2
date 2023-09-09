package src.ex05;

import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction transaction) throws TransactionNotFoundException;

    void removeTransaction(UUID id) throws TransactionNotFoundException;

    Transaction[] toArray();

    Transaction getTransaction(UUID id) throws TransactionNotFoundException;

    void printTransactionList();
}
