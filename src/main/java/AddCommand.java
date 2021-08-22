public abstract class AddCommand implements Command {

    private final Task.Type type;
    private final String userInput;

    public abstract boolean verifyAddCommand(String input);

    public AddCommand(String userInput, Task.Type typeToAdd) {
        this.userInput = userInput;
        this.type = typeToAdd;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public void invalidArguments() {
        Ui.printErrorMessage(type);
    }

    public String getUserInput() {
        return userInput;
    }

    /**
     * Removes the first word from a String. This method is used to remove the
     * command word from a user input so as to extract the required information
     * for the command.
     *
     * @return The details of an add task command.
     */
    public String removeFirstWordFromInput() {
        try {
            return this.userInput.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.invalidArguments();
            return null;
        }
    }
}
