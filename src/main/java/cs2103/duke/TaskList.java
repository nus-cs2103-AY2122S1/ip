package cs2103.duke;

import java.util.ArrayList;

/**
 * This class encapsulates a TaskList object, which represents all the tasks added by a user.
 */
public class TaskList {
    private ArrayList<Task> taskArrayList;
    protected String previousInput;
    protected ArrayList<Task> previousTaskList;

    /**
     * This constructor instantiates a new Tasklist with no tasks inside.
     */
    public TaskList() {
        taskArrayList = new ArrayList<>();
    }

    /**
     * This constructor instantiates a new TaskList with tasks provided by the input taskArrayList.
     *
     * @param taskArrayList The ArrayList containing the tasks with which to instantiate the new TaskList object.
     * @throws DukeException If an invalid input is detected.
     */
    public TaskList(ArrayList<Task> taskArrayList) throws DukeException {
        this.taskArrayList = taskArrayList;
    }

    /**
     * This method takes the user's input list and beautifies it for display.
     *
     * @return The beautified string to display.
     */
    public String listBeautify() {
        StringBuilder listBeautified = new StringBuilder();
        for (int i = 0; i < taskArrayList.size(); i++) {
            listBeautified.append(taskArrayList.get(i).getIndex() + 1)
                    .append(".")
                    .append(taskArrayList.get(i).toString());
            if (i < taskArrayList.size() - 1) { // new line except for last item
                listBeautified.append("\n");
            }
        }
        return listBeautified.toString();
    }

    /**
     * Validates input string to ensure it follows the valid format YYYY-MM-DD, and is a valid date.
     *
     * @param input The string to be validated.
     * @return True if the string is a valid date.
     */
    public boolean isValidDate(String input) {
        String[] splitInputs = input.split("-");
        if (splitInputs.length != 3) {
            return false;
        }
        boolean isLeapYear;
        String year = splitInputs[0];
        String month = splitInputs[1];
        String day = splitInputs[2];
        int maxDay;

        // check year
        if (year.length() != 4 || !year.matches("\\d+")) {
            return false;
        } else {
            isLeapYear = (Integer.parseInt(year) % 4 == 0);
        }

        // check month
        if (month.length() != 2 || !month.matches("\\d+")) {
            return false;
        } else {
            switch (Integer.parseInt(month)) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    maxDay = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    maxDay = 30;
                    break;
                case 2:
                    if (isLeapYear) {
                        maxDay = 29;
                    } else {
                        maxDay = 28;
                    }
                    break;
                default:
                    return false;
            }
        }

