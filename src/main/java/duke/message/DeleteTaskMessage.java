package duke.message;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

/**
 * Represents Duke' response after a task is deleted from the list.
 */
public class DeleteTaskMessage extends DukeMessage {
    private Task deletedTask;
    private int taskIndex;

    public DeleteTaskMessage(int index) {
        this.taskIndex = index;
    }

    /**
     * Finds the task with a given reference id
     */
    private void findTask(int refId) {
        ArrayList<Task> taskList =
                (ArrayList<Task>) TaskList.getTaskList().getTasks();
        for (Task task : taskList) {
            int currTaskId = task.getRefId();
            if (currTaskId == refId) {
                this.deletedTask = task;
            }
        }
    }

    @Override
    public String createMessageString() {
        String reply;
        findTask(this.taskIndex);
        if(deletedTask != null) {
            TaskList.getTaskList().getTasks().remove(deletedTask);
            reply = "Theek hai... ye task hata diya!(deleted task)\n"
                    + deletedTask.getTaskString() + "\n";
        } else {
            reply = "Bhai dekh ke index daal!(no such index)";
        }

        return reply;
    }
}
