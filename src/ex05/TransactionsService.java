package src.ex05;

import java.util.UUID;

public class TransactionsService {
    private UsersList userList;

    public TransactionsService() {
        userList = new UsersArrayList();
    }

    public void addUser(User user) {
        userList.addUser(user);
    }

    public int getUsersBalance(int userId) {
        return userList.retrieveUserById(userId).getBalance();
    }

    public void executeTransaction(int recipientId, int senderId, int transferAmount) throws IllegalTransactionException{
        User sender = userList.retrieveUserById(senderId);
        User recipient = userList.retrieveUserById(recipientId);
        if (transferAmount > sender.getBalance() || sender == null || recipient == null) {
            throw new IllegalTransactionException("Balance is not enough for transfer, check the balance and try again");
        }
        Transaction debit = new Transaction(recipient, sender, transferAmount, Transaction.TransferCategory.DEBIT);
        sender.addTransaction(debit);
        Transaction credit = new Transaction(debit);
        recipient.addTransaction(credit);
        sender.setBalance(sender.getBalance() - transferAmount);
        recipient.setBalance(recipient.getBalance() + transferAmount);
    }

    public Transaction[] getUsersTransfers(int userId) {
        return userList.retrieveUserById(userId).getTransactionsList().toArray();
    }

    public void removeTransaction(UUID transactionId, int userId) {
        userList.retrieveUserById(userId).getTransactionsList().removeTransaction(transactionId);
    }

    public Transaction[] checkTransactionsValidity() {
        TransactionsList unpaired = new TransactionsLinkedList();
        for (int i = 0; i < userList.retrieveNumberOfUsers(); ++i) {
            Transaction[] usersTransactions = getUsersTransfers(userList.retrieveUserByIndex(i).getIdentifier());
            for (Transaction t : usersTransactions) {
                if (!transactionHasPair(t.getIdentifier(), i))
                    unpaired.addTransaction(t);
            }
        }
        return unpaired.toArray();
    }

    private boolean transactionHasPair(UUID id, int index) {
        for (int i = 0; i < userList.retrieveNumberOfUsers(); ++i) {
            Transaction[] tmp = userList.retrieveUserByIndex(i).getTransactionsList().toArray();
            for (Transaction t : tmp) {
                if (index != i && t.getIdentifier().equals(id)) return true;
            }
        }
        return false;
    }

    public User getUser(int userId){
        return userList.retrieveUserById(userId);
    }

    public void printUsersInfo() {
        System.out.println("Number of users = " + userList.retrieveNumberOfUsers() + ". User's list: ");
        for (int i = 0; i < userList.retrieveNumberOfUsers(); ++i) {
            System.out.println("User's №" + (i + 1) + " name = " + userList.retrieveUserByIndex(i).getName() + ", balance = " + userList.retrieveUserByIndex(i).getBalance());
            userList.retrieveUserByIndex(i).getTransactionsList().printTransactionList();
            System.out.println();
        }
    }

}
