package duke;

public class CommandHandler {
    protected Ui ui;
    protected TaskList taskList;

    public CommandHandler(Ui ui, TaskList taskList) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public String handleEvent(Task eventTask) throws EmptyDescriptionException {
        taskList.add(eventTask);
        return ui.addTask(eventTask);
    }

    public String handleDeadline(Task deadlineTask) throws EmptyDescriptionException {
        taskList.add(deadlineTask);
        return ui.addTask(deadlineTask);
    }

    public String handleTodo(Task toDoTask) throws EmptyDescriptionException {
        taskList.add(toDoTask);
        return ui.addTask(toDoTask);
    }

    public String handleFind(String input) throws EmptyDescriptionException {
        if (input.length() == 4) {
            throw new EmptyDescriptionException("error");
        }

        String keyword = input.substring(5);

        return ui.findTask(keyword);
    }

    public String handleDelete(String input) throws EmptyDescriptionException {
        if (input.length() == 6) {
            throw new EmptyDescriptionException("error");
        }
        char taskIndex = input.charAt(7);
        int index = Integer.parseInt(String.valueOf(taskIndex));

        Task taskAtIndex = taskList.getTask(index);

        taskList.removeTask(index);
        return ui.deleteTask(taskAtIndex);
    }

    public String handleDone(String input) throws EmptyDescriptionException {
        if (input.length() == 4) {
            throw new EmptyDescriptionException("error");
        }

        char indexOfTask = input.charAt(5);
        int index = Integer.parseInt(String.valueOf(indexOfTask));

        Task taskAtIndex = taskList.getTask(index);
        taskAtIndex.markAsDone();

        return ui.doneTask(taskAtIndex);
    }

}
