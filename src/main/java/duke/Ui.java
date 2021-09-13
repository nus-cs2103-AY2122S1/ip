package duke;

public class Ui {
    private final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Prints greeting message.
     */
    public String greetingMsg() {
        String str = "Hello from\n"
                + LOGO
                + "My favorite partner is back! How can I help?\n";
        return (str);
    }

    /**
     * Prints goodbye message.
     */
    public String goodbyeMsg() {
        return ("Have a good day, friend!\n");
    }

    /**
     * Returns task list.
     *
     * @param taskList Task list to be printed.
     * @return Task list in string.
     */
    public String listTaskList(TaskList taskList) {
        return taskList.toString();
    }

    /**
     * Returns done task message.
     *
     * @param task Task to be printed.
     * @return String message for done command.
     */
    public String doneTaskMsg(Task task) {
        return ("Well Done, I'll get it marked:\n"
                + task.toString() + "\n");
    }

    /**
     * Returns delete task message.
     *
     * @param task Task to be printed.
     * @return String message for delete command.
     */
    public String deleteTaskMsg(Task task, int noOfTask) {
        return ("Roger! I will remove this task from the list: \n"
                + task.toString() + "\n"
                + "Now you have "
                + noOfTask
                + " tasks left in the list.\n");
    }

    /**
     * Return add task message.
     *
     * @param task Task to be printed.
     * @return String message for new task command.
     */
    public String addTaskMsg(Task task, int noOfTask) {
        return ("Roger! I will add this task in: \n"
                + task.toString() + "\n"
                + "Now you have "
                + noOfTask
                + " tasks left in the list.\n");
    }

    /**
     * Return find task message and lists relevant tasks.
     *
     * @param taskList Task list that contains relevant tasks to be printed.
     * @return Task list that contains the keyword.
     */
    public String findTaskMsg(TaskList taskList) {
        return ("Are these what you were looking for?\n"
                + taskList.toString());
    }

    public String helpMsg() {
        return "Available commands: "
                + "todo, "
                + "deadline, "
                + "event, "
                + "list, "
                + "done, "
                + "delete, "
                + "find\n"
                + "for format of specific commands input:\n"
                + "help {command}";
    }

    /**
     * Return the format of command specified.
     *
     * @param input The command specified.
     * @return the format of the command specified.
     */
    public String helpCommandMsg(String input) {
        String command = input;
        String response;
        switch(input) {
        case("todo"):
            response = "todo {task}";
            break;
        case("deadline"):
            response = "deadline {task} /by {date in yyyy-mm-dd} {time in hhmm 24hr format}\n"
                    + "eg. deadline homework /by 2020-12-31 2359\n";
            break;
        case("event"):
            response = "event {task} /at {date in yyyy-mm-dd} {time in hhmm 24hr format}\n"
                    + "eg. event meeting /at 2020-12-31 2100\n";
            break;
        case("list"):
            response = "list\n";
            break;
        case("done"):
            response = "done {index}\n"
                    + "eg. done 1\n";
            break;
        case("delete"):
            response = "delete          | delete {index}\n"
                    + "eg.delete 1\n";
            break;
        case("find"):
            response = "find {keyword}\n"
                    + "eg. find book\n";
            break;
        default:
            response = "RAWR!!! Enter the correct command input";
        }
        return response;
    }
}
