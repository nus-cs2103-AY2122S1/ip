package duke.ui;

/**
 * Represents a Ui interface to deal with user interactions.
 *
 * @author: James Kua
 * @version: Duke-Level-8
 */
public class Ui {

    /**
     * Prints the logo, together with a greeting.
     */
    public static String intro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo;
    }

    /**
     * Greets the user and shows available commands.
     */
    public static String greet() {
        return formatMessage("Hello, I'm Faker! What can I do for you?", "Enter <help> for supported commands!");
    }

    /**
     * Says goodbye to the user.
     */
    public static String exit() {
        String message = "Bye. Hope to see you again soon!";
        return message;
    }

    /**
     * Prints the message.
     *
     * @param message Message to be printed.
     */
    public static String printMessage(String message) {
        return message;
    }

    /**
     * Informs users of the supported commands and how to use them.
     *
     * @return String containing the available commands.
     */
    public String showSupportedCommands() {
        String message = formatMessage(
                "bye - ends current chat session",
                "deadline <description> (by: <yyyy-mm-dd> <hh:mm>) - adds new Deadline",
                "event <description> (at: <yyyy-mm-dd> <hh:mm>) - adds new Event",
                "list - shows your current task list",
                "done <task number> - marks task as done",
                "delete <task number> - deletes task from list",
                "find <keyword> - shows all tasks that contains keyword"
        );
        return message;
    }

    /**
     * Formats the String from varargs, separated by a newline.
     *
     * @param messages Messages to format.
     * @return String of combined messages.
     */
    public static String formatMessage(String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message: messages) {
            sb.append(message);
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }
}
