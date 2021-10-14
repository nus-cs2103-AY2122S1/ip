package pib.utility;

import static pib.utility.Messages.DIVIDER;

import java.util.ArrayList;

import pib.Pib;
import pib.enums.TaskType;
import pib.pibexception.PibException;
import pib.tasks.Deadline;
import pib.tasks.Event;
import pib.tasks.Task;
import pib.tasks.Todo;

/**
 * Class to store the tasks created
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs a new TaskList
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Constructs a new TaskList from an existing arraylist
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Prints to UI the entire list of Tasks and returns the string version.
     *
     * @return String representation of the list
     * @throws PibException when the TaskList is empty
     */
    public String viewList() throws PibException {
        if (list.size() == 0) {
            throw new PibException("empty-list");
        }

        String response = "";
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i).toString());
            response = response.concat((i + 1) + "." + list.get(i).toString() + "\n");
        }
        System.out.println(DIVIDER);
        assert !response.isBlank();
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
     * @return String containing response to be printed to user
     * @throws PibException if the creation of new Tasks throws a PibException
     */
    public String add(TaskType t, String taskDetails) throws PibException {
        assert t != null;
        assert taskDetails != null;
        assert !taskDetails.isBlank();
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
        assert newTask != null;
        list.add(newTask);
        Storage.saveData(this, Pib.DATA_FILE_PATH);
        response = response.concat(Ui.printAddSuccess(newTask.getDescription()));
        response = response.concat(Ui.printListSize(this));
        assert !response.isBlank();
        return response;
    }

    /**
     * Adds tasks into the TaskList that were loaded from the saved data
     *
     * @param t Task to be added
     */
    public void addSavedData(Task t) {
        assert t != null;
        list.add(t);
    }

    /**
     * Deletes task specified by the task number
     *
     * @param taskNum Index of task to be deleted (starting from 1)
     * @return String containing response to be printed to user
     * @throws PibException when IndexOutOfBoundsException is thrown when user enters invalid task number/blank
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
     * Marks a task as specified by the task number
     *
     * @param taskNum Index of task to be marked as done (starting from 1)
     * @return String containing response to be printed to user
     * @throws PibException when IndexOutOfBoundsException is thrown when user enters invalid task number/blank
     */
    public String markAsDone(int taskNum) throws PibException {
        try {
            String response = list.get(taskNum - 1).markAsDone();
            Storage.saveData(this, Pib.DATA_FILE_PATH);
            return response;
        } catch (IndexOutOfBoundsException e) {
            throw new PibException("ioob-exception");
        }
    }

    /**
     * Edits the task's description, date or time
     *
     * @param taskDetails string that includes the task number to edit, which part of the task to edit and the new value
     * @return string response if the update is successful
     * @throws PibException when the command is blank, formatted wrongly or an invalid task number is provided
     */
    public String edit(String taskDetails) throws PibException {
        try {
            if (taskDetails.isBlank()) {
                throw new PibException("edit-wrong-format");
            }
            taskDetails = taskDetails.trim();
            int slash = taskDetails.indexOf("/");

            Task t = list.get(Integer.parseInt(taskDetails.substring(0, slash).trim()) - 1);
            char partToEdit = taskDetails.charAt(slash + 1);
            String newValue = taskDetails.substring(slash + 3);
            String response = editHelper(t, partToEdit, newValue);
            Storage.saveData(this, Pib.DATA_FILE_PATH);
            return response;
        } catch (IndexOutOfBoundsException e) {
            throw new PibException("edit-wrong-format");
        } catch (NumberFormatException e) {
            throw new PibException("ioob-exception");
        }
    }

    private String editHelper(Task t, char partToEdit, String newValue) throws PibException {
        String dateOrTime;
        switch (partToEdit) {
        case 'i':
            return t.editDescription(newValue);
        case 'd':
            dateOrTime = "date";
            break;
        case 't':
            dateOrTime = "time";
            break;
        default:
            throw new PibException("invalid-update-part");
        }

        if (t instanceof Todo) {
            throw new PibException("no-todo-date");
        } else if (t instanceof Deadline) {
            Deadline deadline = (Deadline) t;
            return deadline.editDateTime(dateOrTime, newValue);
        } else {
            assert t instanceof Event;
            Event event = (Event) t;
            return event.editDateTime(dateOrTime, newValue);
        }
    }

    /**
     * Converts a TaskList to a string format to be saved inside a file
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
        if (filtered.size() == 0) {
            return Ui.printQueryNotFound(query);
        }
        return Ui.printQueryFound(query).concat("\n").concat(new TaskList(filtered).viewList());
    }
}
