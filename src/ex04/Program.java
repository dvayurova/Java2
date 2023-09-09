package src.ex04;

public class Program {
    public static void main(String[] args) {
        User paul = new User("Paul", 1000);
        User jessica = new User("Jessica", 800);
        User duncan = new User("Duncan", 500);
        User leto = new User("Leto", 5000);

        TransactionsService program = new TransactionsService();
        program.addUser(paul);
        program.addUser(jessica);
        program.addUser(duncan);
        program.addUser(leto);

        System.out.println(paul.getName() + "'s balance = " + program.getUsersBalance(paul));
        System.out.println(jessica.getName() + "'s balance = " + program.getUsersBalance(jessica));
        System.out.println(duncan.getName() + "'s balance = " + program.getUsersBalance(duncan));
        System.out.println(leto.getName() + "'s balance = " + program.getUsersBalance(leto));

        System.out.println("-------Execute transactions-------");
        program.executeTransaction(jessica.getIdentifier(), paul.getIdentifier(), 300);
        program.executeTransaction(duncan.getIdentifier(), paul.getIdentifier(), 150);
        program.executeTransaction(paul.getIdentifier(), leto.getIdentifier(), 4300);
        System.out.println("Print all users info");
        program.printUsersInfo();

        Transaction[] paulsTransfers = program.getUsersTransfers(paul);
        for(int i = 0; i < paulsTransfers.length; i++){
            System.out.println("Paul's transfer №" + (i+1) + " id = " + paulsTransfers[i].getIdentifier());
        }

        System.out.println("Remove last Paul's transfer");
        program.removeTransaction(paulsTransfers[paulsTransfers.length - 1].getIdentifier(), paul.getIdentifier());

        paulsTransfers = program.getUsersTransfers(paul);
        for(int i = 0; i < paulsTransfers.length; i++){
            System.out.println("Paul's transfer №" + (i+1) + " id = " + paulsTransfers[i].getIdentifier());
        }

        System.out.println("Check unpaired transactions");
        Transaction[] check = program.checkTransactionsValidity();
        for(int i = 0; i < check.length; i++){
            System.out.println((i+1) + ". id = " + check[i].getIdentifier());
        }
    }
}