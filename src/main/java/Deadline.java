import java.util.Arrays;

public class Deadline extends Task {
    public static final String TYPE = "Deadline";
    public static final String SYMBOL = "D";
    private String deadline;

    public static Deadline of(String taskSummary, String byDate) {
        return new Deadline(taskSummary, byDate);
    }

    public static Deadline parse(String storageLine) {
        //example line: "D | 0 | work | tonight"
        String[] args = storageLine.split(" \\| ");
        if (args.length != 4) {
            throw new IllegalArgumentException("storage line passed in doesnt have enough arguments");
        }
        System.out.println(Arrays.toString(args));
        Deadline loadedTask = new Deadline(args[2], args[3]);
        Boolean completed = args[1].equals("1");
        if (completed) {
            loadedTask.markCompleted();
        }
        return loadedTask;
    }

    public Deadline(String taskSummary, String byDate) {
        super(taskSummary);
        this.deadline = byDate;
    }

    public static String syntax() {
        return "deadline command syntax: \'deadline <task> /by <deadlineTime>\'";
    }

    @Override
    public String toStorageFormat() {
        return String.format(
            "%s | %d | %s | %s",
            Deadline.SYMBOL, this.isCompleted() ? 1 : 0,this.getTaskSummary(), this.deadline
        );
    }

    private String taskTypeSymbol() {
        return Character.toString(Deadline.TYPE.charAt(0));
    }

    @Override
    public String toString() {
        return String.format(
            "[%s][%s] %s (by: %s)",
            Deadline.SYMBOL,
            this.isCompleted() ? "X" : "",
            this.getTaskSummary(),
            this.deadline
        );
    }
}
