package duke;

import java.util.ArrayList;

public class Parser {

    public static String parseCommand(String command) {
        return command.split(" ")[0];
    }

    public static int parseNumber(String command) {
        return Integer.parseInt(command.split(" ")[1]) - 1;
    }

    public static String parseTodo(String command) {
        return command.split(" ", 2)[1];
    }

    public static String[] parseDeadline(String command) {
        return command.split(" ", 2)[1].split(" /by ", 2);
    }

    public static String[] parseEvent(String command) {
        return command.split(" ", 2)[1].split(" /at ", 2);
    }

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