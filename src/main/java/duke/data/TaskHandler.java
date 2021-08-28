package duke.data;

import duke.data.task.*;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Class that contains the tasklist and handles all tasklist operations.
 *
 * @author Won Ye Ji
 */
public class TaskHandler {
    private ArrayList<Task> list;

    /**
     * Constructor for the TaskHandler class.
     *
     * @param list Tasklist.
     */
    public TaskHandler(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Prints the tasklist.
     */
    public void printList() {
        if (list.size() != 0) {
            Ui.printList();
            for (int i = 0; i < list.size(); i++) {
                System.out.println(Ui.indentation() + (i + 1) + ". " + list.get(i).toString());
            }
        } else {
            Ui.printEmptyList();
        }
    }

    /**
     * Prints the number of tasks in the tasklist.
     */
    public void printNoOfTasks() {
        Ui.printNoOfTasks(list.size());
    }

    /**
     * Marks the task at the given task index as done.
     *
     * @param taskNo Task index of the task.
     */
    public void markTaskAsDone(int taskNo) {
        Ui.markAsDone();
        Task task = list.get(taskNo - 1);
        task.markAsDone();
        System.out.println(Ui.indentation() + task);
    }

    /**
     * Deletes the task at the given task index.
     *
     * @param taskNo Task index of the task.
     */
    public void deleteTask(int taskNo) {
        Ui.deleteTask();
        Task task = list.get(taskNo - 1);
        list.remove(taskNo - 1);
        System.out.println(Ui.indentation() + task);
    }

    /**
     * Adds a To Do task to the tasklist.
     *
     * @param todo ToDo task to be added.
     */
    public void addToDo(ToDo todo) {
        list.add(todo);
        Ui.addTask();
        System.out.println(Ui.indentation() + Ui.indentation() + todo);
    }

    /**
     * Adds a deadline task to the tasklist.
     *
     * @param deadline Deadline task to be added.
     */
    public void addDeadline(Deadline deadline) {
        list.add(deadline);
        Ui.addTask();
        System.out.println(Ui.indentation() + Ui.indentation() + deadline);
    }

    /**
     * Adds an event task to the tasklist.
     *
     * @param event Event task to be added.
     */
    public void addEvent(Event event) {
        list.add(event);
        Ui.addTask();
        System.out.println(Ui.indentation() + Ui.indentation() + event);
    }

    /**
     * Finds tasks with the keyword from tasklist.
     *
     * @param keyword Keyword user wants to find.
     */
    public void findTasks(String keyword) {
        ArrayList<String> tasks = new ArrayList<>();
        int j = 1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).toString().contains(keyword)) {
                tasks.add(Ui.indentation() + j + ". " + list.get(i).toString());
                j++;
            }
        }
        if (tasks.size() == 0) {
            Ui.noSuchTasksFound();
        } else {
            Ui.printFoundTasks();
            for (int k = 0; k < tasks.size(); k++) {
                System.out.println(tasks.get(k));
            }
        }
    }

    /**
     * Formats the tasklist to be put into storage.
     *
     * @return A string of the formatted tasklist.
     */
    public String formatTasksToSave() {
        String[] tasksToSave = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            tasksToSave[i] = list.get(i).toSave();
        }
        return String.join("\n", tasksToSave);
    }
}