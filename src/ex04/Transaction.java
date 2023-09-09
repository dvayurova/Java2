package src.ex04;

import java.util.UUID;

public class Transaction {
    private UUID identifier;
    private User recipient;
    private User sender;
    private EnumTransfer transferCategory;
    private int transferAmount;

    enum EnumTransfer {
        DEBIT,
        CREDIT
    }

    public Transaction(Transaction otherTransaction) {
        identifier = otherTransaction.identifier;
        recipient = otherTransaction.recipient;
        sender = otherTransaction.sender;
        transferCategory = otherTransaction.transferCategory == EnumTransfer.CREDIT ? EnumTransfer.DEBIT : EnumTransfer.CREDIT;
        transferAmount = otherTransaction.transferAmount;
    }

    public Transaction(User recipient, User sender, int transferAmount, EnumTransfer transferCategory) {
        setRecipient(recipient);
        setSender(sender);
        setTransferAmount(transferAmount);
        setTransferCategory(transferCategory);
        identifier = UUID.randomUUID();
    }

    public void setTransferAmount(int transferAmount) {
        this.transferAmount = transferAmount;
    }

    public void setTransferCategory(EnumTransfer transferCategory) {
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

    public String getTransferCategory() {
        if (transferCategory == EnumTransfer.CREDIT)
            return "Credit";
        else return "Debit";
    }


}
