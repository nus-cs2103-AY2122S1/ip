package duke;

public class Parser {
    Ui ui;
    Storage storage;
    TaskList taskList;

    /**
     * Constructor for parser which requires Storage and TaskList Objects.
     * @param storage Storage object.
     * @param tasks TaskList object.
     */
    public Parser(Storage storage, TaskList tasks) {
        this.storage = storage;
        this.taskList = tasks;
        this.ui = new Ui();
    }

    /**
     * Parses the users input and decides acts accordingly.
     *
     * @param userInput Input from user.
     * @return False if user wants to close the program and true otherwise.
     */
    public Boolean command(String userInput) {
        String toReply;
        if (userInput.equalsIgnoreCase("bye")) {
            ui.dukeReply("Bye. Hope to see you again soon!");
            return false;
        } else if(userInput.equalsIgnoreCase("list")) {
            toReply = taskList.showTasks(taskList.getTasks());
            // cant put done word as a task
        }else if (userInput.toLowerCase().startsWith("done")) {
            toReply = taskList.markTaskDone(userInput);
            storage.updateDataSet(taskList.getTasks());
        } else if(userInput.toLowerCase().startsWith("delete")) {
            toReply = taskList.deleteTask(userInput);
            storage.updateDataSet(taskList.getTasks());
        } else {
            toReply = taskList.addTask(userInput, storage.getDataPath());
        }
        ui.dukeReply(toReply);
        return true;
    }
}


