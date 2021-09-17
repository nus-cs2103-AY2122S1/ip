package duke;

import java.util.ArrayList;

/**
 * Represents Duke's response after a task is marked as done.
 */
public class CompleteTaskMessage extends DukeMessage{
    private int taskIndex;

    public CompleteTaskMessage(int index) {
        this.taskIndex = index;
    }

    public String createMessageString() {
        ArrayList<Task> taskList =
                (ArrayList<Task>) TaskList.getTaskList().getTasks();
        Task completedTask = taskList.get(taskIndex);
        completedTask.markAsDone();
        String reply = "Theek hai... ye task khatam!\n"
                + completedTask.getTaskString() + "\n";
        return reply;
    }
}
