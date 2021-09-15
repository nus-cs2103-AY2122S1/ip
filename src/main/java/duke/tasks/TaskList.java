package duke.tasks;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.EmptyListException;
import duke.exception.InvalidDateTimeException;
import duke.exception.InvalidIndexException;
import duke.ui.Ui;

/**
 * Represents the list of tasks.
 */
public class TaskList {

    /** The list of Task. */
    private final ArrayList<Task> taskList;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Overloaded constructor for TaskList that accepts a task list as an ArrayList.
     *
     * @param taskList list of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Adds a task to the list of Tasks with a confirmation message printed out after.
     *
     * @param task The duke.tasks.Task to be added to the list of Tasks.
     */
    public String addToList(Task task) {
        taskList.add(task);

        String message = "Got it. I've added this task:\n" + task;
        String taskGrammar = (taskList.size() == 1) ? " task" : " tasks";
        return message + "\nNow you have " + taskList.size() + taskGrammar + " in the list.";
    }

    /**
     * Prints out the full contents of the list of Tasks.
     *
     * @throws EmptyListException if the list of Tasks is empty and there is nothing to be printed.
     */
    public String displayList() throws EmptyListException {
        if (taskList.size() == 0) {
            throw new EmptyListException();
        }
        return Ui.printList(taskList);
    }

