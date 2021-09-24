package duke;

public class Parser {
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Constructor for parser which requires Storage and TaskList Objects.
     * @param storage Storage object.
     * @param tasks TaskList object.
     */
    public Parser(Storage storage, TaskList tasks) {
        this.storage = storage;
        this.taskList = tasks;
    }

    /**
     * Parses the users input and decides acts accordingly.
     *
     * @param userInput Input from user.
     * @return False if user wants to close the program and true otherwise.
     */
    public String command(String userInput) {
        String toReply;
        if (userInput.equalsIgnoreCase("bye")) {
            toReply = "Bye. Hope to see you again soon!";

        } else if (userInput.equalsIgnoreCase("list")) {
            toReply = taskList.showTasks(taskList.getTasks());
        } else if (userInput.toLowerCase().startsWith("done")) {
            toReply = taskList.markTaskDone(userInput);
            storage.updateDataSet(taskList.getTasks());
        } else if (userInput.toLowerCase().startsWith("delete")) {
            toReply = taskList.deleteTask(userInput);
            storage.updateDataSet(taskList.getTasks());
        } else if (userInput.toLowerCase().startsWith("find")) {
            toReply = taskList.findTask(taskList.getTasks(), userInput);
        } else {
            toReply = taskList.addTask(userInput, storage.getDataPath());
        }

        return toReply;
    }
}
