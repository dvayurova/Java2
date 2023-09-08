package src.ex00;

public class User {
    public User() {
        identifier = 0;
        name = "Unknown";
        balance = 0;
    }

    public User(int identifier, String name, int balance) {
        setName(name);
        setBalance(balance);
        setIdentifier(identifier);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(int balance) {
        if (balance < 0) {
            System.err.println("Balance cannot be negative");
            return;
        }
        this.balance = balance;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
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

    private int identifier;
    private String name;
    private int balance;

}
