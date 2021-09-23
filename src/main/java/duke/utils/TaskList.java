package duke.utils;

import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Dr-Octavius
 *
 * Class that represents Task List of Tasks
 *
 * @version 1.0
 */
public class TaskList {

    private List<Task> taskList;

    /**
     * Class Constructor that initiates a Task List from a given Storage File
     *
     * If storage file is empty, a new List is created instead
     *
     * @param storageFile provided storage file
     */
    public TaskList(File storageFile) {
        try {
            Scanner sc = new Scanner(storageFile);
            sc.useDelimiter(",");
            taskList = new ArrayList<>();
            while (sc.hasNext()) {
                String taskType = sc.next();
                String x1,x2,x3;
                if (taskType.equals(TASK_TYPE.T.name())) {
                    Task t = new Todo(Boolean.parseBoolean(sc.next()),sc.next());
                    add(t);
                    sc.nextLine();
                } else if (taskType.equals(TASK_TYPE.D.name())) {
                    Task t = new Deadline(Boolean.parseBoolean(sc.next()),sc.next(),sc.next());
                    add(t);
                    sc.nextLine();
                } else {
                    Task t = new Event(Boolean.parseBoolean(sc.next()),sc.next(),sc.next(),sc.next());
                    add(t);
                    sc.nextLine();
                }
            }
        } catch (FileNotFoundException e) {
            taskList = new ArrayList<>();
        }
    }

    /**
     * Class Constructor that initiates a new Task List
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds a Task into the Task List
     */
    public void add(Task t) {
        taskList.add(t);
    }

    /**
     * Removes a Task from the Task List
     */
    public void remove(int index) {
        taskList.remove(index);
    }

    /**
     * Retrieves the size of the task list
     *
     * @return size of the task list
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Retrieves the Task at the specified index in the Task List
     *
     * @param index target index within the Task List
     * @return Task at the specified index
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Retrieves a Task array containing the provided string
     *
     * @param txt target text to search for within text
     * @return Task array of tasks containing the provided text string
     */
    public TaskList find(String txt) {
        TaskList lst = new TaskList();
        for (Task t : taskList) {
            if (t.getDescription().contains(txt)) {
                lst.add(t);
            }
        }
        return lst;
    }
}
