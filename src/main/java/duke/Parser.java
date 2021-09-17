package duke;

import java.util.ArrayList;

/**
 * Class for Parser in Duke which
 * deals with making sense of the user command.
 *
 * @author Samuel Lau
 */
public class Parser {

    /**
     * Parse the user command to find out type of command.
     *
     * @param command Command from the CLI.
     * @return String representation of type of command.
     */
    public static String parseCommand(String command) {
        return command.split(" ")[0];
    }

    /**
     * Parse command to find out task number in the list.
     *
     * @param command Command from the CLI.
     * @return Integer representing task location in the ArrayList.
     */
    public static int parseNumber(String command) {
        return Integer.parseInt(command.split(" ")[1]) - 1;
    }

    /**
     * Parse command to get description for the to-do task
     * to be created.
     *
     * @param command Command from the CLI.
     * @return Description of the to-do task.
     */
    public static String parseTodo(String command) {
        return command.split(" ", 2)[1];
    }

    /**
     * Parse command to get description and date for the deadline task
     * to be created.
     *
     * @param command Command from the CLI.
     * @return Array containing description and date of the deadline task.
     */
    public static String[] parseDeadline(String command) {
        return command.split(" ", 2)[1].split(" /by ", 2);
    }

    /**
     * Parse command to get description and date for the event task
     * to be created.
     *
     * @param command Command from the CLI.
     * @return Array containing description and date of the event task.
     */
    public static String[] parseEvent(String command) {
        return command.split(" ", 2)[1].split(" /at ", 2);
    }

    /**
     * Returns the task to be added to the TaskList from
     * a line of text in the loaded text file.
     *
     * @param line Text in the file that represents a task.
     * @return Task to be added.
     */
    public static Task parseData(String line) {
        String[] split = line.split("\\|");
        switch(split[0]) {
            case("T"):
                Todo t = new Todo(split[2]);
                if (!split[1].equals("0")) {
                    t.markAsDone();
                }
                return t;
            case("E"):
                Event e = new Event(split[2], split[3]);
                if (!split[1].equals("0")) {
                    e.markAsDone();
                }
                return e;
            case("D"):
                Deadline d = new Deadline(split[2], split[3]);
                if (!split[1].equals("0")) {
                    d.markAsDone();
                }
                return d;
        }
        return null;
    }

    /**
     * Returns ArrayList of tasks that matches the keyword to be found.
     *
     * @param list ArrayList of tasks in Duke.
     * @param command Command from the CLI.
     * @return ArrayList with matching tasks.
     */
    public static ArrayList<Task> parseFind(ArrayList<Task> list, String command) {
        String keyword = command.split(" ", 2)[1];
        ArrayList<Task> keyTasks = new ArrayList<>();
        for (Task t : list) {
            if (t.toString().contains(keyword)) {
                keyTasks.add(t);
            }
        }
        return keyTasks;
    }
}