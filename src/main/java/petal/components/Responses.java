package petal.components;

public enum Responses {

    //Greetings
    WELCOME_BACK("Welcome back! It definitely is good to see you again :D"),
    LINE("ꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤ"
          + "ꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤ"
          + "ꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤꕤ"),
    START_MESSAGE("Welcome to Petal (•◡•)/" + "\nI am the best chat bot you'll meet! Don't be shy, say something! :P" +
                  "\nPssss here's a disclaimer: use the 'bye' command to exit or else your tasks won't save properly!"),
    //Exception responses
    UNINTELLIGIBLE("I do not understand what you mean :("),
    REQUIRED_FORMAT("Use 'todo <insert activity>' to create a to-do!" +
                    "\nUse 'deadline <insert activity> /by <DD/MM/YYYY> <Time in 24-hour format>' " +
                    "to create an activity with a deadline!" +
                    "\nUse 'event <insert activity> /at <DD/MM/YYYY> <Start Time> <End Time>' " +
                    "to create an activity with a start/end time!" +
                    "\nUse 'delete <insert task number> to delete a task!" +
                    "\nUse 'date <DD/MM/YYYY>' to get the tasks on that date!" +
                    "\nUse 'done <insert task number>' to mark task as done!"),
    INVALID_TASK_NUMBER("Invalid task number given! Please enter another value!"),
    FILE_ERROR("Something when wrong whilst creating/accessing these files :/\n" +
               "As such, the saving/retrieval of tasks will be turned off. Sorry!"),
    SAVE_ERROR("Sorry, the tasks couldn't be saved properly :/'"),
    GOODBYE("You're leaving :( I hope you return soon!");

    private final String response;

    private Responses(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return this.response;
    }

}
