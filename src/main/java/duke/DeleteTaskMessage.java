package duke;

/**
 * Represents Duke' response after a task is deleted from the list.
 */
public class DeleteTaskMessage extends DukeMessage {
    private Task deletedTask;
    private int taskIndex;

    public DeleteTaskMessage(int index) {
        this.taskIndex = index;
        this.deletedTask = TaskList.getTaskList().getTasks()
                .remove(taskIndex);
    }

    public String createMessageString() {
        String reply = "Theek h bhai... ye task hata diya\n"
                + deletedTask.getTaskString()
                + "\nAb " + TaskList.getTaskList().getSize()
                + " tasks bache list mein.\n";
        return reply;
    }
}
