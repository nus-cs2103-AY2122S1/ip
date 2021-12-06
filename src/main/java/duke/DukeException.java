package duke;

public class DukeException extends Exception {

    private String dukeError;

    /**
     * Constructor for a DukeException
     * @param errorCommand String representing failed operation
     */
    public DukeException(String errorCommand) {
        if (errorCommand.equals("todo")) {
            this.dukeError = "OOPS!!! The description of a todo cannot be empty.";
        }

        if (errorCommand.equals("unknown duke command")) {
            this.dukeError = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }

        if (errorCommand.equals("invalid data")) {
            this.dukeError = "Database contains invalid entries!";
        }

        if (errorCommand.equals("failed database creation")) {
            this.dukeError = "Database cannot be created! Please check that duke.Duke has write and read permissions";
        }

        if (errorCommand.equals("failed data save")) {
            this.dukeError = "Failed to save data. Please check that duke.Duke has write permissions";
        }

        if (errorCommand.equals("invalid tasktype")) {
            this.dukeError = "Invalid task type. Task type should be either deadline, event or todo";
        }
    }

    @Override
    public String toString() {
        return this.dukeError;
    }

}
