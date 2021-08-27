import java.util.Arrays;

public class ToDo extends Task {
    public static String TYPE = "Todo";
    public static final String SYMBOL = "T";


    public static ToDo of(String taskSummary) {
        return new ToDo(taskSummary);
    }

    public static ToDo parse(String storageLine) {
        //example line: "T | 0 | eat"
        String[] args = storageLine.split(" \\| ");
        if (args.length != 3) {
            throw new IllegalArgumentException("storage line passed in doesnt have enough arguments");
        }
        ToDo loadedToDo = new ToDo(args[2]);
        Boolean completed = args[1].equals("1");
        if (completed) {
            loadedToDo.markCompleted();
        }
        return loadedToDo;
    }

    public ToDo(String taskSummary) {
        super(taskSummary);
    }

//    private String taskTypeSymbol() {
//        return Character.toString(ToDo.TYPE.charAt(0));
//    }

    public static String syntax() {
        return "todo command syntax: \'todo <task>\'";
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
