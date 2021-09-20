package duke;


/**
 * Represents the class that helps to parse user inputs.
 */

public class Parser {

    public Parser() {

    }

    /**
     * Returns boolean of whether the Done command had been inputted correctly by the user.
     * @param input input of User.
     * @return boolean of whether Done input was correct.
     */
    public boolean parseDone(String input) {
        return input.length() >= 5 && input.substring(0, 5).equals("done ");
    }

    /**
     * Returns boolean of whether the Todo command had been inputted correctly by the user.
     * @param input input of User.
     * @return boolean of whether Todo input was correct.
     */
    public boolean parseToDo(String input) {
        return input.length() >= 5 && input.substring(0, 5).equals("todo ");
    }

    /**
     * Returns boolean of whether the Deadline command had been inputted correctly by the user.
     * @param input input of User.
     * @return boolean of whether Deadline input was correct.
     */
    public boolean parseDeadline(String input) {
        return input.length() >= 9 && input.substring(0, 9).equals("deadline ");
    }

    /**
     * Returns boolean of whether the Event command had been inputted correctly by the user.
     * @param input input of User.
     * @return boolean of whether Event input was correct.
     */
    public boolean parseEvent(String input) {
        return input.length() >= 6 && input.substring(0, 6).equals("event ");
    }

    /**
     * Returns boolean of whether the Delete command had been inputted correctly by the user.
     * @param input input of User.
     * @return boolean of whether Delete input was correct.
     */
    public boolean parseDelete(String input) {
        return input.length() >= 7 && input.substring(0, 7).equals("delete ");
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
        String deadlineTitle = input.substring(9, indexOfOpenBracketD);
        String[] deadlineWords = {deadlineDate, deadlineTitle};
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
        String eventTitle = input.substring(9, indexOfOpenBracketE);
        String[] eventWords = {eventDate, eventTitle};
        return eventWords;
    }

    public boolean parseFind(String input) {
        return input.length() >= 5 && input.substring(0, 5).equals("find ");
    }
}
