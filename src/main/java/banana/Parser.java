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
        String label = information;
        return label;
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
            return deadlineOrEventCommand(input, tasks);
        } else {
            return addTaskCommand(input, tasks);
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
        return displayLabel(("Here are the tasks in your list: \n"
                + "     " + itemCollection));
    }

    /**
     * Indicates a task as done.
     *
     * @param tasks the list of tasks.
     * @return the label.
     */
    public String doneCommand(TaskList tasks) {
        int index = Integer.parseInt(input.substring(5, 6)) - 1;
        tasks.getTask(index).setIsDone();
        return displayLabel("Nice! I've marked this task as done: \n" +
                "       " + tasks.getTask(index).toString());
    }

    /**
     * Adds a task or the subclass ToDo.
     *
     * @param input the user input.
     * @param tasks the list of tasks.
     * @return the label.
     */
    public String addTaskCommand(String input, TaskList tasks) {
        if (input.contains("todo")) {
            String info = input.substring(5);
            tasks.addTask(new ToDo(info));
        } else {
            tasks.addTask(new Task(input));
        }
        return displayLabel("Got it. I've added this task:  \n" +
                "       " + tasks.getTask(tasks.getSize() - 1).toString() + "\n     " +
                "Now you have " + Integer.toString(tasks.getSize()) + " tasks in the list.");
    }

    /**
     * Adds an Event or a Deadline and
     * gets the time/date in correct format
     * if necessary.
     *
     * @param input the user input.
     * @param tasks the list of tasks.
     * @return the label.
     */
    public String deadlineOrEventCommand(String input, TaskList tasks) {
        if (input.contains("deadline")) {
            String[] info = input.substring(9).split(" /by ");
            tasks.addTask(getDateAndTime(info, input, "deadline"));
        } else {
            String[] info = input.substring(6).split(" /at ");
            tasks.addTask(getDateAndTime(info, input, "event"));
        }
        return displayLabel("Got it. I've added this task:  \n" +
                "       " + tasks.getTask(tasks.getSize() - 1).toString() + "\n     Now you have "
                + Integer.toString(tasks.getSize()) + " tasks in the list.");
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
        Task removedTask = tasks.getTask(index);
        tasks.removeTask(removedTask);
        return displayLabel("Noted. I've removed this task:  \n" +
                "       " + removedTask.toString() + "\n     Now you have "
                + Integer.toString(tasks.getSize()) + " tasks in the list.");
    }

    /**
     * Finds the task that contains
     * or matches the user's input.
     *
     * @param tasks the list of tasks.
     * @return the label.
     */
    public String findCommand(TaskList tasks) {
        TaskList newTasks = new TaskList(new ArrayList<>());
        if (input.contains("find")) {
            String item = input.split(" ")[1];
            for (int i = 0; i < tasks.getSize(); i++) {
                if (tasks.getTask(i).getDescription().contains(item)) {
                    newTasks.addTask(tasks.getTask(i));
                }
            }
            return displayLabel(
                    "Here are the matching tasks in your list: \n" +
                            "     " + getItems(newTasks));
        }
        return displayLabel("No matching tasks, sorry");
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
     * @param input the user input.
     * @param type the Task type.
     * @return the new Task.
     */
    public Task getDateAndTime(String[] info, String input, String type) {
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
