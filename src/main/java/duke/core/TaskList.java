package duke.core;

import duke.gui.Ui;
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
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        listOfTasks.add(task);
        String outputLine1 = String.format("Got it. I've added this task:\n%s\n", task);
        String outputLine2 = String.format("Now you have %s tasks in the list.", listOfTasks.size());
        String output = outputLine1 + outputLine2;
        Ui.formatAndPrint(output);
    }

    /**
     * Lists the tasks within the task list.
     */
    public void listTasks() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        int index = 1;
        for (Task task : listOfTasks) {
            sb.append(String.format("%s. %s\n", index, task));
            index++;
        }
        // Removed the last \n for nicer output
        Ui.formatAndPrint(sb.substring(0, sb.length() - 1));
    }

    /**
     * Marks a task within the task list as completed based on the index provided.
     *
     * @param index Index of the task to be marked as completed.
     */
    public void markAsDone(int index) {
        Task taskToMark = listOfTasks.get(index - 1);
        taskToMark.setCompleted();
        String outputLine1 = "Nice! I've marked this task as done:\n";
        String output = outputLine1 + taskToMark;
        Ui.formatAndPrint(output);
    }

    /**
     * Deletes a task within the task list based on the index provided.
     *
     * @param index Index of the task to be deleted.
     */
    public void delete(int index) {
        Task taskToRemove = listOfTasks.get(index - 1);
        listOfTasks.remove(index - 1);
        String outputLine1 = "Noted. I've removed this task: \n";
        String outputLine2 = taskToRemove.toString() + "\n";
        String outputLine3 = String.format("Now you have %s tasks in the list.", listOfTasks.size());
        String output = outputLine1 + outputLine2 + outputLine3;
        Ui.formatAndPrint(output);
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
     */
    public void saveContents(File file) {
        try {
            FileWriter fw = new FileWriter(file.getPath());
            for (Task task : listOfTasks) {
                fw.write(task.toStorageFormat() +"\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
