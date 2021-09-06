package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Main class of project.
 */
public class Duke {
    private Storage storage;
    private Ui userInt;
    private TaskList tasks;
    private User user;

    /**
     * Constructor of Duke instance, which initalises the other
     * components of the project.
     *
     * @param user User object which encapsulates the users' name and visit history
     */
    public Duke(User user) {
        assert(user != null) : "User for Duii cannot be null!";
        this.user = user;
        this.storage = new Storage();
        this.userInt = new Ui();
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            userInt.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Gets the string representation of the response by the User Interface.
     *
     * @param input The input string by the user
     * @return The string to be printed out to the GUI as the response
     */

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, userInt, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Gets the user associated to the bot.
     *
     * @return User object associated with the bot.
     */
    public User getUser() {
        return this.user;
    }

}

