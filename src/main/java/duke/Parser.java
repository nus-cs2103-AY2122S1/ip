package duke;

import duke.*;

import java.io.IOException;
import java.time.DateTimeException;

import static java.lang.Integer.parseInt;

/**
 * Represents a class that handles all the input command.
 */
public class Parser {
    /**
     * Handles the input commands and execute the commands.
     *
     * @param input The command given by the user.
     * @throws DukeException If input do not meet the requirements.
     * @throws IOException If the file cannot be read/found.
     */
    public static void process(String input) throws DukeException, IOException {
        String[] split = input.split(" ", 2);
        if (split[0].equals("done")) {
            done(split);
        } else if (split[0].equals("delete")) {
            delete(split);
        } else if (split[0].equals("todo")) {
            if (split.length == 1) {
                throw new DukeTodoException();
            }
            Task newItem = new Todo(split[1]);
            List.todos.add(newItem);
            echo(newItem);
        } else if (split[0].equals("deadline")) {
            if (split.length == 1) {
                throw new DukeDeadlineException();
            }
            try {
                Task newItem = new Deadline(split[1]);
                List.todos.add(newItem);
                echo(newItem);
            } catch (DateTimeException e) {
                System.out.println("Please enter the date in yyyy-mm-dd format!");
            }
        } else if (split[0].equals("event")) {
            if (split.length == 1) {
                throw new DukeEventException();
            }
            Task newItem = new Event(split[1]);
            List.todos.add(newItem);
            echo(newItem);
        } else {
            throw new DukeException();
        }
        Storage.writeToFile();
    }

    /**
     * Marks the target Task as done.
     *
     * @param array The input command.
     * @throws DukeDoneException If the number of entry is not specified in the command.
     */
    public static void done(String[] array) throws DukeDoneException {
        if (array.length == 1) {
            throw new DukeDoneException();
        }
        int index = parseInt(array[1]);
        Task temp = List.todos.get(index - 1);
        temp.markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + temp);
    }

    /**
     * Deletes the target Task.
     *
     * @param array The input command.
     */
    public static void delete(String[] array) {
        int index = parseInt(array[1]);
        Task temp = List.todos.remove(index - 1);
        System.out.println("Noted. I've removed this task:\n"
                + temp
                + "\nNow you have "
                + List.todos.size()
                + " task"
                + (List.todos.size() == 1 ? "" : "s")
                + " in the list");

    }

    /**
     * Prints out the new Task added and the total number of Tasks in the List.
     *
     * @param item The new Task created.
     */
    public static void echo(Task item) {
        System.out.println("Got it. I've added this task:\n"
                + item
                + "\nNow you have "
                + List.todos.size()
                + " task"
                + (List.todos.size() == 1 ? "" : "s")
                + " in the list"); // will take care of when it is 1
    }


}
