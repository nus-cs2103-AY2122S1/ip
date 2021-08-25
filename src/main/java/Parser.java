public class Parser {
    public static Command parse(String fullCommand, TaskList tasks) throws DukeException {
        String[] cmdWordDetails = fullCommand.split(" ", 2);
        String cmdWord = cmdWordDetails[0]; // get first word as command
        String description = cmdWordDetails.length > 1 ? cmdWordDetails[1] : "";
        Integer taskNum;
        
        switch(cmdWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new DisplayCommand();
            case "todo":
                return new AddCommand(TaskList.TaskType.TODO, description);
            case "deadline":
                return new AddCommand(TaskList.TaskType.DEADLINE, description);
            case "event":
                return new AddCommand(TaskList.TaskType.EVENT, description);
            case "done":
                taskNum = validateTaskNumber(description, tasks);
                return new DoneCommand(taskNum);
            case "delete":
                taskNum = validateTaskNumber(description, tasks);
                return new DeleteCommand(taskNum);
            default:
                throw new DukeException("Sorry, I don't know what that means.");
        }
    }

    /** checks if input is a valid task number and returns task number if valid */
    private static Integer validateTaskNumber(String input, TaskList tasks) throws DukeException {
        Integer taskNum;
        try {
            taskNum = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DukeException("You did not specify the task number.");
        }
        int listLength = tasks.getListSize();
        int taskIndex = taskNum - 1;
        if (listLength == 0) {
            throw new DukeException("The operation cannot be performed as you have 0 tasks in your list.");
        }
        if (taskIndex < 0 || taskIndex >= tasks.getListSize()) {
            throw new DukeException("The task number must be from 1 to " + listLength + ".");
        }
        return taskNum;
    }
}
