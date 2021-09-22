package duke;


/**
 * Represents the class that helps to parse user inputs.
 */

public class Parser {

    private static final int doneConstant = 5;
    private static final int todoConstant = 5;
    private static final int deadlineConstant = 9;
    private static final int eventConstant = 6;
    private static final int deleteConstant = 7;
    private static final int storageConstant = 9;

    public Parser() {

    }

    /**
     * Returns boolean of whether the Done command had been inputted correctly by the user.
     * @param input input of User.
     * @return boolean of whether Done input was correct.
     */
    public boolean parseDone(String input) {
        return input.length() >= doneConstant && input.substring(0, 5).equals("done ");
    }

    /**
     * Returns boolean of whether the Todo command had been inputted correctly by the user.
     * @param input input of User.
     * @return boolean of whether Todo input was correct.
     */
    public boolean parseToDo(String input) {
        return input.length() >= todoConstant && input.substring(0, 5).equals("todo ")
                || input.substring(0, 2).equals("t ");
    }

    /**
     * Returns boolean of whether the Deadline command had been inputted correctly by the user.
     * @param input input of User.
     * @return boolean of whether Deadline input was correct.
     */
    public boolean parseDeadline(String input) {
        return input.length() >= deadlineConstant && input.substring(0, 9).equals("deadline ")
                || input.substring(0, 2).equals("d ");
    }

    /**
     * Returns boolean of whether the Event command had been inputted correctly by the user.
     * @param input input of User.
     * @return boolean of whether Event input was correct.
     */
    public boolean parseEvent(String input) {
        return input.length() >= eventConstant && input.substring(0, 6).equals("event ")
                || input.substring(0, 2).equals("e ");
    }

    /**
     * Returns boolean of whether the Delete command had been inputted correctly by the user.
     * @param input input of User.
     * @return boolean of whether Delete input was correct.
     */
    public boolean parseDelete(String input) {
        return input.length() >= deleteConstant && input.substring(0, 7).equals("delete ");
    }

    /**
     * Returns an array of strings that contains the created Deadline from the Storage class.
     * @param input the title of the deadline.
     * @return an array of strings that contains the title and date of Deadline.
     */
    public String[] storageDeadline(String input) {
        int indexOfOpenBracketD = input.indexOf("(");
        int indexOfCloseBracketD = input.indexOf(")");
        String deadlineDate = input.substring(indexOfOpenBracketD + 4, indexOfCloseBracketD);
        String deadlineTitle = input.substring(storageConstant, indexOfOpenBracketD - 1);
        String[] deadlineWords = {deadlineTitle, deadlineDate};
        return deadlineWords;
    }

    /**
     * Returns an array of strings that contains the created Event from the Storage class.
     * @param input the title of the deadline.
     * @return an array of strings that contains the title and date of Event.
     */
    public String[] storageEvent(String input) {
        int indexOfOpenBracketE = input.indexOf("(");
        int indexOfCloseBracketE = input.indexOf(")");
        String eventDate = input.substring(indexOfOpenBracketE + 4, indexOfCloseBracketE);
        String eventTitle = input.substring(storageConstant, indexOfOpenBracketE - 1);
        String[] eventWords = {eventTitle, eventDate};
        return eventWords;
    }

    public boolean parseFind(String input) {
        return input.length() >= 5 && input.substring(0, 5).equals("find ");
    }
}
