package petal.components;

/**
 * The Enum that holds all the responses that user might see, as these responses
 * remain a constant
 */
public enum Responses {

    WELCOME_BACK("Welcome back! It definitely is good to see you again :D"),
    START_MESSAGE("Welcome to Petal!" + "\nI am the best chat bot you'll meet! Don't be shy, "
                + "say something! :P\nPssss here's a disclaimer: "
                + "use the 'bye' command to exit or else your tasks won't save properly!"),
    INVALID_FORMAT("Invalid format given! Please try again :("),
    INVALID_TASK_NO("Invalid task number given! Please enter another value!"),
    EMPTY_DESCRIPTION("The description cannot be empty! Enter a valid one! :("),
    INVALID_DATE_TIME("The date/time format used was wrong! Try again :("),
    UNINTELLIGIBLE("I do not understand what you mean :("),
    INVALID_TASK_NUMBER("Invalid task number given! Please enter another value!"),
    GOODBYE("You're leaving :( I hope you return soon!");

    private final String response;

    /**
     * Constructs a Response instance
     *
     * @param response The String response
     */
    Responses(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return this.response;
    }

}
