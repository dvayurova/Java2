package src.ex05;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private TransactionNode head;
    private TransactionNode tail;
    private int size;

    public TransactionsLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void addTransaction(Transaction transaction) throws TransactionNotFoundException {
        if (transaction == null)
            throw new TransactionNotFoundException("Transaction doesn't exist");
        if (size == 0) {
            TransactionNode newNode = new TransactionNode(null, null, transaction);
            head = tail = newNode;
        } else {
            TransactionNode newNode = new TransactionNode(null, tail, transaction);
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void removeTransaction(UUID id) throws TransactionNotFoundException {
        TransactionNode node = getNodeById(id);
        if (node == null) throw new TransactionNotFoundException("Transaction not found");
        removeNode(node);
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] array = new Transaction[size];
        int i = 0;
        for (TransactionNode it = head; it != null; it = it.next, i++) {
            array[i] = it.transaction;
        }
        return array;
    }

    private void removeNode(TransactionNode node) {
        if (size == 1) {
            head = tail = null;
        } else if (node.previous == null) {
            head = head.next;
        } else if (node.next == null) {
            tail = node.previous;
            tail.next = null;
        } else {
            node.previous.next = node.next;
            node.next.previous = node.previous;
        }
        size--;
    }

    @Override
    public Transaction getTransaction(UUID id) throws TransactionNotFoundException {
        TransactionNode node = getNodeById(id);
        if (node == null) throw new TransactionNotFoundException("Transaction not found");
        return node.transaction;
    }

    private TransactionNode getNodeById(UUID id) throws TransactionNotFoundException {
        if (id == null)
            throw new TransactionNotFoundException("ID didn't found");
        for (TransactionNode it = head; it != null; it = it.next) {
            if (it.transaction == null)
                throw new TransactionNotFoundException("Transaction not found");
            if (it.transaction.getIdentifier().equals(id)) {
                return it;
            }
        }
        return null;
    }

    @Override
    public void printTransactionList() {
        for (TransactionNode it = head; it != null; it = it.next) {
            if (it.transaction.getTransferCategory() == Transaction.TransferCategory.DEBIT) {
                System.out.print("To " + it.transaction.getRecipient().getName() + "(id = " + it.transaction.getRecipient().getIdentifier() + ") -");
            } else {
                System.out.print("From " + it.transaction.getSender().getName() + "(id = " + it.transaction.getSender().getIdentifier() + ") +");
            }
            System.out.println(it.transaction.getTransferAmount() + " with id = " + it.transaction.getIdentifier());
        }
    }
}
