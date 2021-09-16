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
 *
 * @author Benjamin Lui
 */

public class TaskListDecoder {
    private static final int eventTypeIndexStart = 1;
    private static final int eventTypeIndexEnd = 2;
    private static final int eventDoneIndexStart = 4;
    private static final int eventDoneIndexEnd = 5;
    private static final int taskDescriptionIndexStart = 7;
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
        String modifiedTask = task.trim();
        String type = modifiedTask.substring(eventTypeIndexStart, eventTypeIndexEnd);
        String done = modifiedTask.substring(eventDoneIndexStart, eventDoneIndexEnd);
        String taskDescription = modifiedTask.substring(taskDescriptionIndexStart).trim();
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
