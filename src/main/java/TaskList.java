import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    TaskList() {
        taskList = new ArrayList<>();
    }

    TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Add the task entered by the user into the list.
     *
     * @param task task input by the user
     */
    private void addTask(Task task) {
        taskList.add(task);
        Duke.printMessage("Got it. I've added this task:",
                task.toString(),
                String.format("Now you have %o task(s).", taskList.size()));
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
            dateTimeErrorMessage();
        }
    }

    public void addEvent(String message) {
        try {
            String[] details = message.split(Keyword.EVENTS.getSeparator());
            addEvent(details[0].substring(Keyword.EVENTS.length() + 1), details[1]);
        } catch (IndexOutOfBoundsException e) {
            eventErrorMessage();
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
            dateTimeErrorMessage();
        }
    }

    public void addDeadline(String message) {
        try {
            String[] details = message.split(Keyword.DEADLINE.getSeparator());
            addDeadline(details[0].substring(Keyword.DEADLINE.length() + 1), details[1]);
        } catch (IndexOutOfBoundsException e) {
            deadlineErrorMessage();
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
            todoErrorMessage();
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
            Duke.printMessage("Nice! I've did mark this task as done:", task.toString());
        } else
            Duke.printMessage("Ugh! This task was already done:", task.toString());
    }

    public void markDone(String message) {
        try {
            markDone(Integer.parseInt(message.substring(Keyword.DONE.length() + 1)));
        } catch (NumberFormatException e) {
            doneErrorMessage();
        } catch (IndexOutOfBoundsException e) {
            doneIndexErrorMessage();
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
        Duke.printMessage("Noted. I've removed this task:",
                task.toString(),
                String.format("Now you have %o task(s).", taskList.size()));
    }

    public void deleteTask(String message) {
        try {
            deleteTask(Integer.parseInt(message.substring(Keyword.DELETE.length() + 1)));
        } catch (NumberFormatException e) {
            deleteErrorMessage();
        } catch (IndexOutOfBoundsException e) {
            deleteIndexErrorMessage();
        }
    }

    private void saveTaskList() {
        Storage.exportTask(this.taskList);
    }

    public List<Task> getTaskList() {
        return taskList;
    }


    /**
     * Prints out error message if done message does not contains number.
     */
    private void doneErrorMessage() {
        Duke.printMessage("Ugh! The command should be in this format:",
                "done <number>");
    }

    /**
     * Prints out error message if done message is out of range.
     */
    private void doneIndexErrorMessage() {
        Duke.printMessage("Ugh! The command should be in this format:",
                "done <number>",
                "Note: number is based on the number from command 'list'");
    }

    /**
     * Prints out error message if todo message does not contains description.
     */
    private void todoErrorMessage() {
        Duke.printMessage("Ugh! The command should be in this format:",
                "todo <description>");
    }

    /**
     * Prints out error message if deadline message does not contains /by.
     */
    private void deadlineErrorMessage() {
        Duke.printMessage("Ugh! The command should be in this format:",
                "deadline <description> /by <date/time>");
    }

    /**
     * Prints out error message if delete message does not contains number.
     */
    private void deleteErrorMessage() {
        Duke.printMessage("Ugh! The command should be in this format:",
                "delete <number>");
    }

    /**
     * Prints out error message if delete message is out of range.
     */
    private void deleteIndexErrorMessage() {
        Duke.printMessage("Ugh! The command should be in this format:",
                "delete <number>",
                "Note: number is based on the number from command 'list'");
    }

    /**
     * Prints out error message if event message does not contains /at.
     */
    private void eventErrorMessage() {
        Duke.printMessage("Ugh! The command should be in this format:",
                "event <description> /at <date/time>");
    }

    /**
     * Prints out error message if dateTime format is invalid.
     */
    private void dateTimeErrorMessage() {
        Duke.printMessage("Date/Time format is wrong. Ensure that it is in the this format:",
                "dd/mm/yy hhmm (24hrs format)");
    }
}
