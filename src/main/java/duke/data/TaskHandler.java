package duke.data;

import java.util.ArrayList;

import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.ToDo;
import duke.ui.Ui;

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
     * Returns the tasklist.
     *
     * @return the tasklist.
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Returns the String representation of the tasklist.
     *
     * @return String representation of the tasklist.
     */
    public String printList() {
        if (list.size() != 0) {
            String toPrint = Ui.printList();
            for (int i = 0; i < list.size(); i++) {
                toPrint = toPrint.concat(Ui.indentation() + (i + 1) + ". " + list.get(i).toString() + "\n");
            }
            return toPrint;
        } else {
            return Ui.printEmptyList();
        }
    }

    /**
     * Returns the number of tasks in the tasklist.
     *
     * @return Number of tasks in the tasklist.
     */
    public String printNoOfTasks() {
        return Ui.printNoOfTasks(list.size());
    }

    /**
     * Marks the task at the given task index as done and returns a message to the user.
     *
     * @param taskNo Task index of the task.
     * @return Duke's response to the user.
     */
    public String markTaskAsDone(int taskNo) {
        String toPrint = Ui.markAsDone();
        Task task = list.get(taskNo - 1);
        task.markAsDone();
        toPrint = toPrint.concat(Ui.indentation() + task + "\n");
        return toPrint;
    }

    /**
     * Deletes the task at the given task index and returns a message to the user.
     *
     * @param taskNo Task index of the task.
     * @return Duke's response to the user.
     */
    public String deleteTask(int taskNo) {
        String toPrint = Ui.deleteTask();
        Task task = list.get(taskNo - 1);
        list.remove(taskNo - 1);
        toPrint = toPrint.concat(Ui.indentation() + task + "\n");
        return toPrint;
    }

    /**
     * Adds a To Do task to the tasklist and returns a message to the user.
     *
     * @param todo ToDo task to be added.
     * @return Duke's response to the user.
     */
    public String addToDo(ToDo todo) {
        list.add(todo);
        String toPrint = Ui.addTask();
        toPrint = toPrint.concat(Ui.indentation() + Ui.indentation() + todo + "\n");
        return toPrint;
    }

    /**
     * Adds a deadline task to the tasklist and returns a message to the user.
     *
     * @param deadline Deadline task to be added.
     * @return Duke's response to the user.
     */
    public String addDeadline(Deadline deadline) {
        list.add(deadline);
        String toPrint = Ui.addTask();
        toPrint = toPrint.concat(Ui.indentation() + Ui.indentation() + deadline + "\n");
        return toPrint;
    }

    /**
     * Adds an event task to the tasklist and returns a message to the user.
     *
     * @param event Event task to be added.
     * @return Duke's response to the user.
     */
    public String addEvent(Event event) {
        list.add(event);
        String toPrint = Ui.addTask();
        toPrint = toPrint.concat(Ui.indentation() + Ui.indentation() + event + "\n");
        return toPrint;
    }

    /**
     * Finds tasks with the keyword from tasklist and returns a message to the user.
     *
     * @param keyword Keyword user wants to find.
     * @return Duke's response to the user.
     */
    public String findTasks(String keyword) {
        ArrayList<String> tasks = new ArrayList<>();
        int j = 1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).toString().contains(keyword)) {
                tasks.add(Ui.indentation() + j + ". " + list.get(i).toString() + "\n");
                j++;
            }
        }
        if (tasks.size() == 0) {
            return Ui.noSuchTasksFound();
        } else {
            String toPrint = Ui.printFoundTasks();
            for (int k = 0; k < tasks.size(); k++) {
                toPrint = toPrint.concat(tasks.get(k));
            }
            return toPrint;
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
