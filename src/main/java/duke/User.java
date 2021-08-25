package duke;

/**
 * User class which enables for multi-user usage of the bot.
 */
public class User {
    private String username;
    private boolean isNewUser;

    /**
     * Public constructor to create a new user to start up their
     * respective Duii Bot.
     *
     * @param username The desired name of the user.
     */
    public User(String username) {
        this.username = username;
        this.isNewUser = true;
    }

    /**
     * Checks whether the user is logging in for the first time.
     *
     * @return Whether the user is a new user.
     */
    public boolean isNewUser() {
        return this.isNewUser;
    }

    /**
     * Updates the users' past login experience.
     */
    public void hasLoginBefore() {
        this.isNewUser = false;
    }

    /**
     * Gets the username of the current user.
     *
     * @return The username of the current user.
     */
    public String getUsername() {
        return this.username;
    }
}
