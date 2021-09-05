package duke;

/**
 * Class which converts user command to functions
 */
public class Parser {

    public Parser() {}

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
