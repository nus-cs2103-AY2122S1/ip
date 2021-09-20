package duke;

/**
 * A parser which aids with deciphering the nature of the user input.
 *
 * @author mrmrinal
 */
public class Parser {

    /**
     * Method that returns an enum and determines the control flow of the dukebot.
     *
     * @param userInput User Input of the user using the bot
     * @return the enum which is used to determine the task the bot will perform
     */
    public static Duke.RequestType parse(String userInput) {

        Duke.RequestType userRequest;
        if (userInput.equals("list")) {
            userRequest = Duke.RequestType.DEFAULT;
        } else if (userInput.startsWith("find")) {
            userRequest = Duke.RequestType.FIND;
        } else if (userInput.startsWith("done")) {
            userRequest = Duke.RequestType.DONE;
        } else if (userInput.startsWith("delete")) {
            userRequest = Duke.RequestType.DELETE;
        } else if (userInput.startsWith("update")) {
            userRequest = Duke.RequestType.UPDATE;
        } else if (userInput.length() > 8
                && userInput.startsWith("deadline") && userInput.contains("/by")) {
            userRequest = Duke.RequestType.DEADLINE;
        } else if (userInput.length() > 5 && userInput.startsWith("event") && userInput.contains("/at")) {
            userRequest = Duke.RequestType.EVENT;
        } else if (userInput.startsWith("todo")) {
            userRequest = Duke.RequestType.TODO;
        } else {
            userRequest = Duke.RequestType.UNUSUAL;
        }
        return userRequest;
    }

}
