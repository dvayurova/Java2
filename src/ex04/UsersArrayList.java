package src.ex04;

public class UsersArrayList implements UsersList {
    private final int defaultSize = 10;
    private int size;
    private User[] userArray;

    public UsersArrayList() {
        size = defaultSize;
        userArray = new User[size];
    }

    @Override
    public void addUser(User user) {
        int i = retrieveNumberOfUsers();
        if (i == size - 1) {
            doubleSize();
        }
        userArray[i] = user;
    }

    private void doubleSize() {
        size *= 2;
        User[] tmp = new User[size];
        for (int i = 0; i < size / 2; i++) {
            tmp[i] = userArray[i];
        }
        userArray = tmp;
    }

    @Override
    public User retrieveUserById(int id) throws UserNotFoundException {
        int i = 0;
        if (userArray[i] == null) {
            throw new UserNotFoundException("User with the ID didn't found");
        }
        while (userArray[i] != null && i < size) {
            if (userArray[i].getIdentifier() == id) {
                return userArray[i];
            }
            i++;
        }
        if (i < size) {
            throw new UserNotFoundException("User with the ID didn't found");
        }
        return null;
    }

    @Override
    public User retrieveUserByIndex(int index) throws UserNotFoundException {
        if (index < 0 || index >= retrieveNumberOfUsers()) {
            throw new UserNotFoundException("User with the index didn't found");
        }
        return userArray[index];
    }

    @Override
    public int retrieveNumberOfUsers() {
        int i = 0;
        while (userArray[i] != null && i < size) {
            i++;
        }
        return i;
    }
}
