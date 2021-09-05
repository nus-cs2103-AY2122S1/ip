package banana;

import java.util.ArrayList;
import java.time.LocalDate;

/**
 * The Parser class makes sense
 * of the user's input.
 *
 * @author: Ravi Ananya
 **/

public class Parser {

    private String input;

    /**
     * Constructor for the Parser class.
     *
     * @param input the user input.
     */
    public Parser(String input) {
        this.input = input;
    }

    /**
     * Gets the user input.
     *
     * @return the user input.
     */
    public String getInput() {
        return input;
    }

    /**
     * Prints the information.
     *
     * @param information display information.
     * @return the label
     */
    public static String displayLabel(String information) {
        return information;
    }

    /**
     * Checks how to handle the user's
     * input command.
     *
     * @param tasks the list of tasks.
     * @return the label.
     * @throws DukeException if the input was invalid.
     */
    public String parseInput(TaskList tasks) throws DukeException {
        exceptionCommand(tasks);
        if (input.equals("list")) {
            return listCommand(tasks);
        } else if (input.contains("done")) {
            return doneCommand(tasks);
        } else if (input.contains("delete")) {
            return deleteCommand(tasks);
        } else if (input.contains("find")) {
            return findCommand(tasks);
        } else if (input.contains("deadline") || input.contains("event")) {
            return deadlineOrEventCommand(tasks);
        } else {
            return addTaskCommand(tasks);
        }
    }

    /**
     * Gets and prints the existing
     * list of tasks.
     *
     * @param tasks the list of tasks.
     * @return the label.
     */
    public String listCommand(TaskList tasks) {
        String itemCollection = getItems(tasks);
        String listText = "Here are the tasks in your list: \n"
                + "     ";
        return listText + itemCollection;
    }

    /**
     * Indicates a task as done.
     *
     * @param tasks the list of tasks.
     * @return the label.
     */
    public String doneCommand(TaskList tasks) {
        int index = Integer.parseInt(input.substring(5, 6)) - 1;
        assert index >= 0;
        tasks.getTask(index).setIsDone();
        String doneText = "Nice! I've marked this task as done: \n"
                + "       ";
        return doneText + tasks.getTask(index).toString();
    }

    /**
     * Adds a task or the subclass ToDo.
     *
     * @param tasks the list of tasks.
     * @return the label.
     */
    public String addTaskCommand(TaskList tasks) {
        if (input.contains("todo")) {
            String info = input.substring(5);
            tasks.addTask(new ToDo(info));
        } else {
            tasks.addTask(new Task(input));
        }
        String addTaskText = "Got it. I've added this task:  \n"
                + "       ";
        String taskNumberText = "\n     "
                + "Now you have " + Integer.toString(tasks.getSize())
                + " tasks in the list.";
        return addTaskText + tasks.getTask(
                tasks.getSize() - 1).toString() + taskNumberText;
    }

    /**
     * Adds an Event or a Deadline and
     * gets the time/date in correct format
     * if necessary.
     *
     * @param tasks the list of tasks.
     * @return the label.
     */
    public String deadlineOrEventCommand(TaskList tasks) {
        assert input.contains("deadline") || input.contains("event");
        if (input.contains("deadline")) {
            assert input.contains("/by");
            String[] info = input.substring(9).split(" /by ");
            tasks.addTask(getDateAndTime(info, "deadline"));
        } else {
            assert input.contains("/at");
            String[] info = input.substring(6).split(" /at ");
            tasks.addTask(getDateAndTime(info, "event"));
        }
        String addTaskText = "Got it. I've added this task:  \n"
                + "       ";
        String taskNumberText = "\n     "
                + "Now you have " + Integer.toString(tasks.getSize())
                + " tasks in the list.";
        return addTaskText + tasks.getTask(
                tasks.getSize() - 1).toString() + taskNumberText;
    }