        // check day
        if (day.length() != 2 || !day.matches("\\d+")) {
            return false;
        } else {
            return Integer.parseInt(day) <= maxDay;
        }
    }

    /**
     * Adds a todo task into the task list.
     *
     * @param input The input containing the remaining user input after the command.
     * @return String representing the newly added todo task.
     * @throws DukeException if the user input is invalid.
     */
    public String addTodo(String input) throws DukeException {
        if (input.trim().equals("")) {
            throw new DukeException("No task name");
        }
        Task newestTodo = new ToDo(taskArrayList.size(), input);
        // using copy by value, store current taskArrayList in a buffer, in case of an undo command later.
        previousTaskList = new ArrayList<>(taskArrayList);
        taskArrayList.add(newestTodo);
        return ("New todo task added:\n"
                + newestTodo
                + "\nYou now have "
                + taskArrayList.size()
                + " item(s) in your task list.");
    }

    /**
     * Adds a deadline task into the task list.
     *
     * @param input The input containing the remaining user input after the command.
     * @return String representing the newly added deadline task.
     * @throws DukeException if the user input is invalid.
     */
    public String addDeadline(String input) throws DukeException {
        String[] deadlineTokens = input.split("\\s*/by\\s*");
        if (deadlineTokens.length == 0) {
            throw new DukeException("No task description");
        } else if (deadlineTokens.length == 1) {
            throw new DukeException("No task deadline");
        }
        String name = deadlineTokens[0];
        String description = deadlineTokens[1];
        if (!isValidDate(description)) {
            throw new DukeException("Invalid Deadline Date, please follow the format YYYY-MM-DD");
        }
        Task newestDeadline = new Deadline(taskArrayList.size(), name, description);
        // using copy by value, store current taskArrayList in a buffer, in case of an undo command later.
        previousTaskList = new ArrayList<>(taskArrayList);
        taskArrayList.add(newestDeadline);
        return ("New deadline task added:\n"
                + newestDeadline
                + "\nYou now have "
                + taskArrayList.size()
                + " item(s) in your task list.");
    }

    /**
     * Adds an event task into the task list.
     *
     * @param input The input containing the remaining user input after the command.
     * @return String representing the newly added event task.
     * @throws DukeException if the user input is invalid.
     */
    public String addEvent(String input) throws DukeException {
        String[] eventTokens = input.split("\\s*/at\\s*");
        if (eventTokens.length == 0) {
            throw new DukeException("No task description");
        } else if (eventTokens.length == 1) {
            throw new DukeException("No task duration");
        }
        String name = eventTokens[0];
        String description = eventTokens[1];
        if (!isValidDate(description)) {
            throw new DukeException("Invalid Event Date, please follow the format YYYY-MM-DD");
        }
        Task newestEvent = new Event(taskArrayList.size(), name, description);
        // using copy by value, store current taskArrayList in a buffer, in case of an undo command later.
        previousTaskList = new ArrayList<>(taskArrayList);
        taskArrayList.add(newestEvent);
        return ("New deadline task added:\n"
                + newestEvent
                + "\nYou now have "
                + taskArrayList.size()
                + " item(s) in your task list.");
    }

    /**
     * This method lists all tasks in the TaskList.
     *
     * @return A string representing all tasks in the TaskList.
     */
    public String listTasks() {
        return (listBeautify());
    }

    /**
     * This method marks a task as finished, and returns a string informing the user of the change.
     *
     * @param index The index of the task to be marked as finished, the first task will have an index of 1.
     * @return A string informing the user of the change.
     * @throws DukeException If the user specified an invalid index.
     */
    public String finishTask(int index) throws DukeException {
        assert index > 0;
        if (index > taskArrayList.size()) {
            throw new DukeException("This task index is not in the task list!");
        }
        taskArrayList.get(index - 1).markAsDone();
        return ("Congratulations! You have finished this task:\n"
                + taskArrayList.get(index - 1).toString());
    }

    /**
     * This method marks a task as unfinished, and returns a string informing the user of the change.
     *
     * @param index The index of the task to be marked as unfinished, the first task will have an index of 1.
     * @return A string informing the user of the change.
     * @throws DukeException If the user specified an invalid index.
     */
    public String unFinishTask(int index) throws DukeException {
        assert index > 0;
        if (index > taskArrayList.size()) {
            throw new DukeException("This task index is not in the task list!");
        }
        taskArrayList.get(index - 1).markAsNotDone();
        return ("Got it, marking this task as unfinished:\n"
                + taskArrayList.get(index - 1).toString());
    }

    /**
     * This method removes a task from the TaskList, and lets the user know which task was removed.
     *
     * @param index The index of the task to be deleted, the first task will have an index of 1.
     * @return A string informing the user of the deletion.
     * @throws DukeException If the user specified an invalid index.
     */
    public String deleteTask(int index) throws DukeException {
        assert index > 0;
        if (index > taskArrayList.size()) {
            throw new DukeException("This task index is not in the task list!");
        }
        // using copy by value, store current taskArrayList in a buffer, in case of an undo command later.
        previousTaskList = new ArrayList<>(taskArrayList);
        String deleteMessage = ("Got it, I have deleted this task:\n"
                + taskArrayList.get(index - 1).toString()
                + "\nYou now have "
                + (taskArrayList.size() - 1)
                + " item(s) in your task list.");
        taskArrayList.remove(index - 1);
        return deleteMessage;
    }

    /**
     * This method finds all tasks matching a keyword entered by the user.
     *
     * @param keyword The keyword to match tasks to.
     * @return All tasks matching the keyword.
     * @throws DukeException If keyword is invalid.
     */
    public String findTasks(String keyword) throws DukeException {
        assert keyword != null;
        // if 'keyword' contains more than one word or any spaces
        if (keyword.trim().split("\\s+").length > 1) {
            throw new DukeException("Invalid keyword, please enter only one keyword without blanks");
        }
        ArrayList<Task> subList = new ArrayList<>();
        for (Task task : taskArrayList) {
            if (task.description.contains(keyword)) {
                subList.add(task);
            }
        }
        if (subList.isEmpty()) {
            return ("No matches found for keyword: " + keyword.trim());
        }
        TaskList matchedTaskList = new TaskList(subList);
        return ("Here are the matching tasks in your list:\n"
                + matchedTaskList.listBeautify()
        );
    }

    /**
     * This method undoes the user's previous command, if it can be undone.
     *
     * @return A string representing if the previous command was deleted.
     * @throws DukeException If previous command cannot be undone.
     */
    public String undo() throws DukeException {
        if (previousInput == null) {
            throw new DukeException("Nothing to Undo");
        }
        // parse out the first word from input as the user's command
        String[] userPreviousInputs = previousInput.split("\\s+", 2);
        String previousCommand = userPreviousInputs[0];
        String remainingPreviousInput = "";
        if (userPreviousInputs.length > 1) {
            remainingPreviousInput = userPreviousInputs[1];
        }

        switch (previousCommand) {
            case "todo":
            case "deadline":
            case "event":
            case "delete":
                taskArrayList = new ArrayList<>(previousTaskList);
                String undoMessage = "I undid your previous command: \n"
                        + previousInput
                        + "\nhere is your current task list:\n"
                        + listBeautify();
                previousTaskList = new ArrayList<>();
                previousInput = "";
                return undoMessage;
            case "done":
                // finishTask() manipulates the Task objects directly, a shallow copy of task ArrayList is not enough
                undoDoneCommand(remainingPreviousInput);
                String undoMessageDone = "I undid your previous command: \n"
                        + previousInput
                        + "\nhere is your current task list:\n"
                        + listBeautify();
                previousTaskList = new ArrayList<>();
                previousInput = "";
                return undoMessageDone;
            default:
                throw new DukeException("Cannot undo this command");
        }
    }

    /**
     * This method is a helper method to handle the undo-ing of marking a task as done.
     *
     * @param input The remaining parts of the user's previous command without the command word.
     */
    public void undoDoneCommand(String input) throws DukeException {
        if (input == null) {
            throw new DukeException("unspecified task to mark as done");
        }
        try {
            int taskNumber = Integer.parseInt(input);
            unFinishTask(taskNumber);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }
}
