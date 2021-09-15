package duke;

import java.io.File;
import java.util.Objects;

/**
 * Duke is the main class of the bot.
 *
 * @author meerian
 */
public class Duke {
    /**
     * Represents the storage to read/write files and the list to
     * hold any tasks created.
     */
    private final Storage storage;
    private final TaskList list;

    /**
     * Creates a duke with storage pointing to the right file.
     *
     * @param file the file to write/read the list to.
     */
    public Duke(File file) {
        this.storage = new Storage(file);
        this.list = new TaskList();
        storage.read(list);
    }

    /**
     * The main program. Takes in user input and returns the relevant
     * response from the bot.
     *
     * @param string the user input.
     * @return the relevant string to display.
     */
    public String getResponse(String string) {
        if (Objects.equals(string.charAt(0), '/')) {
            return Parser.helpCommand(string);
        } else if (Objects.equals(string, "save")) {
            return storage.write(list);
        } else if (Objects.equals(string, "bye")) {
            String saveMsg = storage.write(list) + "\n";
            return saveMsg + Ui.goodbye();
        } else if (string.indexOf(' ') != -1) {
            String task = string.substring(0, string.indexOf(' '));
            String description = string.substring(string.indexOf(' ') + 1);
            String result;

            switch (task) {
            case "done":
                try {
                    int pointer = Integer.parseInt(description);

                    Task cur = list.get(pointer - 1);
                    cur.done();
                    result = "Nice! I've marked this task as done:\n" + cur;
                    return result;

                } catch (DukeException | NumberFormatException e) {
                    return e.getMessage();
                }

            case "todo":
                try {
                    Todo todo = new Todo(description);

                    return list.add(todo);

                } catch (DukeException e) {
                    return e.getMessage();
                }

            case "deadline":
                try {
                    Task deadline = Parser.getTask(description, "/by ");
                    return list.add(deadline);

                } catch (DukeException e) {
                    return e.getMessage();
                }

            case "event":
                try {
                    Task event = Parser.getTask(description, "/at ");
                    return list.add(event);

                } catch (DukeException e) {
                    return e.getMessage();
                }

            case "delete":
                try {
                    int pointer = Integer.parseInt(description);
                    return list.delete(pointer);

                } catch (DukeException | NumberFormatException e) {
                    return e.getMessage();
                }

            case "find":
                try {
                    return list.find(description);

                } catch (DukeException e) {
                    return e.getMessage();
                }

            default:
                return Ui.unknownCommand();

            }
        } else {
            return Ui.unknownCommand();
        }
    }

    public String displayList() {
        return list.display();
    }
}