    /**
     * Marks a current Task in the list of Tasks as Done.
     *
     * @param taskIndex The index of the duke.tasks.Task in the list of Tasks to be marked as Done.
     * @throws EmptyListException If the list of Tasks is empty and there is nothing to be marked as Done.
     * @throws InvalidIndexException If the index of the Task provided is out of range of the current list of Tasks.
     */
    public String markDone(int taskIndex) throws EmptyListException, InvalidIndexException {
        int taskListSize = taskList.size();

        if (taskListSize == 0) {
            throw new EmptyListException();
        } else if (taskIndex < 0 || taskIndex >= taskListSize) {
            throw new InvalidIndexException(1, taskListSize, taskIndex + 1);
        }

        Task task = taskList.get(taskIndex);
        if (task.isDone()) {
            return task + " has already been marked as done!";
        }
        task.markAsDone();

        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Adds a Deadline to the list of Tasks.
     *
     * @param deadline The Deadline to be added to the list of Tasks which is the whole input barring the command.
     * @throws InvalidDateTimeException If the date/time format in deadline command is incorrect.
     */
    public String addDeadline(ArrayList<String> deadline) throws InvalidDateTimeException {
        String description = deadline.get(0);
        String by = deadline.get(1);

        LocalDateTime finalBy;

        try {
            // checks if the formats of the input date and time are correct
            finalBy = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }

        Deadline d = new Deadline(description, finalBy);
        return addToList(d);
    }

    /**
     * Adds a Todo to the list of Tasks.
     *
     * @param todo Todo to be added to the list of Tasks.
     */
    public String addTodo(String todo) {
        Todo tempTask = new Todo(todo);
        return addToList(tempTask);
    }

    /**
     * Adds an Event to the list of Tasks.
     *
     * @param event The Event to be added to the list of Tasks, which is the entire user input barring the command.
     * @throws InvalidDateTimeException If the date/time format in the event command is incorrect.
     */
    public String addEvent(ArrayList<String> event) throws InvalidDateTimeException {
        String description = event.get(0);
        String date = event.get(1);
        String startTime = event.get(2).trim();
        String endTime = event.get(3).trim();

        LocalDate finalDate;
        LocalTime finalStartTime;
        LocalTime finalEndTime;

        try {
            // checks if the formats of the input date and time are correct
            finalDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            finalStartTime = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HHmm"));
            finalEndTime = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HHmm"));
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }

        Event e = new Event(description, finalDate, finalStartTime, finalEndTime);

        return addToList(e);
    }

    /**
     * Deletes a Task from the list of Tasks.
     *
     * @param taskIndex Index of the Task to be deleted.
     * @throws EmptyListException If the list of Tasks is empty and there is nothing to be deleted.
     * @throws InvalidIndexException If the index of the Task provided is out of range of the current list of Tasks.
     */
    public String deleteTask(int taskIndex) throws EmptyListException, InvalidIndexException {
        int taskListSize = taskList.size();

        if (taskListSize == 0) {
            throw new EmptyListException();
        } else if (taskIndex < 0 || taskIndex >= taskListSize) {
            throw new InvalidIndexException(1, taskListSize, taskIndex + 1);
        }

        Task task = taskList.remove(taskIndex);

        String message = "Noted. I've removed this task:\n" + task;

        String taskGrammar = (taskList.size() == 1) ? " task" : " tasks";

        return message + "\nNow you have " + taskList.size() + taskGrammar + " in the list.";
    }

    /**
     * Finds all Tasks that matches the query and is case insensitive.
     *
     * @param query case insensitive query to find from Task list.
     * @return ArrayList of Tasks filled with Tasks that match the query.
     */
    public ArrayList<Task> findTask(String query) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        // regex that matches any string to the query and is case insensitive
        String regex = "(?i)(.*)(" + query + ")(.*)";

        for (Task task : taskList) {
            String taskString = task.toString();
            if (taskString.matches(regex)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }

    /**
     * Adds an expense to the task.
     *
     * @param taskIndex index of task for expense to be added to.
     * @param purpose purpose of expenditure.
     * @param amount amount spent.
     * @return appropriate message for adding an expense to a task.
     * @throws EmptyListException if the task list is empty.
     * @throws InvalidIndexException if the index of task given is out of bounds.
     */
    public String addExpense(int taskIndex, String purpose, float amount) throws EmptyListException,
            InvalidIndexException {
        int taskListSize = taskList.size();

        if (taskListSize == 0) {
            throw new EmptyListException();
        } else if (taskIndex < 0 || taskIndex >= taskListSize) {
            throw new InvalidIndexException(1, taskListSize, taskIndex + 1);
        }
        Task task = taskList.get(taskIndex);
        DecimalFormat df = new DecimalFormat("0.00");
        task.addExpenseToTask(purpose, amount);
        return "I added " + purpose + ": $" + df.format(amount) + " to " + task + "!";
    }

    /**
     * Deletes an expense from a task in the task list.
     *
     * @param taskIndex index of task for expense to be deleted from.
     * @param deleteIndex index of expense to be deleted from task.
     * @return an appropriate message for the deletion of an expense.
     * @throws EmptyListException if the task list is empty.
     * @throws InvalidIndexException if the index of task given is out of bounds.
     */
    public String deleteExpense(int taskIndex, int deleteIndex) throws EmptyListException,
            InvalidIndexException {
        int taskListSize = taskList.size();

        if (taskListSize == 0) {
            throw new EmptyListException();
        } else if (taskIndex < 0 || taskIndex >= taskListSize) {
            throw new InvalidIndexException(1, taskListSize, taskIndex + 1);
        }
        Task task = taskList.get(taskIndex);
        try {
            String expensePurpose = task.deleteExpenseFromTask(deleteIndex);
            if (expensePurpose == "") {
                return "I couldn't find the expense you were looking for!";
            }
            return "I deleted:\n" + expensePurpose + "\nfrom " + task + "!";
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Shows the expenses of a single task.
     *
     * @param taskIndex index of task to show expenses of.
     * @return all expenses associated with that task.
     * @throws EmptyListException if the task list is empty.
     * @throws InvalidIndexException if the index of task is out of bounds.
     */
    public String showExpense(int taskIndex) throws EmptyListException, InvalidIndexException {
        int taskListSize = taskList.size();

        if (taskListSize == 0) {
            throw new EmptyListException();
        } else if (taskIndex < 0 || taskIndex >= taskListSize) {
            throw new InvalidIndexException(1, taskListSize, taskIndex + 1);
        }
        Task task = taskList.get(taskIndex);
        return task.displayExpenses();
    }

    /**
     * Displays expenses for every task.
     *
     * @return string that includes every expense for every task to be displayed.
     * @throws EmptyListException if the task list is empty.
     * @throws InvalidIndexException if the index given is out of bounds.
     */
    public String showAllExpenses() throws EmptyListException, InvalidIndexException {
        int taskListSize = taskList.size();

        if (taskListSize == 0) {
            throw new EmptyListException();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= taskList.size(); i++) {
            sb.append(i + ". " + showExpense(i - 1));
        }
        return sb.toString();
    }

    /**
     * Sums all expenses up in one Task and returns the total as a formatted String.
     *
     * @param taskIndex task whose expenses are to be summed up.
     * @return total expenses of that task in a formatted String.
     * @throws EmptyListException if the task list is empty.
     * @throws InvalidIndexException if the index given is out of bounds.
     */
    public String sumExpense(int taskIndex) throws EmptyListException, InvalidIndexException {
        int taskListSize = taskList.size();

        if (taskListSize == 0) {
            throw new EmptyListException();
        } else if (taskIndex < 0 || taskIndex >= taskListSize) {
            throw new InvalidIndexException(1, taskListSize, taskIndex + 1);
        }
        Task task = taskList.get(taskIndex);
        return task.sumExpenseToString();
    }

    /**
     * Sums the total expenses in the entire task list and returns it as a formatted String.
     *
     * @return total expenses in the entire task list as a formatted String.
     * @throws EmptyListException if the task list is empty.
     */
    public String sumAllExpense() throws EmptyListException {
        int taskListSize = taskList.size();

        if (taskListSize == 0) {
            throw new EmptyListException();
        }
        float total = 0;
        for (Task task : taskList) {
            total += task.sumExpense();
        }
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(total);
    }
}
