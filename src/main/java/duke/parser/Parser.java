package duke.parser;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Parser {

    private final String input;
    enum Commands {
        BYE, LIST, DONE, DELETE, TODO, DEADLINE, EVENT, FIND
    }

    public Parser(String input) {
        this.input = input;
    }

    public static LocalDate dateFormatter(String str) throws DukeException {
        try {
            String[] splitStr = str.split("/");
            String day = String.format("%1$" + 2 + "s", splitStr[0]).replace(' ', '0');
            String month = String.format("%1$" + 2 + "s", splitStr[1]).replace(' ', '0');
            return LocalDate.parse(splitStr[2] + "-" +  month + "-" + day);
        } catch (Exception e) {
            throw new DukeException("Your Date is wrongly formatted! It should be in the form of dd-mm-yyyy.");
        }
    }

    public static LocalTime timeFormatter(String str) throws DukeException {
        try {
            return LocalTime.parse(str.substring(0, 2) + ":" + str.substring(2));
        } catch (Exception e) {
            throw new DukeException("Your Time is wrongly formatted! It should be in the the form of hhmm.");
        }
    }

    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        if (input.equals(Commands.BYE.toString().toLowerCase())) {
            byeCommand(ui);
            return true;
        }
        Task t;
        if (input.equals(Commands.LIST.toString().toLowerCase())) {
            taskCommand(tasks, ui);
        } else if (input.startsWith(Commands.DONE.toString().toLowerCase())) {
            doneCommand(tasks, ui);
        } else if (input.startsWith(Commands.DELETE.toString().toLowerCase())) {
            deleteCommand(tasks, ui);
        } else if (input.startsWith(Commands.FIND.toString().toLowerCase())) {
            findCommand(tasks, ui);
        } else {
            if (input.startsWith(Commands.TODO.toString().toLowerCase())) {
                t = todoCommand();
            } else if (input.startsWith(Commands.DEADLINE.toString().toLowerCase())) {
                t = deadlineCommand();
            } else if (input.startsWith(Commands.EVENT.toString().toLowerCase())) {
                t = eventCommand();
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            tasks.addTask(t, ui);
        }
        try {
            storage.saveToDisk(tasks);
        } catch (IOException e) {
            ui.printError(e);
        }
        return false;
    }

    public void findCommand(TaskList tasks, Ui ui) {
        ArrayList<Task> list = new ArrayList<>();
        String description = input.substring("find".length()).trim();
        ui.matchTaskMessage();
        for (int i = 0; i < tasks.numberOfTasks(); i++) {
            if (tasks.taskNumber(i).getDescription().contains(description)) {
                list.add(tasks.taskNumber(i));
            }
        }
        TaskList matchingTasks = new TaskList(list);
        matchingTasks.printAllTasks();
    }

    /** Prints out all user's tasks in numerical order. */
    public void taskCommand(TaskList tasks, Ui ui) {
        ui.taskListMessage();
        tasks.printAllTasks();
    }

    /** Marks a task as done. */
    public void doneCommand(TaskList tasks, Ui ui) {
        String[] splitStr = input.split("\\s+");
        tasks.taskNumber(Integer.parseInt(splitStr[1]) - 1).markTaskDone();
        ui.taskDone(tasks.taskNumber(Integer.parseInt(splitStr[1]) - 1));
    }

    /** Deletes a task from the list. */
    public void deleteCommand(TaskList tasks, Ui ui) {
        String[] splitStr = input.split("\\s+");
        ui.deleteTask(tasks.taskNumber(Integer.parseInt(splitStr[1]) - 1));
        tasks.removeTask(Integer.parseInt(splitStr[1]) - 1);
        ui.printTaskLength(tasks);
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
            throw new DukeException("OOPS!!! The description of a todo cannot be empty :-(");
        }
        return new Todo(description);
    }

    /**
     * Returns a Deadline object with the corresponding deadline item.
     *
     * @return A Deadline object.
     */
    public Deadline deadlineCommand() {
        try {
            int slashPosition = input.indexOf('/');
            String description = input.substring("deadline".length() + 1, slashPosition);
            String by = input.substring(slashPosition + 4);
            String[] splitStr = by.split("\\s+");
            return new Deadline(description.trim(), timeFormatter(splitStr[1]), dateFormatter(splitStr[0]));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Your date and time is wrongly formatted. It should be in the form of" +
                    " dd-mm-yyyy hhmm");
        }

    }

    /**
     * Returns a Event object with the corresponding event item.
     *
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
            throw new DukeException("Your date and time is wrongly formatted. It should be in the form of" +
                    "dd-mm-yyyy hhmm.");
        }
    }

    /** Signals the end of the program */
    private void byeCommand(Ui ui) {
        ui.byeMessage();
    }
}
