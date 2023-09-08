package src.ex00;

import java.util.UUID;

public class Transaction {
    public enum TransferCategory {
        DEBIT,
        CREDIT
    }

    private UUID identifier;
    private User recipient;
    private User sender;
    private TransferCategory transferCategory;
    private int transferAmount;

    public Transaction(User recipient, User sender, int transferAmount, TransferCategory transferCategory) {
        setRecipient(recipient);
        setSender(sender);
        identifier = UUID.randomUUID();
        setCategory(transferCategory);
        setAmount(transferAmount);
    }

    public void executeTransation() {
        if (transferCategory == TransferCategory.CREDIT && transferAmount >= 0 && sender.getBalance() >= transferAmount) {
            recipient.setBalance(recipient.getBalance() + transferAmount);
            sender.setBalance(sender.getBalance() - transferAmount);
        }
    }

    public void setAmount(int transferAmount) {
        if (((transferAmount < 0) && (transferCategory == TransferCategory.CREDIT)) || ((transferAmount >= 0) && (transferCategory == TransferCategory.DEBIT))) {
            System.err.println("Wrong values. Credit - is incoming transaction, debit is outgoing transaction");
            return;
        }
        this.transferAmount = transferAmount;
    }

    public void setCategory(TransferCategory transferCategory) {
        this.transferCategory = transferCategory;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public int getTransferAmount() {
        return transferAmount;
    }

    public TransferCategory getTransferCategory() {
        return transferCategory;
    }

}
