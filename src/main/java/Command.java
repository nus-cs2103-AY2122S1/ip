import java.util.ArrayList;

/**
 * The Command class encapsulates all commands behaviour for a bot.
 */
public class Command {
    private String name;
    private ArrayList<Task> records = new ArrayList<>();

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
        records.add(toAdd);
        return String.format("Got it. I've added this task:\n\t %1$s \n\t" +
                        "Now you have %2$d tasks in the list.\n\t", toAdd.toString(), records.size());
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
        records.add(toAdd);
        return String.format("Got it. I've added this task:\n\t %1$s \n\t" +
                        "Now you have %2$d tasks in the list.\n\t", toAdd.toString(), records.size());
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
        records.add(toAdd);
        return String.format("Got it. I've added this task:\n\t %1$s \n\t" +
                        "Now you have %2$d tasks in the list.\n\t", toAdd.toString(), records.size());
    }

    /**
     * Returns message representing list of all items user added.
     *
     * @return formatted string representing elements in records array.
     */
    public String list() {
        final StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n\t");
        records.forEach((el) -> sb.append(
                String.format("%1$d. %2$s \n\t",
                        records.indexOf(el) + 1, el.toString())));
        return sb.toString();
    }

    /**
     * Returns delete message for bot.
     * @param index index of task to be deleted.
     * @return delete message for bot.
     * @throws DukeException
     */
    public String delete(int index) throws DukeException {
        if (index < 0) {
            throw new DukeException("OOPS!!! Index must be greater than 0");
        } else if (index >= records.size()) {
            throw new DukeException(String.format("OOPS!!! You only have %1$d tasks",records.size()));
        }
        Task removed = records.remove(index);
        return String.format("Noted! I've removed this task:\n\t %1$s\n\t" +
                "Now you have %2$d tasks in the list.\n\t", removed.toString(), records.size());
    }

    /**
     * Return task mark as done message.
     *
     * @param index index of task that is done.
     * @return task mark as done message.
     * @throws DukeException if index < 0 or index points to null value.
     */
    public String done(int index) throws DukeException {
        if (index < 0) {
            throw new DukeException("OOPS!!! Index must be greater than 0");
        } else if (index >= records.size()) {
            throw new DukeException(String.format("OOPS!!! You only have %1$d tasks",records.size()));
        }
        Task done = records.get(index);
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
