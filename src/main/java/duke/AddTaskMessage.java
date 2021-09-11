package duke;
/**
 * Represents Duke's response after a task is added to the
 * list.
 */
public class AddTaskMessage extends DukeMessage {
    private String userString;
    private Task createdTask;

    public AddTaskMessage(String userStr) throws IllegalFormatException
            , EmptyDescriptionException, InvalidCommandException {
        userString = userStr;
        createdTask = TaskFactory.createTask(this.userString);
        int taskListSize = TaskList.getTaskList().addTask(createdTask);
        assert taskListSize > 0 : "taskListSize should be greater than 0";
    }

    public String display() {
        String reply = "Theek h bhai... ye task bhi list mein daal diya"
                +"\n" + createdTask.getTaskString() +
                "\nAb " + TaskList.getTaskList().getSize()
                + " tasks hain list mein.\n";
        return reply;
    }
}


