package duke.parser;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

import java.io.IOException;

public class Parser {

    private final String input;
    enum Commands {
        BYE, LIST, DONE, DELETE, TODO, DEADLINE, EVENT
    }

    public Parser(String input) {
        this.input = input;
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
            doneCommand(input, tasks, ui);
        } else if (input.startsWith(Commands.DELETE.toString().toLowerCase())) {
            deleteCommand(input, tasks, ui);
        } else {
            if (input.startsWith(Commands.TODO.toString().toLowerCase())) {
                t = todoCommand(input);
            } else if (input.startsWith(Commands.DEADLINE.toString().toLowerCase())) {
                t = deadlineCommand(input, storage);
            } else if (input.startsWith(Commands.EVENT.toString().toLowerCase())) {
                t = eventCommand(input, storage);
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            tasks.addTask(tasks, t, ui);
        }
        try {
            storage.saveToDisk(tasks);
        } catch (IOException e) {
            ui.printError(e);
        }
        return false;
    }

    /** Prints out all user's tasks in numerical order. */
    public void taskCommand(TaskList tasks, Ui ui) {
        ui.taskList();
        for (int i = 0; i < tasks.numberOfTasks(); i++) {
            System.out.println((i + 1) + "." + tasks.taskNumber(i));
        }
    }

    /**
     * Marks a task as done.
     *
     * @param input input given by the user starting with "done".
     */
    public void doneCommand(String input, TaskList tasks, Ui ui) {
        String[] splitStr = input.split("\\s+");
        tasks.taskNumber(Integer.parseInt(splitStr[1]) - 1).markTaskDone();
        ui.taskDone(tasks.taskNumber(Integer.parseInt(splitStr[1]) - 1));
    }

    /**
     * Deletes a task from the list.
     *
     * @param input input given by the user starting with "delete" and the corresponding task number to delete.
     */
    public void deleteCommand(String input, TaskList tasks, Ui ui) {
        String[] splitStr = input.split("\\s+");
        ui.deleteTask(tasks.taskNumber(Integer.parseInt(splitStr[1]) - 1));
        tasks.removeTask(Integer.parseInt(splitStr[1]) - 1);
        ui.printTaskLength(tasks);
    }

    /**
     * Returns a Todo object with the corresponding todo item.
     *
     * @param input input given by the user starting with "todo" and the corresponding todo item.
     * @return A Todo object.
     * @throws DukeException If the description of todo is empty.
     */
    public Todo todoCommand(String input) throws DukeException {
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty :-(");
        }
        return new Todo(description);
    }

    /**
     * Returns a Deadline object with the corresponding deadline item.
     *
     * @param input input given by the user starting with "deadline" and the corresponding deadline item.
     * @return A Deadline object.
     */
    public Deadline deadlineCommand(String input, Storage storage) {
        try {
            int slashPosition = input.indexOf('/');
            String description = input.substring("deadline".length() + 1, slashPosition);
            String by = input.substring(slashPosition + 4);
            String[] splitStr = by.split("\\s+");
            return new Deadline(description, storage.timeFormatter(splitStr[1]), storage.dateFormatter(splitStr[0]));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Your date and time is wrongly formatted. It should be in the form of" +
                    " dd-mm-yyyy hhmm");
        }

    }

    /**
     * Returns a Event object with the corresponding event item.
     *
     * @param input input given by the user starting with "event" and the corresponding event item.
     * @return A Event object.
     */
    public Event eventCommand(String input, Storage storage) throws DukeException {
        try {
            int slashPosition = input.indexOf('/');
            String description = input.substring("event".length() + 1, slashPosition);
            String at = input.substring(slashPosition + 4);
            String[] splitStr = at.split("\\s+");
            return new Event(description, storage.timeFormatter(splitStr[1]), storage.dateFormatter(splitStr[0]));
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
