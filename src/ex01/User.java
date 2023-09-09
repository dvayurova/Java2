package src.ex01;

public class User {
    private final int identifier;
    private String name;
    private int balance;

    public User(String name, int balance) {
        setName(name);
        setBalance(balance);
        identifier = UserIdsGenerator.getInstance().generateId();
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

}