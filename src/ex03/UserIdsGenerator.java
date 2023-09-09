package src.ex03;

public class UserIdsGenerator {
    private static UserIdsGenerator instance;
    private int id;

    private UserIdsGenerator() {
        id = 0;
    }

    public static UserIdsGenerator getInstance() {
        if (instance == null)
            instance = new UserIdsGenerator();
        return instance;
    }

    public int generateId() {
        instance.id += 1;
        return instance.id;
    }
}
