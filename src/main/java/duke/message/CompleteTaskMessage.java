package duke.message;

import duke.task.TaskList;
import duke.task.Task;
import java.util.ArrayList;

/**
 * Represents Duke's response after a task is marked as done.
 */
public class CompleteTaskMessage extends DukeMessage{
    private int taskIndex;
    private Task queriedTask;

    public CompleteTaskMessage(int index) {
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
                this.queriedTask = task;
            }
        }
    }

    @Override
    public String createMessageString() {
        String reply;
        findTask(this.taskIndex);
        if(queriedTask != null) {
            queriedTask.markAsDone();
            reply = "Theek hai... ye task khatam!(task done)\n"
                    + queriedTask.getTaskString() + "\n";
        } else {
            reply = "Bhai dekh ke index daal!(no such index)";
        }

        return reply;
    }
}
