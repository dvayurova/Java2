package src.ex02;

public class Program {
    public static void main(String[] args) {
        UsersList myList = new UsersArrayList();
        for (int i = 0; i < 15; i++) {
            String name = "Tom" + i;
            User u = new User(name, 100);
            myList.addUser(u);
            System.out.print("ByIndex: myList[" + i + "] = " + myList.retrieveUserByIndex(i).getName());
            System.out.println(", ById : myList[" + i + "] = " + myList.retrieveUserById(u.getIdentifier()).getName());
        }
        System.out.println("Number of users = " + myList.retrieveNumberOfUsers());
        try {
            User er = myList.retrieveUserById(21);
            System.out.println("user with id 21 = " + er.getName());
        } catch (UserNotFoundException e) {
            System.out.println("error = " + e.getLocalizedMessage());
        }
    }
}