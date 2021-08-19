import java.text.ParseException;
import java.time.LocalDate;

public abstract class Task {
    protected String description;
    protected boolean done;

    public Task(String input, boolean done) {
        description = input;
        this.done = done;
    }

    public boolean toggleDone() {
        done = !done;
        return done;
    }

    /**
     * String representation of Task
     *
     * @return task display
     */
    @Override
    public String toString() {
        String checkBox = done
                ? "[X] "
                : "[ ] ";
        return checkBox + description;
    }

    abstract String saveString();

    public static Task StringToTask(String task) throws ParseException {
        String[] args = task.split("\\t");
        String taskType = args[0];
        try {
            switch(taskType) {
                case "T":
                    return new TaskTodo(args[2], args[1].equals("1"));
                case "D":
                    return args.length==4
                            ? new TaskDeadline(args[2], LocalDate.parse(args[3]), null, !args[1].equals("0"))
                            : new TaskDeadline(args[2], LocalDate.parse(args[3]), args[4], !args[1].equals("0"));
                case "E":
                    return args.length==4
                            ? new TaskEvent(args[2], LocalDate.parse(args[3]), null, !args[1].equals("0"))
                            : new TaskEvent(args[2], LocalDate.parse(args[3]), args[4], !args[1].equals("0"));
                default:
                    throw new ParseException("Failed to read task; file not read", 0);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException("Invalid task found; file not read", 0);
        }
    }
}
