package cs2103.duke;

import java.util.ArrayList;

/**
 * This class encapsulates a TaskList object, which represents all the tasks added by a user.
 */
public class TaskList {
    private ArrayList<Task> taskArrayList;

    /**
     * This constructor instantiates a new Tasklist with no tasks inside.
     */
    public TaskList() {
        this.taskArrayList = new ArrayList<>();
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
     * This method takes an input string and formats it by including horizontal lines above
     * and below the input string.
     *
     * @param str Input string to be sandwiched.
     * @return The original string sandwiched between two horizontal lines.
     */
    public static String sandwich(String str) {
        return "____________________________________________________________\n"
                + str + "\n"
                + "____________________________________________________________";
    }

    /**
     * This method takes the user's input list and beautifies it for display.
     *
     * @return The beautified string to display.
     */
    public String listBeautify() {
        StringBuilder listBeautified = new StringBuilder();
        for (int i = 0; i < taskArrayList.size(); i++) {
            listBeautified.append(i + 1)
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
     * Adds a task into the task list.
     *
     * @param type        The type of task to be added.
     * @param name        The name of task to be added.
     * @param description The description of the task.
     * @return String representing the newly added task.
     * @throws DukeException if the user input is invalid.
     */
    public String addTask(String type, String name, String description) throws DukeException {
        switch (type) {
        case "todo":
            if (name.trim().equals("")) {
                throw new DukeException("No task name");
            }
            Task newestTodo = new ToDo(name);
            taskArrayList.add(newestTodo);
            return (sandwich("New todo task added:\n"
                    + newestTodo
                    + "\nYou now have "
                    + taskArrayList.size()
                    + " item(s) in your task list."));
        case "deadline":
            if (!isValidDate(description)) {
                throw new DukeException("Invalid Date, please follow the format YYYY-MM-DD");
            }
            Task newestDeadline = new Deadline(name, description);
            taskArrayList.add(newestDeadline);
            return (sandwich("New deadline task added:\n"
                    + newestDeadline
                    + "\nYou now have "
                    + taskArrayList.size()
                    + " item(s) in your task list."));
        case "event":
            if (!isValidDate(description)) {
                throw new DukeException("Invalid Date, please follow the format YYYY-MM-DD");
            }
            Task newestEvent = new Event(name, description);
            taskArrayList.add(newestEvent);
            return (sandwich("New deadline task added:\n"
                    + newestEvent
                    + "\nYou now have "
                    + taskArrayList.size()
                    + " item(s) in your task list."));
        default:
            throw new DukeException("Unknown task type");
        }
    }

    /**
     * This method lists all tasks in the TaskList.
     *
     * @return A string representing all tasks in the TaskList.
     */
    public String listTasks() {
        return (sandwich(listBeautify()));
    }

    /**
     * This method marks a task as finished, and returns a string informing the user of the change.
     *
     * @param index The index of the task to be marked, the first task will have an index of 1.
     * @return A string informing the user of the change.
     * @throws DukeException If the user specified an invalid index.
     */
    public String finishTask(int index) throws DukeException {
        if (index > taskArrayList.size()) {
            throw new DukeException("This task index is not in the task list!");
        }
        taskArrayList.get(index - 1).markAsDone();
        return (sandwich("Congratulations! You have finished this task: "
                + taskArrayList.get(index - 1).toString()));
    }

    /**
     * This method removes a task from the TaskList, and lets the user know which task was removed.
     *
     * @param index The index of the task to be deleted, the first task will have an index of 1.
     * @return A string informing the user of the deletion.
     * @throws DukeException If the user specified an invalid index.
     */
    public String deleteTask(int index) throws DukeException {
        if (index > taskArrayList.size()) {
            throw new DukeException("This task index is not in the task list!");
        }
        String deleteMessage = (sandwich("Got it, I have deleted this task: "
                + taskArrayList.get(index - 1).toString()
                + "\nYou now have "
                + (taskArrayList.size() - 1)
                + " item(s) in your task list."));
        // actual logic of deletion
        taskArrayList.remove(index - 1);
        return deleteMessage;
    }
}
