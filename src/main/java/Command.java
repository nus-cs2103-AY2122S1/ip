import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * The Command class encapsulates all commands behaviour for a bot.
 */
public class Command {
    private final String name;

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
     * @param records records of existing tasks.
     * @return added message for command to-do.
     * @throws DukeException if description is empty.
     */
    public String todo(String description, ArrayList<Task> records) throws DukeException {
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
     * @param at string of event date in YYYY-MM-DD.
     * @param records records of existing tasks.
     * @return message for command event
     * @throws DukeException if description or date/time is not in YYYY-MM-DD.
     */
    public String event(String description, String at, ArrayList<Task> records) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        } else if (at.isEmpty()) {
            throw new DukeException("OOPS!!! No date for event! Use format of event description /at date");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(at.trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!! Date and Time must be specified by YYYY-MM-DD");
        }

        Event toAdd = new Event(description, date);
        records.add(toAdd);
        return String.format("Got it. I've added this task:\n\t %1$s \n\t" +
                        "Now you have %2$d tasks in the list.\n\t", toAdd.toString(), records.size());
    }

    /**
     * Returns added message for command deadline.
     *
     * @param description description of deadline task.
     * @param by string of deadline date in YYYY-MM-DD.
     * @param records records of existing tasks.
     * @return deadline message for command deadline.
     * @throws DukeException if description or date/time is not in YYYY-MM-DD.
     */
    public String deadline(String description, String by, ArrayList<Task> records) throws DukeException{
        if (description.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (by.isEmpty()) {
            throw new DukeException("OOPS!!! No date for deadline! Use format of deadline description /by date");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(by.trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!! Date and Time must be specified by YYYY-MM-DD");
        }

        Deadline toAdd = new Deadline(description, date);
        records.add(toAdd);
        return String.format("Got it. I've added this task:\n\t %1$s \n\t" +
                        "Now you have %2$d tasks in the list.\n\t", toAdd.toString(), records.size());
    }

    /**
     * Returns message representing list of all items user added.
     *
     * @param records records of existing tasks.
     * @return formatted string representing elements in records array.
     */
    public String list(ArrayList<Task> records) {
        final StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n\t");
        records.forEach((el) -> sb.append(
                String.format("%1$d. %2$s \n\t",
                        records.indexOf(el) + 1, el.toString())));
        return sb.toString();
    }

    /**
     * Returns delete message for bot.
     *
     * @param index index of task to be deleted.
     * @param records records of existing tasks.
     * @return delete message for bot.
     * @throws DukeException if index < 0 or index > number of tasks
     */
    public String delete(int index, ArrayList<Task> records) throws DukeException {
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
     * @param records records of existing tasks.
     * @return task mark as done message.
     * @throws DukeException if index < 0 or index points to null value.
     */
    public String done(int index, ArrayList<Task> records) throws DukeException {
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
     * Return message for tasks matched with keyword.
     *
     * @param keyword String of keyword to be matched.
     * @param records records of existing tasks.
     * @return message for all tasks matched with keyword.
     * @throws DukeException if keyword is empty.
     */
    public String find(String keyword, ArrayList<Task> records) throws DukeException{
        if (keyword.isEmpty()) {
            throw new DukeException("OOPS!!! No keyword provided.");
        }
        ArrayList<Task> result = new ArrayList<>();
        final StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n\t");

        for (int i = 0; i < records.size(); i++) {
            Task current = records.get(i);
            if (current.description.matches(keyword)) {
                result.add(current);
            }
        }
        result.forEach((el) -> sb.append(
                String.format("%1$d. %2$s \n\t",
                        result.indexOf(el) + 1, el.toString())));
        return sb.toString();
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
