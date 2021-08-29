package duke.storage;

import java.util.ArrayList;
import java.util.List;

import duke.task.DeadLine;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;



/**
 * Decodes the class from a list of strings in the file to an Array list of tasks.
 */

public class TaskListDecoder {
    /**
     * Decodes the list of string into a TaskList object.
     * @param encodedTaskList the tasks when read directly from the file
     * @return a TaskList object for further tasks operations
     */
    public static TaskList decodeTaskList(List<String> encodedTaskList) {
        final ArrayList<Task> decodedTasks = new ArrayList<Task>();
        for (String encodedTask : encodedTaskList) {
            decodedTasks.add(decodeTaskFromString(encodedTask));
        }

        return new TaskList(decodedTasks);
    }

    /**
     * Decodes the task from string to create a Task object.
     * @param task the task to be decoded
     * @return the task that has been to decoded as a Task object
     */
    static Task decodeTaskFromString(String task) {
        task.trim();
        String type = task.substring(1, 2);
        String done = task.substring(4, 5);
        String taskDescription = task.substring(7, task.length()).trim();
        switch (type) {
        case "T" :
            return new Todo(taskDescription, done);
        case "D" :
            DesAndTime deadLineDetails = new DesAndTime(taskDescription);
            deadLineDetails.deadLine();
            return new DeadLine(deadLineDetails.getDes(), deadLineDetails.getTime(), done);
        case "E" :
            DesAndTime eventDetails = new DesAndTime(taskDescription);
            eventDetails.event();
            return new Event(eventDetails.getDes(), eventDetails.getTime(), done);
        default:
            return new Task("Invalid Task");
        }
    }
}