    /**
     * Throws an exception if the wrong
     * commands/inputs have been entered.
     *
     * @param tasks the list of tasks.
     * @return the label.
     * @throws DukeException the exception.
     */
    public void exceptionCommand(TaskList tasks) throws DukeException {
        if (input.equals("todo") || input.equals("event") || input.equals("deadline")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else if (input.equals("done")) {
            throw new DukeException("☹ OOPS!!! The completed task number must be given.");
        } else if (input.equals("delete")) {
            throw new DukeException("☹ OOPS!!! You need to specify which task you want to delete.");
        } else if (input.equals("blah")) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Delete a task from the list.
     *
     * @param tasks the list of tasks.
     * @return the label.
     */
    public String deleteCommand(TaskList tasks) {
        int index = Integer.parseInt(input.substring(7, 8)) - 1;
        assert index >= 0;
        Task removedTask = tasks.getTask(index);
        tasks.removeTask(removedTask);
        String removeTaskText = "Noted. I've removed this task:  \n"
                + "       ";
        String taskNumberText = "\n     "
                + "Now you have " + Integer.toString(tasks.getSize())
                + " tasks in the list.";
        return removeTaskText + removedTask.toString()
                + taskNumberText;
    }

    /**
     * Finds the task that contains
     * or matches the user's input.
     *
     * @param tasks the list of tasks.
     * @return the label.
     */
    public String findCommand(TaskList tasks) {
        String outputText = "No matching tasks, sorry";
        TaskList newTasks = new TaskList(new ArrayList<>());
        if (input.contains("find")) {
            String item = input.split(" ")[1];
            for (int i = 0; i < tasks.getSize(); i++) {
                if (tasks.getTask(i).getDescription().contains(item)) {
                    newTasks.addTask(tasks.getTask(i));
                }
            }
            outputText = "Here are the matching tasks "
                    + "in your list: \n" + "     ";
            return outputText + getItems(newTasks);
        }
        return outputText;
    }

    /**
     * Gets the list of tasks.
     *
     * @param tasks the list of tasks.
     * @return the tasks in string format.
     */
    public String getItems(TaskList tasks) {
        String collection = "";
        for (int index = 0; index < tasks.getSize(); index++) {
            if (index != 0) {
                collection += "     ";
            }
            String info = tasks.getTask(index).toString();
            collection += Integer.toString(index + 1) + "." + info;
            if (index != tasks.getSize() - 1) {
                collection += "\n";
            }
        }
        return collection;
    }

    /**
     * Gets the date and time in correct
     * notation/format if necessary.
     *
     * @param info the task information.
     * @param type the Task type.
     * @return the new Task.
     */
    public Task getDateAndTime(String[] info, String type) {
        String[] potentialDate = info[1].split(" ");
        LocalDate date = null;
        if (!getTime(potentialDate[0]).equals("")) {
            info[1] = getTime(potentialDate[0]);
        } else if (potentialDate.length > 1 &&
                !getTime(potentialDate[1]).equals("")) {
            potentialDate[1] = getTime(potentialDate[1]);
            info[1] = potentialDate[0] + " " + potentialDate[1];
        }
        if (potentialDate.length > 1 && potentialDate[0].contains("/")) {
            date = LocalDate.parse(parseDates(potentialDate[0]));
        }
        if (type.equals("deadline")) {
            if (date != null) {
                return new Deadline(info[0], date, potentialDate[1]);
            } else {
                return new Deadline(info[0], info[1]);
            }
        } else {
            if (date != null) {
                return new Event(info[0], date, potentialDate[1]);
            } else {
                return new Event(info[0], info[1]);
            }
        }
    }

    /**
     * Changes the date format to be
     * parsed by LocalDate.
     *
     * @param date the old-format date.
     * @return the date.
     */
    public String parseDates(String date) {
        String[] sepIntoYearMonthDate = date.split("/");
        assert sepIntoYearMonthDate.length == 3;
        if (Integer.parseInt(sepIntoYearMonthDate[0]) < 10) {
            sepIntoYearMonthDate[0] = "0" + sepIntoYearMonthDate[0];
        }
        return sepIntoYearMonthDate[2] + "-" +
                sepIntoYearMonthDate[1] + "-" + sepIntoYearMonthDate[0];
    }

    /**
     * Changes the time format.
     *
     * @param time the old-format time.
     * @return the new-format time.
     */
    public String getTime(String time) {
        try {
            int timeVal = Integer.parseInt(time) / 100;
            if (timeVal > 12) {
                timeVal -= 12;
            }
            return Integer.toString(timeVal) + "pm";
        } catch (NumberFormatException e) {
            return "";
        }
    }

}
