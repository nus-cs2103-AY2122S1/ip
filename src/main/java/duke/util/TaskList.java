package duke.util;

import duke.exception.InvalidIndexException;
import duke.exception.MissingArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

/**
 * The TaskList Class is a representation of a list of tasks that Duke is keeping track of.
 *
 * Tasks can be added into the list via Duke and the list can be displayed as well.
 *
 * @author Benedict Chua
 */
public class TaskList {
    private static final String INDENTATION = "     ";
    private ArrayList<Task> taskList;
    private Storage storage;

    public TaskList(ArrayList<String> existingTask, Storage storage) {
        this.storage = storage;
        if (existingTask != null) {
            taskList = new ArrayList<>();

            for (String taskString : existingTask) {
                String[] taskDetails = taskString.split(" \\| ");
                switch (taskDetails[0]) {
                case "T":
                    taskList.add(new ToDo(taskDetails[1], taskDetails[2]));
                    break;
                case "D":
                    taskList.add(new Deadline(taskDetails[1], taskDetails[2], taskDetails[3]));
                    break;
                case "E":
                    taskList.add(new Event(taskDetails[1], taskDetails[2], taskDetails[3]));
                    break;
                default:
                    // Unrecognised string -> do something next time?
                    // Ignore for now.
                    break;
                }
            }
        } else {
            taskList = new ArrayList<>();
        }
    }

    private String convertListToString() {
        String allTasks = "";
        for (Task task : taskList) {
            allTasks = allTasks + task.saveAsString() + "\n";
        }
        return allTasks;
    }

    /**
     * Adds a given task to the tasks list.
     *
     * @param task the task to add to the list.
     * @return String representation of messages stating that the task has been added
     */
    public String[] addToList(String task, String typeOfTask) throws MissingArgumentException {
        switch (typeOfTask) {
        case "ToDo":
            taskList.add(new ToDo(task));
            break;
        case "Deadline":
            String[] deadlineDetails = Parser.parseDeadlineDate(task);
            taskList.add(new Deadline(deadlineDetails[0], deadlineDetails[1]));
            break;
        case "Event":
            String[] eventDetails = Parser.parseEventDate(task);
            taskList.add(new Event(eventDetails[0], eventDetails[1]));
            break;
        default:
            // will NOT execute as Duke calls this function to add a task and it only calls them based on
            // the same switch cases.
            break;
        }

        storage.writeToFile(convertListToString());

        return new String[] {
                "I've added this task but it's not like I did it for you or anything!",
                String.format("  %s", taskList.get(taskList.size() - 1)),
                String.format("Now you have %d %s in the list. Do your best doing them okay?",
                        taskList.size(), taskList.size() == 1 ? "task" : "tasks")
        };
    }

    /**
     * Prints the tasks in the list with indexing starting from 1.
     */
    public void printList(String dateString) {
        if (dateString != null) {
            int index = 1;

            for (int i = 0; i < taskList.size(); i++) {
                Task currTask = taskList.get(i);
                if (currTask.onDate(dateString)) {
                    System.out.println(INDENTATION + String.format("%d:%s", index, currTask));
                    index++;
                }
            }
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                Task currTask = taskList.get(i);
                System.out.println(INDENTATION + String.format("%d:%s", i + 1, currTask));
            }
        }
    }

    /**
     * Marks tasks (based on index) as done if it exists.
     *
     * @param index index given by the User for Task in the TaskList (starts from 1).
     * @return message of the completion of the Task if it exists, else user will be informed that no such task exists.
     */
    public String[] markTaskAsDone(int index) throws InvalidIndexException {
        if (index <= 0 || index > taskList.size()) {
            throw new InvalidIndexException(taskList.size());
        }
        String[] message = {
                "You completed a task! Maybe you aren't so incompetent after all.",
                taskList.get(index - 1).markTaskAsDone()
        };

        storage.writeToFile(convertListToString());

        return message;

    }

    public String[] deleteTask(int index) throws InvalidIndexException {
        if (index <= 0 || index > taskList.size()) {
            throw new InvalidIndexException(taskList.size());
        }

        Task deletedTask = taskList.remove(index - 1);
        String[] message = {
                "I've deleted this task so show me some gratitude!",
                String.format("  %s", deletedTask),
                String.format("Now you have %d %s in the list. Do your best doing them okay?",
                        taskList.size(), taskList.size() == 1 ? "task" : "tasks")
        };

        storage.writeToFile(convertListToString());

        return message;

    }
}