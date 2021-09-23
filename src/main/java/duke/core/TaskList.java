package duke.core;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Encapsulates a task list.
 */
public class TaskList {
    private ArrayList<Task> listOfTasks;

    /**
     * Constructs a TaskList object. Initializes an empty task list.
     */
    public TaskList() {
        listOfTasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList Object. Initializes the task list with the contents of the list of tasks passed in.
     *
     * @param listOfTasks An ArrayList of tasks that the TaskList should contain upon instantiation.
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    /**
     * Adds a task to the task list and returns the message to be displayed by Duke.
     *
     * @param task The task to be added.
     * @return The message to be displayed by Duke.
     */
    public String addTask(Task task) {
        listOfTasks.add(task);
        String outputLine1 = String.format("Got it. I've added this task:\n%s\n", task);
        String outputLine2 = String.format("Now you have %s tasks in the list.", listOfTasks.size());
        String output = outputLine1 + outputLine2;
        return output;
    }

    public String findAndListTasks(String keyword) {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        int index = 1;
        final String REGEX = " ";
        for (Task task : listOfTasks) {
            String[] splittedTaskString = task.toString().split(REGEX);
            for (String s : splittedTaskString) {
                if (s.length() == 0) {
                    continue;
                }
                // Check whether final character of string is ')'. If so, remove the ')'
                if (s.charAt(s.length() - 1) == ')') {
                    s = s.substring(0, s.length() - 1);
                }
                if (s.equals(keyword)) {
                    sb.append(String.format("%s. %s\n", index, task));
                    break;
                }
            }
            index++;
        }

        // Remove the last \n for a nicer output
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * Marks a task within the task list as completed based on the index provided and returns the message to be
     * diplayed by Duke.
     *
     * @param index Index of the task to be marked as completed.
     * @return The message to be displayed by Duke.
     */
    public String markAsDone(int index) {
        assert index > 0: "Index of completed task should be at least 1.";
        Task taskToMark = listOfTasks.get(index - 1);
        taskToMark.setCompleted();
        String outputLine1 = "Nice! I've marked this task as done:\n";
        String output = outputLine1 + taskToMark;
        return output;
    }

    /**
     * Deletes a task within the task list based on the index provided and returns the message to be displayed by Duke.
     *
     * @param index Index of the task to be deleted.
     * @return The message to be displayed by Duke.
     */
    public String delete(int index) {
        assert index > 0: "Index of task to delete should be at least 1.";
        Task taskToRemove = listOfTasks.get(index - 1);
        listOfTasks.remove(index - 1);
        String outputLine1 = "Noted. I've removed this task: \n";
        String outputLine2 = taskToRemove.toString() + "\n";
        String outputLine3 = String.format("Now you have %s tasks in the list.", listOfTasks.size());
        String output = outputLine1 + outputLine2 + outputLine3;
        return output;
    }

    /**
     * Returns the number of elements within the task list.
     *
     * @return Number of elements within the task list.
     */
    public int getSize() {
        return listOfTasks.size();
    }

    /**
     * Saves the tasks within the task list in the storage file.
     *
     * @param file A File object encapsulating the storage file.
     * @throws IOException If the storage filepath exists but is a directory rather than a regular file,
     *  does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public void saveContents(File file) throws IOException {
        FileWriter fw = new FileWriter(file.getPath());
        for (Task task : listOfTasks) {
            fw.write(task.toStorageFormat() +"\n");
        }
        fw.close();
    }

    /**
     * Returns a String representation of all the tasks within the task list.
     *
     * @return A String representation of all the tasks within the task list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        int index = 1;
        for (Task task : listOfTasks) {
            sb.append(String.format("%s. %s\n", index, task));
            index++;
        }
        // Removed the last \n for nicer output
        return(sb.substring(0, sb.length() - 1));
    }
}
