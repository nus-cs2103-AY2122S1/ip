package duke.task;

public class ToDo extends Task {
    public static final String SYMBOL = "T";
    public static final String COMMAND_REGEX = "todo \\w+.*";

    /**
     * Factory method of ToDo class
     * @param taskSummary task description
     */
    public static ToDo of(String taskSummary) {
        return new ToDo(taskSummary);
    }

    /**
     * Factory method of ToDo class.
     * Takes in a String, parses it and returns the ToDo instance it represented
     *
     * @param storageLine string representing task
     * @return ToDo instance which the string represented
     */
    public static ToDo parse(String storageLine) {
        //eg. "T | 0 | eat"
        String[] args = storageLine.split(" \\| ");
        if (args.length != 3) {
            throw new IllegalArgumentException("storage line passed in doesnt have enough arguments");
        }
        ToDo loadedToDo = new ToDo(args[2]);

        boolean completed = args[1].equals("1");
        if (completed) {
            loadedToDo.markCompleted();
        }

        return loadedToDo;
    }

    /**
     * Constructor for ToDo class
     * @param taskSummary task description
     */
    public ToDo(String taskSummary) {
        super(taskSummary);
    }

    /**
     * Returns a string detailing the syntax of the ToDo command
     *
     * @return helper text of correct syntax
     */
    public static String syntax() {
        return "todo command syntax: 'todo <task>'";
    }

    /**
     * Converts an Task instance to a string to be stored.
     *
     * @return line of text detailing task details.
     */
    @Override
    public String toStorageFormat() {
        return String.format(
            "%s | %d | %s",
            ToDo.SYMBOL, this.isCompleted() ? 1 : 0,
            this.getTaskSummary()
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ToDo) {
            ToDo toDo = (ToDo) obj;
            return toDo.getTaskSummary().equals(this.getTaskSummary());
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format(
            "[%s][%s] %s",
            ToDo.SYMBOL,
            this.isCompleted() ? "X" : "",
            this.getTaskSummary()
        );
    }
}
