package src.ex04;

import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction transaction) throws TransactionNotFoundException;

    void removeTransaction(UUID id) throws TransactionNotFoundException;

    Transaction[] toArray();

    void printTransactionList();
}
