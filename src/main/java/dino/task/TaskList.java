package dino.task;

import java.util.ArrayList;
import java.util.List;

import dino.exception.*;

public class TaskList {

    private final List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Adds a new task to the task list
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
        int size = taskList.size();
        System.out.println("Got it. I've added this task: \n"
                + "  " + taskList.get(size - 1));
        System.out.println("Now you have " + size +
                (size > 1 ? " tasks" : " task") + " in the list.");
    }

    /**
     * Prints out the task list in console, prefixed with index
     *
     * @throws EmptyListException if the current task list is empty
     */
    public void printTaskList() throws EmptyListException{
        if (taskList.isEmpty()) throw new EmptyListException();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }

    /**
     * Marks the task indicated by the given index as done
     *
     * @param index the index of the task as indicated by the task list
     * @throws InvalidIndexException if the index entered is out of bounds
     * @throws TaskAlreadyDoneException if the task is already marked as done
     */

    public void markTaskDone(int index) throws InvalidIndexException, TaskAlreadyDoneException {
        if (index > taskList.size()) {
            throw new InvalidIndexException();
        } else {
            Task t = taskList.get(index - 1);
            if (t.getStatus()) throw new TaskAlreadyDoneException();
            t.setDone();
            System.out.println("Nice! I've marked this task as done: \n" + t);
        }
    }

    /**
     * Deletes the task indicated by the index from the task list
     *
     * @param index the index of the task in the task list
     * @throws InvalidIndexException if the index entered is out of bounds
     */

    public void deleteTask(int index) throws InvalidIndexException {
        if (index > taskList.size()) {
            throw new InvalidIndexException();
        } else {
            Task t = taskList.remove(index - 1);
            System.out.println("Noted. I've removed this task: \n" + t);
            int size = taskList.size();
            System.out.println("Now you have " + size +
                    (size > 1 ? " tasks" : " task") + " in the list.");
        }
    }

    /**
     * Prints out the task(s) that contains the input keyword in description
     *
     * @param keyword the keyword for searching tasks
     * @throws TaskNotFoundException if there's no task in the task list that
     * matches the given keyword
     */

    public void searchKeyword(String keyword) throws TaskNotFoundException {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task: taskList) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            throw new TaskNotFoundException(keyword);
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + ". " + matchingTasks.get(i));
            }
        }
    }

}
