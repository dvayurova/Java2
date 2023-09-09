package src.ex05;

import java.util.UUID;

public class Transaction {
    private UUID identifier;
    private User recipient;
    private User sender;
    private TransferCategory transferCategory;
    private int transferAmount;

    enum TransferCategory {
        DEBIT,
        CREDIT
    }

    public Transaction(Transaction otherTransaction) {
        identifier = otherTransaction.identifier;
        recipient = otherTransaction.recipient;
        sender = otherTransaction.sender;
        transferCategory = otherTransaction.transferCategory == TransferCategory.CREDIT ? TransferCategory.DEBIT : TransferCategory.CREDIT;
        transferAmount = otherTransaction.transferAmount;
    }

    public Transaction(User recipient, User sender, int transferAmount, TransferCategory transferCategory) {
        setRecipient(recipient);
        setSender(sender);
        setTransferAmount(transferAmount);
        setTransferCategory(transferCategory);
        identifier = UUID.randomUUID();
    }

    public void setTransferAmount(int transferAmount) {
        this.transferAmount = transferAmount;
    }

    public void setTransferCategory(TransferCategory transferCategory) {
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
