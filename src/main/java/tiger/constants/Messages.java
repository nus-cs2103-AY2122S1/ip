package tiger.constants;

public enum Messages {


    EXCEPTION_INPUT_DATE_PARSING ("Please ensure you key in dates in the input specified."),
    EXCEPTION_INPUT_TOO_MANY_ARGUMENTS ("Too many arguments specified!"),
    EXCEPTION_INPUT_SEMICOLON ("Input command cannot contain semicolons! (;)"),
    EXCEPTION_STORAGE_INIT ("Error encountered in initialising/finding storage file!"),
    EXCEPTION_STORAGE_MEMORY_CORRUPTED ("Error encountered in loading the file! Did you alter my memory directly?\nIf"
            + " you didn't backup my memory, would you like to try a partial load to see what can be recovered? "
            + "[Y/N]\nPressing N will wipe data currently stored."),
    EXCEPTION_STORAGE_SAVE ("Error encountered in saving the file! Be sure you don't have the file open while "
            + "writing!"),
    TIGER_BYE_MESSAGE("Bye. Hope to see you again soon!"),
    TIGER_CLEAR_MESSAGE("I've cleared all your tasks!"),
    TIGER_INVALID_COMMAND_MESSAGE("Please enter in a valid command."),
    TIGER_CANNOT_FIND_TASKS_MESSAGE("I can't find any matching tasks.");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
