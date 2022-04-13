package parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import duke.Type;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
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
            return parseDoneCall(userInput);
        } else if (isRemoveCall(userInput)) {
            return parseRemoveCall(userInput);
        } else if (isFindCall(userInput)) {
            return parseFindCall(userInput);
        } else if (isSnoozeCall(userInput)) {
            return parseSnoozeCall(userInput);
        } else if (userInput.startsWith("todo ")) {
            return parseTodoCall(userInput, tasks);
        } else if (userInput.startsWith("deadline ")) {
            return parseDeadlineCall(userInput, tasks);
        } else if (userInput.startsWith("event ")) {
            return parseEventCall(userInput, tasks);
        } else {
            return "Oops! I don't understand what you mean!";
        }
    }

    /**
     * Check whether user input is a valid done call for a task to be marked as done.
     * @param str String input from user.
     * @return True if input is a valid done call, false otherwise.
     */
    private boolean isDoneCall (String str) {
        if (str == null) {
            return false;
        }
        if (str.length() < 6) {
            return false;
        }
        try {
            int d = Integer.parseInt(str.substring(5));
        } catch (NumberFormatException nfe) {
            return false;
        }
        if (!str.startsWith("done ")) {
            return false;
        }
        return true;
    }

    /**
     * Check whether user input is a valid remove call for a task to be removed.
     * @param str String input from user.
     * @return True if input is a valid remove call, false otherwise.
     */
    private boolean isRemoveCall (String str) {
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
     * Handles user input when user calls to add a Todo task.
     * @param userInput String format of user input
     * @param tasks TaskList object used to store tasks
     * @return Duke response to the user
     */
    private String parseTodoCall(String userInput, TaskList tasks) {
        if (isTodoDescEmpty(userInput)) {
            return " OOPS!!! The description of a todo cannot be empty.";
        }
        tasks.addTask(new Todo(userInput.substring(5)));
        storage.newTaskToData(userInput.substring(5), Type.TODO, "");
        return "Got it. I've added this task:\n" + "  " + tasks.getTask(tasks.size() - 1).toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Handles user input when user calls to add a Deadline task.
     * @param userInput String format of user input
     * @param tasks TaskList object used to store tasks
     * @return Duke response to the user
     */
    private String parseDeadlineCall(String userInput, TaskList tasks) {
        if (isDeadlineDescEmpty(userInput)) {
            return " OOPS!!! The description of a deadline cannot be empty.";
        }
        int slashIndex;
        if (isByPresent(userInput)) {
            slashIndex = userInput.indexOf("/by");
        } else {
            return " Please set a deadline by adding /by";
        }
        try {
            tasks.addTask(new Deadline(userInput.substring(9, slashIndex - 1),
                    LocalDate.parse(userInput.substring(slashIndex + 4, slashIndex + 14)),
                    LocalTime.parse(userInput.substring(slashIndex + 15))));
        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            return " Date and Time must be specified by YYYY-MM-DD HH:MM";
        }
        storage.newTaskToData(userInput.substring(9, slashIndex - 1), Type.DEADLINE,
                userInput.substring(slashIndex + 4));
        return "Got it. I've added this task:\n" + "  " + tasks.getTask(tasks.size() - 1).toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Handles user input when user calls to add an Event task.
     * @param userInput String format of user input
     * @param tasks TaskList object used to store tasks
     * @return Duke response to the user
     */
    private String parseEventCall(String userInput, TaskList tasks) {
        if (isEventDescEmpty(userInput)) {
            return " OOPS!!! The description of an event cannot be empty.";
        }
        int slashIndex;
        if (isAtPresent(userInput)) {
            slashIndex = userInput.indexOf("/at");
        } else {
            return " Please set a specified timing by adding /at";
        }
        try {
            tasks.addTask(new Event(userInput.substring(6, slashIndex - 1),
                    LocalDate.parse(userInput.substring(slashIndex + 4, slashIndex + 14)),
                    LocalTime.parse(userInput.substring(slashIndex + 15))));
        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            return " Date and Time must be specified by YYYY-MM-DD HH:MM";
        }

        storage.newTaskToData(userInput.substring(6, slashIndex - 1), Type.EVENT,
                userInput.substring(slashIndex + 4));
        return "Got it. I've added this task:\n" + "  " + tasks.getTask(tasks.size() - 1).toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Check whether user input is a valid find call to find Tasks with a certain keyword.
     * @param str User input.
     * @return True if valid find call, false otherwise.
     */
    private boolean isFindCall(String str) {
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

    /**
     * Checks if description portion of user input is empty.
     * @param todo User input from which is a Todo.
     * @return True if description is empty, false if not empty.
     */
    private boolean isTodoDescEmpty(String todo) {
        return todo.substring(4).trim().isEmpty();
    }

    /**
     * Checks if description portion of user input is empty.
     * @param deadline User input from which is a Deadline.
     * @return True if description is empty, false if not empty.
     */
    private boolean isDeadlineDescEmpty(String deadline) {
        return deadline.substring(8).trim().isEmpty();
    }

    /**
     * Checks if description portion of user input is empty.
     * @param event User input from which is an Event.
     * @return True if description is empty, false if not empty.
     */
    private boolean isEventDescEmpty(String event) {
        return event.substring(5).trim().isEmpty();
    }

    /**
     * Checks if user input includes /by String to specify date and time.
     * @param deadline User input
     * @return True if /by String is present, false otherwise
     */
    private boolean isByPresent(String deadline) {
        if (deadline.indexOf("/by") == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks if user input includes /by String to specify date and time.
     * @param event User input
     * @return True if /at String is present, false otherwise
     */
    private boolean isAtPresent(String event) {
        if (event.indexOf("/at") == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Handles actions to be done when the user inputs a done call.
     * @param str User input in String format
     * @return String that Duke will reply the user
     */
    private String parseDoneCall(String str) {
        int index = Integer.parseInt(str.substring(5));

        if (tasks.size() >= index) {
            tasks.getTask(index - 1).markAsDone();
            storage.markAsDoneData(index - 1);
            return ui.doneMessage(tasks.getTask(index - 1));
        } else {
            return ui.noSuchTaskMessage();
        }
    }

    /**
     * Handles actions to be done when the user inputs a remove call.
     * @param str User input in String format
     * @return String that Duke will reply the user
     */
    private String parseRemoveCall(String str) {
        int index = Integer.parseInt(str.substring(7));
        if (tasks.size() >= index) {
            String result = ui.removeMessage(tasks.getTask(index - 1), tasks.size() - 1);
            storage.removeData(index - 1);
            tasks.removeTask(index - 1);
            return result;
        } else {
            return ui.noSuchTaskMessage();
        }
    }

    /**
     * Handles actions to be done when the user inputs a find call.
     * @param str User input in String format
     * @return String that Duke will reply the user
     */
    private String parseFindCall(String str) {
        String keyword = str.substring(5);
        TaskList matchingTasks = tasks.find(keyword);
        if (matchingTasks.size() == 0) {
            return "There are no tasks that include your keyword.";
        } else {
            assert matchingTasks.size() > 0;
            String result = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < matchingTasks.size(); i++) {
                result += ((i + 1) + "." + matchingTasks.getTask(i).toString() + "\n");
            }
            return result;
        }
    }

    /**
     * Check if user input is a command to snooze a task.
     * @param str User input
     * @return True if user input did request for a snooze call, false otherwise
     */
    private boolean isSnoozeCall(String str) {
        if (str == null) {
            return false;
        }
        if (str.length() < 6) {
            return false;
        }
        try {
            // Split userInput by spaces, split should be ['snooze', index, date, time]
            String[] split = str.split(" ");
            int d = Integer.parseInt(split[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return false;
        }
        if (!str.startsWith("snooze ")) {
            return false;
        }
        return true;
    }

    /**
     * Handles user input when a snooze command is requested.
     * @param str User input
     * @return Duke response to snooze call
     */
    private String parseSnoozeCall(String str) {
        // Split userInput by spaces, split should be ['snooze', index, date, time]
        String[] split = str.split(" ");
        int index = Integer.parseInt(split[1]);
        if (split.length != 4) {
            return "Snooze command must be in the format 'snooze task-number YYYY-MM-DD HH:MM'";
        }
        if (index > tasks.size()) {
            return "Sorry, there are only " + tasks.size() + " tasks currently.";
        }
        if (tasks.getTask(index - 1) instanceof Todo) {
            return "Sorry, you cannot snooze a Todo Task.";
        }
        try {
            Task toBeModified = tasks.getTask(index - 1);
            if (toBeModified instanceof Event) {
                // Need to cast to Event in order to utilise modifyDateTime method
                Event eventToBeModified = (Event) toBeModified;
                eventToBeModified.modifyDateTime(LocalDate.parse(split[2]),
                        LocalTime.parse(split[3]));
                // Date and time is concatenated into one string
                String datetime = split[2] + " " + split[3];
                storage.modifyDateTimeData(datetime, index - 1);
                return "Successfully snoozed Task " + index + " to " + datetime;
            } else if (toBeModified instanceof Deadline) {
                // Need to cast to Deadline in order to utilise modifyDateTime method
                Deadline deadlineToBeModified = (Deadline) toBeModified;
                deadlineToBeModified.modifyDateTime(LocalDate.parse(split[2]),
                        LocalTime.parse(split[3]));
                // Date and time is concatenated into one string
                String datetime = split[2] + " " + split[3];
                storage.modifyDateTimeData(datetime, index - 1);
                return "Successfully snoozed Task " + index + " to " + datetime;
            } else {
                // A Todo object should never reach this point, since it has already been checked
                assert false;
                return "Oops! There is an issue with your Task List, you cannot snooze this task right now.";
            }
        } catch (DateTimeParseException e) {
            return " Date and Time must be specified by YYYY-MM-DD HH:MM";
        } catch (ClassCastException e) {
            return "Oops! There is an issue with your Task List, you cannot snooze this task right now.";
        }
    }
}
