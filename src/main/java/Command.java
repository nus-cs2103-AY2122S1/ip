/**
 * The Command class encapsulates all commands behaviour for a bot.
 */
public class Command {
    private String name;
    private Task[] records = new Task[100];
    private int count = 0;

    /**
     * Constructor for command.
     * @param name name of bot.
     */
    public Command(String name) {
        this.name = name;
    }

    /**
     * Returns greeting message for bot.
     *
     * @return greeting message for bot.
     */
    public String greeting() {
        return String.format("Hihi! This is %1$s! \n\tHow can I help?\n\t", name);
    }

    /**
     * Checks if command is valid.
     *
     * @param input command
     * @return An int to represent command.
     * @throws DukeException if command is invalid for bot.
     */
    public int checkCommand(String input) throws DukeException{
        switch (input) {
            case "list":
                return 1;
            case "done":
                return 2;
            case "todo":
                return 3;
            case "deadline":
                return 4;
            case "event":
                return 5;
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Returns added message for command to-do.
     *
     * @param description description of to-do task.
     * @return added message for command to-do.
     * @throws DukeException if description is empty.
     */
    public String todo(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        Todo toAdd = new Todo(description);
        records[count] = toAdd;
        count += 1;
        return String.format("Got it. I've added this task:\n\t %1$s \n\t" +
                        "Now you have %2$d tasks in the list.\n\t", toAdd.toString(), count);
    }

    /**
     * Returns added message for command event.
     *
     * @param description description of event task.
     * @param at string of event date/time.
     * @return added message for command event.
     * @throws DukeException if description or date/time is empty.
     */
    public String event(String description, String at) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        } else if (at.isEmpty()) {
            throw new DukeException("OOPS!!! No date for event! Use format of event description /at date");
        }
        Event toAdd = new Event(description, at);
        records[count] = toAdd;
        count += 1;
        return String.format("Got it. I've added this task:\n\t %1$s \n\t" +
                        "Now you have %2$d tasks in the list.\n\t", toAdd.toString(), count);
    }

    /**
     * Returns added message for command deadline.
     *
     * @param description description of deadline task.
     * @param by string of deadline date/time.
     * @return added message for command deadline.
     * @throws DukeException if description or date/time is empty.
     */
    public String deadline(String description, String by) throws DukeException{
        if (description.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (by.isEmpty()) {
            throw new DukeException("OOPS!!! No date for deadline! Use format of deadline description /by date");
        }
        Deadline toAdd = new Deadline(description, by);
        records[count] = toAdd;
        count += 1;
        return String.format("Got it. I've added this task:\n\t %1$s \n\t" +
                        "Now you have %2$d tasks in the list.\n\t", toAdd.toString(), count);
    }

    /**
     * Returns message representing list of all items user added.
     *
     * @return formatted string representing elements in records array.
     */
    public String list() {
        String result = "Here are the tasks in your list:\n\t";
        for (int i = 0; i < count; i++) {
            result += String.format("%1$d. %2$s \n\t",
                    i + 1, records[i].toString());
        }
        return result;
    }

    /**
     * Return task mark as done message.
     *
     * @param index index of task that is done.
     * @return task mark as done message.
     * @throws DukeException if index < 0 or index points to null value.
     */
    public String done(int index) throws DukeException{
        if (index < 0) {
            throw new DukeException("OOPS!!! Index must be greater than 0");
        }
        Task done = records[index];
        if (done == null) {
            throw new DukeException(String.format("OOPS!!! You only have %1$d tasks",count));
        }
        done.markAsDone();
        return String.format("Nice! I've marked this task as done:\n\t %1$s \n\t", done.toString());
    }

    /**
     * Returns ending message for bot.
     *
     * @return ending message for bot.
     */
    public String end() {
        return "Bye bye!\n\t";
    }
}
