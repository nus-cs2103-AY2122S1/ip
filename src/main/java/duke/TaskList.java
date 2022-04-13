package duke;

import duke.dukeException.DukeException;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Path;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains the task list which has the operations to add/delete tasks in the list
 *
 * @author Erwin Quek
 * @version CS2103 AY21/22 Sem 1
 */
public class TaskList {

    private final ArrayList<Task> TASKLIST = new ArrayList<>();

    /**
     * TaskList constructor.
     * @param path takes in the path of the file.
     * @throws FileNotFoundException throws an error if file not found.
     */
    public TaskList(Path path) throws FileNotFoundException, DukeException {
        File f = new File(String.valueOf(path));
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            // Read and add the task into the list
            String input = s.nextLine();
            String[] arrOfInputs = input.split("\\|");
            updateList(arrOfInputs);
        }
    }

    /**
     * A method to update task list.
     * @param arrOfInputs taking in an array of inputs
     */
    private void updateList(String[] arrOfInputs) throws DukeException {
        //Check for T, D, E and update in the list
        Task task = null;

        if (arrOfInputs[0].equals("T")) {
            String t = arrOfInputs[2];
            task = new ToDo(t);
        } else if (arrOfInputs[0].equals("D")) {
            String t = arrOfInputs[2] + " /by " + arrOfInputs[3];
            task = new Deadline(t);
        } else if (arrOfInputs[0].equals("E")) {
            String t = arrOfInputs[2] + " /at " + arrOfInputs[3];
            task = new Event(t);
        }
        this.TASKLIST.add(task);

        int currListLength = this.TASKLIST.size();
        //Check if its completed or not (0 or 1) and mark accordingly
        if (arrOfInputs[1].equals("1")) {
            this.TASKLIST.get(currListLength - 1).markAsDone();
        }
    }
    
    /**
     * Adds a new task to the task list. But prevents duplicate.
     * @param t takes in a task object
     */
    public void addNewTask(Task t) throws DukeException {
        String currTask = t.toString();
        for (Task oldT : TASKLIST) {
            if (oldT.toString().contains(currTask)) {
                throw new DukeException("\nDuplicate task! Please enter a unique task!!");
            }
        }
        this.TASKLIST.add(t);
    }

    /**
     * Returns an integer value of number of tasks in task list.
     * @return number of tasks in task list.
     */
    public int getSize() {
        return TASKLIST.size();
    }

    /**
     * Returns an ArrayList of Tasks.
     * @return An ArrayList containing all the task.
     */
    public ArrayList<Task> getTaskList() {
        return TASKLIST;
    }

    /**
     * Returns a task from the TaskList of the specified index.
     * @param i Index of task to retrieve.
     * @return A Task object.
     */
    public Task getTask(int i) {
        return TASKLIST.get(i);
    }

    /**
     * Deletes a task from a given index from the TaskList.
     * @param i Index of task to be deleted.
     */
    public void deleteGivenTask(int i) {
        TASKLIST.remove(i-1);
    }

    /**
     * Updates the storage file (duke.txt) with the given TaskList.
     * @param filePath Filepath to storage file.
     * @param taskList The tasklist object.
     * @throws DukeException Throws a Duke error.
     */
    public static void updateMemory(String filePath, TaskList taskList) throws DukeException {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Task t: taskList.getTaskList()) {
                bufferedWriter.write(t.getTaskInfo() + System.lineSeparator());
            }
            bufferedWriter.close();
        } catch (IOException e) {
            throw new DukeException("Could not write to file!");
        }
    }
}