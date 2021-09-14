package duke.parser;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * This class deals with making sense of the user command.
 */
public class Parser {

    private final String input;
    private boolean isByeCommand;

    /** A list of all valid commands recognised. */
    enum Commands {
        BYE, LIST, DONE, DELETE, TODO, DEADLINE, EVENT, FIND, SCHEDULE
    }

    /**
     * Public constructor to parse a user's input.
     *
     * @param input The whole command given by the user.
     */
    public Parser(String input) {
        this.input = input;
        this.isByeCommand = false;
    }

    public boolean isByeCommand() {
        return isByeCommand;
    }

    /**
     * Formats the date inputted by the user into a LocalDate object.
     *
     * @param str The date string inputted by the user.
     * @return A LocalDate object representation of the date string.
     * @throws DukeException If the date is wrongly formatted.
     */
    public static LocalDate dateFormatter(String str) throws DukeException {
        try {
            String[] splitStr = str.split("/");
            String day = String.format("%1$" + 2 + "s", splitStr[0]).replace(' ', '0');
            String month = String.format("%1$" + 2 + "s", splitStr[1]).replace(' ', '0');
            return LocalDate.parse(splitStr[2] + "-" + month + "-" + day);
        } catch (Exception e) {
            throw new DukeException("Your Date is wrongly formatted! It should be in the form of dd-mm-yyyy.");
        }
    }

    /**
     * Formats the time inputted by the user.
     *
     * @param str The time string inputted by the user.
     * @return A LocalTime object representation of the time string.
     * @throws DukeException If the time is wrongly formatted.
     */
    public static LocalTime timeFormatter(String str) throws DukeException {
        try {
            return LocalTime.parse(str.substring(0, 2) + ":" + str.substring(2));
        } catch (Exception e) {
            throw new DukeException("Your Time is wrongly formatted! It should be in the the form of hhmm.");
        }
    }

    /**
     * Executes a command given by the user and returns a String that contains all the corresponding
     * texts associated with the command.
     *
     * @param tasks The current list of tasks.
     * @param ui The current user interface.
     * @param storage The storage to store/load data from.
     * @throws DukeException If there is a parsing error from the commands.
     * @return A String that contains the corresponding texts associated with the command.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String str;
        if (input.equals(Commands.BYE.toString().toLowerCase())) {
            isByeCommand = true;
            return byeCommand(ui);
        }
        Task t;
        if (input.equals(Commands.LIST.toString().toLowerCase())) {
            str = listCommand(tasks, ui);
        } else if (input.startsWith(Commands.DONE.toString().toLowerCase())) {
            str = doneCommand(tasks, ui);
        } else if (input.startsWith(Commands.DELETE.toString().toLowerCase())) {
            str = deleteCommand(tasks, ui);
        } else if (input.startsWith(Commands.FIND.toString().toLowerCase())) {
            str = findCommand(tasks, ui);
        } else if (input.startsWith(Commands.SCHEDULE.toString().toLowerCase())) {
            str = scheduleCommand(tasks, ui);
        } else {
            if (input.startsWith(Commands.TODO.toString().toLowerCase())) {
                t = todoCommand();
            } else if (input.startsWith(Commands.DEADLINE.toString().toLowerCase())) {
                t = deadlineCommand();
            } else if (input.startsWith(Commands.EVENT.toString().toLowerCase())) {
                t = eventCommand();
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
            assert t != null : "Task to be added should not be null.";
            str = tasks.addTask(t, ui);
        }
        try {
            storage.saveToDisk(tasks);
        } catch (IOException e) {
            str = ui.printError(e);
        }
        assert str != null : "String to be printed out by execute() should not be null.";
        assert !str.isEmpty() : "String to be printed out by execute() should not be empty.";
        return str;
    }

    /**
     * Finds all related tasks which contains input as a keyword and returns a String that contains texts
     * to be printed out by "find" command.
     *
     * @param tasks The current list of tasks by the user.
     * @param ui The current user interface by the user.
     * @return A string that contains texts to be printed out by "find" command.
     */
    public String findCommand(TaskList tasks, Ui ui) {
        ArrayList<Task> list = new ArrayList<>();
        String description = input.substring("find".length()).trim();
        String str = ui.matchTaskMessage();
        for (int i = 0; i < tasks.numberOfTasks(); i++) {
            if (tasks.taskNumber(i).getDescription().toUpperCase().contains(description.toUpperCase())) {
                assert tasks.taskNumber(i) != null : "Task to be added should not be null.";
                list.add(tasks.taskNumber(i));
            }
        }
        TaskList matchingTasks = new TaskList(list);
        return str + matchingTasks.printAllTasks();
    }

    /**
     * Lists all user's tasks and returns a String that contains texts to be printed out by "list" command.
     *
     * @param tasks A TaskList object containing all user's tasks.
     * @param ui The current user interface by the user.
     * @return A String that contains texts to be printed out by "list" command.
     */
    public String listCommand(TaskList tasks, Ui ui) {
        return ui.taskListMessage() + "\n" + tasks.printAllTasks();
    }

    /**
     * Marks a task as done and returns a String that contains texts to be printed out by "done" command.
     *
     * @param tasks A TaskList object containing all user's tasks.
     * @param ui The current user interface by the user.
     * @throws DukeException If task index is out of range.
     * @return A String that contains texts to be printed out by "done" command.
     */
    public String doneCommand(TaskList tasks, Ui ui) throws DukeException {
        // format is "done x", for any positive integer x
        String[] splitStr = input.split("\\s+");
        int taskIndexFromInputIndex = parseCommandArgument(splitStr[1]) - 1;
        if (taskIndexFromInputIndex >= tasks.numberOfTasks() || taskIndexFromInputIndex < 0) {
            throw new DukeException("Invalid task number!");
        }
        tasks.taskNumber(taskIndexFromInputIndex).markTaskDone();
        return ui.taskDone(tasks.taskNumber(taskIndexFromInputIndex));
    }

