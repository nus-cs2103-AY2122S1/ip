import java.time.format.DateTimeFormatter;

public class Parser {
    public static Command.CommandType stringToCommand(String s) throws AliceException {
        switch (s) {
            case "list":
                return Command.CommandType.LIST;
            case "date":
                return Command.CommandType.DATE;
            case "todo":
                return Command.CommandType.TODO;
            case "deadline":
                return Command.CommandType.DEADLINE;
            case "event":
                return Command.CommandType.EVENT;
            case "done":
                return Command.CommandType.DONE;
            case "delete":
                return Command.CommandType.DELETE;
            case "commands":
            case "?":
            case "help":
                return Command.CommandType.COMMANDS;
            case "bye":
                return Command.CommandType.BYE;
            default:
                throw new AliceException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public static TaskList.TaskType stringToTaskType(String s) throws AliceException {
        switch (s) {
            case "TD":
                return TaskList.TaskType.TODO;
            case "DL":
                return TaskList.TaskType.DEADLINE;
            case "EV":
                return TaskList.TaskType.EVENT;
            default:
                throw new AliceException("Invalid String detected: " + s);
        }
    }

    public static TaskList.TaskType classNameToTaskType(String cn) throws AliceException {
        switch (cn) {
            case "Todo":
                return TaskList.TaskType.TODO;
            case "Deadline":
                return TaskList.TaskType.DEADLINE;
            case "Event":
                return TaskList.TaskType.EVENT;
            default:
                throw new AliceException("Invalid ClassName detected: " + cn);
        }
    }

    public static String taskTypeToString(TaskList.TaskType t) throws AliceException {
        switch(t) {
            case TODO:
                return "TD";
            case DEADLINE:
                return "DL";
            case EVENT:
                return "EV";
            default:
                throw new AliceException("Invalid TaskType detected: " + t);
        }
    }

    public static String taskToSaveFormat(Task task) {
        TaskList.TaskType type = classNameToTaskType(task.getClass().getName());
        String s = taskTypeToString(type) + " | " + (task.isDone ? 1 : 0) + " | " + task.description;
        switch (type) {
            case EVENT:
                Event ev = (Event) task;
                s += " | " + ev.getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                break;
            case DEADLINE:
                Deadline dl = (Deadline) task;
                s += " | " + dl.getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                break;
            default:
                break;
        }
        return s;
    }

    public static boolean yesNoToBoolean(String yesNo) {
        switch(yesNo) {
            case "yes":
            case "y":
            case "Y":
                return true;
            default:
                return false;
        }
    }

    public static Command parse(String fullCommand) {
        return new Command(fullCommand);
    }
}
