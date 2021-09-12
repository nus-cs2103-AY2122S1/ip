package duke.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskComparator;
import duke.task.Todo;

/**
 * Encapsulates a checkList which can hold 100 tasks, display task information, add, delete, sort, mark
 * task as done. If TaskList is linked with a Storage, any changes to the TaskList will be saved.
 *
 * @author Clifford
 */
public class TaskList {
    private static final int TASKS_LIMIT = 100;
    private ArrayList<Task> tasks;
    private int currentIdx;
    private Storage tasksStorage;

    /**
     * Initialises TaskList without an associated storage.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.currentIdx = 0;
        this.tasksStorage = null;
    }

    /**
     * Initialises the TaskList and its associated storage to save the tasks in.
     *
     * @param tasksStorage the storage to store task in TaskList
     */
    public TaskList(Storage tasksStorage) {
        this.tasks = new ArrayList<>();
        this.currentIdx = 0;
        this.tasksStorage = tasksStorage;
    }

    /**
     * Requests from storage the save file and translates the file to Task that are added into list.
     *
     * @throws DukeException if there is an IOException when handling the file
     */
    public void retrieveTasks() throws DukeException {
        File taskData = this.tasksStorage.retrieveTasks();
        if (taskData != null && tasksStorage != null) {
            try {
                Scanner sc = new Scanner(taskData);
                while (sc.hasNext()) {
                    record(Task.createTaskFromText(sc.nextLine()));
                }
            } catch (IOException e) {
                throw new DukeException("File could not be read by the taskList. :(");
            }
        }
    }

    /**
     * Translates the existing tasks in the list to text and passes it to Storage to be saved.
     * If no Storage is linked to TaskList, this operation does not take place.
     *
     * @throws DukeException if there is an IOException when handling the file
     */
    public void saveTasks() throws DukeException {
        if (tasksStorage != null) {
            this.tasksStorage.saveTasks(this.convertTasksToText());
        }
    }

    /**
     * Adds Todo Task to the TaskList and return a String representation of the results of the operation.
     *
     * @param description user description of the Task
     * @return a String representation of the results of the operation.
     * @throws DukeException
     */
    public String recordTodo(String description) throws DukeException {
        return record(new Todo(description));
    }

    /**
     * Adds Deadline Task to the TaskList and return a String representation of the results of the operation.
     *
     * @param description user description of the Task
     * @param date date of the Task
     * @return a String representation of the results of the operation.
     * @throws DukeException
     */
    public String recordDeadline(String description, String date) throws DukeException {
        return record(new Deadline(description, date));
    }

    /**
     * Adds Event Task to the TaskList and return a String representation of the results of the operation.
     *
     * @param description  user description of Task
     * @param date date of the Task
     * @return a String representation of the results of the operation.
     * @throws DukeException
     */
    public String recordEvent(String description, String date) throws DukeException {
        return record(new Event(description, date));
    }

