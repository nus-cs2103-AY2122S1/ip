package duke;

import duke.dukeException.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tools.Storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains the task list e.g., it has operations to add/delete tasks in the list
 *
 * @author Erwin Quek
 * @version CS2103 AY21/22 Sem 1
 */
public class TaskList {

    private final ArrayList<Task> TASKLIST = new ArrayList<>();

    /**
     * TaskList constructor
     * @param path takes in the path of the file
     * @throws FileNotFoundException throws an error if file not found
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
     * Method to update task list.
     * @param arrOfInputs taking in an array of inputs
     */
    private void updateList(String[] arrOfInputs) throws DukeException {
        //Check for T, D, E and update in the list
        if (arrOfInputs[0].equals("T")) {
            String t = arrOfInputs[2];
            ToDo td = new ToDo(t);
            this.TASKLIST.add(td);
        } else if (arrOfInputs[0].equals("D")) {
            String t = arrOfInputs[2] + " /by " + arrOfInputs[3];
            Deadline d = new Deadline(t);
            this.TASKLIST.add(d);
        } else if (arrOfInputs[0].equals("E")) {
            String t = arrOfInputs[2] + " /at " + arrOfInputs[3];
            Event e = new Event(t);
            this.TASKLIST.add(e);
        }

        int currListLength = this.TASKLIST.size();
        //Check if its completed or not (0,1) and mark accordingly
        if (arrOfInputs[1].equals("1")) {
            this.TASKLIST.get(currListLength - 1).markAsDone();
        }
    }
    
    /**
     * Method to add a new task to the task list. But prevents duplicate.
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
     * Method to get the size of the task list.
     * @return size of task list
     */
    public int getSize() {
        return TASKLIST.size();
    }

    /**
     * Method to get the ArrayList which contains all the tasks.
     * @return an ArrayList containing all the task
     */
    public ArrayList<Task> getTaskList() {
        return TASKLIST;
    }

    /**
     * Method to get a task at a specified index.
     * @param i index of task to get
     * @return task object
     */
    public Task getTask(int i) {
        return TASKLIST.get(i);
    }

    /**
     * Method to delete task at given index.
     * @param i index of task to delete
     */
    public void deleteGivenTask(int i) {
        TASKLIST.remove(i-1);
    }

    /**
     * Method to update the storage file.
     * @param filePath filepath to storage file
     * @param taskList the tasklist object
     * @throws DukeException throws an error
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