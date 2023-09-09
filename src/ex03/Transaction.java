package src.ex03;

import java.util.UUID;

public class Transaction {
    private UUID identifier;
    private User recipient;
    private User sender;
    private TransferCategory transferCategory;
    private int transferAmount;

    private enum TransferCategory {
        DEBIT,
        CREDIT
    }

    public Transaction(User recipient, User sender, int transferAmount) {
        setRecipient(recipient);
        setSender(sender);
        setTransferAmount(transferAmount);
        identifier = UUID.randomUUID();
    }

    public void setTransferAmount(int transferAmount) {
        if (transferAmount < 0) {
            transferCategory = TransferCategory.DEBIT;
        } else {
            transferCategory = TransferCategory.CREDIT;
        }
        this.transferAmount = transferAmount;
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


}
