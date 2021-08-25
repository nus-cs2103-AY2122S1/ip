package duke;

public class Parser {
    Ui ui;
    Storage storage;
    TaskList taskList;

    public Parser(Storage storage, TaskList tasks) {
        this.storage = storage;
        this.taskList = tasks;
        this.ui = new Ui();
    }

    public Boolean command(String userInput) {
        String toReply;
        if (userInput.equalsIgnoreCase("bye")) {
            ui.dukeReply("Bye. Hope to see you again soon!");
            return false;
        } else if(userInput.equalsIgnoreCase("list")) {
            toReply = taskList.showTasks(taskList.getTasks());
        }else if (userInput.toLowerCase().startsWith("done")) {
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
        ui.dukeReply(toReply);
        return true;
    }
}


