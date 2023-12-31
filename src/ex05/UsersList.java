package src.ex05;

public interface UsersList {
    void addUser(User user);

    User retrieveUserById(int id) throws UserNotFoundException;

    User retrieveUserByIndex(int index) throws UserNotFoundException;

    int retrieveNumberOfUsers();

}
