package duke;

public class DukeException extends Exception{

    private String errorMessage;
    public enum Type {
        DESCRIPTION, INDEX, COMMAND, DEADLINE, EVENT
    }
    private Type type;

    public DukeException(Type s) {
        type = s;
        switch (type) {
        case DESCRIPTION:
            this.errorMessage = "☹ OOPS!!! The description of a task cannot be empty.";
            break;
        case INDEX:
            this.errorMessage = "☹ OOPS!!! The index of the task is out of range.";
            break;
        case COMMAND:
            this.errorMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            break;
        case DEADLINE:
            this.errorMessage =  "☹ OOPS!!! Usage of deadline does not match 'description' /by 'deadline'";
            break;
        case EVENT:
            this.errorMessage = "☹ OOPS!!! Usage of event does not match 'description' /at 'timeframe'";
            break;
        }
    }

    @Override
    public String toString() {
        return errorMessage;
    }

    public Type getType() {
        return type;
    }
}
