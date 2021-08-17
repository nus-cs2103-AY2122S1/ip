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
     * Returns echo message for bot.
     *
     * @param input
     * @return input
     */
    public String echo(String input) {
        return input;
    }

    /**
     * Returns added message for add command.
     *
     * @param input String to be added.
     * @return successfully added message.
     */
    public String add(String input) {
        Task toAdd = new Task(input);
        records[count] = toAdd;
        count += 1;
        return String.format("added: %1$s \n\t", input);
    }

    /**
     * Returns added message for command to-do.
     *
     * @param description description of to-do task.
     * @return added message for command to-do.
     */
    public String todo(String description) {
        Todo toAdd = new Todo(description);
        records[count] = toAdd;
        count += 1;
        return String.format("Got it. I've added this task:\n\t %1$s \n\t" +
                        "Now you have %2$d tasks in the list.\n\t", toAdd.toString(), count);
    }

    /**
     * Returns added message for command event.
     * @param description description of event task.
     * @param at string of event date/time.
     * @return added message for command event.
     */
    public String event(String description, String at) {
        Event toAdd = new Event(description, at);
        records[count] = toAdd;
        count += 1;
        return String.format("Got it. I've added this task:\n\t %1$s \n\t" +
                        "Now you have %2$d tasks in the list.\n\t", toAdd.toString(), count);
    }

    /**
     * Returns added message for command deadline.
     * @param description description of deadline task.
     * @param by string of deadline date/time.
     * @return added message for command deadline.
     */
    public String deadline(String description, String by) {
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
     */
    public String done(int index) {
        Task done = records[index];
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