    /**
     * Converts an arraylist of Tasks to text representation to be saved in Storage file.
     *
     * @return text representation of tasks in Storage
     */
    public String convertTasksToText() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < currentIdx; i++) {
            if (i == 0) {
                sb.append(convertTaskToText(tasks.get(i)));
                continue;
            }
            sb.append(System.lineSeparator()).append(convertTaskToText(tasks.get(i)));
        }
        return sb.toString();
    }

    /**
     * Converts a Task to text representation to be saved in Storage file.
     *
     * @param task a Task object
     * @return text representation of Task in save file
     */
    public String convertTaskToText(Task task) {
        return task.convertToText();
    }

    /**
     * Adds task to the taskList if the list is not full. After adding a task,
     * the TaskList is saved into the save file. If TaskList is full, a DukeException is thrown.
     *
     * @param task the Task object to be added
     * @return String representation of the result.
     * @throws DukeException the TaskList is full
     */
    public String record(Task task) throws DukeException {
        final boolean isOverTaskLimitCapacity = currentIdx >= TASKS_LIMIT;
        try {
            if (isOverTaskLimitCapacity) {
                throw new IndexOutOfBoundsException(
                        String.format("I can only record maximum of %d tasks.:(\n"
                                + "Maybe remove your expired or done task?", TASKS_LIMIT));
            }
            tasks.add(task);
            currentIdx++;
            saveTasks();
            return "Noted task down! I've added this task:\n  " + task.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I cannot remember so many things!");
        }
    }

    /**
     * Enumerates the items recorded in a list to be displayed to user.
     *
     * @return String representation of list of items in TaskList
     */
    public String printTasks() {
        if (currentIdx == 0) {
            return "--- List is Empty ---";
        }
        StringBuilder sb = new StringBuilder("--- Start of List ---\n");
        for (int i = 0; i < currentIdx; i++) {
            sb = sb.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        sb = sb.append("--- End of List ---");
        return sb.toString();
    }

    /**
     * Chooses and marks a task in the list to be marked as done. If the task is already marked
     * as done, the task will continue to be marked as done.
     *
     * @param taskId the id of the task within the list
     * @return a String that tells user whether the task marked was previously unmarked or already marked
     * @throws DukeException if the selected task to be marked does not exist
     */
    public String markAsDone(int taskId) throws DukeException {
        try {
            final boolean isOutOfTaskIdRange = taskId <= 0 || taskId > currentIdx;
            if (isOutOfTaskIdRange) {
                throw new IllegalArgumentException(
                        String.format("I'm a smart dragon!"
                                + "There is no taskId of %1$d as there are %2$d recorded task(s)!",
                                taskId,
                                currentIdx));
            }
            Task task = tasks.get(taskId - 1);
            boolean isDone = task.isDone();
            String result = task.markAsDone();
            // Saves task only if there is a change in Done Status
            if (!isDone) {
                saveTasks();
            }
            return result;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new DukeException("No such task exists!");
        }
    }

    /**
     * Removes a given task from the list.
     *
     * @param taskId the id of the task starting from 1 for the first task
     * @return a String that confirms the success of the delete operation
     * @throws DukeException when a task that does not exist is selected for deletion
     */
    public String deleteTask(int taskId) throws DukeException {
        final boolean isOutOfTaskIdRange = taskId <= 0 || taskId > currentIdx;
        try {
            if (isOutOfTaskIdRange) {
                throw new IllegalArgumentException(
                        String.format("I'm a smart dragon!"
                                + "There is no taskId of %1$d as there are %2$d recorded task(s)!",
                                taskId,
                                currentIdx));
            }
            Task deletedTask = tasks.get(taskId - 1);
            tasks.remove(taskId - 1);
            currentIdx--;
            saveTasks();
            return String.format("Noted I've removed this task:\n  %s\nNow you have %d task(s) in the list",
                    deletedTask.toString(),
                    currentIdx);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new DukeException("No such task exists to be deleted!");
        }
    }

    /**
     * Finds all task in TaskList that contains the word.
     *
     * @param searchWord the word that filters the search results
     * @return a String representation of the list of task that contains the searchWord
     * @throws DukeException if the number of tasks exceed the task limit
     */
    public String findTasks(String searchWord) throws DukeException {
        TaskList matchList = new TaskList();
        for (int i = 0; i < currentIdx; i++) {
            // Checks whether the searchWord is in the ith Task (1-indexed).
            if (tasks.get(i).toString().indexOf(searchWord) != -1) {
                matchList.record(tasks.get(i));
            }
        }
        return matchList.printTasks();
    }

    /**
     * Sorts permanently the taskList based on chronological order followed by
     * description lexicographical order and ranks Todo Task to the bottom of the list.
     *
     * @return a String showing that sorting is done and the sorted TaskList.
     */
    public String sortTasks() throws DukeException {
        Collections.sort(tasks, new TaskComparator());
        saveTasks();
        return "I have sorted your tasks chronologically! ^_^\n\n" + printTasks();
    }
}

