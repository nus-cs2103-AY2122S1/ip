package duke;

/**
 * Class which converts user command to functions
 */
public class Parser {

    public Parser() {}

    /**
     * Parses input given by user into a Task
     *
     * @param type of the task T, D or E
     * @param description description of task
     * @param dateTime for D,E to track time
     * @return Task of the specific specifications
     */
    public static Task taskParse(String type, String description, String dateTime){
        if (type.equalsIgnoreCase("T")) {
            return new Todo(description);
        } else if (type.equalsIgnoreCase("D")) {
            return new Deadline(description,dateTime);
        } else if (type.equalsIgnoreCase("E")) {
            return new Event(description,dateTime);
        } else {
            return null;
        }
    }
}
