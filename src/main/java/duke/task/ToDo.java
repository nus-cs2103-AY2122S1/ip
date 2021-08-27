package duke.task;

public class ToDo extends Task {
    public static final String SYMBOL = "T";
    public static final String COMMAND_REGEX = "todo \\w+.*";


    public static ToDo of(String taskSummary) {
        return new ToDo(taskSummary);
    }

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

    public ToDo(String taskSummary) {
        super(taskSummary);
    }

    public static String syntax() {
        return "todo command syntax: 'todo <duke.task>'";
    }

    @Override
    public String toStorageFormat() {
        return String.format(
            "%s | %d | %s",
            ToDo.SYMBOL, this.isCompleted() ? 1 : 0,this.getTaskSummary()
        );
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
