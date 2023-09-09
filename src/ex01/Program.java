package src.ex01;

public class Program {
    public static void main(String[] args) {
        User first = new User("Paul", 1000);
        System.out.println("First user id = " + first.getIdentifier() + ", name = " + first.getName());

        User second = new User("Jessica", 2500);
        System.out.println("Second user id = " + second.getIdentifier() + ", name = " + second.getName());

        User third = new User("Leto", 3500);
        System.out.println("Third user id = " + third.getIdentifier() + ", name = " + third.getName());

        User fourth = new User("Duncan", 500);
        System.out.println("Fourth user id = " + fourth.getIdentifier() + ", name = " + fourth.getName());
    }
}