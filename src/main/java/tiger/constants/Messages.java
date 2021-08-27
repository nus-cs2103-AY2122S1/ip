package tiger.constants;

public enum Messages {


    EXCEPTION_INPUT_DATE_PARSING ("Please ensure you key in dates in the input specified."),
    EXCEPTION_INPUT_TOO_MANY_ARGUMENTS ("Too many arguments specified!"),
    EXCEPTION_INPUT_SEMICOLON ("Input command cannot contain semicolons! (;)"),
    EXCEPTION_STORAGE_INIT ("Error encounted in initalising/finding storage file!"),
    EXCEPTION_STORAGE_MEMORY_CORRUPTED ("Error encountered in loading the file! Did you alter my memory directly?\nIf" +
            " you didn't backup my memory, would you like to try a partial load to see what can be recovered? " +
            "[Y/N]\nPressing N will wipe data currently stored."),
    EXCEPTION_STORAGE_SAVE ("Error encountered in saving the file! Be sure you don't have the file open while " +
            "writing!");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
