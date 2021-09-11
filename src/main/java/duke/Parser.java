package duke;

public class Parser {

    /**
     * Return the integer x value when input "done x" command.
     *
     * @param input Input of user.
     * @return The index of the task to be marked done.
     */
    public Integer doneInputParser(String input) {
        if (input.length() < 6) {
            throw new MissingIndexException();
        }
        return Integer.parseInt(input.substring(5)) - 1;
    }

    /**
     * Return the integer x value when input "delete x" command.
     *
     * @param input Input of user.
     * @return The index of the task to be deleted.
     */
    public Integer deleteInputParser(String input) {
        if (input.length() < 8) {
            throw new MissingIndexException();
        }
        return Integer.parseInt(input.substring(7)) - 1;
    }

    /**
     * Return the description of the to-do task when input "todo xxx" command.
     *
     * @param input Input of user.
     * @return The to-do task description.
     */
    public String toDoInputParser(String input) {
        return input.replace("todo ", "");
    }

    /**
     * Splits the input into 2 strings, description and date of task.
     *
     * @param input Input of user.
     * @return An array containing description and date of task.
     */
    public String[] deadlineInputParser(String input) {
        if (!input.contains("/by")) {
            //for missing dateline
            throw new MissingDateException();
        }
        String[] parts = input.split(" /by ");
        parts[0] = parts[0].replace("deadline " , "");
        return parts;
    }

    /**
     * Splits the input into 2 strings, description and date of task.
     *
     * @param input Input of user.
     * @return An array containing description and date of task.
     */
    public String[] eventInputParser(String input) {
        if (!input.contains("/at")) {
            //for missing dateline
            throw new MissingDateException();
        }
        String[] parts = input.split(" /at ");
        parts[0] = parts[0].replace("event " , "");
        return parts;
    }

    /**
     *
     *
     * @param input
     * @return
     */
    public String findInputParser(String input) {
        if (input.length() < 6) {
            throw new MissingKeywordException();
        }
        String keyword = input.substring(5);
        return keyword;
    }
    /**
     *
     *
     * @param input
     * @return
     */
    public String helpInputParser(String input) {
        String command = input.substring(5);
        return command;
    }
}
