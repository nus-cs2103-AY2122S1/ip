package pib.utility;

import pib.Pib;
import pib.enums.TaskType;
import pib.tasks.Deadline;
import pib.tasks.Event;
import pib.tasks.Task;
import pib.tasks.Todo;
import pib.pibexception.PibException;

import java.util.ArrayList;

import static pib.utility.Ui.DIVIDER;

/**
 * Class to store the tasks created
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Public constructor to create a new TaskList
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Prints to UI the entire list of Tasks and returns the string version.
     *
     * @throws PibException when the TaskList is empty
     * @return String representation of the list
     */
    public String viewList() throws PibException {
        String response = "";
        if (list.size() == 0) {
            throw new PibException("empty-list");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + "." + list.get(i).toString());
                response = response.concat((i + 1) + "." + list.get(i).toString() + "\n");
            }
            System.out.println(DIVIDER);
        }
        return response;
    }

    /**
     * Prints to UI how many tasks the TaskList contains and returns the string version.
     *
     * @return String representation of the list size
     */
    public String viewListSize() {
        System.out.println("There are " + list.size() + " task(s) in the list\n" + DIVIDER);
        return "There are " + list.size() + " task(s) in the list\n";
    }

    /**
     * Adds a new Task to the TaskList
     *
     * @param t type of task as specified by the enum TaskType
     * @param taskDetails task details (i.e. the remaining part of the command)
     * @throws PibException if the creation of new Tasks throws a PibException
     * @return String containing response to be printed to user
     */
    public String add(TaskType t, String taskDetails) throws PibException {
        Task newTask = null;
        String response = "";
        switch (t) {
        case TODO:
            newTask = Todo.createTodo(taskDetails, false);
            break;
        case EVENT:
            newTask = Event.createEvent(taskDetails, false);
            break;
        case DEADLINE:
            newTask = Deadline.createDeadline(taskDetails, false);
            break;
        default:
            break;
        }
        if (newTask != null) {
            list.add(newTask);
            Storage.saveData(this, Pib.DATA_FILE_PATH);
            response = response.concat(Ui.printAddSuccess(newTask.getDescription()));
            response = response.concat(Ui.printListSize(this));
        }
        return response;
    }

    /**
     * Adds tasks into the TaskList that were loaded from the saved data
     *
     * @param t Task to be added
     */
    public void addSavedData(Task t) {
        list.add(t);
    }

    /**
     * Delete task specified by the task number
     *
     * @param taskNum Index of task to be deleted (starting from 1)
     * @throws PibException when IndexOutOfBoundsException is thrown when user enters invalid task number/blank
     * @return String containing response to be printed to user
     */
    public String delete(int taskNum) throws PibException {
        try {
            String taskDesc = list.get(taskNum - 1).getDescription();
            list.remove(taskNum - 1);
            Storage.saveData(this, Pib.DATA_FILE_PATH);
            return Ui.printDeleteSuccess(taskDesc);
        } catch (IndexOutOfBoundsException e) {
            throw new PibException("ioob-exception");
        }
    }

    /**
     * Mark a task as specified by the task number
     *
     * @param taskNum Index of task to be marked as done (starting from 1)
     * @throws PibException when IndexOutOfBoundsException is thrown when user enters invalid task number/blank
     * @return String containing response to be printed to user
     */
    public String markAsDone(int taskNum) throws PibException {
        try {
            Storage.saveData(this, Pib.DATA_FILE_PATH);
            return list.get(taskNum - 1).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new PibException("ioob-exception");
        }
    }

    /**
     * Convert a TaskList to a string format to be saved inside a file
     *
     * @return String format of TaskList to be saved
     */
    public String convertListToSaveData() {
        StringBuilder data = new StringBuilder();
        for (Task t : list) {
            data.append(t.toDataString());
        }
        return data.toString();
    }

    /**
     * Returns the string representation of the task list containing tasks that contains the query word
     *
     * @param query String to query the task list
     * @return String representation of the filtered task list
     * @throws PibException when query is blank
     */
    public String find(String query) throws PibException {
        if (query.isBlank()) {
            throw new PibException("empty-query");
        }
        ArrayList<Task> filtered = new ArrayList<>();
        for (Task t : this.list) {
            if (t.getDescription().contains(query)) {
                filtered.add(t);
            }
        }
        if (filtered.size() > 0) {
            String response = Ui.printQueryFound(query).concat("\n") ;
            response = response.concat(new TaskList(filtered).viewList());
            return response;
        } else {
            return Ui.printQueryNotFound(query);
        }
    }
}