    private int parseCommandArgument(String s) {
        return Integer.parseInt(s);
    }

    /**
     * Deletes a task from the list.
     *
     * @param tasks A TaskList object containing all user's tasks.
     * @param ui The current user interface by the user.
     * @throws DukeException If task index is out of range.
     * @return A String that contains texts to be printed out by "delete" command.
     */
    public String deleteCommand(TaskList tasks, Ui ui) throws DukeException {
        // format is "delete x", for any positive integer x
        String[] splitStr = input.split("\\s+");
        int taskIndexFromInputIndex = parseCommandArgument(splitStr[1]) - 1;
        if (taskIndexFromInputIndex >= tasks.numberOfTasks() || taskIndexFromInputIndex < 0) {
            throw new DukeException("Invalid task number!");
        }
        String str = ui.deleteTask(tasks.taskNumber(taskIndexFromInputIndex));
        tasks.removeTask(parseCommandArgument(splitStr[1]) - 1);
        return str + ui.printTaskLength(tasks);
    }

    /**
     * Returns a Todo object with the corresponding todo item.
     *
     * @return A Todo object.
     * @throws DukeException If the description of todo is empty.
     */
    public Todo todoCommand() throws DukeException {
        String description = input.substring("todo".length()).trim();
        if (description.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty :-(");
        }
        return new Todo(description);
    }

    /**
     * Returns a Deadline object with the corresponding deadline item.
     *
     * @throws DukeException If date or time is wrongly formatted.
     * @return A Deadline object.
     */
    public Deadline deadlineCommand() throws DukeException {
        try {
            int slashPosition = input.indexOf('/');
            String description = input.substring("deadline".length() + 1, slashPosition);
            String by = input.substring(slashPosition + 4);
            String[] splitStr = by.split("\\s+");
            return new Deadline(description.trim(), timeFormatter(splitStr[1]), dateFormatter(splitStr[0]));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Your date and time is wrongly formatted. It should be in the form of"
                    + " dd-mm-yyyy hhmm");
        }

    }

    /**
     * Returns a Event object with the corresponding event item.
     *
     * @throws DukeException If date or time is in the wrong format.
     * @return A Event object.
     */
    public Event eventCommand() throws DukeException {
        try {
            int slashPosition = input.indexOf('/');
            String description = input.substring("event".length() + 1, slashPosition);
            String at = input.substring(slashPosition + 4);
            String[] splitStr = at.split("\\s+");
            return new Event(description.trim(), timeFormatter(splitStr[1]), dateFormatter(splitStr[0]));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Your date and time is wrongly formatted. It should be in the form of"
                    + "dd-mm-yyyy hhmm.");
        }
    }

    /**
     * Returns a {@code String} with a list of events happening per hour, like a schedule, on the specified date.
     *
     * @param tasks A TaskList object containing all user's tasks.
     * @param ui The current user interface by the user.
     * @return A {@code String} with a list of events happening on the specified date.
     */

    public String scheduleCommand(TaskList tasks, Ui ui) {
        try {
            ArrayList<Task> list = new ArrayList<>();
            String date = getDate();
            LocalDate formattedDate = dateFormatter(date);

            extractSameDayTasks(tasks, list, formattedDate);

            if (list.isEmpty()) {
                return ui.noTaskScheduledMessage(formattedDate);
            }

            Collections.sort(list);
            TaskList sameDayTasks = new TaskList(list);

            StringBuilder str = new StringBuilder(ui.viewScheduleMessage(formattedDate) + '\n');
            groupTasksByHour(ui, sameDayTasks, str);

            return str.toString();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Your date is wrongly formatted. It should be in the form of dd-mm-yyyy.");
        } catch (DukeException e) {
            ui.printError(e);
        }
        return "";
    }

    private String getDate() {
        String[] splitStr = input.split("\\s+");
        return splitStr[1];
    }

    private void groupTasksByHour(Ui ui, TaskList matchingTasks, StringBuilder str) {
        int ctr = -1;
        for (int i = 0; i < matchingTasks.numberOfTasks(); i++) {
            Task t = matchingTasks.taskNumber(i);
            assert t != null : "Task should not be null.";
            int hourOfTask = t.getTime().getHour();
            if (ctr != hourOfTask) {
                ctr = hourOfTask;
                str.append('\n');
                str.append(ui.printScheduleByHourMessage(t.getTime()));
            }
            str.append(t);
            str.append('\n');
        }
    }

    private void extractSameDayTasks(TaskList tasks, ArrayList<Task> list, LocalDate formattedDate) {
        for (int i = 0; i < tasks.numberOfTasks(); i++) {
            Task t = tasks.taskNumber(i);
            assert t != null : "Task to be added should not be null.";
            if (t instanceof Deadline | t instanceof Event) {
                boolean isSameDate = t.getDate().compareTo(formattedDate) == 0;
                if (isSameDate) {
                    list.add(t);
                }
            }
        }
    }

    /**
     * Signals the end of the program and returns a standard bye message.
     *
     * @param ui The current user interface of the user.
     * @return A {@code String} with a standard bye message.
     */
    private String byeCommand(Ui ui) {
        return ui.byeMessage();
    }
}
