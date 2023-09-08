package src.ex00;

public class Program {
    public static void main(String[] args) {

        User firstUser = new User(1, "Paul", 100);
        User secondUser = new User(2, "Jessica", 500);

        System.out.println("User id = " + firstUser.getIdentifier() + ", name = " + firstUser.getName() + ", balance before transaction = " + firstUser.getBalance());
        System.out.println("User id = " + secondUser.getIdentifier() + ", name = " + secondUser.getName() + ", balance before transaction = " + secondUser.getBalance());

        Transaction transaction = new Transaction(firstUser, secondUser, 450, Transaction.TransferCategory.CREDIT);
        System.out.println("Transaction:\nsender = " + transaction.getSender().getName());
        System.out.println("recipient = " + transaction.getRecipient().getName());
        System.out.println("transaction identifier = " + transaction.getIdentifier());
        System.out.println("transaction amount = " + transaction.getTransferAmount());

        transaction.executeTransation();

        System.out.println("User id = " + firstUser.getIdentifier() + ", name = " + firstUser.getName() + ", balance after transaction = " + firstUser.getBalance());
        System.out.println("User id = " + secondUser.getIdentifier() + ", name = " + secondUser.getName() + ", balance after transaction = " + secondUser.getBalance());
    }
}

