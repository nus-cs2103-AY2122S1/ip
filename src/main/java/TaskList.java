import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;
    private Ui ui;
    private Storage storage;

    TaskList(List<Task> taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Add the task entered by the user into the list.
     *
     * @param task task input by the user
     */
    private void addTask(Task task) {
        taskList.add(task);
        ui.addMessage(task, taskList.size());
    }

    /**
     * Adds a new Event to the task list.
     *
     * @param s Description of the task.
     * @param dateTime Date and time of the event.
     */
    private void addEvent(String s, String dateTime) {
        try {
            addTask(new Events(s, dateTime));
            saveTaskList();
        } catch (ParseException e) {
            ui.dateTimeErrorMessage();
        }
    }

    public void addEvent(String message) {
        try {
            String[] details = message.split(Keyword.EVENTS.getSeparator());
            addEvent(details[0].substring(Keyword.EVENTS.length() + 1), details[1]);
        } catch (IndexOutOfBoundsException e) {
            ui.eventErrorMessage();
        }
    }

    /**
     * Adds a new deadline to the task list.
     *
     * @param s Description of the task
     * @param dateTime Deadline of the task
     */
    private void addDeadline(String s, String dateTime) {
        try {
            addTask(new Deadlines(s, dateTime));
            saveTaskList();
        } catch (ParseException e) {
            ui.dateTimeErrorMessage();
        }
    }

    public void addDeadline(String message) {
        try {
            String[] details = message.split(Keyword.DEADLINE.getSeparator());
            addDeadline(details[0].substring(Keyword.DEADLINE.length() + 1), details[1]);
        } catch (IndexOutOfBoundsException e) {
            ui.deadlineErrorMessage();
        }
    }

    /**
     * Add a new todo to the task list.
     *
     * @param message Description of the task.
     */
    public void addTodo(String message) {
        try {
            addTask(new ToDos(message.substring(Keyword.TODOS.length() + 1)));
            saveTaskList();
        } catch (IndexOutOfBoundsException e) {
            ui.todoErrorMessage();
        }
    }

    /**
     * Mark the nth task as done.
     *
     * @param n the task to be mark as done.
     */
    private void markDone(int n) {
        Task task = taskList.get(n - 1);
        boolean success = task.markDone();
        if (success) {
            saveTaskList();
            ui.doneSuccessMessage(task);
        } else
            ui.doneFailedMessage(task);
    }

    public void markDone(String message) {
        try {
            markDone(Integer.parseInt(message.substring(Keyword.DONE.length() + 1)));
        } catch (NumberFormatException e) {
            ui.doneErrorMessage();
        } catch (IndexOutOfBoundsException e) {
            ui.doneIndexErrorMessage();
        }
    }

    /**
     * Delete the nth task from the task list.
     *
     * @param n the task to be deleted.
     */
    private void deleteTask(int n) {
        Task task = taskList.remove(n - 1);
        saveTaskList();
        ui.deleteMessage(task, taskList.size());
    }

    public void deleteTask(String message) {
        try {
            deleteTask(Integer.parseInt(message.substring(Keyword.DELETE.length() + 1)));
        } catch (NumberFormatException e) {
            ui.deleteErrorMessage();
        } catch (IndexOutOfBoundsException e) {
            ui.deleteIndexErrorMessage();
        }
    }

    private void saveTaskList() {
        storage.exportTask(this.taskList);
    }

    public List<Task> getTaskList() {
        return taskList;
    }
}
