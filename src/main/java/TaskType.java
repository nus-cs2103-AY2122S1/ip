import java.util.regex.Matcher;

public enum TaskType {
    TODO,
    EVENT,
    DEADLINE;

    public static Task getTask(Matcher input, TaskType type) {
        switch(type) {
            case TODO:
                return new Todo(input.group(1));
            case DEADLINE:
                return new Deadline(input.group(1), input.group(2));
            case EVENT:
//                return new Event(input.group(1), input.group(2));
            default: return null;
        }
    }


}
