package duke;

public class User {
    private String username;
    private boolean isNewUser;

    public User(String username) {
        this.username = username;
        this.isNewUser = true;
    }

    public boolean isNewUser() {
        return this.isNewUser;
    }

    public void hasLoginBefore() {
        this.isNewUser = false;
    }

    public String getUsername() {
        return this.username;
    }
}
