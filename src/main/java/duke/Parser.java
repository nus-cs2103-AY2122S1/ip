package duke;

import java.io.IOException;
import java.time.DateTimeException;

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
    public void process(String input, List list, Storage storage) throws DukeException, IOException {
        String[] split = input.split(" ", 2);
        if (split[0].equals("done")) {
            list.done(split);
        } else if (split[0].equals("delete")) {
            list.delete(split);
        } else if (split[0].equals("find")) {
            list.search(split[1]);
        } else if (split[0].equals("todo")) {
            if (split.length == 1) {
                throw new DukeTodoException();
            }
            Task newItem = new Todo(split[1]);
            list.getTodos().add(newItem);
            list.echo(newItem);
        } else if (split[0].equals("deadline")) {
            if (split.length == 1) {
                throw new DukeDeadlineException();
            }
            try {
                Task newItem = new Deadline(split[1]);
                list.getTodos().add(newItem);
                list.echo(newItem);
            } catch (DateTimeException e) {
                System.out.println("Please enter the date in yyyy-mm-dd format!");
            }
        } else if (split[0].equals("event")) {
            if (split.length == 1) {
                throw new DukeEventException();
            }
            Task newItem = new Event(split[1]);
            list.getTodos().add(newItem);
            list.echo(newItem);
        } else {
            throw new DukeException();
        }
        storage.writeToFile(list);
    }
}
