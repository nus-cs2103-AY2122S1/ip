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
 * Contains functions to add/remove Tasks in the list and to display the list.
 *
 * @author Benedict Chua
 */
public class TaskList {
    private static final String INDENTATION = "     ";
    private ArrayList<Task> tasks;
    private Storage storage;

    /**
     * Constructs a TaskList with the given arguments.
     * If existing task is empty, creates an empty ArrayList of task.
     * Otherwise, parses the Tasks from the saved file and adds it to the ArrayList of task.
     *
     * @param existingTasks Tasks in the formatted into a String from the saved file.
     * @param storage Storage object to use to save state of TaskList.
     */
    public TaskList(ArrayList<String> existingTasks, Storage storage) {
        this.storage = storage;
        if (existingTasks != null) {
            tasks = new ArrayList<>();

            for (String taskString : existingTasks) {
                String[] taskDetails = taskString.split(" \\| ");
                switch (taskDetails[0]) {
                case "T":
                    tasks.add(new ToDo(taskDetails[1], taskDetails[2]));
                    break;
                case "D":
                    tasks.add(new Deadline(taskDetails[1], taskDetails[2], taskDetails[3]));
                    break;
                case "E":
                    tasks.add(new Event(taskDetails[1], taskDetails[2], taskDetails[3]));
                    break;
                default:
                    // Unrecognised string -> do something next time?
                    // Ignore for now.
                    break;
                }
            }
        } else {
            tasks = new ArrayList<>();
        }
    }

    /**
     * Converts and formats current TaskList into a String suitable to write into saved file.
     *
     * @return Formatted TaskList in the form of a String.
     */
    private String convertListToString() {
        String tasksString = "";
        for (Task task : this.tasks) {
            tasksString = tasksString + task.saveAsString() + "\n";
        }
        return tasksString;
    }

    /**
     * Adds a given task to the tasks list.
     * Task can be in the from of ToDo, Deadline or Event.
     *
     * @param task the task to add to the list.
     * @return String representation of messages stating that the task has been added
     */
    public String[] addToList(String task, String typeOfTask) throws MissingArgumentException {
        switch (typeOfTask) {
        case "ToDo":
            tasks.add(new ToDo(task));
            break;
        case "Deadline":
            String[] deadlineDetails = Parser.parseDeadlineDate(task);
            tasks.add(new Deadline(deadlineDetails[0], deadlineDetails[1]));
            break;
        case "Event":
            String[] eventDetails = Parser.parseEventDate(task);
            tasks.add(new Event(eventDetails[0], eventDetails[1]));
            break;
        default:
            // will NOT execute as Duke calls this function to add a task and it only calls them based on
            // the same switch cases.
            break;
        }

        storage.writeToFile(convertListToString());

        return new String[] {
                "I've added this task but it's not like I did it for you or anything!",
                String.format("  %s", tasks.get(tasks.size() - 1)),
                String.format("Now you have %d %s in the list. Do your best doing them okay?",
                        tasks.size(), tasks.size() == 1 ? "task" : "tasks")
        };
    }

    /**
     * Prints the tasks in the list with indexing starting from 1.
     * If dateString is not null, prints tasks that are due on that date.
     * Otherwise, prints all tasks.
     *
     * @param filterType the type of filtering to be done when displaying list
     * @param filterCondition String containing the respective filtering condition (expects date or keyword).
     */
    public void printList(String filterType, String filterCondition) {
        switch (filterType) {
        case "all":
            for (int i = 0; i < tasks.size(); i++) {
                Task currTask = tasks.get(i);
                System.out.println(INDENTATION + String.format("%d:%s", i + 1, currTask));
            }
            break;
        case "date":
            int dateIndex = 1;

            for (int i = 0; i < tasks.size(); i++) {
                Task currTask = tasks.get(i);
                if (currTask.isOnDate(filterCondition)) {
                    System.out.println(INDENTATION + String.format("%d:%s", dateIndex, currTask));
                    dateIndex++;
                }
            }
            break;
        case "keyword":
            int keywordIndex = 1;

            for (int i = 0; i < tasks.size(); i++) {
                Task currTask = tasks.get(i);
                if (currTask.containsKeyword(filterCondition)) {
                    System.out.println(INDENTATION + String.format("%d:%s", keywordIndex, currTask));
                    keywordIndex++;
                }
            }
        }
    }

    /**
     * Marks tasks based on index as done if it exists.
     *
     * @param index index given by the User for Task in the TaskList starting from 1.
     * @return message of the completion of the Task.
     * @throws InvalidIndexException if index given does not exist in the TaskList.
     */
    public String[] markTaskAsDone(int index) throws InvalidIndexException {
        if (index <= 0 || index > tasks.size()) {
            throw new InvalidIndexException(tasks.size());
        }
        String[] message = {
                "You completed a task! Maybe you aren't so incompetent after all.",
                tasks.get(index - 1).markTaskAsDone()
        };

        storage.writeToFile(convertListToString());

        return message;

    }

    /**
     * Deletes a task based on index if it exists.
     *
     * @param index index given by the User for Task in the TaskList starting from 1.
     * @return message of the deletion of the Task.
     * @throws InvalidIndexException if index given does not exist in the TaskList.
     */
    public String[] deleteTask(int index) throws InvalidIndexException {
        if (index <= 0 || index > tasks.size()) {
            throw new InvalidIndexException(tasks.size());
        }

        Task deletedTask = tasks.remove(index - 1);
        String[] message = {
                "I've deleted this task so show me some gratitude!",
                String.format("  %s", deletedTask),
                String.format("Now you have %d %s in the list. Do your best doing them okay?",
                        tasks.size(), tasks.size() == 1 ? "task" : "tasks")
        };

        storage.writeToFile(convertListToString());

        return message;

    }
}