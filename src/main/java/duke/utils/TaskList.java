package duke.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.TASKTYPE;
import duke.task.Task;
import duke.task.Todo;

public class TaskList {
    // Level-6 -> A-Collections: Task List
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
                if (taskType.equals(TASKTYPE.T.name())) {
                    Task t = new Todo(Boolean.parseBoolean(sc.next()), sc.next());
                    add(t);
                    sc.nextLine();
                } else if (taskType.equals(TASKTYPE.D.name())) {
                    Task t = new Deadline(Boolean.parseBoolean(sc.next()), sc.next(), sc.next());
                    add(t);
                    sc.nextLine();
                } else {
                    Task t = new Event(Boolean.parseBoolean(sc.next()), sc.next(), sc.next(), sc.next());
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

    private TaskList(List<Task> input) {
        taskList = new ArrayList<>(input);
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

    /**
     * Creates a copy of a sorted Task List without amending original Task List
     *
     * @return Copy of a Sorted Task List
     */
    public TaskList safeSort() {
        TaskList copy = new TaskList(taskList);
        copy.sort();
        return copy;
    }

    private void sort() {
        taskList.sort(new TaskListComparator());
    }

    private class TaskListComparator implements Comparator<Task> {

        @Override
        public int compare(Task o1, Task o2) {
            if (o1.getType() == TASKTYPE.T) {
                if (o2.getType() == TASKTYPE.T) {
                    return 0;
                } else {
                    return -1;
                }
            } else if (o1.getType() == TASKTYPE.D) {
                if (o2.getType() == TASKTYPE.T) {
                    return 1;
                } else if (o2.getType() == TASKTYPE.D) {
                    return o1.getDate().compareTo(o2.getDate());
                } else {
                    if (o1.getDate().compareTo(o2.getDate()) == 0) {
                        return -1;
                    } else {
                        return o1.getDate().compareTo(o2.getDate());
                    }
                }
            } else {
                if (o2.getType() == TASKTYPE.T) {
                    return 1;
                } else if (o2.getType() == TASKTYPE.D) {
                    if (o1.getDate().compareTo(o2.getDate()) == 0) {
                        return 1;
                    } else {
                        return o1.getDate().compareTo(o2.getDate());
                    }
                } else {
                    if (o1.getDate().compareTo(o2.getDate()) == 0) {
                        return o1.getTime().compareTo(o2.getTime());
                    } else {
                        return o1.getDate().compareTo(o2.getDate());
                    }
                }
            }
        }
    }
}
