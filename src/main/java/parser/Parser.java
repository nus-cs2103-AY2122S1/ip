package parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import duke.Duke;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;
import ui.Ui;

public class Parser {

    /** Ui object to print output to user */
    private Ui ui;
    /** List of all Tasks */
    private TaskList tasks;
    /** Storage object to interact with the data file */
    private Storage storage;

    /**
     * Constructor for Parser object.
     * @param ui Ui object to print output to user.
     * @param tasks List of all Tasks.
     * @param storage Storage object to interact with the data file.
     */
    public Parser(Ui ui, TaskList tasks, Storage storage) {
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Make sense of String input from the user.
     * @param userInput String input from user.
     * @return True if user input is to exit the program, false otherwise.
     */
    public String parse(String userInput) {
        if (userInput.equals("bye")) {
            return ui.goodbyeMessage();
        } else if (userInput.equals("list")) {
            return ui.listMessage(tasks);
        } else if (isDoneCall(userInput)) {
            int index = Integer.parseInt(userInput.substring(5));

            if (tasks.size() >= index) {
                tasks.getTask(index - 1).markAsDone();
                storage.markAsDoneData(index - 1);
                return ui.doneMessage(tasks.getTask(index - 1));
            } else {
                return ui.noSuchTaskMessage();
            }

        } else if (isRemoveCall(userInput)) {
            int index = Integer.parseInt(userInput.substring(7));
            if (tasks.size() >= index) {
                String result = ui.removeMessage(tasks.getTask(index - 1), tasks.size() - 1);
                storage.removeData(index - 1);
                tasks.removeTask(index - 1);
                return result;
            } else {
                return ui.noSuchTaskMessage();
            }
        } else if (isFindCall(userInput)) {
            String keyword = userInput.substring(5);
            TaskList matchingTasks = tasks.find(keyword);
            if (matchingTasks.size() == 0) {
                return "There are no tasks that include your keyword.";
            } else {
                String result = "Here are the matching tasks in your list:\n";
                for (int i = 0; i < matchingTasks.size(); i++) {
                    result += ((i + 1) + "." + matchingTasks.getTask(i).toString() + "\n");
                }
                return result;
            }
        } else {
            try {
                if (userInput.startsWith("todo")) {
                    return parseAddTask(userInput, tasks, Duke.Type.TODO);
                } else if (userInput.startsWith("deadline")) {
                    return parseAddTask(userInput, tasks, Duke.Type.DEADLINE);
                } else if (userInput.startsWith("event")) {
                    return parseAddTask(userInput, tasks, Duke.Type.EVENT);
                } else {
                    return " OOPS!!! I'm sorry, but I don't know what that means :-(";
                }
            } catch (IllegalArgumentException e) {
                return e.getMessage();
            }
        }
    }

    /**
     * Check whether user input is a valid done call for a task to be marked as done.
     * @param strNum String input from user.
     * @return True if input is a valid done call, false otherwise.
     */
    public boolean isDoneCall (String strNum) {
        if (strNum == null) {
            return false;
        }
        if (strNum.length() < 6) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum.substring(5));
        } catch (NumberFormatException nfe) {
            return false;
        }
        if (!strNum.startsWith("done ")) {
            return false;
        }
        return true;
    }

    /**
     * Check whether user input is a valid remove call for a task to be removed.
     * @param str String input from user.
     * @return True if input is a valid remove call, false otherwise.
     */
    public boolean isRemoveCall (String str) {
        if (str == null) {
            return false;
        }
        if (str.length() < 8) {
            return false;
        }
        try {
            int d = Integer.parseInt(str.substring(7));
        } catch (NumberFormatException nfe) {
            return false;
        }
        if (!str.startsWith("remove ")) {
            return false;
        }
        return true;
    }

    /**
     * Handles the user input when it is a command to add a Task.
     * @param userInput String input from user.
     * @param tasks Current list of tasks to be edited.
     * @param type Type of Task added.
     */
    public String parseAddTask(String userInput, TaskList tasks, Duke.Type type) {
        if (type == Duke.Type.TODO) {
            if (userInput.substring(4).trim().isEmpty()) {
                return " OOPS!!! The description of a todo cannot be empty.";
            }
            tasks.addTask(new Todo(userInput.substring(5)));
            storage.newTaskToData(userInput.substring(5), Duke.Type.TODO, "");
            return "Got it. I've added this task:\n" + "  " + tasks.getTask(tasks.size() - 1).toString() + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.";
        } else if (type == Duke.Type.DEADLINE) {
            if (userInput.substring(8).trim().isEmpty()) {
                return " OOPS!!! The description of a deadline cannot be empty.";
            }
            int timeIndex = userInput.indexOf("/by");
            if (timeIndex == -1) {
                return " Please set a deadline by adding /by";
            }
            try {
                tasks.addTask(new Deadline(userInput.substring(9, timeIndex - 1),
                        LocalDate.parse(userInput.substring(timeIndex + 4, timeIndex + 14)),
                        LocalTime.parse(userInput.substring(timeIndex + 15))));
            } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
                return " Date and Time must be specified by YYYY-MM-DD HH:MM";
            }
            storage.newTaskToData(userInput.substring(9, timeIndex - 1), Duke.Type.DEADLINE,
                    userInput.substring(timeIndex + 4));
            return "Got it. I've added this task:\n" + "  " + tasks.getTask(tasks.size() - 1).toString() + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.";
        } else {
            if (userInput.substring(5).trim().isEmpty()) {
                return " OOPS!!! The description of an event cannot be empty.";
            }
            int timeIndex = userInput.indexOf("/at");
            if (timeIndex == -1) {
                return " Please set a deadline by adding /at";
            }
            try {
                tasks.addTask(new Event(userInput.substring(6, timeIndex - 1),
                        LocalDate.parse(userInput.substring(timeIndex + 4, timeIndex + 14)),
                        LocalTime.parse(userInput.substring(timeIndex + 15))));
            } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
                return " Date and Time must be specified by YYYY-MM-DD HH:MM";
            }

            storage.newTaskToData(userInput.substring(6, timeIndex - 1), Duke.Type.EVENT,
                    userInput.substring(timeIndex + 4));
            return "Got it. I've added this task:\n" + "  " + tasks.getTask(tasks.size() - 1).toString() + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.";
        }
    }

    /**
     * Check whether user input is a valid find call to find Tasks with a certain keyword.
     * @param str User input.
     * @return True if valid find call, false otherwise.
     */
    public boolean isFindCall(String str) {
        if (str == null) {
            return false;
        }
        if (str.length() < 5) {
            return false;
        }
        if (!str.startsWith("find ")) {
            return false;
        }
        return true;
    }
}
