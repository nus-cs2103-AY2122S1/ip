package duke.core;

import duke.task.Deadline;
import duke.task.Event;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> tasks;
    private static final int tasksLimit = 100;
    private int currentIdx;
    private Storage tasksStorage;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.currentIdx = 0;
        this.tasksStorage = null;
    }

    public TaskList(Storage tasksStorage) {
        this.tasks = new ArrayList<>();
        this.currentIdx = 0;
        this.tasksStorage = tasksStorage;
    }

    public void retrieveTasks() throws DukeException {
        File taskData = this.tasksStorage.retrieveTasks();
        if(taskData == null || tasksStorage == null) {
            return ;
        }
        try {
            Scanner sc = new Scanner(taskData);
            while(sc.hasNext()) {
                record(Task.createTaskFromText(sc.nextLine()));
            }
        } catch (IOException e) {
            throw new DukeException("File could not be read by the taskList. :(");
        }

    }

    public void saveTasks() throws DukeException {
        if(tasksStorage == null) {
            return ;
        }
        this.tasksStorage.saveTasks(this.convertTasksToText());
    }

    public String recordTodo(String description) throws DukeException {
        return record(new Todo(description));
    }

    public String recordDeadline(String description, String date) throws DukeException {
        return record(new Deadline(description, date));
    }

    public String recordEvent(String description, String date) throws DukeException {
        return record(new Event(description, date));
    }

    public String convertTasksToText() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < currentIdx; i++) {
            if(i == 0) {
                sb.append(convertTaskToText(tasks.get(i)));
                continue;
            }
            sb.append(System.lineSeparator()).append(convertTaskToText(tasks.get(i)));
        }
        return sb.toString();
    }

    public String convertTaskToText(Task task) {
        return task.convertToText();
    }

    public String record(Task task) throws DukeException {
        try {
            if(currentIdx >= tasksLimit) {
                throw new IndexOutOfBoundsException(
                        String.format("ChatBot can only record maximum of %d responses.", tasksLimit));
            }
            tasks.add(task);
            currentIdx++;
            saveTasks();
            return "Noted task down! I've added this task:\n  " + task.toString();
        } catch(IndexOutOfBoundsException e) {
            throw new DukeException("I cannot remember so many things!");
        }
    }

    /**
     * formats the items recorded in a list to be shown to user.
     *
     * @return list representation of items recorded by user
     */
    public String printTasks() {
        if(currentIdx == 0) {
            return "--- List is Empty ---";
        }
        StringBuilder sb = new StringBuilder("--- Start of List ---\n");
        for(int i = 0; i < currentIdx; i++) {
            sb = sb.append(Integer.toString(i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        sb = sb.append("--- End of List ---");
        return sb.toString();
    }

    /**
     * Allows users to choose a task in the list to be crossed off.
     *
     * @param taskId the id of the task starting from 1 for the first task
     * @return a String that confirms the success or failure of the mark as done operation.
     * @throws IllegalArgumentException when a task that does not exist is selected to be mark as done
     */
    public String markAsDone(int taskId) throws DukeException {
        try {
            if(taskId <= 0 || taskId > currentIdx) {
                throw new IllegalArgumentException(
                        String.format("taskId of %1$d invalid as there are %2$d recorded task(s)", taskId, currentIdx));
            }
            Task task = tasks.get(taskId - 1);
            boolean isChanged = task.markAsDone();
            if(!isChanged) {
                return "duke.task.Task is already marked as done";
            }
            saveTasks();
            return "Nice! I've marked this task as done:\n  " + task.toString();
        } catch (IllegalArgumentException e) {
            throw new DukeException("No such task exists to be marked as done!");
        }
    }

    /**
     * Allows users to remove items from the list.
     *
     * @param taskId the id of the task starting from 1 for the first task
     * @return a String that confirms the success or failure of the delete operation.
     * @throws IllegalArgumentException when a task does not exist is selected to be deleted
     */
    public String deleteTask(int taskId) throws DukeException {
        try {
            if(taskId <= 0 || taskId > currentIdx) {
                throw new IllegalArgumentException(
                        String.format("taskId of %1$d invalid as there are %2$d recorded task(s)", taskId, currentIdx));
            }
            Task deletedTask = tasks.get(taskId - 1);
            tasks.remove(taskId - 1);
            currentIdx--;
            saveTasks();
            return String.format("Noted I've removed this task:\n  %s\nNow you have %d task(s) in the list",
                    deletedTask.toString(),
                    currentIdx);
        } catch (IllegalArgumentException e) {
            throw new DukeException("No such task exists to be deleted!");
        }
    }
}
