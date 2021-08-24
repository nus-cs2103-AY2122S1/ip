import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Task {
    protected String description;
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public static Duke.TaskType inputTaskType(String[] data) throws InvalidCommandException {
        Duke.TaskType type = null;
        if (data.length < 3
                || !(data[1].equals("0") || data[1].equals("1"))
                || data[2].trim().isEmpty()) {
            throw new InvalidCommandException();
        }
        switch (data[0]) {
            case "T":
                if (data.length != 3) {
                    throw new InvalidCommandException();
                }
                type = Duke.TaskType.TODO;
                break;
            case "D":
                if (data.length != 4 || data[3].trim().isEmpty()) {
                    throw new InvalidCommandException();
                }
                try {
                    LocalDate.parse(data[3]);
                } catch (DateTimeParseException e) {
                    throw new InvalidCommandException();
                }
                type = Duke.TaskType.DEADLINE;
                break;
            case "E":
                if (data.length != 4 || data[3].trim().isEmpty()) {
                    throw new InvalidCommandException();
                }
                try {
                    LocalDate.parse(data[3]);
                } catch (DateTimeParseException e) {
                    throw new InvalidCommandException();
                }
                type = Duke.TaskType.EVENT;
                break;
            default:
                throw new InvalidCommandException();
        }
        return type;
    }

    public String taskToLine() {
        String isDone = this.isDone ? "1" : "0";
        return String.format(" | %s | %s", isDone, this.description);
    }

    public static Task of(String[] data){
        Task result = null;
        Duke.TaskType taskType = inputTaskType(data);

        boolean isDone = data[1].equals("1");
        String description = data[2];

        switch (taskType) {
            case TODO:
                result = new ToDo(description, isDone);
                break;
            case DEADLINE:
                result = new Deadline(description, isDone, LocalDate.parse(data[3]));
                break;
            case EVENT:
                result = new Event(description, isDone, LocalDate.parse(data[3]));
                break;
        }
        return result;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String markDoneIcon() {
        return this.isDone ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return this.markDoneIcon() + " " + this.description;
    }
}
