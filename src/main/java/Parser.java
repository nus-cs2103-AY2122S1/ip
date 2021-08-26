public class Parser {

    private enum TaskObjective {
        BYE,
        DEADLINE,
        DELETE,
        DONE,
        EVENT,
        LIST,
        TODO,
        UNKNOWN
    }

    private TaskObjective getCommand(String command) {
        try {
            if (command != null) {
                return TaskObjective.valueOf(command.toUpperCase());
            } else {
                return TaskObjective.UNKNOWN;
            }
        } catch (IllegalArgumentException e) {
            return TaskObjective.UNKNOWN;
        }
    }

    public Command parse(String taskDescription) throws DukeException {
        if (taskDescription == null || taskDescription.equals("")) {
            return new Command(Command.Commands.UNKNOWN, "");
        } else {
            String taskObjective, taskDetails;
            String[] task = taskDescription.split(" ", 2);
            taskObjective = task[0];
            taskDetails = task.length > 1 ? task[1].trim() : "";
            switch (getCommand(taskObjective)) {
            case BYE:
                return new Command(Command.Commands.BYE, taskDetails);
            case DEADLINE:
                return new Command(Command.Commands.DEADLINE, taskDetails);
            case DELETE:
                return new Command(Command.Commands.DELETE, taskDetails);
            case DONE:
                return new Command(Command.Commands.DONE, taskDetails);
            case EVENT:
                return new Command(Command.Commands.EVENT, taskDetails);
            case LIST:
                return new Command(Command.Commands.LIST, taskDetails);
            case TODO:
                return new Command(Command.Commands.TODO, taskDetails);
            case UNKNOWN:
                return new Command(Command.Commands.UNKNOWN, taskDetails);
            }
            return new Command(Command.Commands.UNKNOWN, taskDetails);
        }
    }
}