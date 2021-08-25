package pilcrow;

// Object to parse commands entered by the user
public class Parser {
    private String fullCommand;
    private String commandWord;
    private String restOfCommand;

    /**
     * Constructor for Parser object.
     * @param fullCommand The String of the full text to be parsed.
     */
    public Parser(String fullCommand) {
        this.fullCommand = fullCommand.trim();

        if (!this.fullCommand.contains(" ")) {
            this.commandWord = fullCommand.trim();
            this.restOfCommand = "";
        } else {
            this.commandWord = fullCommand.substring(0, this.fullCommand.indexOf(' ')).trim();
            this.restOfCommand = fullCommand.substring(this.fullCommand.indexOf(' ')).trim();
        }
    }

    public String getFullCommand() {
        return this.fullCommand;
    }

    public String getCommandWord() {
        return this.commandWord;
    }

    public String getRestOfCommand() {
        return this.restOfCommand;
    }

    /**
     * Returns the integer following the first word, if it exists.
     * An error is thrown if an integer does not exist.
     * Used for commands that expect an integer to follow, e.g. done, delete.
     * @return Integer following initial word.
     */
    public int getIndex() {
        int index;
        if (restOfCommand.equals("")) {
            throw new InvalidInputException("Must enter task number.");
        }

        try {
            if (restOfCommand.contains(" ")) {
                index = Integer.parseInt(restOfCommand.substring(0, restOfCommand.indexOf(' ')));
            } else {
                index = Integer.parseInt(restOfCommand);
            }
        } catch (NumberFormatException exception) {
            throw new InvalidInputException("Must enter integer following command.");
        }
        return index;
    }
}
