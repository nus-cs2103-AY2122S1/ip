package duke;

import java.io.IOException;
import java.time.DateTimeException;
import static java.lang.Integer.parseInt;

public class Parser {
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

    public static void done(String[] array) throws DukeDoneException {
        if (array.length == 1) {
            throw new DukeDoneException();
        }
        int index = parseInt(array[1]);
        Task temp = List.todos.get(index - 1);
        temp.markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + temp);
    }

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

    public static void echo(Task item) {
        System.out.println("Got it. I've added this task:\n"
                + item
                + "\nNow you have "
                + List.todos.size()
                + " task"
                + (List.todos.size() == 1 ? "" : "s")
                + " in the list");
    }
}
