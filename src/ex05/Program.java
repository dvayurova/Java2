package src.ex05;

public class Program {
    public static void main(String[] args) {
        Menu menu = new Menu();
        if (args.length != 0 && args[0].equals("--profile=dev")) {
            menu.setDevMode();
        } else if (args.length > 0) {
            System.out.println("Incorrect flag");
        }
        menu.run();
    }
}