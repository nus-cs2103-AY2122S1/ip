package duke.utils;

/**
 * Class that contains methods to make sense of the user command
 */
public class Parser {

    /**
     * Returns respective integer selector based on user command
     *
     * If "bye" is the user command, 0 is returned
     * If "list" is the user command, 1 is returned
     * If "done" is the user command, 2 is returned
     * If "todo" is the user command, 3 is returned
     * If "deadline" is the user command, 4 is returned
     * If "event" is the user command, 5 is returned
     * If "delete" is the user command, 6 is returned
     * If "find" is the user command, 7 is returned
     *
     * Default integer returned is 8.
     *
     * @param userInput X coordinate of position.
     * @return integer representation of user command
     */
    public static int decoder(String userInput) {
        int res;
        switch (userInput) {
        case "bye":
            res = 0;
            break;
        case "list":
            res = 1;
            break;
        case "done":
            res = 2;
            break;
        case "todo":
            res = 3;
            break;
        case "deadline":
            res = 4;
            break;
        case "event":
            res = 5;
            break;
        case "delete":
            res = 6;
            break;
        case "find":
            res = 7;
            break;
        default:
            res = 8;
            break;
        }
        return res;
    }

}
