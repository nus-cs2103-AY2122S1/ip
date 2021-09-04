package duke.parser;

import java.time.LocalDate;

import duke.exception.DeadlineException;
import duke.exception.DukeException;
import duke.exception.EventException;
import duke.exception.ToDoException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.Ui;


public class Parser {
    private final TaskList taskList;
    private final Ui ui;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
        this.ui = new Ui();
    }

    /**
     * Returns true if the program should still keep going on (e.g., when input is not equal to "bye")
     *
     * @param input full raw user input line.
     * @return true if input is not equal to "bye".
     */
    public String parseInput(String input) {
        String response = "";
        try {
            if (input.equals("bye")) {
                response += "Goodbye human. See you soon!\n";
            } else if (input.equals("list")) {
                response += taskList.printItems();
            } else if (input.contains("done")) {
                response += markAsDone(input);
            } else if (input.contains("delete")) {
                response += deleteItem(input);
            } else if (input.contains("find")) {
                response += findItem(input);
            } else {
                response += addItem(input);
            }
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }

        return response;
    }

    /**
     * Parses add command from the user.
     *
     * @param input full raw user input line.
     * @throws DukeException if the user input is of an invalid format.
     */
    private String addItem(String input) throws DukeException {
        Task newItem = null;
        if (input.contains("todo")) {
            String[] parsedInput = input.split(" ", 2); // Splits input into array of [todo, ...]
            if (isIncomplete(parsedInput)) {
                throw new ToDoException();
            }

            newItem = new ToDo(parsedInput[1]);
        } else if (input.contains("deadline")) {
            String[] parsedInput = input.split(" ", 2); // Splits input into array of [deadline, ...]
            if (isIncomplete(parsedInput)) {
                throw new DeadlineException();
            }

            String[] description = parsedInput[1].split("/");
            if (isIncomplete(description)) {
                throw new DeadlineException("The deadline cannot be empty. Please provide a deadline for this task.");
            }

            String name = description[0];
            String deadline = description[1].split(" ")[1];
            newItem = new Deadline(name, LocalDate.parse(deadline));
        } else if (input.contains("event")) {
            String[] parsedInput = input.split(" ", 2); // Splits input into array of [event, ...]
            if (isIncomplete(parsedInput)) {
                throw new EventException();
            }

            String[] description = parsedInput[1].split("/");
            if (isIncomplete(description)) {
                throw new EventException("The time of event cannot be empty. Please provide a time for this task.");
            }
            String name = description[0];
            String time = description[1].split(" ", 2)[1];
            newItem = new Event(name, time);
        } else {
            throw new DukeException("I'm sorry, but I do not quite understand what that means :(");
        }

        return taskList.add(newItem);
    }

    /**
     * Parses done command from the user
     *
     * @param input full raw user input line.
     * @throws DukeException if the user input is of an invalid format.
     */
    private String markAsDone(String input) throws DukeException {
        int index = getIndexFromInput(input);
        return taskList.markTaskAsDone(index);
    }

    /**
     * Parses delete command from the user
     *
     * @param input full raw user input line.
     * @throws DukeException if the user input is of an invalid format.
     */
    private String deleteItem(String input) throws DukeException {
        int index = getIndexFromInput(input);
        return taskList.delete(index);
    }

    private String findItem(String input) throws DukeException {
        String[] parsedInput = input.split(" ");
        if (isIncomplete(parsedInput)) {
            throw new DukeException("I'm sorry, but I do not quite understand what that means :(");
        }

        String keyword = parsedInput[1];
        return taskList.find(keyword);
    }

    /**
     * Retrieves the number from user input.
     *
     * @param input full raw user input line.
     * @throws DukeException if the user input is of an invalid format.
     */
    private int getIndexFromInput(String input) throws DukeException {
        String[] parsedInput = input.split(" ");
        if (isIncomplete(parsedInput)) {
            throw new DukeException("I do not know which task you are referring to. Please provide a number.");
        }
        return Integer.parseInt(parsedInput[1]);
    }

    private boolean isIncomplete(String[] arr) {
        return arr.length <= 1;
    }
}
