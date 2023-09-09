package src.ex04;

public class User {
    private final int identifier;
    private String name;
    private int balance;
    private TransactionsList transactionsList;

    public User(String name, int balance) {
        setName(name);
        setBalance(balance);
        identifier = UserIdsGenerator.getInstance().generateId();
        transactionsList = new TransactionsLinkedList();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(int balance) {
        if (balance > 0)
            this.balance = balance;
        else
            this.balance = 0;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public int getIdentifier() {
        return identifier;
    }

    public TransactionsList getTransactionsList() {
        return transactionsList;
    }

    public void addTransaction(Transaction transaction) {
        transactionsList.addTransaction(transaction);
    }

}