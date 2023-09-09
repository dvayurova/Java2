package src.ex05;

public class TransactionNode {
    public TransactionNode next;
    public TransactionNode previous;
    public Transaction transaction;

    public TransactionNode(TransactionNode next, TransactionNode prev, Transaction transaction) {
        this.transaction = transaction;
        this.next = next;
        this.previous = prev;
    }
}
