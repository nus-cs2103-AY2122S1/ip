package nyx.command;

import java.io.IOException;

import nyx.NyxException;
import nyx.Storage;
import nyx.task.Task;
import nyx.task.TaskList;

abstract class AddHandler {
    static String handleAdd(Task task, TaskList taskList, Storage storage) throws NyxException {
        try {
            storage.addData(task);
            taskList.addTask(task);
            return String.format("Got it. I've added this task:\n\t  %s\nNow you have %d tasks in the list.",
                    task, taskList.getNumTasks());
        } catch (IOException e) {
            throw new NyxException("Unable to save this task...");
        }
    }
}
