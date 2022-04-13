package duke.utility;

public class Ui {

    public static final String WELCOME_MESSAGE = "Hello I'm Ted!\nWhat can I do for you?\n"
            + "(you can enter \"help\" at any time if you need help on using this app)";
    private Parser parser;


    /**
     * Starts listening for commands from the user.
     * @param tasks the {@link duke.utility.TaskList} this Ui is linked to.
     * @param storage the {@link duke.utility.Storage} this Ui is linked to.
     */
    public void startListening(TaskList tasks, Storage storage) {
        this.parser = new Parser(tasks, storage);
    }


    /**
     * Receives input from user and sends it to the parser to parse
     * @param input input from the user
     * @return the result of parsing the input
     */
    public String receiveInputFromUser(String input) {
        String message = parser.parseCommand(input);
        if (message.equals("TERMINATE")) {
            return "goodbye";
        } else {
            return message;
        }
    }
}
