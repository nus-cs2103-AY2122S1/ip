public class Parser {
    public static boolean isBye(String task) {
        return task.equals(InputTypes.BYE.getValue());
    }

    public static InputTypes judgeType(String task) {
        InputTypes type = InputTypes.UNKNOWN;
        if (task.contains(InputTypes.TODO.getValue())) {
            type = InputTypes.TODO;
        } else if (task.contains(InputTypes.DEADLINE.getValue())) {
            type = InputTypes.DEADLINE;
        } else if (task.contains(InputTypes.EVENT.getValue())) {
            type = InputTypes.EVENT;
        } else if (task.contains(InputTypes.DONE.getValue())) {
            type = InputTypes.DONE;
        } else if (task.equals(InputTypes.LIST.getValue())) {
            type = InputTypes.LIST;
        } else if (task.contains(InputTypes.DELETE.getValue())) {
            type = InputTypes.DELETE;
        }
        return type;
    }
}
