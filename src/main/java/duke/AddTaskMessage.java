package duke;
/**
 * The class for creating Duke' response after a task is added to the list.
 */
public class AddTaskMessage extends DukeMessage {
    private String userString;
    private Task createdTask;

    public AddTaskMessage(String userStr) throws IllegalFormatException, EmptyDescriptionException, InvalidCommandException {
        userString = userStr;
        createdTask = TaskFactory.createTask(this.userString);
        TaskList.getTaskList().addTask(createdTask);
    }

    public void display() {
        System.out.println("Theek h bhai... ye task bhi list mein daal diya");
        System.out.println(createdTask.getTaskString());
        System.out.println("Ab " + TaskList.getTaskList().getSize() + " tasks hain list mein.");
        Duke.conversationState = true;
    }
}


