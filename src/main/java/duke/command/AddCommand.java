package duke.command;

/**
 * Represents a command that adds an item into the task list from the user's input
 */
abstract class AddCommand implements Command {

    /**
     * Returns false since an addition command should not trigger the program to
     * exit.
     *
     * @return boolean that has value false since it should not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj instanceof AddCommand;
    }
}
