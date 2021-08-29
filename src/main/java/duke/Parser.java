package duke;



/**
 * Class that deals with reading user input
 */
public class Parser {

    /**
     * Constructor for Parser.
     */
    public Parser() {

    }

    /**
     * Method to parse and decide what to do with next line of input
     * @param input the line of input
     * @return an integer signifying the next action to be taken by the bot
     * @throws DukeException
     */
    protected int parse(String input) throws DukeException {
        if (input.startsWith("list")) {
            return 0;
        } else if (input.startsWith("bye")) {
            return 1;
        } else if (input.startsWith("done")) {
            return 2;
        } else if (input.startsWith("delete")) {
            return 3;
        } else if (input.startsWith("todo")) {
            return 4;
        } else if (input.startsWith("deadline")) {
            return 5;
        } else if (input.startsWith("event")) {
            return 6;
        } else if (input.startsWith("find")) {
            return 7;
        } else {
            throw new DukeException("Dude! I don't understand what you're saying!");
        }
    }


}
