public class AddTask extends DukeMessage{
    private String userString;
    private Task createdTask;

    public AddTask(String userStr) throws IllegalFormatException, EmptyDescriptionException, InvalidCommandException {
        userString = userStr;
        createdTask = TaskFactory.createTask(this.userString);
        TaskList.getTaskList().addTask(createdTask);
    }

    public void display() {
        System.out.println("Added: ");
        System.out.println(createdTask.getTaskString());
        System.out.println("There are now " + TaskList.getTaskList().getSize() + " tasks in the list.");
        Duke.conversationState = true;
    }
}


