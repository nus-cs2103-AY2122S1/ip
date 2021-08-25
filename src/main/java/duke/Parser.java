package duke;

public class Parser {

    /**
     * Return the integer x value when input "done x" command.
     *
     * @param input Input of user.
     * @return The index of the task to be marked done.
     */
    public Integer doneInputParser(String input) {
        return Integer.parseInt(input.substring(5)) - 1;
    }

    /**
     * Return the integer x value when input "delete x" command.
     *
     * @param input Input of user.
     * @return The index of the task to be deleted.
     */
    public Integer deleteInputParser(String input) {
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
     * Return the description of the deadline task when input "deadline xxx /by..." command.
     *
     * @param input Input of user.
     * @return The deadline task description.
     */
    public String deadlineInputTaskParser(String input) {
        String[] parts = input.split(" /by ");
        return parts[0].replace("deadline " , "");
    }

    /**
     * Return the date of the deadline task when input "deadline... /by xxxx-xx-xx xxxx" command.
     *
     * @param input Inout of the user.
     * @return The deadline task date.
     */
    public String deadlineInputDateParser(String input) {
        String[] parts = input.split(" /by ");
        return parts[1];
    }

    /**
     * Return the description of the event task when input "event xxx /at..." command.
     *
     * @param input Input of the user.
     * @return The event task description.
     */
    public String eventInputTaskParser(String input) {
        String[] parts = input.split(" /at ");
        return parts[0].replace("event " , "");
    }

    /**
     * Return the date of the event task when input "event... /at xxxx-xx-xx xxxx" command.
     *
     * @param input Input of the user.
     * @return The event task date.
     */
    public String eventInputDateParser(String input) {
        String[] parts = input.split(" /at ");
        return parts[1];
    }
}
